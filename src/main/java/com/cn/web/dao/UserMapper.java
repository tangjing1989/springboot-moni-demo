package com.cn.web.dao;

import com.cn.web.pojo.UserPojo;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by tangjing on 17/1/10.
 */
public interface UserMapper {
    int count();

    UserPojo queryUserByUserName(@Param("userName") String userName, @Param("password") String password) throws SQLException;

    List<UserPojo> queryUserList() throws SQLException;

    List<UserPojo> queryUserListPage(@Param("map") Map<String, Object> map) throws SQLException;

    int queryUserListCount(@Param("map") Map<String, Object> map) throws SQLException;


}
