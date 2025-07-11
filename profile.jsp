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
	<link rel="stylesheet" href="profile_share.css">

	<!--PC用のcss-->
	<link rel="stylesheet" media="(min-width: 601px)" href="profile_pc.css">

	<!--スマホ用のcss-->
	<link rel="stylesheet" media="(max-width: 600px)" href="profile_mobile.css">

	<!--タイトル-->
	<title><%= request.getAttribute("who") %>のプロフィール</title>

	<style>
		#icon-image {
			background-color: <%= pb.getColor() %>;
		}
	</style>
</head>

<body>
	<div class="profile">
		<h1>プロフィール</h1>
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
			<span>血液型:</span>
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
		<% if("自分".equals(request.getAttribute("who"))){ %>
		<div class="center margin">
            <form id="edit-form" action="/bairs/edit_profile" method="post"><input type="hidden" name="state" value="move"></form>
            <button id="edit" class="button" type="button" onclick="subumit('edit-form')">プロフィール編集</button>
		</div>
		<div class="center margin">
            <button class="button" type="button" onclick="ajax_send('logout')">ログアウト</button>
		</div>
		<div class="center margin">
            <button class="button" type="button" onclick="confirmation('deleteaccount')">アカウント削除</button>
		</div>
		<% } %>

		<div class="space"></div>

		<form id="home" action="/bairs/home" method="post"></form>
		<form id="match" action="/bairs/match" method="post"></form>
		<form id="chat" action="/bairs/chat" method="post"></form>
		<form id="profile" action="/bairs/profile" method="post"><input type="hidden" name="state" value="myprofile"></form>
	</div>

	<div id="bottom-element">
		<button class="under-button" onclick="subumit('home')">
			<img class="formbutton" src="image/home.png" alt="ホーム">
		</button>
		<button class="under-button" onclick="subumit('match')">
			<img class="formbutton" src="image/matching.png" alt="マッチング">
		</button>
		<button class="under-button" onclick="subumit('chat')">
			<img class="formbutton" src="image/keijibann.png" alt="掲示板">
		</button>
		<button class="under-button" onclick="subumit('profile')">
			<img class="formbutton" src="image/profile.png" alt="プロフィール">
		</button>
	</div>

	<audio id="ClickSound" src="sound/click_sound.mp3"></audio>

	<script src="sound.js"></script>
	<script src="profile.js"></script>

</body>

</html>