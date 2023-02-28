package com.devops.lbnum_project.Models;

import com.devops.lbnum_project.services.chat.Message;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOMessage {

    private final DatabaseConnection DBConnection;
    public DAOMessage() {
        this.DBConnection = new DatabaseConnection();
    }

    public List<Message> getConversation(int idUser, int idUser1) {
        List<Message> conversation = new ArrayList<>();
        try (
             PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(
                     "SELECT * FROM Messages WHERE (Id_User = ? AND Id_User_1 = ?) OR (Id_User = ? AND Id_User_1 = ?)")) {
            preparedStatement.setInt(1, idUser);
            preparedStatement.setInt(2, idUser1);
            preparedStatement.setInt(3, idUser1);
            preparedStatement.setInt(4, idUser);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idMessage = resultSet.getInt("Id_Message");
                String content = resultSet.getString("Content");
                Timestamp date = resultSet.getTimestamp("date");
                Message message = new Message( content, idUser);
                conversation.add(message);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBConnection.close();
        }
        return conversation;
    }


//    public List<MessageWithAttachment> getConversationWithAttachment(int idUser, int idUser1) {
//        List<MessageWithAttachment> conversation = new ArrayList<>();
//        try (Connection connection = Database.getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(
//                     "SELECT * FROM Messages WHERE (Id_User = ? AND Id_User_1 = ?) OR (Id_User = ? AND Id_User_1 = ?)")) {
//            preparedStatement.setInt(1, idUser);
//            preparedStatement.setInt(2, idUser1);
//            preparedStatement.setInt(3, idUser1);
//            preparedStatement.setInt(4, idUser);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()) {
//                int idMessage = resultSet.getInt("Id_Message");
//                String content = resultSet.getString("Content");
//                Timestamp date = resultSet.getTimestamp("date");
//                Message message = new Message(idMessage, content, idUser, idUser1, date);
//
//                List<Attachment> attachments = new ArrayList<>();
//                try (PreparedStatement attachmentStatement = connection.prepareStatement(
//                        "SELECT * FROM Attachements WHERE Id_Message = ?")) {
//                    attachmentStatement.setInt(1, idMessage);
//                    ResultSet attachmentResultSet = attachmentStatement.executeQuery();
//                    while (attachmentResultSet.next()) {
//                        int idAttachment = attachmentResultSet.getInt("Id_Attachement");
//                        String type = attachmentResultSet.getString("Type");
//                        String fileName = attachmentResultSet.getString("FileName");
//                        String size = attachmentResultSet.getString("Size");
//                        Timestamp attachmentDate = attachmentResultSet.getTimestamp("date");
//                        Attachment attachment = new Attachment(idAttachment, type, fileName, size, idMessage, attachmentDate);
//                        attachments.add(attachment);
//                    }
//                }
//                MessageWithAttachment messageWithAttachment = new MessageWithAttachment(message, attachments);
//                conversation.add(messageWithAttachment);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return conversation;
//    }


}
