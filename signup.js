async function search() {
    // ユーザーID・パスワードの入力確認
    var bool = false;
    if(error("userid")){
        // ユーザーIDの取得
        var userid = document.getElementById("userid").value;

        // fetchを使用してサーブレットに名前を送信
        await fetch('/bairs/signup?state=search&userid=' + encodeURIComponent(userid), {
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
                // ページの移動の許可
                bool = true;
            } else {
                // エラーメッセージを表示する
                document.getElementById('error-message').innerHTML = '<strong>'+ result.trim() +'</strong>';
            }
        })
        .catch(error => {
            console.error('Error:', error);
        });

        // サーバーからの重複結果とパスワードの照合結果を見てページを遷移する
        if(bool && error("pass") && error("pass-again") && collation()){
            // サーバーからの応答によって分岐するための変数
            var work = false;

            // ユーザーIDの取得
            var pass = document.getElementById("pass").value;
            var question = document.getElementById("question").value;
            var answer = document.getElementById("answer").value;

            var data = {
                pass: pass,
                question: question,
                answer: answer
            }

            await fetch('/bairs/signup?state=move', {
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
                if(result.trim() === 'true') {
                    // ページの移動の許可
                    work = true;
                } else {
                    // エラーメッセージを表示する
                    document.getElementById('error-message').innerHTML = '<strong>'+ result.trim() +'</strong>';
                }
            })
            .catch(error => {
                console.error('Error:', error);
            });

            if(work){
                window.location.href = "signup_profile.html";
            }
        }
    }
}

// パスワードがどちらも同じか確認する関数
function collation() {
    // ここからパスワードがどちらも同じかを確認して、同じ場合はtrueを返し、違う場合はfalseを返す
    var pass = document.getElementById("pass").value;
    var passAgain = document.getElementById("pass-again").value;

    if (pass === passAgain) {
        return true;
    } 
    else {
        document.getElementById('error-message').innerHTML = '<strong>'+ "パスワードが一致していません" +'</strong>';
        return false;
    }
}