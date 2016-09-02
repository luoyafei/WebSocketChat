package com.socketchat.chatsocket;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.socketchat.bean.ChatMessage;
import com.socketchat.bean.User;
import com.socketchat.dao.IChatMessageDao;
import com.socketchat.dao.IUserDao;
import com.socketchat.websockettoolkit.GetHttpSessionConfigurator;

@ServerEndpoint(value = "/chat/server",configurator=GetHttpSessionConfigurator.class)
public class ChatServer  {
	
	private User fromUser;
	private User toUser;
	private int i = 0;
	private Gson gson = new Gson();
	private Session session;
	private JsonObject jo = new JsonObject();
	
	private IUserDao ud;
	private IChatMessageDao cmd;
	
	
	private static final Map<String,Object> connections = new HashMap<String,Object>();  
	
	private List<ChatMessage> getNotReadChatMessage(String fromUserId, String toUserId) {
		
		return cmd.getNotReadChatMessages(fromUserId, toUserId, 0);
		
	}
	
	
	@OnMessage // 接受客户端消息
	public void onMessage(String msg) throws Exception {
		
		session.setMaxIdleTimeout(-1);//设置永不断线

		if(i == 0 && msg != null && msg.trim().length() == 32) {
			toUser = ud.getOneUser(msg);
			toUser.setPassword(null);
			toUser.setRemoteIp(null);
			jo.add("toUser", gson.toJsonTree(toUser));
			jo.add("fromUser", gson.toJsonTree(fromUser));
			/**
			 * 获取历史消息
			 */
			jo.add("historyMsg", gson.toJsonTree(getHistoryMessage(0, 100)));
			
			/**
			 * 先获取对方发给自己的未读消息
			 */
			List<ChatMessage> notRead = getNotReadChatMessage(toUser.getUserId(), fromUser.getUserId());
			jo.add("notRead", gson.toJsonTree(notRead));
			for(ChatMessage cm : notRead) {
				cm.setNeedRead(1);
			}
			cmd.updateChatMessage(notRead);//将其更新为已读
			
		} else if(i > 0 && toUser != null && fromUser != null) {
			
			ChatMessage cm = null;
			if(msg != null && msg.trim().hashCode() != 0) {
				cm = new ChatMessage();
				cm.setChatMessageId(UUID.randomUUID().toString().replace("-", ""));
				cm.setChatMessage(msg);
				cm.setFromUserId(fromUser.getUserId());
				cm.setToUserId(toUser.getUserId());
				cm.setMessageSendTime(new Timestamp(System.currentTimeMillis()));
				cm.setNeedRead(0);
				
				/*若对方在线，进行推送！*/
				
				Session toUserSession= null;
				for(int j = 0; j < connections.size(); j++) {
					if(connections.containsKey(toUser.getUserId())) {
						
						jo.addProperty("notOnline", true);
						
						toUserSession = (Session)connections.get(toUser.getUserId());
						JsonObject jb = new JsonObject();
						User pushToUserShow = new User();
						pushToUserShow.setUserId(fromUser.getUserId());
						pushToUserShow.setNickName(fromUser.getNickName());
						pushToUserShow.setUserPicture(fromUser.getUserPicture());
						
						/**
						 * 将自己的信息传给对方
						 */
						jb.add("OnlineToUser", gson.toJsonTree(pushToUserShow));
						jb.add("OnlinePushToUserCM", gson.toJsonTree(cm));
						
						toUserSession.getBasicRemote().sendText(jb.toString());
						cm.setNeedRead(1);
						
						break;
					} else {
						jo.addProperty("notOnline", false);
						break;
					}
				}
				
				cmd.saveChatMessage(cm);
				
				jo.add("sendOne", gson.toJsonTree(cm));
			}
			
		} else
			jo.addProperty("error", "请先登录！确认登录状态正常！");
		
		session.getBasicRemote().sendText(jo.toString());
		i++;
	}

	private List<ChatMessage> getHistoryMessage(int start, int maxLength) {
		// TODO Auto-generated method stub
		return cmd.getHistoryChatMessagesByTwoUserId(fromUser.getUserId(), toUser.getUserId(), start, maxLength);
	}


	@SuppressWarnings("resource")
	@OnOpen // 成功连接时执行此代码
	public void onOpen(Session session, EndpointConfig config) {
		
		this.session = session;
		
		HttpSession httpSession= (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
		fromUser = (User)httpSession.getAttribute("user");
		fromUser.setRemoteIp(null);
		fromUser.setPassword(null);
		
		ud = new ClassPathXmlApplicationContext("beans.xml").getBean("ud", IUserDao.class);
		cmd = new ClassPathXmlApplicationContext("beans.xml").getBean("cmd", IChatMessageDao.class);
		
		/**
		 * 将连接成功的用户存入connections内
		 */
		connections.put(fromUser.getUserId(), session);
		
		
	
		System.out.println("------------webSocket is Open------------");
	}

	@OnClose // 连接关闭时执行
	public void onClose() {
		/**
		 * 当用户下线时，进行移除！
		 */
		connections.remove(fromUser.getUserId());
		System.out.println("------------webSocket is onClose------------");
	}
	
}