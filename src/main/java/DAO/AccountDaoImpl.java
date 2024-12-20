package DAO;

import Model.Account;
import Util.ConnectionUtil;

import java.sql.*;

public class AccountDaoImpl implements AccountDao{

    public static final String INSERT = "INSERT INTO account (username, password) VALUES (?, ?)";
    public static final String GET_BY_USERNAME = "SELECT * FROM account WHERE username = ?";

    @Override
    public Account createAccount(Account account) {

        try {
            Connection connection = ConnectionUtil.getConnection();

            // cannot proceed without a connection
            if (connection == null) throw new SQLException("Could not get connection");

            // set our vars and make sure to return the pk
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getPassword());

            // cannot proceed unless user was persisted
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0)  throw new SQLException("User was not persisted");

            // extract our pk
            ResultSet pk = preparedStatement.getGeneratedKeys();
            if (pk.next()) {
                account.setAccount_id(pk.getInt(1));
            }

        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }

        return account;
    }

    @Override
    public Account getAccountByUsername(String username) {

        Account returnAccount = null;

        try {
            Connection connection = ConnectionUtil.getConnection();

            // cannot proceed without a connection
            if (connection == null) throw new SQLException("Could not get connection");

            // set the var for our prepared statement and execute
            PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_USERNAME);
            preparedStatement.setString(1, username);

            // our results should be here, project and return otherwise return null
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                returnAccount = new Account();
                returnAccount.setAccount_id(resultSet.getInt("account_id"));
                returnAccount.setUsername(resultSet.getString("username"));
                returnAccount.setPassword(resultSet.getString("password"));
            }

        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }

        return returnAccount; // either null or returned entity
    }
}
