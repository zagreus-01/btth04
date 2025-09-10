package vn.iotstar.dao;

import vn.iotstar.entity.User;

public interface UserDao {
    User findByUsernameAndPassword(String username, String password);
}