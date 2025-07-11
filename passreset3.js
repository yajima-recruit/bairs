async function reset() {
    // パスワードの照合結果を見てページを遷移する
    if (error("pass") && error("pass-again") && collation()) {
        var pass = document.getElementById("pass").value;
        var data = {
            pass: pass
        };
        await fetch('/bairs/reset?state=reset', {
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
            if (result.trim() === 'true') {
                window.location.href = 'passreset4.html';
            } else {
                // エラーメッセージを表示する
                document.getElementById('error-message').innerHTML = '<strong>' + result.trim() + '</strong>';
            }
        })
        .catch(error => {
            console.error('Error:', error);
        });
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
        document.getElementById('error-message').innerHTML = '<strong>' + "パスワードが一致していません" + '</strong>';
        return false;
    }
}