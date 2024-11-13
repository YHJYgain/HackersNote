package com.example.hackersnote.dao;

import com.example.hackersnote.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * 用户数据接口访问类，用于执行与 {@link User} 实体相关的数据库操作.
 */
@Repository
public interface UserDao extends CrudRepository<User, Long> {
    /**
     * 判断是否存在指定用户名的用户.
     *
     * @param username 用户名
     * @return 如果存在具有该用户名的用户，则返回 true；否则返回 false
     */
    boolean existsByUsername(String username);

    /**
     * 根据用户名查找用户.
     *
     * @param username 用户名
     * @return 匹配的 {@link User} 对象，如果不存在则返回 null
     */
    User findUserByUsername(String username);

    /**
     * 统计指定用户的粉丝数量.
     *
     * @param userId 用户 ID
     * @return 该用户的粉丝数量
     */
    @Query("SELECT COUNT(u) FROM User u JOIN u.followedUsers followed WHERE followed.id = :userId")
    long countFollowersForUserId(@Param("userId") Long userId);

    /**
     * 查询指定用户的所有粉丝.
     *
     * @param userId 用户 ID
     * @return 关注该用户的粉丝集合
     */
    @Query("SELECT u FROM User u JOIN u.followedUsers followed WHERE followed.id = :userId")
    Set<User> findFollowersForUserId(@Param("userId") Long userId);
}
