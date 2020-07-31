package arobs.library.core.repository;

import arobs.library.core.model.Book;
import arobs.library.core.model.Copy;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CopyJDBCRepository {
    private static final String URL="jdbc:postgresql://localhost:5432/library";
    private static final String USER="postgres";
    private static final String PASSWORD="1234";
    private Connection connection;

    public CopyJDBCRepository(){
        try{
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<Copy> getAllCopiesJDBC(){
        String sqlComm = "select * from copy";
        List<Copy> copies = new ArrayList<>();

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sqlComm);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Long id = resultSet.getLong("id");
                Integer code = resultSet.getInt("code");
                Boolean isAvailable = resultSet.getBoolean("is_available");
                String status = resultSet.getString("status");

                Copy copy = new Copy(code, isAvailable, status, null);
                copy.setId(id);
                copies.add(copy);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return copies;
    }
}
