package com.cn.web.util.page;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * Describe:
 * User:tangjing
 * Date:17/1/23
 * Time:下午12:23
 */

public class PageBaseUtil {

    private String              sEcho;
    private int                 iDisplayStart;  // 起始索引
    private int                 iDisplayLength; // 每页显示的行数
    private int                 iTotalRecords;  //总页数
    private int                 sumNum;         //总记录数
    private String              PageObject;
    private Map<String, String> paraMap;//查询参数


    public PageBaseUtil() {
        this.sEcho = null;
        this.iDisplayStart = 0;
        this.iDisplayLength = 0;
        this.iTotalRecords = 0;
    }



    public int getSumNum() {
        return sumNum;
    }

    public void setSumNum(int sumNum) {
        this.sumNum = sumNum;
    }

    public void setPageObject(String aoData) {
        JSONArray jsonarray = JSONArray.fromObject(aoData);
        for (int i = 0; i < jsonarray.size(); i++) {
            JSONObject obj = (JSONObject) jsonarray.get(i);
            if (obj.get("name").equals("sEcho"))
                this.sEcho = obj.get("value").toString();

            if (obj.get("name").equals("iDisplayStart"))
                this.iDisplayStart = obj.getInt("value");

            if (obj.get("name").equals("iDisplayLength"))
                this.iDisplayLength = obj.getInt("value");
            if (obj.get("name").equals("param")) {
                JSONObject jasonObject = JSONObject.fromObject(obj.getString("value"));
                this.paraMap = (Map<String, String>) jasonObject;
            }
        }
    }


    public String getPageObject(List list) {
        JSONObject getObj = new JSONObject();
        getObj.put("sEcho", this.sEcho);// 不知道这个值有什么用,有知道的请告知一下
        getObj.put("iTotalRecords", this.iTotalRecords);//实际的行数
        getObj.put("iTotalDisplayRecords", sumNum);//显示的行数,这个要和上面写的一样
        getObj.put("aaData", list);//要以JSON格式返回
        return getObj.toString();

    }

    public Map<String, Object> getParam() {
        Map<String, Object> resultMap = new HashedMap();
        resultMap.put("start", iDisplayStart);
        resultMap.put("end", iDisplayLength);
        for (String str : this.paraMap.keySet()) {
            if(!StringUtils.isEmpty(this.paraMap.get(str).toString()))
                resultMap.put(str, this.paraMap.get(str));
        }
        return resultMap;
    }
}
