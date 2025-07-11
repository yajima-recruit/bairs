<%@page contentType="text/html;charset=utf-8" %>
<html lang="ja">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="bairs.ico" type="image/x-icon">
    <link rel="shortcut icon" href="bairs.ico" type="image/x-icon">

    <!-- ページ全体に共通のcss -->
    <link rel="stylesheet" href="share.css">

    <!-- このページだけに共通のcss -->
    <link rel="stylesheet" href="macth_share.css">

    <!--PC用のcss-->
    <link rel="stylesheet" media="(min-width: 601px)" href="match_pc.css">

    <!--スマホ用のcss-->
    <link rel="stylesheet" media="(max-width: 600px)" href="match_mobile.css">

    <title>マッチング</title>

</head>

<body>
    <div>
        <h1>マッチング</h1>
        <% 
            String[][] matchResult = (String[][]) request.getAttribute("matchResult");
            if(matchResult != null && matchResult.length > 0 && matchResult[0] != null && matchResult[0][0] != null) {
        %>
        <table>
            <tr>
                <th>ユーザーID</th>
                <th>ニックネーム</th>
                <th>一致数</th>
            </tr>
        <% for (int i = 0; i < matchResult.length; i++) { %>
            <tr>
                <td class="id"><button class="btn" onclick="subumit('user<%= i %>')"><%= matchResult[i][0] %></button></td>
                <td class="info">
                    <form id="user<%= i %>" action="profile" method="post">
                        <input type="hidden" name="state" value="profile">
                        <input type="hidden" name="userid" value="<%= matchResult[i][0] %>">
                    </form><%= matchResult[i][1] %>
                </td>
                <td class="hit info"><%= matchResult[i][2] %>個</td>
            </tr>
        <% } %>
        </table>
        <% } else { %>
        <p style="position: absolute; top: 50%; left: 50%; transform: translate(-50%, -50%);">見つかりませんでした</p>
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

    <script src="match.js"></script>
</body>

</html>