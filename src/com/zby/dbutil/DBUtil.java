package com.zby.dbutil;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;


public class DBUtil {
    public static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    /*
    c3p0连接
    */
    public static DataSource getDataSourceWithC3P0ByXml(){
        ComboPooledDataSource c3p0 = new ComboPooledDataSource("com/zby");
        return c3p0 ;
    }

    //获取复制的连接，如果没有则创建
    public static Connection getconnection() throws Exception{
        Connection conn = threadLocal.get();
        if (conn == null){
            conn = getDataSourceWithC3P0ByXml().getConnection();
            threadLocal.set(conn);
        }
        return  conn;
    }
    //开始事务
    public static void beginTransaction() throws Exception{
    Connection connection = getconnection();
    connection.setAutoCommit(false);

    }

    //提交事务-----正常
    public static void commitTransaction() throws Exception{
    Connection connection = getconnection();
    if(connection != null){
        connection.commit();
    }

    }
    //回滚事务-----失败
    public static void rollbackTransaction() throws Exception{
        Connection connection = getconnection();
        if(connection != null){
            connection.rollback();
        }

    }





}
