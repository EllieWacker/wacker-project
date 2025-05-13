package edu.kirkwood.wackerproject.model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static edu.kirkwood.shared.MySQL_Connect.getConnection;

public class OrderDAO {
    public static void main(String[] args) {
        getOrders().forEach(System.out::println);
    }

    // This method get products for the Shop page.
    public static List<Order> getOrders() {
        List<Order> orders = new ArrayList<>();
        try(Connection connection = getConnection()) {
            CallableStatement statement = connection.prepareCall("{CALL sp_get_all_orders_admin()}");
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                int id = rs.getInt("order_id");
                Instant orderDate = rs.getTimestamp("order_date").toInstant();
                String shipFName = rs.getString("ship_fname");
                String shipLName = rs.getString("ship_lname");
                String shipEmail = rs.getString("ship_email");
                String shipAddress = rs.getString("ship_address");
                String shipCity = rs.getString("ship_city");
                String shipState = rs.getString("ship_state");
                String shipZip = rs.getString("ship_zip");
                ArrayList<OrderItem> items = new ArrayList<>();
                // Get order items
                orders.add(new Order(id, orderDate, shipFName, shipLName, shipEmail, shipAddress, shipCity, shipState, shipZip, items));
            }
        } catch(SQLException e) {
            throw new RuntimeException("Database error - " + e.getMessage());
        }
        return orders;
    }

    public static Order getOrder(int id) {
        Order order = null;
        try(Connection connection = getConnection()) {
            CallableStatement statement = connection.prepareCall("{CALL sp_get_order_details(?)}");
            statement.setInt(1, id);
            boolean hasResults = statement.execute();

            if (hasResults) {
                order = new Order();
                // First ResultSet: Order info
                try (ResultSet rs1 = statement.getResultSet()) {
                    while (rs1.next()) {
                        order.setOrderID(id);
                        order.setOrderDate(rs1.getTimestamp("order_date").toInstant());
                        order.setShipFirstName(rs1.getString("ship_fname"));
                        order.setShipLastName(rs1.getString("ship_lname"));
                        order.setShipEmail(rs1.getString("ship_email"));
                        order.setShipAddress(rs1.getString("ship_address"));
                        order.setShipCity(rs1.getString("ship_city"));
                        order.setShipState(rs1.getString("ship_state"));
                        order.setShipZipCode(rs1.getString("ship_zip"));
                    }
                }

                // Move to the second result set: Order items
                if (statement.getMoreResults()) {
                    ArrayList<OrderItem> items = new ArrayList<>();
                    try (ResultSet rs2 = statement.getResultSet()) {
                        OrderItem orderItem = new OrderItem();
                        while (rs2.next()) {
                            orderItem.setPuppyID(rs2.getString("puppy_id"));
                            orderItem.setPuppyName(rs2.getString("breed_id"));
                            orderItem.setPrice(rs2.getDouble("item_price"));
                            orderItem.setTotalPrice(rs2.getDouble("total_price"));
                            items.add(orderItem);
                        }
                    }
                    order.setItems(items);
                }
            }
        } catch(SQLException e) {
            throw new RuntimeException("Database error - " + e.getMessage());
        }
        return order;
    }

    public static ArrayList<OrderItem> getOrderItems(int orderId) {
        return null;
    }

    public static int addOrder(String[] shippingInfo, String email, String shoppingCartJson) {
        try(Connection connection = getConnection()) {
            CallableStatement statement = connection.prepareCall("{CALL sp_create_order(?,?,?,?,?,?,?,?)}");
            statement.setString(1, shippingInfo[0]); // first name
            statement.setString(2, shippingInfo[1]); // last name
            statement.setString(3, email);
            statement.setString(4, shippingInfo[2]); // address
            statement.setString(5, shippingInfo[3]); // city
            statement.setString(6, shippingInfo[4]); // state
            statement.setString(7, shippingInfo[5]); // zip
            statement.setString(8, shoppingCartJson);
            int newOrderId = -1;
            ResultSet rs = statement.executeQuery();
            if(rs.next()) {
                newOrderId = rs.getInt("new_order_id");
            }
            return newOrderId;
        } catch(SQLException e) {
            throw new RuntimeException("Database error - " + e.getMessage());
        }
    }
}
