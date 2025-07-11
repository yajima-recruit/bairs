<%@page contentType="text/html;charset=utf-8" %>
<html lang="ja">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="bairs.ico" type="image/x-icon">
	<link rel="shortcut icon" href="bairs.ico" type="image/x-icon">

    <link rel="stylesheet" href="share.css">
    <link rel="stylesheet" href="chat_share.css">
    <!-- PC用のcss -->
    <link rel="stylesheet" media="(min-width: 601px)" href="chat_pc.css">
    <!-- スマホ用のcss -->
    <link rel="stylesheet" media="(max-width: 600px)" href="chat_mobile.css">
    <title>チャット</title>
</head>

<body>
    <h1>チャット</h1>
    <hr id="hr">
    <ul id="post-list"></ul>
    <form id="move-profile" action="/bairs/profile" method="post">
        <input type="hidden" name="state" value="profile">
        <input id="user-profile" type="hidden" name="userid">
    </form>
    <div id="under-element">
        <div class="scrol-area">
            <div class="scrollable-area">
                <div><strong>あいさつ</strong></div>
                <div class="scrollable-item" onclick="selectItem(this)">こんにちは</div>
                <div class="scrollable-item" onclick="selectItem(this)">よろしく～</div>
                <div class="scrollable-item" onclick="selectItem(this)">またねー</div>
                <div class="scrollable-item" onclick="selectItem(this)">初めまして</div>
                <div class="scrollable-item" onclick="selectItem(this)">やっほ～！</div>

                <hr>

                <div><strong>汎用性高め</strong></div>
                <div class="scrollable-item" onclick="selectItem(this)">マジ</div>
                <div class="scrollable-item" onclick="selectItem(this)">ヤバ</div>
                <div class="scrollable-item" onclick="selectItem(this)">あー</div>
                <div class="scrollable-item" onclick="selectItem(this)">ww</div>

                <hr>

                <div><strong>質問</strong></div>
                <div class="scrollable-item" onclick="selectItem(this)">どこにいる？</div>
                <div class="scrollable-item" onclick="selectItem(this)">ちょうしどう？</div>
                <div class="scrollable-item" onclick="selectItem(this)">今、暇？</div>
                <div class="scrollable-item" onclick="selectItem(this)">teams行っていい？</div>
                <div class="scrollable-item" onclick="selectItem(this)">プロフ見て</div>
    
                <hr>
                
                <div><strong>回答</strong></div>
                <div class="scrollable-item" onclick="selectItem(this)">学校</div>
                <div class="scrollable-item" onclick="selectItem(this)">家</div>
                <div class="scrollable-item" onclick="selectItem(this)">うん</div>
                <div class="scrollable-item" onclick="selectItem(this)">ううん</div>
                <div class="scrollable-item" onclick="selectItem(this)">いいよ</div>
                <div class="scrollable-item" onclick="selectItem(this)">普通</div>
                <div class="scrollable-item" onclick="selectItem(this)">ダメ</div>
            </div>
        </div>
        <div class="button-area">
            <button id="scroll-btn" onclick="scrollToBottom()"><img id="bottom_vector" src="image/bottom_vector.png"></button>
            <button type="button" id="submit-btn" onclick="sendPost()">投稿</button>
        </div>

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

    <script src="chat.js"></script>

</body>

</html>