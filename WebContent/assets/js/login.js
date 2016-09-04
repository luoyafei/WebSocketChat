/**
 * 
 */
jQuery(document).ready(function($) {
	$("#login").bind("click", function() {
		if($("#nickName").val().trim()=="" || $("#password").val().trim()=="") {
   			alert("你在逗我吗？赶紧填完滚去聊天！");
   		} else {
			$.post('SignIn!login.action', {
				nickName : $("#nickName").val().trim(),
				password : $("#password").val().trim(),
				span : true
			}, function(data, textStatus){
				if(textStatus == "success") {
					if(!data.result) {
						alert(data.reason);
					} else {
						window.location.href = "/WebSocketChat/pages/choicePicture.html";
						return;
					}
				} else {
					alert("请刷新重新试试！");
				}
			}, 'json');
		}
	});
});