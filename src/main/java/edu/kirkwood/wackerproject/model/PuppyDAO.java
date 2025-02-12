package edu.kirkwood.wackerproject.model;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static edu.kirkwood.shared.MySQL_Connect.getConnection;

public class PuppyDAO {
    public static void main(String[] args) {
        System.out.println(getPuppy("ALit1One"));
    }

    public static Puppy getPuppy(String puppyId) {
        Puppy puppy = null;
        try (Connection connection = getConnection()) {
            if (connection != null) {
                try (CallableStatement statement = connection.prepareCall("{CALL sp_get_puppy(?)}")) {
                    statement.setString(1, puppyId);
                    try (ResultSet resultSet = statement.executeQuery()) {
                        if (resultSet.next()) {
                            String puppyID = resultSet.getString("puppyID");
                            String breedID = resultSet.getString("breedID");
                            String litterID = resultSet.getString("litterID");
                            String medicalRecordID = resultSet.getString("medicalRecordID");
                            String image = resultSet.getString("image");
                            String gender = resultSet.getString("gender");
                            String adopted = resultSet.getString("adopted");
                            boolean microchip = resultSet.getBoolean("microchip");
                            double price = resultSet.getDouble("price");

                            puppy = new Puppy(puppyID, breedID, litterID, medicalRecordID, image, gender, adopted, microchip, price);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return puppy;
    }
}
