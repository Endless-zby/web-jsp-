package com.zby.service;

import com.zby.dao.UserDao;
import com.zby.dao.UserDaoIn;
import com.zby.entity.*;
import com.zby.entity.Account;

import java.util.List;

public  class UserServiceIn implements UserService {
        UserDao userdao = new UserDaoIn();

    public List<queuser> queryUsers(queuser queusers) {
        return userdao.queryUsers(queusers);
    }

    @Override
    public  List<Information> information(Information information) {
        return userdao.bankInformation(information);
    }

    @Override
    public List<Account> account(Account account) throws Exception{
        return userdao.accountmoney(account);
    }

    @Override
    public List<sql> sql() throws Exception{

        return userdao.sqldao();
    }

    @Override
    public boolean login(Login login) throws Exception {
        return userdao.login(login);
    }


}
