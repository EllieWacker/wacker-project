package edu.kirkwood.wackerproject.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static edu.kirkwood.shared.MySQL_Connect.getConnection;

public class LitterDAO {
    public static boolean addLitter(Litter litter) {
        try (Connection connection = getConnection()) {
            CallableStatement statement = connection.prepareCall("{CALL sp_add_litter(?, ?, ?, ?, ?, ?, ?)}");
            statement.setString(1, litter.getLitterID());
            statement.setString(2, litter.getFatherDogID());
            statement.setString(3, litter.getMotherDogID());
            statement.setString(4, litter.getImage());
            statement.setDate(5, litter.getDateOfBirth());
            statement.setDate(6, litter.getGoHomeDate());
            statement.setInt(7, litter.getNumberPuppies());

            int rowsAffected = statement.executeUpdate();
            return rowsAffected == 1;
        } catch (SQLException e) {
            System.err.println("Error adding puppy: " + e.getMessage());
            e.printStackTrace(); // Log full stack trace
            return false;
        }
    }

    public static List<Litter> getAllLitters() {
        List<Litter> list = new ArrayList<>();
        try (Connection connection = getConnection()) {
            CallableStatement cstmt = connection.prepareCall("{CALL sp_get_all_litters()}");
            ResultSet rs = cstmt.executeQuery();

            while (rs.next()) {
                String litterID = rs.getString("litter_id");
                String fatherDogID = rs.getString("father_dog_id");
                String motherDogID = rs.getString("mother_dog_id");
                String image = rs.getString("image");
                Date dateOfBirth = rs.getDate("date_of_birth");
                Date goHomeDate = rs.getDate("go_home_date");
                int numberPuppies = rs.getInt("number_puppies");

                Litter litter = new Litter(litterID, fatherDogID, motherDogID, image, dateOfBirth, goHomeDate, numberPuppies);
                list.add(litter);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static boolean updateLitter(Litter originalLitter, Litter newLitter) {
        try (Connection connection = getConnection()) {
            CallableStatement statement = connection.prepareCall("{CALL sp_update_litter(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");

            statement.setString(1, originalLitter.getLitterID());
            statement.setString(2, originalLitter.getFatherDogID());
            statement.setString(3, originalLitter.getMotherDogID());
            statement.setString(4, originalLitter.getImage());
            statement.setDate(5, originalLitter.getDateOfBirth());
            statement.setDate(6, originalLitter.getGoHomeDate());
            statement.setInt(7, originalLitter.getNumberPuppies());

            statement.setString(8, newLitter.getFatherDogID());
            statement.setString(9, newLitter.getMotherDogID());
            statement.setString(10, newLitter.getImage());
            statement.setDate(11, newLitter.getDateOfBirth());
            statement.setDate(12, newLitter.getGoHomeDate());
            statement.setInt(13, newLitter.getNumberPuppies());

            int rowsAffected = statement.executeUpdate();
            return rowsAffected == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Litter getLitter(String litterID) {
        Litter litter = null;
        try (Connection connection = getConnection()) {
            if (connection != null) {
                try (CallableStatement statement = connection.prepareCall("{CALL sp_get_litter(?)}")) {
                    statement.setString(1, litterID);
                    try (ResultSet resultSet = statement.executeQuery()) {
                        if (resultSet.next()) {
                            String fatherDogID = resultSet.getString("father_dog_id");
                            String motherDogID = resultSet.getString("mother_dog_id");
                            String image = resultSet.getString("image");
                            Date dateOfBirth = resultSet.getDate("date_of_birth");
                            Date goHomeDate = resultSet.getDate("go_home_date");
                            int numberPuppies = resultSet.getInt("number_puppies");

                            litter = new Litter(litterID, fatherDogID, motherDogID, image, dateOfBirth, goHomeDate, numberPuppies);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving litter: " + e.getMessage(), e);
        }
        return litter;
    }

    public static boolean deleteLitter(Litter litter) {
        try (Connection connection = getConnection()) {
            CallableStatement statement = connection.prepareCall("{CALL sp_delete_litter(?)}");
            statement.setString(1, litter.getLitterID());
            int rowsAffected = statement.executeUpdate();
            return rowsAffected == 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
