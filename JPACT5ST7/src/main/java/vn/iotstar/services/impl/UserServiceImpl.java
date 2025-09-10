package vn.iotstar.services.impl;

import vn.iotstar.services.UserService;
import vn.iotstar.dao.UserDao;
import vn.iotstar.dao.impl.UserDaoImpl;
import vn.iotstar.entity.User;
public class UserServiceImpl implements UserService {
	private UserDao userDao;

	public UserServiceImpl() {
		this.userDao = new UserDaoImpl();
	}

	@Override
	public User findByUsernameAndPassword(String username, String password) {
		return userDao.findByUsernameAndPassword(username, password);
	}
}
