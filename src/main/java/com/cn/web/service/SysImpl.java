package com.cn.web.service;

import com.cn.web.dao.SysMapper;
import com.cn.web.pojo.MenuListPojo;
import com.cn.web.pojo.MenuPojo;
import com.cn.web.util.SysConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Describe:
 * User:tangjing
 * Date:17/1/16
 * Time:上午10:42
 */
@Service
public class SysImpl implements ISysImpl {

    @Autowired
    private SysMapper sysMapper;

    public List<MenuListPojo> queryLev1Menu() {
        List<MenuListPojo> MenuList     = new ArrayList<MenuListPojo>();
        List<MenuPojo>     menuLev1Lsit = sysMapper.queryMenuByLev(SysConstants.MenuLeve1);
        List<MenuPojo>     menuLev2Lsit = sysMapper.queryMenuByLev(SysConstants.MenuLeve2);
        for (MenuPojo pojo : menuLev1Lsit) {
            List<MenuPojo> nodes        = new ArrayList<MenuPojo>();
            MenuListPojo   menuListPojo = new MenuListPojo();
            for (MenuPojo nodePojo : menuLev2Lsit) {
                if (nodePojo.getParentCode().equals(pojo.getMenuCode())) {
                    nodes.add(nodePojo);
                }
            }
            menuListPojo.setMenuId(pojo.getMenuId());
            menuListPojo.setMenuCode(pojo.getMenuCode());
            menuListPojo.setMenuName(pojo.getMenuName());
            menuListPojo.setMenuHref(pojo.getMenuHref());
            menuListPojo.setParentCode(pojo.getParentCode());
            menuListPojo.setImgText(pojo.getImgText());
            menuListPojo.setNodes(nodes);
            MenuList.add(menuListPojo);
        }
        return MenuList;
    }
}
