package com.socketchat.dao.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Component;

import com.socketchat.bean.ChatMessage;
import com.socketchat.bean.User;
import com.socketchat.dao.IChatMessageDao;

@Component(value="cmd")
public class ChatMessageDaoImpl implements IChatMessageDao {

	private SqlSessionTemplate sqlSession;
	
	public SqlSessionTemplate getSqlSession() {
		return sqlSession;
	}
	@Resource(name="sqlSession")
	public void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public boolean saveChatMessage(ChatMessage cm) {
		// TODO Auto-generated method stub
		return sqlSession.insert("ChatMessage.insertChatMessage", cm)==1;
	}

	@Override
	public boolean saveChatMessage(ChatMessage[] cms) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteChatMessage(ChatMessage cm) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean emptyChatMessagesByUserIdToSomeOne(String fromUserId, String toUserId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean emptyChatMessagesByUserIdToSomeOne(String fromUserId, String toUserId, Timestamp beforeDate) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean emptyChatMessagesByUserId(String fromUserId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<ChatMessage> getChatMessageInId(String chatMessageId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ChatMessage> getHistoryChatMessagesByTwoUserId(String fromUserId, String toUserId, int maxLength) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ChatMessage> getHistoryChatMessagesByTwoUserId(String fromUserId, String toUserId, Timestamp beforeDate, int maxLength) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ChatMessage> getNotReadChatMessages(String fromUserId, String toUserId, int needRead) {
		// TODO Auto-generated method stub
		Map<String, Object> maps = new HashMap<String, Object>();
		maps.put("fromUserId", fromUserId);
		maps.put("toUserId", toUserId);
		maps.put("needRead", needRead);
		return sqlSession.selectList("ChatMessage.getNotReadChatMessages", maps);
	}

	@Override
	public Long getNotReadChatMessagesCount(String fromUserId, String toUserId, int needRead) {
		// TODO Auto-generated method stub
		Map<String, Object> maps = new HashMap<String, Object>();
		maps.put("fromUserId", fromUserId);
		maps.put("toUserId", toUserId);
		maps.put("needRead", needRead);
		return sqlSession.selectOne("ChatMessage.getNotReadChatMessagesCount", maps);
	}

	@Override
	public Long getAllNotReadChatMessageCounts(String userId, int needRead) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getAllNotReadChatMessages(String userId, int needRead) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
