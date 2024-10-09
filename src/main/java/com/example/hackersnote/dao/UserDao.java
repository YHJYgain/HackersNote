package com.example.hackersnote.dao;

import com.example.hackersnote.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * 用户数据接口访问类
 */
@Repository
public interface UserDao extends CrudRepository<User, Long> {
    // CrudRepository 内置了一些的 CRUD 方法：
    // Iterable<User> UserDao.findAll();
    // Iterable<User> UserDao.findById(id);
    // boolean UserDao.existsById(id);
    // User UserDao.save(user);
    // Iterable<User> UserDao.saveAll(Iterable<User>);
    // void UserDao.deleteById(id);
    // void UserDao.delete(user);
    // void UserDao.deleteAll();

    boolean existsByUsername(String username);

    User findUserByUsername(String username);

    @Query("SELECT COUNT(u) FROM User u JOIN u.followedUsers followed WHERE followed.id = :userId")
    long countFollowersForUserId(@Param("userId") Long userId);

    @Query("SELECT u FROM User u JOIN u.followedUsers followed WHERE followed.id = :userId")
    Set<User> findFollowersForUserId(@Param("userId") Long userId);
} // end interface UserDao
