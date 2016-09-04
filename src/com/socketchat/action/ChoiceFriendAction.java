package com.socketchat.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;
import com.socketchat.bean.User;
import com.socketchat.dao.IUserDao;

@SuppressWarnings("serial")
@Component(value="choiceFriendAction")
@Scope("prototype")
public class ChoiceFriendAction extends ActionSupport {
	
	private IUserDao ud;
	private User user;
	private HttpServletRequest request;
	public IUserDao getUd() {
		return ud;
	}
	@Resource(name="ud")
	public void setUd(IUserDao ud) {
		this.ud = ud;
	}
	
	@Override
	public void validate() {
		request = ServletActionContext.getRequest();
		user = (User)request.getSession().getAttribute("user");
		if(user == null)
			this.addFieldError("error", "请先进行登陆");
	}
	
	@Override
	public String execute() throws Exception {
		//ud.getFriendsByUserId(user.getUserId())
		List<User> others = ud.getAllUser(0, 100);
		List<User> friends = ud.getFriendsByUserId(user.getUserId());
		for(User fr : friends)
			others.remove(fr);
		request.setAttribute("friends", friends);
		request.setAttribute("others", others);
		return SUCCESS;
	}
}
