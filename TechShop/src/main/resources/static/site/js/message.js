
		var stompClient = null;

		function connect() {
			var socket = new SockJS('/chat');
			stompClient = Stomp.over(socket);

			stompClient.connect({}, function (frame) {
				console.log('Connected: ' + frame);

				// Subscribe vào địa chỉ /topic/messages để nhận tin nhắn từ server
				stompClient.subscribe('/topic/messages', function (response) {
					showMessage(JSON.parse(response.body));
					// loadAllMessages()
				});

				stompClient.subscribe('/admin/message', function (response) {
					loadAllMessages()
					// MyNotification.alertSucessWait("Thông báo", "Bạn có tin nhắn mới vui lòng kiểm tra!",false);
				});
				loadAllMessages()
			});
		}

		function loadAllMessages() {
			axios.get("/getAllMessages")
				.then(res => {
					$(".chat-logs").empty()
					res.data.forEach(message => {
						showMessage(message);
					})
				})
				.catch(error => {

				})
		}

		function showMessage(message) {
			var type = message.admin ?"user":"self"
			var str = "";
			str += "<div id='cm-msg-'class=\"chat-msg "+type+"\">";
			str += "          <span class=\"msg-avatar\">";
			str += "            <img src=\"/site/images/none.png\">";
			str += "          <\/span>";
			str += "          <div class=\"cm-msg-text\" style=\"white-space: normal;text-align:left\">";
			str += message.message;
			str += "          <\/div>";
			str += "        <\/div>";
			$(".chat-logs").append(str);
			$("#cm-msg-").hide().fadeIn(300);

			$(".chat-logs").stop().animate({ scrollTop: $(".chat-logs")[0].scrollHeight }, 1000);
		}

		function sendMessage() {
			var messageInput = document.getElementById("chat-input");
			var messageContent = messageInput.value;

			var currentUser =  /*[[${session.loggedUser}]]*/ null;

			console.log(currentUser )
			
		
			if (currentUser) {
			
				var message = {
				
					"username":currentUser.username,
					"authenticationType":currentUser.authenticationType,
					"message": messageContent
				};

				stompClient.send("/app/send", {}, JSON.stringify(message));
				messageInput.value = "";
			}

			else {
				MyNotification.alertError("Thông báo", "Vui lòng đăng nhập để thực hiện chức năng!")
			}

		}

		connect();
