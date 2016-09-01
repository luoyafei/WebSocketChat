package com.socketchat.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;
import com.socketchat.dao.IUserDao;

@SuppressWarnings("serial")
@Component(value="choiceFriendAction")
@Scope("prototype")
public class ChoiceFriendAction extends ActionSupport {
	
	private IUserDao ud;
	public IUserDao getUd() {
		return ud;
	}
	@Resource(name="ud")
	public void setUd(IUserDao ud) {
		this.ud = ud;
	}
	
	
	@Override
	public String execute() throws Exception {
		return null;
	}
}
