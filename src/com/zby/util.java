package com.zby;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.*;

public class util {
    //private  final static String  DRIVER="com.mysql.jdbc.Driver";

    private static Connection con = null;
    private static PreparedStatement pstmt = null;
    private static ResultSet rs = null;


    public static Connection getCon() {
        return con;
    }
    public static PreparedStatement getPstmt() {
        return pstmt;
    }
    //select *from student where id = ?



    public static ResultSet executeQuery(String sql,Object[] params) throws SQLException {
        pstmt = getPreparedStatement(sql, params);
        rs = pstmt.executeQuery();
        return rs;
    }

    public static PreparedStatement getPreparedStatement(String sql,Object[] params) throws SQLException{

        //将访问数据库的连接 指向“数据源”
        try {

            /*
             *
            采用Tomcat-dbcp方式
             *
             */
//              Class.forName(DRIVER);
//            Context ctx = new InitialContext() ;//context.xml
//            DataSource ds = (DataSource)ctx.lookup("java:comp/env/user") ;

            /*
            采用c3p0文件XML文件读取
             */

           ComboPooledDataSource c3p0 = new ComboPooledDataSource("com/zby");
            con = c3p0.getConnection();

            /*
            c3p0 硬编码模式   set所有配置
             */

            /*
            采用dbcp - properties方式
             */
//            InputStream in = new util().getClass().getClassLoader().getResourceAsStream("dbcpconfig.properties");
//            Properties properties = new Properties();
//            properties.load(in);
//
//            DataSource dbcp = BasicDataSourceFactory.createDataSource(properties);
//            con = dbcp.getConnection();



            System.out.println("连接成功");

        } catch (Exception e) {
            e.printStackTrace();
        }

//    	con = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        pstmt =  con.prepareStatement(sql);
        if(params !=null){
            for(int i=0;i<params.length;i++){
                pstmt.setObject(i+1, params[i]);
            }
        }
        return pstmt;
    }
    public static boolean   executeUpdate(String sql,Object[] params) throws SQLException, ClassNotFoundException{
        boolean flag = false;
        pstmt =  getPreparedStatement(sql, params);
        System.out.println(sql);
        int result  = pstmt.executeUpdate();
        if(result>0){
            flag = true;
        }
        closeAll(null, pstmt, con);
        return flag;
    }
    public static void closeAll(ResultSet rs,Statement stmt,Connection con) throws SQLException{
        if(rs!=null) rs.close();
        if(stmt!=null) stmt.close();
        if(con !=null) con.close();
    }
}
