package edu.kirkwood.wackerproject.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static edu.kirkwood.shared.MySQL_Connect.getConnection;

public class FatherDogDAO {
    public static List<FatherDog> getAllFatherDogs() {
        List<FatherDog> list = new ArrayList<>();
        try (Connection connection = getConnection()) {
            CallableStatement cstmt = connection.prepareCall("{CALL sp_get_all_father_dogs()}");
            ResultSet rs = cstmt.executeQuery();

            while (rs.next()) {
                String fatherDogID = rs.getString("father_dog_id");
                String breedID = rs.getString("breed_id");
                String personality = rs.getString("personality");
                String energyLevel = rs.getString("energy_level");
                String barkingLevel = rs.getString("barking_level");
                String trainability = rs.getString("trainability");
                String image = rs.getString("image");
                String description = rs.getString("description");

                FatherDog litter = new FatherDog(fatherDogID, breedID, personality, energyLevel, barkingLevel, trainability, image, description);
                list.add(litter);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
