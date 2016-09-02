package com.socketchat.dao;

import java.sql.Timestamp;
import java.util.List;

import com.socketchat.bean.ChatMessage;
import com.socketchat.bean.User;

public interface IChatMessageDao {

	public boolean saveChatMessage(ChatMessage cm);
	public boolean saveChatMessage(ChatMessage[] cms);
	
	public boolean deleteChatMessage(ChatMessage cm);
	/**
	 * 将给某人发送过的所有消息全部清空
	 * @param fromUserId
	 * @param toUserId
	 */
	public boolean emptyChatMessagesByUserIdToSomeOne(String fromUserId, String toUserId);
	
	/**
	 * 将某个日期前，给某人发送过的所有消息全部清空
	 * @param fromUserId
	 * @param toUserId
	 * @param beforeDate
	 */
	public boolean emptyChatMessagesByUserIdToSomeOne(String fromUserId, String toUserId, Timestamp beforeDate);
	
	/**
	 * 将某人给所有人的所有聊天记录清空
	 * @param fromUserId
	 */
	public boolean emptyChatMessagesByUserId(String fromUserId);
	
	public List<ChatMessage> getChatMessageInId(String chatMessageId);
	/**
	 * 通过两个互相聊天的用户Id，每次获取的最大条数，可以获取他们双方的聊天记录
	 * @param fromUserId
	 * @param toUserId
	 * @param start
	 * @param maxLength
	 */
	public List<ChatMessage> getHistoryChatMessagesByTwoUserId(String fromUserId, String toUserId, int start, int maxLength);
	/**
	 * 将某个日期前与某个人的聊天记录获取到,限制最大条数
	 * @param fromUserId
	 * @param toUserId
	 * @param beforeDate
	 * @param maxLength
	 */
	public List<ChatMessage> getHistoryChatMessagesByTwoUserId(String fromUserId, String toUserId, Timestamp beforeDate, int maxLength);
	
	/**
	 * 获取与某人聊天的已读或未读的消息
	 * @param fromUserId
	 * @param toUserId
	 * @param needRead
	 */
	public List<ChatMessage> getNotReadChatMessages(String fromUserId, String toUserId, int needRead);
	
	/**
	 * 获取某个用户与另外一个用户，已读或未读的所有消息个数
	 * @param fromUserId
	 * @param toUserId
	 * @param needRead
	 * @return
	 */
	public Long getNotReadChatMessagesCount(String fromUserId, String toUserId, int needRead);
	
	/**
	 * 获取某个用户所有被通知的消息。根据已读未读区分
	 * @param userId
	 * @param needRead
	 * @return
	 */
	public Long getAllNotReadChatMessageCounts(String userId, int needRead);
	/**
	 * 获取某个用户,已读或未读的消息的用户的集合
	 * @param fromUserId
	 * @param needRead
	 * @return
	 */
	public List<User> getAllNotReadChatMessages(String userId, int needRead);
	/**
	 * @param notRead
	 */
	public void updateChatMessage(List<ChatMessage> notRead);
	
}
