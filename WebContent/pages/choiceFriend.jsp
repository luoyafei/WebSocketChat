<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>请选择好友</title>
<link rel="stylesheet" href="../assets/uikit/uikit.css">
<link rel="stylesheet" href="../assets/bootstrap/css/bootstrap.min.css" />
<script src="../assets/uikit/jquery.js"></script>
<script src="../assets/uikit/uikit.js"></script>
<script src="../assets/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>

	
	<button class="uk-button uk-button-large" data-uk-toggle="{target:'#myFriends'}">我的好友</button>
	<button class="uk-button uk-button-large" data-uk-toggle="{target:'#others'}">陌生人</button>
	
	<div aria-hidden="true" id="myFriends" class="uk-panel uk-panel-box uk-hidden">
		<div class="uk-container uk-container-center uk-margin-top uk-margin-large-bottom">
			<div class="uk-grid" data-uk-grid-margin style="text-align: center;">
				<s:iterator var="x" value="#request.friends">
					<div class="uk-width-medium-1-5 uk-text-center">
			             <div class="uk-thumbnail uk-overlay-hover uk-border-circle">
			                 <figure class="uk-overlay">
			                     <img class="uk-border-circle" width="200" height="200" src='<s:property value="userPicture" />' alt="">
			                     <figcaption class="uk-overlay-panel uk-overlay-background uk-flex uk-flex-center uk-flex-middle uk-text-center uk-border-circle">
			                         <div>
			                             <a href="chat.jsp?toUserId=<s:property value="userId" />" class="uk-icon-button"><i class="mouse-pointer" style="color:#3399FF;"></i></a>
			                         </div>
			                     </figcaption>
			                 </figure>
			             </div>
			             <h2 class="uk-margin-bottom-remove"><s:property value="nickName" /></h2>
			             <p class="uk-text-large uk-margin-top-remove uk-text-muted">20<s:property value="createDate"/></p>
			         </div>
				</s:iterator>
			</div>
		</div>
	</div>
	
	<div aria-hidden="true" id="others" class="uk-panel uk-panel-box uk-hidden">
		<div class="uk-container uk-container-center uk-margin-top uk-margin-large-bottom">
			<div class="uk-grid" data-uk-grid-margin style="text-align: center;">
				<s:iterator var="x" value="#request.others">
					<div class="uk-width-medium-1-5 uk-text-center">
			             <div class="uk-thumbnail uk-overlay-hover uk-border-circle">
			                 <figure class="uk-overlay">
			                     <img class="uk-border-circle" width="200" height="200" src='<s:property value="userPicture" />' alt="">
			                     <figcaption class="uk-overlay-panel uk-overlay-background uk-flex uk-flex-center uk-flex-middle uk-text-center uk-border-circle">
			                         <div>
			                             <a href="chat.jsp?toUserId=<s:property value="userId" />" class="uk-icon-button"></a>
			                         </div>
			                     </figcaption>
			                 </figure>
			             </div>
			             <h2 class="uk-margin-bottom-remove"><s:property value="nickName" /></h2>
			             <p class="uk-text-large uk-margin-top-remove uk-text-muted">20<s:property value="createDate"/></p>
			         </div>
				</s:iterator>
			</div>
		</div>
	</div>
	
</body>
</html>