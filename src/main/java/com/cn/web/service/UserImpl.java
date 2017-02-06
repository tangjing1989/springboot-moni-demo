package com.cn.web.service;

import com.cn.web.dao.UserMapper;
import com.cn.web.pojo.UserPojo;
import com.cn.web.util.page.PageBaseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Describe:
 * User:tangjing
 * Date:17/1/10
 * Time:下午4:43
 */
@Service
public class UserImpl implements IUserImpl {

    @Autowired
    UserMapper userMapper;

    @Autowired
    PageBaseUtil pageBaseUtil;

    public UserPojo getUser(String userName, String password) throws SQLException {

        return userMapper.queryUserByUserName(userName, password);
    }

    public String queryUserListPage(String aoData) throws SQLException {

        pageBaseUtil.setPageObject(aoData);
        pageBaseUtil.setSumNum(queryUserListCount(pageBaseUtil));
        Map<String, Object> map = pageBaseUtil.getParam();
        List<UserPojo> list = userMapper.queryUserListPage(map);
        return pageBaseUtil.getPageObject(list);

    }


    public int queryUserListCount(PageBaseUtil pageBaseUtil) throws SQLException {
        Map<String, Object> map = pageBaseUtil.getParam();
        return userMapper.queryUserListCount(map);
    }


}
