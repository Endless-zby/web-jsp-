package com.zby.service;


import com.zby.entity.*;

import java.util.List;

public interface UserService {
    List<queuser> queryUsers(queuser queusers);
    List<Information> information(Information information);
    List<Account> account(Account account) throws Exception;
    List<sql> sql() throws Exception;
    boolean login(Login login)throws Exception;


}
