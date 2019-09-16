package org.easyarch.springboot_netty.mapper;


import org.apache.ibatis.annotations.*;
import org.easyarch.springboot_netty.pojo.User;
import org.easyarch.springboot_netty.pojo.UserSql;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserMapper {

    @Insert("insert into user values (#{userid},#{username})")
    int insert(User user);

    @Insert("insert into user (userid) values (#{userid})")
    int insertId(User user);

    @Select("select * from user where username =#{username}")
    User findByUserName(@Param("username") String username);

    @Select("select userid from user where userid =#{userid}")
    User findByUserID(@Param("userid") String userid);

    @Update("UPDATE user SET username=#{username} WHERE userid=#{userid}")
    void update(User user);

    @Delete("DELETE from user where userid =#{userid}")
    void delete(String userid);
    //INTEGER
    @Insert("INSERT INTO user(userid, username) VALUES(#{userid,jdbcType=VARCHAR}, #{username,jdbcType=VARCHAR})")
    int insertByMap(Map<String, Object> map);

    @SelectProvider(type = UserSql.class, method="select")
    List<User> findAll(User student);

}