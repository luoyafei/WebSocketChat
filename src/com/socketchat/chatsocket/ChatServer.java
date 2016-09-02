package com.socketchat.chatsocket;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.socketchat.bean.User;
import com.socketchat.dao.IChatMessageDao;
import com.socketchat.dao.IUserDao;
import com.socketchat.websockettoolkit.GetHttpSessionConfigurator;

@ServerEndpoint(value = "/chat/server",configurator=GetHttpSessionConfigurator.class)
public class ChatServer  {
	
	private User fromUser;
	private User toUser;
	private String toUserId;
	private int i = 0;
	private Gson gson = new Gson();
	private Session session;
	private JsonObject jo = new JsonObject();
	
	private IUserDao ud;
	private IChatMessageDao cmd;
	
	
	private static final Map<String,Object> connections = new HashMap<String,Object>();  
	@OnMessage // 接受客户端消息
	public void onMessage(String msg) throws Exception {
		
		
		session.setMaxIdleTimeout(-1);//设置永不断线
System.out.println(msg);
		session.getBasicRemote().sendText("hello:" + msg);
		System.out.println(ud);
		if(i == 0) {
			//toUser = ud.getOneUser(msg);
		}
		//System.out.println(toUser.toString());
		i++;
	/*	if(i == 0) {
			toUserId = msg;
			final User toUserTemp = new User();
			boolean rightToUser = (boolean)hibernateTemplate.execute(new HibernateCallback() { 
				@Override
				public Object doInHibernate(org.hibernate.Session session) throws SQLException {
					Object[] s = (Object[]) session.createQuery("select u.userId,u.userNickName,u.userPhoto from User u where u.userId = :userId").setParameter("userId", toUserId).list().get(0);
					if(s != null) {
						toUserTemp.setUserId(s[0].toString());
						toUserTemp.setUserNickName(s[1].toString());
						toUserTemp.setUserPhoto(s[2].toString());
						return true;
					} else
						return false;
				}
			});
			toUser = toUserTemp;
			toUser.setUserPassword(null);


			if(fromUser != null && toUser != null && rightToUser) {
				jo.add("toUser", gson.toJsonTree(toUser));
				
				*//**
				 * 先获取给我发送的消息，且我没有读取的
				 *//*
				List<ChatMessage> notRead = (ArrayList<ChatMessage>)hibernateTemplate.executeFind(new HibernateCallback() {
					@Override
					public Object doInHibernate(org.hibernate.Session session) throws HibernateException, SQLException {
						// TODO Auto-generated method stub
						String ejbql = "from ChatMessage cm where cm.fromUserId = :fromUserId and cm.toUserId = :toUserId and cm.needRead = 0 and cm.toChatRoomId = '0' order by cm.messageSendTime desc";
						return (ArrayList<ChatMessage>)session.createQuery(ejbql).setParameter("fromUserId", toUserId).setParameter("toUserId", fromUser.getUserId()).list(); 
					}
				});
				jo.add("notRead", gson.toJsonTree(notRead));
				jo.addProperty("first", true);
				
				*//**
				 * 将历史消息发送
				 *//*
				jo.add("historyMsg", gson.toJsonTree(getHistoryMessage()));
				
				
				session.getBasicRemote().sendText(jo.toString()); // 发送信息到客户端
				
				*//**
				 * 将获取到未读消息设置为已读
				 *//*
				if(notRead.size() > 0) {
					for(int i = 0; i < notRead.size(); i++) {
						notRead.get(i).setNeedRead(1);
						hibernateTemplate.update(notRead.get(i));
					}
				}
			}
			
		} else {
			jo.add("toUser", gson.toJsonTree(toUser));
			ChatMessage cm = null;
			if(msg != null && msg.trim().hashCode() != 0) {
				cm = new ChatMessage();
				cm.setChatMessage(msg);
				cm.setFromUserId(fromUser.getUserId());
				cm.setToUserId(toUserId);
				cm.setMessageSendTime(new Timestamp(System.currentTimeMillis()));
				cm.setNeedRead(0);
				cm.setToChatRoomId("0");
				
				*//**
				 * 先将发送的消息放入自己的websocketsession内
				 *//*
				jo.add("sendOne", gson.toJsonTree(cm));
				
				*//**
				 * 若对方在线，进行推送！
				 *//*
				Session toUserSession= null;
				for(int i = 0; i < connections.size(); i++) {
					if(connections.containsKey(toUserId)) {
						
						jo.addProperty("notOnline", true);
						
						toUserSession = (Session)connections.get(toUserId);
						JsonObject j = new JsonObject();
						User pushToUserShow = new User();
						pushToUserShow.setUserId(fromUser.getUserId());
						pushToUserShow.setUserNickName(fromUser.getUserNickName());
						pushToUserShow.setUserPhoto(fromUser.getUserPhoto());
						j.add("toUser", gson.toJsonTree(pushToUserShow));
						j.add("pushToUser", gson.toJsonTree(cm));
						toUserSession.getBasicRemote().sendText(j.toString());
						*//**
						 * 当给用户推送过去后，就将其设置为已读！
						 *//*
						cm.setNeedRead(1);
						
					} else {
						*//**
						 * 将用户不在线的消息进行推送！
						 *//*
						jo.addProperty("notOnline", false);
					}
				}
				*//**
				 * 最后将其存储！
				 *//*
				hibernateTemplate.save(cm);
			}
			
			
			jo.add("notRead", gson.toJsonTree(null));
			session.getBasicRemote().sendText(jo.toString());
			if(msg.equals("close")) {
				session.getBasicRemote().sendText("bye bye!"); // 发送信息到客户端
				session.close(); // 关闭连接点
			}
		}
		i++;*/
	}

	@OnOpen // 成功连接时执行此代码
	public void onOpen(Session session, EndpointConfig config) {
		
		this.session = session;
		
		HttpSession httpSession= (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
		fromUser = (User)httpSession.getAttribute("user");

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