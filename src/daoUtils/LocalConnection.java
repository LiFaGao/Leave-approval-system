package daoUtils;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class LocalConnection {
    private static String DBDRIVER;
    private static String DBURL;
    private static String DBUSER;
    private static String DBPASSWORD;
    private Connection conn = null;
    /**
     * 获取本地的数据库连接
     */
    public LocalConnection() {
        Properties pp = new Properties();

        try {
            pp.load(LocalConnection.class.getClassLoader().getResourceAsStream("daoUtils/db.properties"));
            DBDRIVER = pp.getProperty("drive");
            DBURL = pp.getProperty("url");
            DBUSER = pp.getProperty("username");
            DBPASSWORD = pp.getProperty("password");
            Class.forName(DBDRIVER);
            conn = DriverManager.getConnection(DBURL,DBUSER,DBPASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    /**
     * 获取连接对象
     * @return Connection 数据库的连接对象
     */
    public Connection getConnection() {
        return conn;
    }
    /**
     * 关闭数据库连接对象
     * @throws SQLException
     */
    public void close() throws SQLException {
        this.conn.close();
    }
}
