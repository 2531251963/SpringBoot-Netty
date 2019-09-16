package org.easyarch.springboot_netty.pojo;

/**
 * @ClassName User
 * @Description TODO
 * @Author Liyihe
 * @Date 2019/09/16 下午1:51
 * @Version 1.0
 */
public class User {
    private String userid;
    private String username;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "User{" +
                "userid='" + userid + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
