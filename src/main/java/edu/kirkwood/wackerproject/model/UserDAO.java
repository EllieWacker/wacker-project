package edu.kirkwood.wackerproject.model;


import edu.kirkwood.shared.email.EmailThread;
import jakarta.servlet.http.HttpServletRequest;
import org.mindrot.jbcrypt.BCrypt;
import java.sql.*;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import static edu.kirkwood.shared.MySQL_Connect.getConnection;

public class UserDAO {
    public static void main(String[] args) {
        User user = new User();
        user.setEmail("test1@test.com");
        user.setPassword("P@ssw0rd".toCharArray());
        user.setStatus("Active");
        user.setPrivileges("Admin");
        add(user);
    }

    public static void deletePasswordReset(String email) {
        try (Connection connection = getConnection();
             CallableStatement statement = connection.prepareCall("{CALL sp_delete_password_reset(?)}")) {
            statement.setString(1, email);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<User> getAll() {
        List<User> list = new ArrayList<>();
        try (Connection connection = getConnection()) {
            CallableStatement cstmt = connection.prepareCall("{call sp_get_all_users()}");
            ResultSet rs = cstmt.executeQuery();
            while (rs.next()) {
                int userId = rs.getInt("user_id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                char[] password = rs.getString("password").toCharArray();
                String language = rs.getString("language");
                String status = rs.getString("status");
                String privileges = rs.getString("privileges");
                Instant createdAt = rs.getTimestamp("created_at").toInstant();
                String timezone = rs.getString("timezone");
                String interests = rs.getString("interests");

                User user = new User(userId, firstName, lastName, email, phone, password, language, status, privileges, createdAt, timezone, interests);
                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return list;
    }

    public static User get(String email) {
        User user = null;
        try (Connection connection = getConnection()) {
            if (connection != null) {
                try (CallableStatement statement = connection.prepareCall("{CALL sp_get_user(?)}")) {
                    statement.setString(1, email);
                    try (ResultSet resultSet = statement.executeQuery()) {
                        if (resultSet.next()) {
                            int userId = resultSet.getInt("user_id");
                            String firstName = resultSet.getString("first_name");
                            String lastName = resultSet.getString("last_name");
                            String phone = resultSet.getString("phone");
                            char[] password = resultSet.getString("password").toCharArray();
                            String language = resultSet.getString("language");
                            String status = resultSet.getString("status");
                            String privileges = resultSet.getString("privileges");
                            Instant created_at = resultSet.getTimestamp("created_at").toInstant();
                            String timezone = resultSet.getString("timezone");
                            String interests = resultSet.getString("interests");
                            user = new User(userId, firstName, lastName, email, phone, password, language, status, privileges, created_at, timezone, interests);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    public static boolean add(User user) {
        try (Connection connection = getConnection();
             CallableStatement statement = connection.prepareCall("{CALL sp_add_user(?,?,?,?)}");
        ) {
            statement.setString(1, user.getEmail());
            statement.setString(2, BCrypt.hashpw(String.valueOf(user.getPassword()), BCrypt.gensalt(12)));
            statement.setString(3, user.getStatus());
            statement.setString(4, user.getPrivileges());
            int rowsAdded = statement.executeUpdate();
            return rowsAdded == 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean updateStatus(String email, String newStatus) {
        try (Connection connection = getConnection();
             CallableStatement statement = connection.prepareCall("{CALL sp_update_user_status(?,?)}")) {

            statement.setString(1, email);
            statement.setString(2, newStatus);
            int rowsAdded = statement.executeUpdate();
            return rowsAdded == 1;
        } catch (SQLException e) {
            throw new RuntimeException("Error updating user status", e);
        }
    }

    public static String passwordReset(String email, HttpServletRequest req) {
        User user = get(email);
        if (user == null) {
            return "No user found that matches that email";
        } else {
            try (Connection connection = getConnection()) {
                String uuid = String.valueOf(UUID.randomUUID());
                CallableStatement statement = connection.prepareCall("{call sp_add_password_reset(?,?)}");
                statement.setString(1, email);
                statement.setString(2, uuid);
                int rowsAffected = statement.executeUpdate();
                if (rowsAffected > 0) {
                    String subject = "Reset Password";
                    // chatgpt helped me design the <div> style
                    String message = "<div style='width: 400px; margin: 0 auto; padding: 20px; text-align: center; " +
                            "border: 1px solid #ddd; border-radius: 10px; box-shadow: 2px 2px 10px rgba(0,0,0,0.1); " +
                            "background-color: #f9f9f9; font-family: Arial, sans-serif;'>";

                    message += "<img src='https://wacker.azurewebsites.net/images/dogLogo.png' alt='Logo' " +
                                "style='width: 10em; margin-bottom: 1em; display: block;margin-left: auto; margin-right: auto;'>";
                    message += "<img src='https://wacker.azurewebsites.net/images/cLit2Five.jpg' alt='Puppy Image' style='width: 10em; margin-left: auto; margin-right: auto;'>";
                    message += "<h2 style='color: black;'>FORGOT YOUR PASSWORD?</h2>";
                    message += "<p style='color: black;'>Don't worry! Just click this link to reset your password.</p>";

                    String appURL = "";
                    if (req.isSecure()) {
                        appURL = req.getServletContext().getInitParameter("appURLCloud");
                    } else {
                        appURL = req.getServletContext().getInitParameter("appURLLocal");
                    }
                    String fullURL = String.format("%s/new-password?token=%s", appURL, uuid);
                    message += String.format("<p><a href='%s' target='_blank'>%s</a></p>", fullURL, fullURL);
                    message += "<p style='color: black;'>This link expires in 30 minutes.</p>";
                    message += "<p style='color: black;'>If you did not request to reset your password, you can ignore this message and your password will not be changed.</p>";
                    message += "</div>";
                    EmailThread emailThread = new EmailThread(email, subject, message);
                    emailThread.start();
                    try {
                        emailThread.join();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    String errorMessage = emailThread.getErrorMessage();
                    if (errorMessage == null || errorMessage.isEmpty()) {
                        return "If there's an account associated with the email entered, we will send a password reset link.";
                    } else {
                        return "Sorry, we couldn't process your password reset. Try again.";
                    }
                } else {
                    return "Sorry, we couldn't process your password reset. Try again.";
                }
            } catch (SQLException e) {
                return "Sorry, we couldn't process your password reset. Try again.";
            }
        }
    }

    public static String getPasswordReset(String token) {
        String email = "";
        try (Connection connection = getConnection();
             CallableStatement statement = connection.prepareCall("{CALL sp_get_password_reset(?)}")) {
            statement.setString(1, token);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Instant now = Instant.now();
                Instant created_at = resultSet.getTimestamp("created_at").toInstant();
                Duration duration = Duration.between(created_at, now);
                long minutesElapsed = duration.toMinutes();
                if (minutesElapsed < 30) {
                    email = resultSet.getString("email");
                }
                deletePasswordReset(email);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return email;
    }

    public static boolean updatePassword(String email, String password) {
        try (Connection connection = getConnection()) {
            if (connection != null) {
                try (CallableStatement statement = connection.prepareCall("{CALL sp_update_user_password(?, ?)}")) {
                    statement.setString(1, email);
                    String encryptedPassword = BCrypt.hashpw(password, BCrypt.gensalt(12));
                    statement.setString(2, encryptedPassword);
                    int rowsAffected = statement.executeUpdate();
                    return rowsAffected == 1;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }




    public static boolean delete(User user) {
        try (Connection connection = getConnection()) {
            CallableStatement statement = connection.prepareCall("{CALL sp_delete_user(?)}");
            statement.setInt(1, user.getUserId());
            int rowsAffected = statement.executeUpdate();
            return rowsAffected == 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static boolean update(String originalEmail, User newUser) {
        User existingUser = get(originalEmail);
        try (Connection connection = getConnection();
             CallableStatement statement = connection.prepareCall("{CALL sp_update_user(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}")
        ) {
            statement.setInt(1, existingUser.getUserId());
            statement.setString(2, existingUser.getFirstName());
            statement.setString(3, existingUser.getLastName());
            statement.setString(4, existingUser.getEmail());
            statement.setString(5, existingUser.getPhone());
            statement.setString(6, existingUser.getLanguage());
            statement.setString(7, existingUser.getStatus());
            statement.setString(8, existingUser.getPrivileges());
            statement.setString(9, existingUser.getTimezone());
            statement.setString(10, existingUser.getInterests());
            statement.setString(11, newUser.getFirstName());
            statement.setString(12, newUser.getLastName());
            statement.setString(13, newUser.getEmail());
            statement.setString(14, newUser.getPhone());
            statement.setString(15, newUser.getLanguage());
            statement.setString(16, newUser.getStatus());
            statement.setString(17, newUser.getPrivileges());
            statement.setString(18, newUser.getTimezone());
            statement.setString(19, newUser.getInterests());

            int rowsAffected = statement.executeUpdate();
            return rowsAffected == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
