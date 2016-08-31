/**
 * 
 */
 
 $(document).ready(function() {
	$.post('getAllHeadPicture!justDoIt.action', {}, function(data, textStatus) {
		 if(textStatus == "success") {
			 if(!data.register) {
				window.location.href = "/WebSocketChat/login.html";
				return;
			} else {
				for(var i = 0; i < data.pictures.length; i++) {
					var $art = $(".flag").clone();
					$art.attr("class", "uk-width-medium-1-5");
					$art.attr("style", "display: block;margin-buttom: 10px;");
					$art.find("a").attr("href", "dealChoiceHead!justDoIt.action?picture="+data.pictures[i]);
					$art.find("img").attr("src", data.pictures[i]);
					$art.appendTo(".uk-grid");
				}
			}
		 }
	 }, 'json');
 });
         