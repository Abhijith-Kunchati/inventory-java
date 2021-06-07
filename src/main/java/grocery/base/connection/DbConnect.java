package grocery.base.connection;

import java.sql.DriverManager;
import java.sql.*;

import grocery.base.Constants;

public class DbConnect {
    public Connection getConnection() throws Exception{
        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        return DriverManager.getConnection(Constants.Url,Constants.DBUser,Constants.DBPass);
    }
}
