var over = document.getElementById('hr').getBoundingClientRect();
var under = document.getElementById('under-element').getBoundingClientRect();
var target = document.getElementById('post-list');
var target_height = (Math.floor(under.top - 5)) - (over.bottom + 8);
target.style.height = target_height + 'px';

var intervaltime = 10; //秒数間隔 10秒
var grobar_counter = 0;
var selected_element = "";

function startTimer() {
    grobar_counter = intervaltime;
    var intervalId;

    function updateCount() {
        grobar_counter--;

        if (grobar_counter <= 0) {
            stopTimer();
        }
    }

    function stopTimer() {
        clearInterval(intervalId);
    }

    intervalId = setInterval(updateCount, 1000);
}

// 現在のURLからプロトコルとホストを抽出
function extractProtocolAndHost() {
    var parser = document.createElement('a');
    parser.href = window.location.href;

    var protocol = parser.protocol; // プロトコル (http: or https:)
    var host = parser.hostname + (parser.port ? ':' + parser.port : ''); // ホスト

    return { protocol: protocol, host: host };
}

var socket;

document.addEventListener("DOMContentLoaded", function () {
    // 現在のURLからプロトコルとホストを抽出
    var extractedComponents = extractProtocolAndHost();

    // WebSocketのURLを構築
    var websocketProtocol = (extractedComponents.protocol === 'https:') ? 'wss:' : 'ws:';
    var websocketUrl = `${websocketProtocol}//${extractedComponents.host}/bairs/websocket`;

    // WebSocketの接続
    socket = new WebSocket(websocketUrl);

    socket.addEventListener('open', (event) => {
        console.log('WebSocket connection opened.');
    });

    socket.addEventListener('message', (event) => {
        displayMessage(event.data);
    });

    socket.addEventListener('close', (event) => {
        console.log('WebSocket connection closed.');
    });
});

var selectText = "";
function selectItem(element) {
    // 他の行の選択を解除
    var allItems = document.querySelectorAll('.scrollable-item');
    allItems.forEach(function (item) {
        if (item !== element) {
            item.classList.remove('selected-item');
        }
    });

    // 選択された行に selected-item クラスを追加
    element.classList.toggle('selected-item');
    selected_element = element;

    // 選択された行の内容をコンソールに表示
    if (element.classList.contains('selected-item')) {
        selectText = element.textContent;
    } else {
        selectText = ""
    }
}

function sendPost() {
    if (selectText === "") {
        return;
    }

    // 制限時間内に連続してスタンプを送信しないようにする
    if (grobar_counter > 0) {
        alert('スタンプはあと' + grobar_counter + '秒で送信できます。');
        return;
    }

    socket.send(selectText);

    startTimer();

    selectItem(selected_element);
}

function displayMessage(data) {
    var postList = document.getElementById("post-list");
    
    var scrollPosition = 0;
    var lastPost;
    var lastPostPosition = 0;

    try{
        // スクロール位置に対応する座標を取得
        scrollPosition = postList.scrollTop

        // 一番下の要素のY座標を取得
        lastPost = postList.lastElementChild;
        lastPostPosition = lastPost.offsetTop;
    } catch {
    }

    // 差を計算
    var difference = lastPostPosition - scrollPosition;

    var toggle = difference < target_height;

    var dataArray = data.split(":");
    var userid = dataArray[0];
    var username = dataArray[1];
    var message = dataArray[2];

    var now = new Date();
    var date = now.getFullYear() + '-' + ('0' + (now.getMonth() + 1)).slice(-2) + '-' + ('0' + now.getDate()).slice(-2);
    var time = ('0' + now.getHours()).slice(-2) + ':' + ('0' + now.getMinutes()).slice(-2);

    // 新しい li 要素を作成
    var newLi = document.createElement("li");
    newLi.classList.add("post");

    // li 要素内に含まれる div.post の中身
    var postDiv = document.createElement("div");

    var postInfoDiv = document.createElement("div");
    postInfoDiv.classList.add("post-info");
    var userIdSpan = document.createElement("span");
    userIdSpan.classList.add("spanleft");
    var userIdButton = document.createElement("button");
    userIdButton.classList.add("btn");
    userIdButton.textContent = userid;
    userIdButton.onclick = function(){ move_profile(userid) };
    userIdSpan.appendChild(userIdButton);
    var userNameSpan = document.createElement("span");
    userNameSpan.textContent = username;
    postInfoDiv.appendChild(userIdSpan);
    postInfoDiv.appendChild(userNameSpan);

    var postTextDiv = document.createElement("div");
    postTextDiv.classList.add("post-text");
    postTextDiv.textContent = message;

    var postTimeDiv = document.createElement("div");
    postTimeDiv.classList.add("post-info", "post-time");
    var dateSpan = document.createElement("span");
    dateSpan.classList.add("spanleft");
    dateSpan.textContent = date;
    var timeSpan = document.createElement("span");
    timeSpan.textContent = time;
    postTimeDiv.appendChild(dateSpan);
    postTimeDiv.appendChild(timeSpan);

    postDiv.appendChild(postInfoDiv);
    postDiv.appendChild(postTextDiv);
    postDiv.appendChild(postTimeDiv);

    // li 要素に div.post を追加
    newLi.appendChild(postDiv);

    // ul 要素に新しい li 要素を追加
    postList.appendChild(newLi);

    if(toggle) scrollToBottom();
}

function scrollToBottom() {
    var scrollableArea = document.getElementById("post-list");
    scrollableArea.scrollTop = scrollableArea.scrollHeight;
}

function submit(id) {
    if (socket) {
        socket.close(); // ウェブソケットの通信をクローズ
    }
    document.getElementById(id).submit();
}

function move_profile(userid) {
    document.getElementById("user-profile").value = userid;
    document.getElementById("move-profile").submit();
}