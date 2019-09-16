package org.easyarch.springboot_netty.pojo;

import org.apache.ibatis.jdbc.SQL;

/**
 * @ClassName UserSql
 * @Description TODO
 * @Author Liyihe
 * @Date 2019/09/16 下午8:40
 * @Version 1.0
 */
public class UserSql {
    //查询
    public String select(User user) {
        return new SQL() {{
            SELECT("*");
            FROM("user");
            if (user != null) {
                if (user.getUsername() != null) {
                    WHERE("username = #{username}");
                }
            }
        }}.toString();
    }
}
