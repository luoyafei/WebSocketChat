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
        <link rel="stylesheet" href="../assets/uikit/uikit.css">
        <link rel="stylesheet" href="../assets/uikit/slideshow.css">
        <link rel="stylesheet" href="../assets/bootstrap/css/bootstrap.min.css" />
        <script src="../assets/uikit/jquery.js"></script>
        <script src="../assets/uikit/uikit.js"></script>
        <script src="../assets/uikit/slideshow.js"></script>
        <script src="../assets/bootstrap/js/bootstrap.min.js"></script>
    </head>

    <body class="uk-height-1-1">
         
         <!-- button-->
		<!-- <button class="uk-button" data-uk-offcanvas="{target:'#my-id'}" id="viewAllUserInfo"></button> -->
		<a class="uk-button" href="signOut">注销登陆</a>
		<button class="uk-button" data-uk-offcanvas="{target:'#my-right-id'}" id="viewAllHistoryMessage">查看历史消息</button>
		
		<!-- left -->
		<!-- <div id="my-id" class="uk-offcanvas">
		    <div class="uk-offcanvas-bar">
		        <div class="uk-panel uk-container" style="text-align: left;">
					<ul class="uk-list userItemUL">
                    </ul>
				</div>
		    </div>
		</div> -->
		
		<!-- left -->
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
			    	<!-- <button id="nextPage" value="0" class="uk-button">下一页</button> -->
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
        
	<script type="text/javascript" src="../assets/js/chat.js"></script>
</body>
</html>