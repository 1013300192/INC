package com.example.intelligentnavigationcane;

import android.text.TextUtils;
import android.widget.Toast;

import com.example.intelligentnavigationcane.GHJ.Test_Login.Main_LoginActivity;
import com.example.intelligentnavigationcane.GHJ.Test_Login.RegisterActivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class connect {
    public static Connection getConnection(String dbName) throws SQLException {
            Connection conn ;
            try {
                Class.forName("com.mysql.jdbc.Driver"); //加载驱动
                String ip = "rm-bp1bk64541tej95g6oo.mysql.rds.aliyuncs.com";
                conn =(Connection) DriverManager.getConnection(
                        "jdbc:mysql://rm-bp1bk64541tej95g6oo.mysql.rds.aliyuncs.com:3306/inc",
                        "ybf_1013300192", "yin@091123");

            }catch (SQLException | ClassNotFoundException ex) {
                conn=null;
                ex.printStackTrace();
            }
            return conn;//返回Connection型变量conn用于后续连接
        }
    public static int insertIntoData(final String username, final String password) throws SQLException {//增加数据
        Connection conn ;
        conn = getConnection("inc");
        if(conn!=null) {
            RegisterActivity.conn_on=1;
            //使用DriverManager获取数据库连接
            Statement stmt = conn.createStatement();
            //使用Connection来创建一个Statment对象
            ResultSet rs;//从数据库中查询用户名对应的密码并返回
            rs = stmt.executeQuery(
                    "select * from person where username ='" + username + "'");
            if (rs.next()) {
                RegisterActivity.re_call = 0;
            } else {
                RegisterActivity.re_call = 1;
                if(!TextUtils.isEmpty(username)&&!TextUtils.isEmpty(password)){
                    RegisterActivity.call=1;
                    String sql = "insert INTO person(username,password)VALUES('" + username + "','" + password + "')";//把用户名和密码插入到数据库中
                    return stmt.executeUpdate(sql);
                }else {
                    RegisterActivity.call=0;
                }
            }
        }else {
            RegisterActivity.conn_on=0;
        }
        return 0;
    }
        //执行DML语句，返回受影响的记录条数

   /*public static String querycol_P(final String id) throws SQLException {//读取某一行
        //加载数据库驱动
        String a;
        Connection conn ;
        conn = getConnection("inc");
        //使用DriverManager获取数据库连接
       Statement stmt = conn.createStatement();
       //使用Connection来创建一个Statment对象
       ResultSet rs1;//从数据库中查询用户名对应的密码并返回
       rs1 = stmt.executeQuery(
               "select password from person where username='"+id+"'");
       rs1.first();
       a=rs1.getString(1);
       rs1.close();
       return a;
       //把查询结果输出来
    }*/

    public static String Recall_Data(final String username,final String password) throws SQLException {//增加数据
        Connection  conn ;
        conn = getConnection("inc");
        if(conn!=null){
            Main_LoginActivity.conn_on=1;
            //使用DriverManager获取数据库连接
            Statement stmt = conn.createStatement();
            //使用Connection来创建一个Statment对象
            ResultSet rs;//从数据库中查询用户名对应的密码并返回
            rs = stmt.executeQuery(
                    "select username and password from person where username ='" + username + "'and password='"+password+"'");
            if(rs.next()){
                Main_LoginActivity.Recall=1;//表示登录成功
            }else{
                Main_LoginActivity.Recall=0;//表示密码错误
            }
        }else {
            Main_LoginActivity.conn_on=0;
        }
        return null;
    }

}


