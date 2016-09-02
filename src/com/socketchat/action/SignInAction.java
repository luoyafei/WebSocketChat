package com.socketchat.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.gson.JsonObject;
import com.opensymphony.xwork2.ActionSupport;
import com.socketchat.bean.User;
import com.socketchat.dao.IUserDao;

@SuppressWarnings("serial")
@Component(value="signInAction")
@Scope("prototype")
public class SignInAction extends ActionSupport {

	private String nickName;
	private String password;
	private boolean span = false;
	
	private IUserDao ud;
	/**
	 * @return the ud
	 */
	public IUserDao getUd() {
		return ud;
	}
	/**
	 * @param ud the ud to set
	 */
	@Resource(name="ud")
	public void setUd(IUserDao ud) {
		this.ud = ud;
	}
	
	/**
	 * @return the nickName
	 */
	public String getNickName() {
		return nickName;
	}
	/**
	 * @param nickName the nickName to set
	 */
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the span
	 */
	public boolean isSpan() {
		return span;
	}
	/**
	 * @param span the span to set
	 */
	public void setSpan(boolean span) {
		this.span = span;
	}
	
	
	public void login() {
		
		JsonObject jo = new JsonObject();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch(IOException e){}
		
		if(nickName != null && nickName.trim().hashCode() != 0 && nickName.trim().length() < 8 && password != null && password.trim().hashCode() != 0 && password.trim().length() < 16 && span) {
			String remoteIp = ServletActionContext.getRequest().getRemoteAddr();
			if(ud.getRemoteIp4User(remoteIp) > 5) {
				jo.addProperty("result", false);
				jo.addProperty("reason", "同一IP地址不能申请超过5个账号！");
			} else {
				User user = new User();
				user.setNickName(nickName);
				user.setPassword(password);
				User getOneUser = ud.getOneUserFromNickAndPwd(user);

				if(getOneUser == null) {
					if(ud.getOneUserByNickName(nickName) == 1) {
						jo.addProperty("result", false);
						jo.addProperty("reason", "该昵称重复，请换一个！");
					} else {
						user.setUserId(UUID.randomUUID().toString().replace("-", ""));
						user.setCreateDate(new Timestamp(System.currentTimeMillis()));
						user.setRemoteIp(remoteIp);
						if(ud.insertUser(user)) {
							ServletActionContext.getRequest().getSession().setAttribute("user", user);
							jo.addProperty("result", true);
						} else {
							jo.addProperty("result", false);
							jo.addProperty("reason", "注册失败！");
						}
					}
				} else {
					ServletActionContext.getRequest().getSession().setAttribute("user", getOneUser);
					jo.addProperty("result", true);
				}
			}
		} else {
			jo.addProperty("result", false);
			jo.addProperty("reason", "请按要求输入信息！");
		}
		out.print(jo.toString());
		out.flush();
		out.close();
		
		return;
		
	}
}
