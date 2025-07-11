async function search() {
    // 学籍番号・パスワードの入力確認
    var message;
    if (await error("userid") && await error("pass")) {
        // 名前の取得
        var userid = document.getElementById("userid").value;

        // パスワードの取得
        var pass = document.getElementById("pass").value;

        // データを作る
        var data = {
            userid: userid,
            pass: pass
        };

        // fetchを使用してサーブレットに名前とパスワードを送信
        await fetch('/bairs/login?state=search', {
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
            message = result.trim();
        })
        .catch(error => {
            console.error('Error:', error);
        });

    }

    if(message){
        if(message==="true"){
            await fetch('/bairs/login?state=move', {
                method: 'POST'
            })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.text(); // テキストデータを取得
            })
            .then(result => {
                // サーバーからの応答に基づいて条件分岐
                if (result.trim() === 'true') {
                    form.action = '/bairs/home';
                    form.method = 'POST';
                    
                    // フォームを送信
                    form.submit();
                } else {
                    // エラーメッセージを表示する
                    document.getElementById('error-message').innerHTML = '<strong>' + result.trim() + '</strong>';
                }
            })
            .catch(error => {
                console.error('Error:', error);
            });
        }
        else if(message==="hack"){
            form.action = '/bairs/login?state=hack';
            form.method = 'POST';
                    
            // フォームを送信
            form.submit();
        }
        else {
            document.getElementById('error-message').innerHTML = '<strong>' + message + '</strong>';
        }
    }
}

