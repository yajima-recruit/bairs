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
    <link rel="stylesheet" href="passreset3_share.css">

    <!-- タイトル -->
    <title>パスワードリセット</title>
</head>

<body class="middle">
    <!--このdivはコンテンツを縦並びに直すためのもの-->
    <div>
        <h1 class="center">パスワードリセット</h1>
        <div id="progress">(3/3)</div>

        <div class="middle">
            <div>
                <div class="block">
                    <div>ユーザーID</div>
                    <div><%= request.getAttribute("userid") %></div>
                </div>
                <div class="block">
                    <label for="pass" class="item">パスワード</label><br>
                    <input type="password" id="pass" oninput="this.value=this.value.replace(/[^\x01-\x7E]/g, '')">
                    <button id="btn_passview">表示</button>
                </div>
                <div class="block">
                    <label for="pass-again">確認</label><br>
                    <input type="password" name="pass-again" id="pass-again"
                        oninput="this.value=this.value.replace(/[^\x01-\x7E]/g, '')">
                    <button id="btn_passview_again">表示</button>
                </div>
            </div>
        </div>

        <!-- エラーメッセージ -->
        <div id="error-message" class="center"></div>

        <div class="center">
            <button type="button" onclick="reset()">次へ</button>
        </div>
    </div>

    <!--ここから先はaudio関連-->
    <audio id="ClickSound" src="sound/click_sound.mp3"></audio>

    <script src="pass_view_signup.js"></script>
    <script src="sound.js"></script>
    <script src="error.js"></script>
    <script src="passreset3.js"></script>
</body>

</html>