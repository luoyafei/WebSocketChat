package com.socketchat.action;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;
import com.socketchat.bean.HeadPicture;
import com.socketchat.bean.User;
import com.socketchat.dao.IUserDao;


@SuppressWarnings("serial")
@Component(value="dealChoiceHeadAction")
@Scope("prototype")
public class DealChoiceHeadAction extends ActionSupport {

	private String picture;
	private IUserDao ud;
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public IUserDao getUd() {
		return ud;
	}
	@Resource(name="ud")
	public void setUd(IUserDao ud) {
		this.ud = ud;
	}
	
	public String justDoIt(){
		
		if(picture == null && picture.trim().hashCode() == 0)
			return INPUT;
		else {
			for(String p : HeadPicture.getPictures()) {
				if(p.equals(picture.trim())) {
					User user = (User)ServletActionContext.getRequest().getSession().getAttribute("user");
					user.setUserPicture(picture);
					boolean result = ud.updateUser(user);
					if(result)
						return SUCCESS;
					else
						return INPUT;
				}
			}
			return INPUT;
		}
	}
}
