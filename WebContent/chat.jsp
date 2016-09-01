<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ page import="com.chuangyejia.bean.User" %>
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
		<a class="uk-button" href="/ChatNoRefresh/SignOut">注销登陆</a>
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
		<textarea class="form-control" rows="3" id="content"></textarea>
		<button type="button" class="btn btn-default" style="float: right;" id="send">发送</button>
        <script type="text/javascript">
        	$(document).ready(function() {
        		/**
        		*解决接收推送的重复问题
        		*/
        		var toPushSpan = 0;
        		var webSocket = null;
        		init();
        		var toUser = {};
        		
        		function init() {
        			
        		    if ('WebSocket' in window) {  
        		        webSocket = new WebSocket('ws://localhost:8080/ChuangYeJia/chat/server'); //建立连接点 WebSocketAndTomcat要换成自己的项目名
        		    } else if ('MozWebSocket' in window) {  
        		        webSocket = new MozWebSocket('ws://localhost:8080/ChuangYeJia/chat/server'); //建立连接点 WebSocketAndTomcat要换成自己的项目名
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
        			toUser = json.toUser;
        			if(json.notRead != null && json.first != null) {
        				for(var i = 0; i < json.notRead.length; i++) {
        					var article = $('<article></article>');
        					article.attr("class", "uk-comment toUser_article");
        					var header = $('<header></header>');
        					header.attr("class", "uk-comment-header");
        					header.appendTo(article);
        					var img = $('<img />');
        					img.attr("class", "uk-comment-avatar uk-border-rounded toUser_photo");
        					img.attr("src", json.toUser.userPhoto);
        					img.attr("alt", "图片正在玩命加载中...");
        					img.attr("style", "width: 50px; height: 50px;");
        					img.appendTo(header);
        					var h4 = $('<h4></h4>');
        					h4.attr("class", "uk-comment-title");
        					h4.text(json.toUser.userNickName + ":" + json.notRead[i].messageSendTime);
        					h4.appendTo(header);
        					var div = $('<div></div>');
        					div.attr("class", "uk-comment-meta");
        					div.appendTo(header);
        					var span = $('<span></span>');
        					span.attr("class", "uk-h4");
        					span.text(json.notRead[i].chatMessage);
        					span.appendTo(div);
        					
        					article.appendTo($("#message_panel"));
        				}
        				json.notRead = null;//使用之后赋值为 null
        				
        				/*
        				接收到历史消息
        				*/
        				if(json.historyMsg != null) {
        					for(var i = 0; i < json.historyMsg.length; i++) {
        						if(json.historyMsg[i].fromUserId == json.toUser.userId) {
        							var article = $('<article></article>');
        							article.attr("class", "uk-comment");
        							var header = $('<header></header>');
        							header.attr("class", "uk-comment-header");
        							header.appendTo(article);
        							var img = $('<img />');
        							img.attr("class", "uk-comment-avatar uk-border-rounded");
        							img.attr("src", json.toUser.userPhoto);
        							img.attr("alt", "图片正在玩命加载中...");
        							img.attr("style", "width: 50px; height: 50px;");
        							img.appendTo(header);
        							var h4 = $('<h4></h4>');
        							h4.attr("class", "uk-comment-title");
        							h4.text(json.toUser.userNickName + ":" + json.historyMsg[i].messageSendTime);
        							h4.appendTo(header);
        							var div = $('<div></div>');
        							div.attr("class", "uk-comment-meta");
        							div.appendTo(header);
        							var span = $('<span></span>');
        							span.attr("class", "uk-h4");
        							span.text(json.historyMsg[i].chatMessage);
        							span.appendTo(div);
        							
        							article.appendTo($("#his_message_panel"));
        						} else {

        							var article = $('<article></article>');
        							article.attr("class", "uk-comment uk-comment-primary");
        							var header = $('<header></header>');
        							header.attr("class", "uk-comment-header");
        							header.appendTo(article);
        							var img = $('<img />');
        							img.attr("class", "uk-comment-avatar uk-border-rounded");
        							img.attr("src", '<s:property value="#session.user.userPhoto"/>');
        							img.attr("alt", "图片正在玩命加载中...");
        							img.attr("style", "width: 50px; height: 50px;float: right;");
        							img.appendTo(header);
        							var h4 = $('<h4></h4>');
        							h4.attr("class", "uk-comment-title");
        							h4.attr("style", "float: right;");
        							h4.text("我" + ":" + json.historyMsg[i].messageSendTime);
        							h4.appendTo(header);
        							var div = $('<div></div>');
        							div.attr("class", "uk-comment-meta uk-flex-right");
        							div.appendTo(header);
        							var span = $('<span></span>');
        							span.attr("class", "uk-h4");
        							span.text(json.historyMsg[i].chatMessage);
        							span.appendTo(div);
        							
        							
        							article.appendTo($("#his_message_panel"));
        						}
        					}
        				}
        				
        			}
        			
        			if(json.sendOne != null) {
        				
        				var article = $('<article></article>');
        				article.attr("class", "uk-comment uk-comment-primary");
        				var header = $('<header></header>');
        				header.attr("class", "uk-comment-header");
        				header.appendTo(article);
        				var img = $('<img />');
        				img.attr("class", "uk-comment-avatar uk-border-rounded");
        				img.attr("src", '<s:property value="#session.user.userPhoto"/>');
        				img.attr("alt", "图片正在玩命加载中...");
        				img.attr("style", "width: 50px; height: 50px;float: right;");
        				img.appendTo(header);
        				var h4 = $('<h4></h4>');
        				h4.attr("class", "uk-comment-title");
        				h4.attr("style", "float: right;");
        				h4.text("我" + ":" + json.sendOne.messageSendTime);
        				h4.appendTo(header);
        				var div = $('<div></div>');
        				div.attr("class", "uk-comment-meta uk-flex-right");
        				div.appendTo(header);
        				var span = $('<span></span>');
        				span.attr("class", "uk-h4");
        				span.text(json.sendOne.chatMessage);
        				span.appendTo(div);
        				
        				article.appendTo($("#message_panel"));
        				
        				json.sendOne = null;//使用之后赋值为null
        			}

        			if(json.pushToUser != null && toPushSpan === 0) {
        			  /**
        				*解决重复问题
        				*/
        				toPushSpan = 1;
        				var article = $('<article></article>');
        				article.attr("class", "uk-comment");
        				var header = $('<header></header>');
        				header.attr("class", "uk-comment-header");
        				header.appendTo(article);
        				var img = $('<img />');
        				img.attr("class", "uk-comment-avatar uk-border-rounded");
        				img.attr("src", toUser.userPhoto);
        				img.attr("alt", "图片正在玩命加载中...");
        				img.attr("style", "width: 50px; height: 50px;");
        				img.appendTo(header);
        				var h4 = $('<h4></h4>');
        				h4.attr("class", "uk-comment-title");
        				h4.text(json.toUser.userNickName + ":" + json.pushToUser.messageSendTime);
        				h4.appendTo(header);
        				var div = $('<div></div>');
        				div.attr("class", "uk-comment-meta");
        				div.appendTo(header);
        				var span = $('<span></span>');
        				span.attr("class", "uk-h4");
        				span.text(json.pushToUser.chatMessage);
        				span.appendTo(div);
        				
        				article.appendTo($("#message_panel"));
        			} else {
        				/**
        				*解决重复问题
        				*/
        				toPushSpan = 0;
        			}
        			if(json.toUser != null) {
        				$(".toUser_photo").attr("src", json.toUser.userPhoto);
        				$(".toUser_nickname").text(json.toUser.userNickName);
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