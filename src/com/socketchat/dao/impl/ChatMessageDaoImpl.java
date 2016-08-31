package com.socketchat.dao.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.stereotype.Component;

import com.socketchat.bean.ChatMessage;
import com.socketchat.bean.User;
import com.socketchat.dao.IChatMessageDao;

@Component(value="cmd")
public class ChatMessageDaoImpl implements IChatMessageDao {

	@Override
	public boolean saveChatMessage(ChatMessage cm) {
		// TODO Auto-generated method stub
		return false;
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
	public List<ChatMessage> getHistoryChatMessagesByTwoUserId(String fromUserId, String toUserId, Timestamp beforeDate,
			int maxLength) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ChatMessage> getNotReadChatMessages(String fromUserId, String toUserId, int needRead) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getNotReadChatMessagesCount(String fromUserId, String toUserId, int needRead) {
		// TODO Auto-generated method stub
		return null;
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
