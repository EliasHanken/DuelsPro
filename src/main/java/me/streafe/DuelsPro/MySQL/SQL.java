package me.streafe.DuelsPro.MySQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SQL {

    private Connection connection;
    private int port;
    private String databaseName;
    private String userName;
    private String password;
    private String host;

    public SQL(String host, String databaseName, String userName, String password,int port){
        this.password = password;
        this.userName = userName;
        this.databaseName = databaseName;
        this.host = host;
        this.port = port;
    }

    public void openConnection() throws SQLException, ClassNotFoundException {
        if (this.connection != null && !this.connection.isClosed()){
            return;
        }
        synchronized (this){
            if(connection != null && !this.connection.isClosed()){
                return;
            }
            Class.forName("com.mysql.jdbc.Driver");
            this.connection = DriverManager.getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/" + this.databaseName,this.userName,this.password);
        }
    }

    public void createTable(String tableName) throws SQLException{
        Statement st = connection.createStatement();
        st.execute("CREATE TABLE IF NOT EXISTS "+tableName+" ( " +
                "uuid VARCHAR(45), name VARCHAR(45), friendCount INT, bestFriend VARCHAR(45) )");
        st.close();
    }

    public Connection getConnection(){
        return this.connection;
    }





}
