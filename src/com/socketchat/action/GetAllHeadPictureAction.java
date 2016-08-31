package com.socketchat.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.opensymphony.xwork2.ActionSupport;
import com.socketchat.bean.HeadPicture;
import com.socketchat.bean.User;

@SuppressWarnings("serial")
@Component(value="getAllHeadPictureAction")
@Scope("prototype")
public class GetAllHeadPictureAction extends ActionSupport {

	public void justDoIt() {
		JsonObject jo = new JsonObject();
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = null;
		User user = (User)request.getSession().getAttribute("user");
		try {
			out = response.getWriter();
		} catch(IOException e){}
		
		if(user != null) {
			Gson gson = new Gson();
			String[] pictures = HeadPicture.getPictures();
			jo.addProperty("register", true);
			jo.add("pictures", gson.toJsonTree(pictures));
		} else
			jo.addProperty("register", false);
		
		out.print(jo.toString());
		out.flush();
		out.close();
		return;
	}
}
