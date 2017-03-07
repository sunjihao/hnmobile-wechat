package com.hnmobile.wechat.util;

public class UserModel {
    private String userName;// 用户名
    private String passWord;// 密码
    
    public UserModel(){
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    @Override
    public String toString() {
        return "userName=" + userName + "; passWord=" + passWord + ";";
    }

}
