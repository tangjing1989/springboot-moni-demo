package com.cn.web.service;

import com.cn.web.pojo.UserPojo;

import java.util.List;

/**
 * Created by tangjing on 17/1/10.
 */
public interface IUserImpl {


    UserPojo getUser(String userName, String password) throws Exception;

    String queryUserListPage(String aoData) throws Exception;

}
