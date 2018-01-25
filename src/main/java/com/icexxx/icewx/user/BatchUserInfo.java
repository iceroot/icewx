package com.icexxx.icewx.user;

import java.util.List;

/**
 * icewx &nbsp; 微信框架
 * 
 * @author IceWater
 * @version 1.0.0
 */
public class BatchUserInfo {
    private List<UserInfo> user_list;

    public List<UserInfo> getUser_list() {
        return user_list;
    }

    public void setUser_list(List<UserInfo> user_list) {
        this.user_list = user_list;
    }

    @Override
    public String toString() {
        return "BatchUserInfo [user_list=" + user_list + "]";
    }
}
