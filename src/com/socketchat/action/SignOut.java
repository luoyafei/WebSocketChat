package com.socketchat.action;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
@Component(value="signOut")
@Scope("prototype")
public class SignOut extends ActionSupport {

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		HttpSession session = ServletActionContext.getRequest().getSession();
		session.invalidate();
		return SUCCESS;
	}
}
