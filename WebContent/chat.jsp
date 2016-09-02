<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ page import="com.socketchat.bean.User" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
if(session.getAttribute("user")==null || ((User)session.getAttribute("user")).getUserId().equals(request.getParameter("toUserId"))) {
	out.println("<script type='text/javascript'>window.history.back();</script>");
}
%>

<!DOCTYPE html>
<html dir="ltr" class="uk-height-1-1 uk-notouch" lang="en-gb">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>聊天室</title>
        <link rel="stylesheet" href="assets/uikit/uikit.css">
        <link rel="stylesheet" href="assets/uikit/slideshow.css">
        <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css" />
        <script src="assets/uikit/jquery.js"></script>
        <script src="assets/uikit/uikit.js"></script>
        <script src="assets/uikit/slideshow.js"></script>
        <script src="assets/bootstrap/js/bootstrap.min.js"></script>
    </head>

    <body class="uk-height-1-1">
         
         <!-- 这是用来触发抽屉式边栏的按钮 -->
		<button class="uk-button" data-uk-offcanvas="{target:'#my-id'}" id="viewAllUserInfo">查看所有人的信息</button>
		<a class="uk-button" href="signOut">注销登陆</a>
		<button class="uk-button" data-uk-offcanvas="{target:'#my-right-id'}" id="viewAllHistoryMessage">查看历史消息</button>
		
		<!-- 这是抽屉式边栏 -->
		<div id="my-id" class="uk-offcanvas">
		    <div class="uk-offcanvas-bar">
		        <div class="uk-panel uk-container" style="text-align: left;">
					<ul class="uk-list userItemUL">
                    </ul>
				</div>
		    </div>
		</div>
		
		<!-- 这是右边的抽屉式边栏 -->
		<div id="my-right-id" class="uk-offcanvas">
		    <div class="uk-offcanvas-bar uk-offcanvas-bar-flip">
			    <div class="right-container" id="rightSpan">
			    	<article class="uk-comment article-right" style="display: none;">
				        <header class="uk-comment-header">
				            <img class="uk-comment-avatar uk-border-rounded" src="" alt="" style="width: 50px;height: 50px;">
				            <h4 class="uk-comment-title" style="color: black;"></h4>
				            <div class="uk-comment-meta">
				            	<span class="uk-h4"></span>
				            </div>
				        </header>
				    </article>
				</div>
				<div style="position: static;">
			    	<button id="nextPage" value="0" class="uk-button">下一页</button>
				</div>
			</div>
		</div>
		
		
		<div class="uk-panel uk-panel-box uk-width-medium-1-1 constrain">
            <article class="uk-comment article001" style="display: none;">
		        <header class="uk-comment-header">
		            <img class="uk-comment-avatar uk-border-rounded" src="" alt="" style="width: 50px;height: 50px;">
		            <h4 class="uk-comment-title" style="color: black;"></h4>
		            <div class="uk-comment-meta">
		            	<span class="uk-h4"></span>
		            </div>
		        </header>
		    </article>
        </div>
		<textarea class="form-control" rows="3" id="message_input"></textarea>
		<button type="button" class="btn btn-default" style="float: right;" id="send_message">发送</button>
        <script type="text/javascript">
        	$(document).ready(function() {
        		/**
        		*解决接收推送的重复问题
        		*/
        		var toPushSpan = 0;
        		var webSocket = null;
        		init();
        		var toUser = {};
        		var fromUser = {}
        		function init() {
        			
        		    if ('WebSocket' in window) {  
        		        webSocket = new WebSocket('ws://localhost:8080/WebSocketChat/chat/server'); //建立连接点 WebSocketAndTomcat要换成自己的项目名
        		    } else if ('MozWebSocket' in window) {  
        		        webSocket = new MozWebSocket('ws://localhost:8080/WebSocketChat/chat/server'); //建立连接点 WebSocketAndTomcat要换成自己的项目名
        		    }

        			webSocket.onerror = function(event) {
        				onError(event);
        			};

        			webSocket.onopen = function(event) {
        				onOpen(event);
        			};

        			webSocket.onmessage = function(event) //接受客户端消息
        			{
        				onMessage(event);
        			};
        		}

        		
        		function onMessage(event) {
        			var json = JSON.parse(event.data);
        			//alert(JSON.stringify(json.historyMsg));
        			if(json.fromUser != null && json.toUser != null) {
        				fromUser = json.fromUser;
        				toUser = json.toUser;
        			}
        			
        			if(json.notRead != null) {
        				for(var i = 0; i < json.notRead.length; i++) {
        					var $art = $(".article001").clone();
							$art.attr("class", "uk-badge uk-badge-danger");
							$art.attr("style", "display: block;color:black;");
							$art.find("img").attr("src", toUser.userPicture);
							$art.find("h4").text(toUser.nickName);
							$art.find("span").text(json.notRead[i].chatMessage + ":" + json.notRead[i].messageSendTime);
							$art.appendTo(".constrain");
        				}
        				json.notRead = null;//使用之后赋值为 null
        			}
        			if(json.sendOne != null) {
       					var $art = $(".article001").clone();
						$art.attr("class", "uk-badge uk-badge-success");
						$art.attr("style", "display: block;color:black;");
						$art.find("img").attr("src", fromUser.userPicture);
						$art.find("h4").text(fromUser.nickName);
						$art.find("span").text(json.sendOne.chatMessage + ":" + json.sendOne.messageSendTime);
						
						$art.appendTo(".constrain");
        				json.sendOne = null;//使用之后赋值为 null
        			}
        			
        			if(json.OnlinePushToUserCM != null) {
       					var $art = $(".article001").clone();
						$art.attr("class", "uk-badge uk-badge-danger");
						$art.attr("style", "display: block;color:black;");
						$art.find("img").attr("src", toUser.userPicture);
						$art.find("h4").text(toUser.nickName);
						$art.find("span").text(json.OnlinePushToUserCM.chatMessage + ":" + json.OnlinePushToUserCM.messageSendTime);
						$art.appendTo(".constrain");
						
        				OnlinePushToUserCM = null;
        			}
        			
       				/*
       				接收到历史消息
       				*/
       				if(json.historyMsg != null) {
       					for(var i = 0; i < json.historyMsg.length; i++) {
       						if(json.historyMsg[i].fromUserId == toUser.userId) {
       							
       							var $art = $(".article001").clone();
   								$art.attr("class", "uk-badge uk-badge-danger");
   								$art.attr("style", "display: block;");
   								$art.find("img").attr("src", toUser.userPicture);
   								$art.find("h4").text(toUser.nickName);
   								$art.find("span").text(json.historyMsg[i].chatMessage);
   								$art.appendTo(".right-container");
       						} else {
       							
       							var $art = $(".article-right").clone();
   								$art.attr("class", "uk-badge uk-badge-success");
   								$art.attr("style", "display: block;color:black;");
   								$art.find("img").attr("src", "<s:property value='#session.user.userPicture' />");
   								$art.find("h4").text();
   								$art.find("span").text(json.historyMsg[i].chatMessage);
   								$art.appendTo(".right-container");
       						}
       					}
       					historyMsg = null;
       				}
        		}
        		
        		function onOpen(event) {
        			webSocket.send("<s:property value='#parameters.toUserId'/>"); //发送消息给服务器端
        		}

        		function onError(event) {
        			alert(event.data);
        		}

        		$("#send_message").on('click', function() {
        			webSocket.send($("#message_input").val()); //发送消息给服务器端
        			$("#message_input").val("");
        		});
        		
        	});
        </script>
        
        <li class="userItem" style="display: none;">
			<div>
				<img class="uk-border-circle" src="" alt="" style="width: 50px;height: 50px;" />
				<span></span>
			</div>
			<br />
		</li>
</body>
</html>