package com.yearup.dealership.db;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InventoryDao {
    private DataSource dataSource;

    public InventoryDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addVehicleToInventory(String vin, int dealershipId) {
        String query = "Insert into inventory (dealership_id , VIN) " +
                "values(?, ?);";

        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);)
        {
            preparedStatement.setInt(1, dealershipId);
            preparedStatement.setString(2, vin);

            int rows = preparedStatement.executeUpdate();
            System.out.println(rows + " rows affected!");


        }catch (Exception ex) {
            ex.printStackTrace();
        }

    }




    public void removeVehicleFromInventory(String vin) {

        String query = "DELETE FROM inventory WHERE Vin = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            // Set the parameter value for the delete query
            preparedStatement.setString(1, vin);

            // Execute the delete query
            int rows = preparedStatement.executeUpdate();
            System.out.println(rows + " rows affected!");

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
