async function submit() {
    // ユーザーIDの取得
    var answer = document.getElementById("answer").value;
    var data = {
        answer: answer
    };

    var message;
    // fetchを使用してサーブレットに名前を送信
    await fetch('/bairs/reset?state=verification', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.text(); // テキストデータを取得
    })
    .then(result => {
        // サーバーからの応答に基づいて条件分岐
        message = result.trim();
    })
    .catch(error => {
        console.error('Error:', error);
    });

    if(message){
        if(message==="true"){
            document.getElementById("hidden").value = "move2";
            document.getElementById("form").submit();
        } else if(message==="hack"){
            document.getElementById("hidden").value = "hack";
            document.getElementById("form").submit();
        } else {
            // エラーメッセージを表示する
            document.getElementById('error-message').innerHTML = '<strong>' + message + '</strong>';
        }
    }
}