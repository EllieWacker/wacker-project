package edu.kirkwood.wackerproject.model;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static edu.kirkwood.shared.MySQL_Connect.getConnection;

public class TestimonialDAO {
    public static void main(String[] args) {
        getAllTestimonials().forEach(System.out::println);
        System.out.println(getTestimonial("Meyer Family Testimonial"));
    }
    public static List<Testimonial> getAllTestimonials() {

        List<Testimonial> list = new ArrayList<>();
        try (Connection connection = getConnection()) {
            CallableStatement cstmt = connection.prepareCall("{call sp_get_all_testimonials()}");
            ResultSet rs = cstmt.executeQuery();
            while(rs.next()) {
                String testimonialId = rs.getString("testimonial_id");
                int adoptionId = rs.getInt("adoption_id");
                String image = rs.getString("image");
                String details = rs.getString("details");
                int rating = rs.getInt("rating");

                Testimonial testimonial = new Testimonial(testimonialId, adoptionId, image, details, rating);
                list.add(testimonial);


            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public static Testimonial getTestimonial(String testimonialId) {
        Testimonial testimonial = null;
        try (Connection connection = getConnection()) {
            if (connection != null) {
                try (CallableStatement statement = connection.prepareCall("{CALL sp_get_testimonial(?)}")) {
                    statement.setString(1, testimonialId);
                    try (ResultSet resultSet = statement.executeQuery()) {
                        if (resultSet.next()) {
                            int adoptionId = resultSet.getInt("adoption_id");
                            String image = resultSet.getString("image");
                            String details = resultSet.getString("details");
                            int rating = resultSet.getInt("rating");
                            testimonial = new Testimonial(testimonialId, adoptionId, image, details, rating);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return testimonial;
    }
}
