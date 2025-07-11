<%@page contentType="text/html;charset=utf-8" %>
<html lang="ja">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="bairs.ico" type="image/x-icon">
    <link rel="shortcut icon" href="bairs.ico" type="image/x-icon">

    <!-- ページ全体に共通の CSS -->
    <link rel="stylesheet" href="share.css">

    <!-- このページだけに共通の CSS -->
    <link rel="stylesheet" href="passreset2_share.css">

    <!-- タイトル -->
    <title>パスワードリセット</title>
</head>

<body class="middle">
    <!--このdivはコンテンツを縦並びに直すためのもの-->
    <div>
        <h1 class="center">パスワードリセット</h1>
        <div id="progress">(2/3)</div>

        <div>秘密の質問</div>
        <hr>
        <div><%= request.getAttribute("question") %></div>
        <hr>
        <div>質問の答え</div>
        <input id="answer" name="answer" type="text" maxlength="60">
        
        <!-- エラーメッセージ -->
        <div id="error-message" class="center"></div>

        <div class="center">
            <button type="button" onclick="submit()">次へ</button>
        </div>

        <form id="form" action="/bairs/reset" method="post">
            <input id="hidden" type="hidden" name="state">
        </form>
    </div>

    <!--ここから先はaudio関連-->
    <audio id="ClickSound" src="sound/click_sound.mp3"></audio>
    <script src="sound.js"></script>

    <script src="passreset2.js"></script>
</body>

</html>