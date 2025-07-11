<%@page contentType="text/html;charset=utf-8" %>
<html lang="ja">

<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="icon" href="bairs.ico" type="image/x-icon">
	<link rel="shortcut icon" href="bairs.ico" type="image/x-icon">

	<!--ページ全体に共通のcss-->
	<link rel="stylesheet" href="share.css">

	<!--このページだけに共通のcss-->
	<link rel="stylesheet" href="home_share.css">

	<!--PC用のcss-->
	<link rel="stylesheet" media="(min-width: 601px)" href="home_pc.css">

	<!--スマホ用のcss-->
	<link rel="stylesheet" media="(max-width: 600px)" href="home_mobile.css">

	<title>ホーム画面</title>
</head>

<body class="middle">
	<div>
		<h1 class="center">Bairs</h1>
		<%
		String[][] data = (String[][])request.getAttribute("data");
		if(data!=null){
			for(int i = 0; i < data.length; i++){
		%>
		<button class="card" onclick="submit('form<%= i+1 %>')">
			<table>
				<tr>
					<td class="icon">
						<img src="image/icon/<%= data[i][1] %>.png" style="background-color: <%= data[i][2] %>;">
					</td>
					<td>
						<div>ニックネーム</div>
						<div><%= data[i][3] %></div>
						<div class="space"></div>
						<div>性別</div>
						<div><%= data[i][4] %></div>
					</td>
				</tr>
			</table>
		</button>
		<form id="form<%= i+1 %>" action="/bairs/profile" method="post">
			<input type="hidden" name="state" value="profile">
			<input type="hidden" name="userid" value="<%= data[i][0] %>">
		</form>
		<% 
			}
		}
		%>

		<div class="space1"></div>

		<form id="home" action="/bairs/home" method="post"></form>
		<form id="match" action="/bairs/match" method="post"></form>
		<form id="chat" action="/bairs/chat" method="post"></form>
		<form id="profile" action="/bairs/profile" method="post"><input type="hidden" name="state" value="myprofile"></form>
	</div>

	<div id="bottom-element">
		<button class="under-button" onclick="submit('home')">
			<img class="formbutton" src="image/home.png" alt="ホーム">
		</button>
		<button class="under-button" onclick="submit('match')">
			<img class="formbutton" src="image/matching.png" alt="マッチング">
		</button>
		<button class="under-button" onclick="submit('chat')">
			<img class="formbutton" src="image/keijibann.png" alt="掲示板">
		</button>
		<button class="under-button" onclick="submit('profile')">
			<img class="formbutton" src="image/profile.png" alt="プロフィール">
		</button>
	</div>

	<audio id="ClickSound" src="sound/click_sound.mp3"></audio>
	<script src="sound.js"></script>

	<script src="home.js"></script>

</body>

</html>