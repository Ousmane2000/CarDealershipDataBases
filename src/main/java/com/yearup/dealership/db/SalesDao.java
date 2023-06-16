package com.yearup.dealership.db;

import com.yearup.dealership.models.SalesContract;
import com.yearup.dealership.models.Vehicle;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SalesDao {
    private DataSource dataSource;

    public SalesDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addSalesContract(SalesContract salesContract) {

        String query = "Insert into sales_contracts (contract_id , VIN, sale_date, price) " +
                "values(?, ?, ?, ?);";

        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);)
        {
            preparedStatement.setInt(1, salesContract.getContractId() );
            preparedStatement.setString(2, salesContract.getVin());
            preparedStatement.setDate(3, java.sql.Date.valueOf(salesContract.getSaleDate()));
            preparedStatement.setDouble(4, salesContract.getPrice());


            int rows = preparedStatement.executeUpdate();
            System.out.println(rows + " rows affected!");


        }catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    }

