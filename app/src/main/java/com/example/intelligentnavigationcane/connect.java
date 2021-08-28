package com.example.intelligentnavigationcane;

import com.example.intelligentnavigationcane.GHJ.Test_Login.Main_LoginActivity;
import com.example.intelligentnavigationcane.GHJ.Test_Login.RegisterActivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class connect {
    public static Connection getConnection(String dbName) throws SQLException {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver"); //加载驱动
            String ip = "rm-bp1bk64541tej95g6oo.mysql.rds.aliyuncs.com";
            conn =(Connection) DriverManager.getConnection(
                    "jdbc:mysql://rm-bp1bk64541tej95g6oo.mysql.rds.aliyuncs.com:3306/inc",
                    "ybf_1013300192", "yin@091123");
            Main_LoginActivity.conn_on=1;//用于向主函数传参，判断连接是否成功
            RegisterActivity.conn_on=1;
        }catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            Main_LoginActivity.conn_on=2;//用于向主函数传参，判断连接是否成功
            RegisterActivity.conn_on=2;
        }
        return conn;//返回Connection型变量conn用于后续连接
    }
    public static int insertIntoData(final String username, final String password) throws SQLException {//增加数据
        Connection  conn = null;
        conn = getConnection("inc");
        //使用DriverManager获取数据库连接
        Statement stmt = conn.createStatement();
        //使用Connection来创建一个Statment对象
        String sql = "insert INTO person(username,password)VALUES('"+username+"','"+password+"')";//把用户名和密码插入到数据库中
        return stmt.executeUpdate(sql);
        //执行DML语句，返回受影响的记录条数
    }
    public static String querycol(final String id) throws SQLException {//读取某一行
        //加载数据库驱动
        String a;
        Connection conn = null;
        conn = getConnection("inc");
        //使用DriverManager获取数据库连接
        Statement stmt = conn.createStatement();
        //使用Connection来创建一个Statment对象
        ResultSet rs;//从数据库中查询用户名对应的密码并返回
        rs = stmt.executeQuery(
                "select password from person where username='"+id+"'");
        rs.first();
        a=rs.getString(1);
        rs.close();
        return a;
        //把查询结果输出来
    }
}


