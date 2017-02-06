package com.cn.web.service;

import com.cn.web.pojo.MenuListPojo;
import com.cn.web.pojo.MenuPojo;

import java.util.List;

/**
 * Describe:
 * User:tangjing
 * Date:17/1/16
 * Time:上午10:42
 */

public interface ISysImpl {

    List<MenuListPojo> queryLev1Menu();

}
