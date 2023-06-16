package com.yearup.dealership.db;

import com.yearup.dealership.models.Vehicle;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleDao {
    private DataSource dataSource;

    public VehicleDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addVehicle(Vehicle vehicle) {
        String query = "Insert into vehicles (vin, make, model, year, Sold, color, vehicleType, odometer, price) " +
                "values(?, ?, ?, ?,? , ?, ?, ?, ?);";

        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);)
        {
            preparedStatement.setString(1, vehicle.getVin());
            preparedStatement.setString(2, vehicle.getMake());
            preparedStatement.setString(3, vehicle.getModel());
            preparedStatement.setInt(4, vehicle.getYear());
            preparedStatement.setBoolean(5, vehicle.isSold());
            preparedStatement.setString(6, vehicle.getColor());
            preparedStatement.setString(7, vehicle.getVehicleType());
            preparedStatement.setInt(8, vehicle.getOdometer());
            preparedStatement.setDouble(9, vehicle.getPrice());

            int rows = preparedStatement.executeUpdate();
            System.out.println(rows + " rows affected!");


        }catch (Exception ex) {
            ex.printStackTrace();
        }

    }
    public void removeVehicle(String VIN) {

        String query = "DELETE FROM vehicles WHERE Vin = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            // Set the parameter value for the delete query
            preparedStatement.setString(1, VIN);

            // Execute the delete query
            int rows = preparedStatement.executeUpdate();
            System.out.println(rows + " rows affected!");

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public List<Vehicle> searchByPriceRange(double minPrice, double maxPrice) {
        List<Vehicle> vehicles = new ArrayList<>();
        Vehicle vehicle = null;

        String query = "Select * from vehicles where price between ? and ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            // Set the parameter value for the delete query
            preparedStatement.setDouble(1, minPrice);
            preparedStatement.setDouble(2, maxPrice);

            try (ResultSet results = preparedStatement.executeQuery()) {
                while (results.next()) {
                    vehicle = createVehicleFromResultSet(results);
                    vehicles.add(vehicle);

                }
                return vehicles;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return new ArrayList<>();
    }

    public List<Vehicle> searchByMakeModel(String make, String model) {

        List<Vehicle> vehicles = new ArrayList<>();
        Vehicle vehicle = null;

        String query = "Select * from vehicles where make = ? and  model = ? ";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            // Set the parameter value for the delete query
            preparedStatement.setString(1, make);
            preparedStatement.setString(2, model);

            try (ResultSet results = preparedStatement.executeQuery()) {
                while (results.next()) {
                    vehicle = createVehicleFromResultSet(results);
                    vehicles.add(vehicle);

                }
                return vehicles;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return new ArrayList<>();
    }


    public List<Vehicle> searchByYearRange(int minYear, int maxYear) {

        List<Vehicle> vehicles = new ArrayList<>();
        Vehicle vehicle = null;

        String query = "Select * from vehicles where year between ? and ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            // Set the parameter value for the delete query
            preparedStatement.setDouble(1, minYear);
            preparedStatement.setDouble(2, maxYear);

            try (ResultSet results = preparedStatement.executeQuery()) {
                while (results.next()) {
                    vehicle = createVehicleFromResultSet(results);
                    vehicles.add(vehicle);

                }
                return vehicles;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return new ArrayList<>();
    }

    public List<Vehicle> searchByColor(String color) {


        List<Vehicle> vehicles = new ArrayList<>();
        Vehicle vehicle = null;

        String query = "Select * from vehicles where color = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            // Set the parameter value for the delete query
            preparedStatement.setString(1, color);

            try (ResultSet results = preparedStatement.executeQuery()) {
                while (results.next()) {
                    vehicle = createVehicleFromResultSet(results);
                    vehicles.add(vehicle);

                }
                return vehicles;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return new ArrayList<>();
    }

    public List<Vehicle> searchByMileageRange(int minMileage, int maxMileage) {

        List<Vehicle> vehicles = new ArrayList<>();
        Vehicle vehicle = null;

        String query = "Select * from vehicles where odometer between ? and ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            // Set the parameter value for the delete query
            preparedStatement.setDouble(1, minMileage);
            preparedStatement.setDouble(2, maxMileage);

            try (ResultSet results = preparedStatement.executeQuery()) {
                while (results.next()) {
                    vehicle = createVehicleFromResultSet(results);
                    vehicles.add(vehicle);

                }
                return vehicles;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return new ArrayList<>();
    }

    public List<Vehicle> searchByType(String type) {


        List<Vehicle> vehicles = new ArrayList<>();
        Vehicle vehicle = null;

        String query = "Select * from vehicles where vehicleType = ? ";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            // Set the parameter value for the delete query

            preparedStatement.setString(1, type);


            try (ResultSet results = preparedStatement.executeQuery()) {
                while (results.next()) {
                    vehicle = createVehicleFromResultSet(results);
                    vehicles.add(vehicle);

                }
                return vehicles;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }


        return new ArrayList<>();
    }

    private Vehicle createVehicleFromResultSet(ResultSet resultSet) throws SQLException {
        Vehicle vehicle = new Vehicle();
        vehicle.setVin(resultSet.getString("VIN"));
        vehicle.setMake(resultSet.getString("make"));
        vehicle.setModel(resultSet.getString("model"));
        vehicle.setYear(resultSet.getInt("year"));
        vehicle.setSold(resultSet.getBoolean("SOLD"));
        vehicle.setColor(resultSet.getString("color"));
        vehicle.setVehicleType(resultSet.getString("vehicleType"));
        vehicle.setOdometer(resultSet.getInt("odometer"));
        vehicle.setPrice(resultSet.getDouble("price"));
        return vehicle;
    }
}
