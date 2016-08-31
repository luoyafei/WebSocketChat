package com.socketchat.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Component;

import com.socketchat.bean.User;
import com.socketchat.dao.IUserDao;

@Component(value="ud")
public class UserDaoImpl implements IUserDao {

	
	
	private SqlSessionTemplate sqlSession;
	/**
	 * @return the sqlSession
	 */
	public SqlSessionTemplate getSqlSession() {
		return sqlSession;
	}

	/**
	 * @param sqlSession the sqlSession to set
	 */
	@Resource(name="sqlSession")
	public void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	@Override
	public boolean insertUser(User user) {
		// TODO Auto-generated method stub
		return sqlSession.insert("User.insertUser", user)==1;
	}

	@Override
	public boolean updateUser(User user) {
		// TODO Auto-generated method stub
		return sqlSession.update("User.updateUser", user)==1;
	}

	@Override
	public boolean deleteUser(String userId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User getOneUserFromNickAndPwd(User user) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("User.getOneUserFromNickAndPwd", user);
	}

	@Override
	public List<User> getAllUser(int start, int maxSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getOneUser(String userId) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("User.getOneUser", userId);
	}

	@Override
	public int getRemoteIp4User(String remoteIp) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("User.getRemoteIp4User", remoteIp);
	}

}
