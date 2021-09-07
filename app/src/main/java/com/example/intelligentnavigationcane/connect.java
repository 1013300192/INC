package com.example.intelligentnavigationcane;

import android.text.TextUtils;

import com.example.intelligentnavigationcane.GHJ.Test_Login.ForgetActivity;
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
    public static int insertIntoData(final String username, final String password,String phone,String name) throws SQLException {//增加数据
        Connection conn ;
        conn = getConnection("inc");
        if(conn!=null) {
            RegisterActivity.conn_on=1;
            if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)) {
                RegisterActivity.call=1;
                Statement stmt = conn.createStatement();
                //使用Connection来创建一个Statment对象
                ResultSet rs;//从数据库中查询用户名对应的密码并返回
                rs = stmt.executeQuery(
                    "select username from data where username ='" + username + "'");
                if (rs.next()) {
                    RegisterActivity.re_call = 0;
                } else {
                    Statement stmt1 = conn.createStatement();
                    String sql = "INSERT INTO data (username,password,phone,name)VALUES('" + username + "','" + password + "','"+phone+"','"+name+"')";
                    RegisterActivity.re_call = 1;
                    return stmt1.executeUpdate(sql);
                }
            } else {
                RegisterActivity.call = 0; }
        }else {
            RegisterActivity.conn_on=0; }
        return 0;
    }
        //执行DML语句，返回受影响的记录条数

   /*public static String querycol_P(final String phone_P,final String person_name_P) throws SQLException {//读取某一行
        //加载数据库驱动
        String a;
        Connection conn ;
        conn = getConnection("inc");
        //使用DriverManager获取数据库连接
       Statement stmt = conn.createStatement();
       //使用Connection来创建一个Statment对象
       ResultSet rs1;//从数据库中查询用户名对应的密码并返回
       rs1 = stmt.executeQuery(
               "select password from data where phone='"+phone_P+"'and person_name='"+person_name_P+"'");
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
                    "select username and password from data where username ='" + username + "'and password='"+password+"'");
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


  public static String Phone(final String phone, final String person_name) throws SQLException {//增加数据
        Connection conn ;
        conn = getConnection("inc");
        if(conn!=null) {
            ForgetActivity.conn_on = 1;
            if (!TextUtils.isEmpty(phone) && !TextUtils.isEmpty(person_name)) {
                ForgetActivity.call = 1;
                Statement stmt = conn.createStatement();
                //使用Connection来创建一个Statment对象
                ResultSet rs;//从数据库中查询用户名对应的密码并返回
                rs = stmt.executeQuery(
                        "select phone and name from data where phone ='" + phone + "'and name='" + person_name + "'");
                if (rs.next()) {
                    ForgetActivity.re_call = 1;
                    String a;
                    //使用DriverManager获取数据库连接
                    Statement stmt2 = conn.createStatement();
                    //使用Connection来创建一个Statment对象
                    ResultSet rs1;//从数据库中查询用户名对应的密码并返回
                    rs1 = stmt2.executeQuery(
                            "select password from data where phone='" + phone + "'and name='" + person_name + "'");
                    rs1.first();
                    a = rs1.getString(1);
                    rs1.close();
                    return a;
                    //把查询结果输出来
                } else {
                    ForgetActivity.re_call = 0;
                }
            } else {
                ForgetActivity.call = 0;
            }
        }else
            ForgetActivity.conn_on=0;

        return null;
    }

}


