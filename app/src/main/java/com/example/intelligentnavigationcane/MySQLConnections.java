package com.example.intelligentnavigationcane;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySQLConnections {
    // An highlighted block
        private String driver = "";
        private String dbURL = "";
        private String user = "";
        private String password = "";
        private static MySQLConnections connection = null;
        private MySQLConnections() throws Exception {
            driver = "com.mysql.jdbc.Driver";
            dbURL = "jdbc:mysql://rm-bp1bk64541tej95g6oo.mysql.rds.aliyuncs.com:3306/inc";
            user = "ybf_1013300192";
            password = "yin@091123";
            System.out.println("dbURL:" + dbURL);
        }
        public static Connection getConnection() {
            Connection conn = null;
            if (connection == null) {
                try {
                    connection = new MySQLConnections();
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
            try {
                Class.forName(connection.driver);
                conn = DriverManager.getConnection(connection.dbURL,
                        connection.user, connection.password);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return conn;
        }
    }
