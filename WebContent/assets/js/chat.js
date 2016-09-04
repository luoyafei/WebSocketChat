/**
 * 
 */

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
        		        webSocket = new WebSocket('ws://localhost:8080/WebSocketChat/luoChat'); //建立连接点 WebSocketAndTomcat要换成自己的项目名
        		    } else if ('MozWebSocket' in window) {  
        		        webSocket = new MozWebSocket('ws://localhost:8080/WebSocketChat/luoChat'); //建立连接点 WebSocketAndTomcat要换成自己的项目名
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
   								$art.find("h4").text(fromUser.nickName);
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