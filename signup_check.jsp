<%@page contentType="text/html;charset=utf-8" %>
<jsp:useBean id="pb" scope="request" class="bean.ProfileBean" />
<html lang="ja">

<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width,initial-scale=1.0">
	<link rel="icon" href="bairs.ico" type="image/x-icon">
	<link rel="shortcut icon" href="bairs.ico" type="image/x-icon">

	<!--ページ全体に共通のcss-->
	<link rel="stylesheet" href="share.css">

	<!--このページだけに共通のcss-->
	<link rel="stylesheet" href="signup_check_share.css">

	<!--PC用のcss-->
	<link rel="stylesheet" media="(min-width: 601px)" href="signup_check_pc.css">

	<!--スマホ用のcss-->
	<link rel="stylesheet" media="(max-width: 600px)" href="signup_check_mobile.css">

	<!--タイトル-->
	<title>確認画面</title>

	<style>
		#icon-image {
			background-color: <%= pb.getColor() %>;
		}
	</style>
</head>

<body>
	<div class="profile">
		<h1>登録確認</h1>
		<div id="profile-img">
			<img id="icon-image" src="image/icon/<%= pb.getIcon() %>.png" alt="icon">
		</div>
		<div>
			<div>ニックネーム</div>
			<div id="name">
				<%= pb.getName() %>
			</div>
		</div>
		<div>
			<span>学籍番号:</span>
			<% String snum; if(pb.getSnumpublic()) { snum=String.format("%d", pb.getSnum()); } else { snum="非公開" ; } %>
			<span><%= snum %></span>
		</div>
		<div>
			<span>性別:</span>
			<span><%= pb.getGender() %></span>
		</div>
		<div>
			<sapn>血液型:</sapn>
			<span><%= pb.getBloodtype()%></span>
		</div>
		<div>
			<div>趣味</div>
			<ul id="hobby">
			<% String[] hobby=pb.getHobby(); 
            for(int i=0; i < hobby.length; i++){ %>
				<li><%= hobby[i] %></li>
			<% } %>
			</ul>
		</div>
		<div>
			<div>自己紹介</div>
			<div id="pr"><%= pb.getIntroduction() %></div>
		</div>
		<div>
			<span>Instagram:</span>
			<span><%= pb.getInstagram() %></span>
		</div>
		<div>
			<span>X(旧Twitter):</span>
			<span><%= pb.getX() %></span>
		</div>
		<div>
			<span>Tiktok:</span>
			<span><%= pb.getTiktok() %></span>
		</div>
		<div class="center margin">
            <form id="form"></form>
            <button id="register" type="button" onclick="register()">登録</button>
		</div>
	</div>

	<audio id="ClickSound" src="sound/click_sound.mp3"></audio>

	<script src="sound.js"></script>
	<script src="signup_check.js"></script>

</body>

</html>