package edu.kirkwood.wackerproject.model;


import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static edu.kirkwood.shared.MySQL_Connect.getConnection;

public class PuppyDAO {
    public static void main(String[] args) {
     //   System.out.println(getPuppy("ALit1One"));
        //System.out.println(getAllPuppies(5,0, "Mini Aussiedoodle"));
        System.out.println(getPuppyCount("Mini Aussiedoodle"));
        getAllBreeds().forEach(System.out::println);

    }

    public static int getPuppyCount(String breedID) {
        try(Connection connection = getConnection();
            CallableStatement statement = connection.prepareCall("{CALL sp_get_total_puppies(?)}");
        ) {
            statement.setString(1, breedID);
            try(ResultSet resultSet = statement.executeQuery();) {
                if (resultSet.next()) {
                    return resultSet.getInt("total_puppies");
                }
            }
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public static Puppy getPuppy(String puppyId) {
        Puppy puppy = null;
        try (Connection connection = getConnection()) {
            if (connection != null) {
                try (CallableStatement statement = connection.prepareCall("{CALL sp_get_puppy(?)}")) {
                    statement.setString(1, puppyId);
                    try (ResultSet resultSet = statement.executeQuery()) {
                        if (resultSet.next()) {
                            String puppyID = resultSet.getString("puppy_id");
                            String breedID = resultSet.getString("breed_id");
                            String litterID = resultSet.getString("litter_id");
                            String medicalRecordID = resultSet.getString("medical_record_id");
                            String image = resultSet.getString("image");
                            String gender = resultSet.getString("gender");
                            boolean adopted = resultSet.getBoolean("adopted");
                            boolean microchip = resultSet.getBoolean("microchip");
                            double price = resultSet.getDouble("price");
                            String breedDescription = resultSet.getString("breed_description");

                            puppy = new Puppy(puppyID, breedID, litterID, medicalRecordID, image, gender, adopted, microchip, price, breedDescription);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return puppy;
    }

    public static boolean addPuppy(Puppy puppy) {
        try (Connection connection = getConnection()) {
            CallableStatement statement = connection.prepareCall("{CALL sp_add_puppy_admin(?, ?, ?, ?, ?, ?, ?, ?,?,?)}");
            statement.setString(1, puppy.getPuppyID());
            statement.setString(2, puppy.getBreedID());
            statement.setString(3, puppy.getLitterID());
            statement.setString(4, puppy.getMedicalRecordID());
            statement.setString(5, puppy.getImage());
            statement.setString(6, puppy.getGender());
            statement.setBoolean(7, puppy.getAdopted());
            statement.setBoolean(8, puppy.getMicrochip());
            statement.setDouble(9, puppy.getPrice());
            statement.setString(10, puppy.getBreedDescription());

            int rowsAffected = statement.executeUpdate();
            return rowsAffected == 1;
        } catch (SQLException e) {
            System.err.println("Error adding puppy: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public static List<Puppy> getAllPuppies(int limit, int offset, String breedFilter, Boolean adoptedFilter) {
        List<Puppy> list = new ArrayList<>();
        try (Connection connection = getConnection()) {
            CallableStatement cstmt = connection.prepareCall("{call sp_get_all_puppies(?,?,?,?)}");
            cstmt.setInt(1, limit);
            cstmt.setInt(2, offset);
            cstmt.setString(3, breedFilter);

            if (adoptedFilter != null) {
                cstmt.setBoolean(4, adoptedFilter); // Set true or false
            } else {
                cstmt.setNull(4, java.sql.Types.BOOLEAN); // Set NULL if adoptedFilter is null
            }

            ResultSet rs = cstmt.executeQuery();

            if (!rs.isBeforeFirst()) {
                System.out.println("No puppies found.");
            }

            while (rs.next()) {
                String puppyID = rs.getString("puppy_id");
                String breedID = rs.getString("breed_id");
                String litterID = rs.getString("litter_id");
                String medicalRecordID = rs.getString("medical_record_id");
                String image = rs.getString("image");
                String gender = rs.getString("gender");
                boolean adopted = rs.getBoolean("adopted");
                boolean microchip = rs.getBoolean("microchip");
                double price = rs.getDouble("price");
                String breedDescription = rs.getString("breed_description");

                Puppy puppy = new Puppy(puppyID, breedID, litterID, medicalRecordID, image, gender, adopted, microchip, price, breedDescription);
                list.add(puppy);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public static boolean updatePuppy(Puppy originalPuppy, Puppy newPuppy) {
        try (Connection connection = getConnection()) {
            CallableStatement statement = connection.prepareCall("{CALL sp_update_puppy_admin(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");

            // Original values
            statement.setString(1, originalPuppy.getPuppyID());
            statement.setString(2, originalPuppy.getBreedID());
            statement.setString(3, originalPuppy.getLitterID());
            statement.setString(4, originalPuppy.getMedicalRecordID());
            statement.setString(5, originalPuppy.getImage());
            statement.setString(6, originalPuppy.getGender());
            statement.setBoolean(7, originalPuppy.getAdopted());
            statement.setBoolean(8, originalPuppy.getMicrochip());
            statement.setDouble(9, originalPuppy.getPrice());
            statement.setString(10, originalPuppy.getBreedDescription());

            // New values
            statement.setString(11, newPuppy.getPuppyID());
            statement.setString(12, newPuppy.getBreedID());
            statement.setString(13, newPuppy.getLitterID());
            statement.setString(14, newPuppy.getMedicalRecordID());
            statement.setString(15, newPuppy.getImage());
            statement.setString(16, newPuppy.getGender());
            statement.setBoolean(17, newPuppy.getAdopted());
            statement.setBoolean(18, newPuppy.getMicrochip());
            statement.setDouble(19, newPuppy.getPrice());
            statement.setString(20, newPuppy.getBreedDescription());

            int rowsAffected = statement.executeUpdate();
            return rowsAffected == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deletePuppy(Puppy puppy) {
        try (Connection connection = getConnection()) {
            CallableStatement statement = connection.prepareCall("{CALL sp_delete_puppy(?)}");
            statement.setString(1, puppy.getPuppyID());
            int rowsAffected = statement.executeUpdate();
            return rowsAffected == 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Puppy> getPuppiesByBreed(String breed) {
        List<Puppy> puppyList = new ArrayList<>();
        try (Connection connection = getConnection()) {
            CallableStatement cstmt = connection.prepareCall("{call sp_get_puppies_by_breedID(?)}");
            cstmt.setString(1, breed);

            ResultSet rs = cstmt.executeQuery();

            if (!rs.isBeforeFirst()) { // if there are no results in the result set
                System.out.println("No puppies found.");
            } else {
                while (rs.next()) {
                    String puppyID = rs.getString("puppy_id");
                    String breedID = rs.getString("breed_id");
                    String litterID = rs.getString("litter_id");
                    String medicalRecordID = rs.getString("medical_record_id");
                    String image = rs.getString("image");
                    String gender = rs.getString("gender");
                    boolean adopted = rs.getBoolean("adopted");
                    boolean microchip = rs.getBoolean("microchip");
                    double price = rs.getDouble("price");
                    String breedDescription = rs.getString("breed_description");

                    Puppy puppy = new Puppy(puppyID, breedID, litterID, medicalRecordID, image, gender, adopted, microchip, price, breedDescription);
                    puppyList.add(puppy);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return puppyList;
    }

    public static List<Puppy> getPuppiesByLitter(String litter) {
        List<Puppy> puppyList = new ArrayList<>();
        try (Connection connection = getConnection()) {
            CallableStatement cstmt = connection.prepareCall("{call sp_get_puppies_by_litterID(?)}");
            cstmt.setString(1, litter);

            ResultSet rs = cstmt.executeQuery();

            if (!rs.isBeforeFirst()) { // if there are no results in the result set
                System.out.println("No puppies found.");
            } else {
                while (rs.next()) {
                    String puppyID = rs.getString("puppy_id");
                    String breedID = rs.getString("breed_id");
                    String litterID = rs.getString("litter_id");
                    String medicalRecordID = rs.getString("medical_record_id");
                    String image = rs.getString("image");
                    String gender = rs.getString("gender");
                    boolean adopted = rs.getBoolean("adopted");
                    boolean microchip = rs.getBoolean("microchip");
                    double price = rs.getDouble("price");
                    String breedDescription = rs.getString("breed_description");

                    Puppy puppy = new Puppy(puppyID, breedID, litterID, medicalRecordID, image, gender, adopted, microchip, price, breedDescription);
                    puppyList.add(puppy);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return puppyList;
    }

    public static List<Breed> getAllBreeds() {
        List<Breed> breeds = new ArrayList<>();

        String query = "{CALL sp_get_breeds()}";

        try (Connection connection = getConnection();
             CallableStatement statement = connection.prepareCall(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                String id = resultSet.getString("breed_id");
                int numPuppies = resultSet.getInt("num_puppies");

                breeds.add(new Breed(id, numPuppies));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return breeds;
    }

//    public static List<Litter> getAllLitters() {
//        List<Litter> litters = new ArrayList<>();
//
//        String query = "{CALL sp_get_litters()}";
//
//        try (Connection connection = getConnection();
//             CallableStatement statement = connection.prepareCall(query);
//             ResultSet resultSet = statement.executeQuery()) {
//
//            while (resultSet.next()) {
//                String id = resultSet.getString("litter_id");
//                int numPuppies = resultSet.getInt("num_puppies");
//
//                litters.add(new Litter(id, numPuppies));
//            }
//
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//
//        return litters;
//    }




}