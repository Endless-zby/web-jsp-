package com.zby.dao;

import com.zby.dbutil.DBUtil;
import com.zby.entity.*;
import com.zby.util;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoIn implements UserDao {

    public List<queuser> queryUsers(queuser queusers) {
     //   List<User> users = new ArrayList<>();
        List<queuser> queusersd = new ArrayList<>();
        String sql = "select age from demo where name =?";
            try {
                Object[] params = {queusers.getName()};
                ResultSet rs = util.executeQuery(sql,params);
                while(rs.next()){
                    System.out.println(rs.getString("age"));
                    queuser queuserd = new queuser(rs.getString("age"));
                    queusersd.add(queuserd);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        System.out.println(queusersd.size());
        return queusersd;
    }

    @Override
    public List<Information> bankInformation(Information information) {
        ArrayList<Information> information1  = new ArrayList<>();
        String sql ="select * from bank where id = ?";
        Object[] params = {information.getId()};
        try {
            ResultSet rs = util.executeQuery(sql,params);
            while(rs.next()){
                Information informations = new Information(rs.getString("name"),rs.getString("id"),rs.getString("balance"));
                information1.add(informations);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return information1;
    }

    @Override
    public List<Account> accountmoney(Account account) {
        try {
            //开启
            DBUtil.beginTransaction();
            UserDao userDao = new UserDaoIn();
            Information informationform = new Information(null,account.getFrommoney(),null);
            List<Information> informationfroms = userDao.bankInformation(informationform);
            for (Information form : informationfroms) {
                if(Integer.valueOf(account.getMoney())>(Integer.valueOf(form.getBalance()))){
                    System.out.println(1/0);
                }
                Object[] params = {(Integer.valueOf(form.getBalance())-Integer.valueOf(account.getMoney()))+"",form.getId()};
                util.executeUpdate("update bank set balance = ? where id = ? ",params);
            }
            Information informationto = new Information(null,account.getTomoney(),null);
            List<Information> informations = userDao.bankInformation(informationto);
            for (Information form : informations) {
                Object[] params = {(Integer.valueOf(form.getBalance())+Integer.valueOf(account.getMoney()))+"",form.getId()};
                util.executeUpdate("update bank set balance = ? where id = ? ",params);
            }
            DBUtil.commitTransaction();//成功！


        } catch (Exception e) {
            e.printStackTrace();

            try {
                DBUtil.rollbackTransaction();
            } catch (Exception e1) {
                e1.printStackTrace();
            }

            System.out.println("转账失败，回滚操作！");
        }
        return null;
    }

    @Override
    public List<sql> sqldao() throws Exception {
            ArrayList<sql> objects = new ArrayList<>();
            Connection connection = DBUtil.getDataSourceWithC3P0ByXml().getConnection();
            DatabaseMetaData metaData = connection.getMetaData();
            sql sql = new sql(metaData.getDriverVersion(),metaData.getDatabaseProductName(),metaData.getUserName());
            objects.add(sql);
        return objects;
    }

    @Override
    public boolean login(Login login) throws Exception {
        login.getUsername();
        login.getPassword();
        Object[] objects = {login.getUsername(), login.getPassword()};
        ResultSet resultSet = util.executeQuery("select * from zby1 where name = ? and password = ?", objects);
        return resultSet.next();
    }
}
