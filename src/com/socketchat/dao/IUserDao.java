package com.socketchat.dao;

import java.util.List;

import com.socketchat.bean.User;

public interface IUserDao {

	public boolean insertUser(User user);
	public boolean updateUser(User user);
	public boolean deleteUser(String userId);
	/**
	 * 通过用户昵称和密码来获取用户的信息
	 * @param user
	 * @return
	 */
	public User getOneUserFromNickAndPwd(User user);
	/**
	 * 获取用户集合，根据开始位置和获取个数来获取
	 * @param start
	 * @param maxSize
	 * @return
	 */
	public List<User> getAllUser(int start, int maxSize);
	
	/**
	 * 根据用户Id还获取整个用户的信息
	 * @param userId
	 * @return
	 */
	public User getOneUser(String userId);
	
	public int getRemoteIp4User(String remoteIp);
	
	/**
	 * 通过用户的Id获取他所有的friend
	 * @param userId
	 * @return
	 */
	public List<User> getFriendsByUserId(String userId);
	public int getOneUserByNickName(String nickname);
}
