package com.cn.web.util;

import org.springframework.ui.Model;

/**
 * Describe:
 * User:tangjing
 * Date:17/1/26
 * Time:下午2:07
 */

public enum Message {

    SERVER_ERROR("9999", false, "服务器异常"),
    USER_NOT_EXIST("1", false, "用户不存在"),
    PASSWORD_ERROR("2", false, "密码错误"),
    lOGIN_ERROR("3", false, "用户名密码错误"),
    UNlOGIN_ERROR("4", false, "请登录"),
    VERIFCODE_ERROR("5", false, "验证码错误"),
    VERIFCODE_NULL("6", false, "验证码不能为空");


    private String  code;
    private Boolean success;
    private String  msg;

    Message(String code, Boolean success, String msg) {
        this.code = code;
        this.success = success;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public Boolean getSuccess() {
        return success;
    }

    public String getMsg() {
        return msg;
    }

    public static void getMessage(Model model, String code) {
        for (Message message : Message.values()) {
            if (code.equals(message.name())) {
                model.addAttribute("message", new MessagePojo(message.getSuccess(), message.getMsg()));
            }
        }
    }

}

class MessagePojo {

    private Boolean success;
    private String  note;


    public MessagePojo() {
    }

    public MessagePojo(Boolean success, String msg) {
        this.success = success;
        this.note = msg;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean succsee) {
        this.success = succsee;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}



