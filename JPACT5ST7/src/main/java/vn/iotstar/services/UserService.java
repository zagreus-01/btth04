package vn.iotstar.services;

import vn.iotstar.entity.User;

public interface UserService {
		User findByUsernameAndPassword(String username, String password);
}
