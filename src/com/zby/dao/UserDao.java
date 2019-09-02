package com.zby.dao;


import com.zby.entity.*;

import java.util.List;

public interface UserDao {
    List<queuser> queryUsers(queuser queusers);
    List<Information> bankInformation(Information information);
    List<Account> accountmoney(Account account) throws Exception;
    List<sql> sqldao() throws Exception;
    boolean login(Login login) throws Exception;



}
