package DAO;

import Model.Account;
import Model.Message;
import Util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MessageDoaImpl implements MessageDao{

    public static final String INSERT = "INSERT INTO message (posted_by, message_text, time_posted_epoch) VALUES (?, ?, ?)";
    public static final String GET_ALL = "SELECT * FROM message";
    public static final String GET_BY_ID = "SELECT * FROM message WHERE MESSAGE_ID = ?";
    public static final String GET_BY_ACCOUNT_ID = "SELECT * FROM message WHERE POSTED_BY = ?";
    public static final String UPDATE_MESSAGE_TEXT = "UPDATE message SET message_text = ? WHERE message_id = ?";
    public static final String DELETE_BY_ID = "DELETE FROM message WHERE message_id = ?";

    @Override
    public Message createMessage(Message message) {
        try {
            Connection connection = ConnectionUtil.getConnection();

            // cannot proceed without a connection
            if (connection == null) throw new SQLException("Could not get connection");

            // set our vars and make sure to return the pk
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, message.getPosted_by());
            preparedStatement.setString(2, message.getMessage_text());
            preparedStatement.setLong(3, message.getTime_posted_epoch());

            // cannot proceed unless user was persisted
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0)  throw new SQLException("Message was not persisted");

            // extract our pk
            ResultSet pk = preparedStatement.getGeneratedKeys();
            if (pk.next()) {
                message.setMessage_id(pk.getInt(1));
            }

        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }

        return message;
    }

    @Override
    public List<Message> getAllMessages() {

        List<Message> returnMessageList = new ArrayList<>();

        try {
            Connection connection = ConnectionUtil.getConnection();

            // cannot proceed without a connection
            if (connection == null) throw new SQLException("Could not get connection");

            // basic statement for a get all no SQL injection risk
            Statement statement = connection.createStatement();

            // our results should be here, project and return
            ResultSet resultSet = statement.executeQuery(GET_ALL);
            while (resultSet.next()) {
                Message message = new Message();
                message.setMessage_id(resultSet.getInt("message_id"));
                message.setPosted_by(resultSet.getInt("posted_by"));
                message.setMessage_text(resultSet.getString("message_text"));
                message.setTime_posted_epoch(resultSet.getLong("time_posted_epoch"));
                returnMessageList.add(message);
            }

        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }

        return returnMessageList;
    }

    @Override
    public Message getMessageById(int id) {
        Message returnMessage = null;

        try {
            Connection connection = ConnectionUtil.getConnection();

            // cannot proceed without a connection
            if (connection == null) throw new SQLException("Could not get connection");

            // set the var for our prepared statement and execute
            PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID);
            preparedStatement.setInt(1, id);

            // our results should be here, project and return otherwise return null
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                returnMessage = new Message();
                returnMessage.setMessage_id(resultSet.getInt("message_id"));
                returnMessage.setPosted_by(resultSet.getInt("posted_by"));
                returnMessage.setMessage_text(resultSet.getString("message_text"));
                returnMessage.setTime_posted_epoch(resultSet.getLong("time_posted_epoch"));
            }

        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }

        return returnMessage; // either null or returned entity
    }

    @Override
    public List<Message> getMessagesByAccount(int accountId) {
        List<Message> returnMessageList = new ArrayList<>();

        try {
            Connection connection = ConnectionUtil.getConnection();

            // cannot proceed without a connection
            if (connection == null) throw new SQLException("Could not get connection");

            // set the var for our prepared statement
            PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ACCOUNT_ID);
            preparedStatement.setInt(1, accountId);

            // our results should be here, project and return
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Message message = new Message();
                message.setMessage_id(resultSet.getInt("message_id"));
                message.setPosted_by(resultSet.getInt("posted_by"));
                message.setMessage_text(resultSet.getString("message_text"));
                message.setTime_posted_epoch(resultSet.getLong("time_posted_epoch"));
                returnMessageList.add(message);
            }

        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }

        return returnMessageList;
    }

    @Override
    public Message updateMessageById(int id, String newMessageText) {
        try {
            Connection connection = ConnectionUtil.getConnection();

            // cannot proceed without a connection
            if (connection == null) throw new SQLException("Could not get connection");

            // will not proceed if message not found
            Message message = getMessageById(id);
            if (message == null) throw new SQLException("Message not found");

            // set the var for our prepared statement
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_MESSAGE_TEXT);
            preparedStatement.setString(1, newMessageText);
            preparedStatement.setInt(2, id);

            // cannot proceed unless message update was persisted
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0)  throw new SQLException("Message update was not persisted");

            // update successful - updating and returning the argument, so I don't have to query the DB again.
            message.setMessage_text(newMessageText);
            return message;

        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }

        return null; // shouldn't reach this
    }

    @Override
    public void deleteMessageById(int id) {

        try {
            Connection connection = ConnectionUtil.getConnection();

            // cannot proceed without a connection
            if (connection == null) throw new SQLException("Could not get connection");

            // set the var for our prepared statement and execute
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
    }
}
