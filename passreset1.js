async function search() {
    var message;
    if(await error("userid")){
        // ユーザーIDの取得
        var userid = document.getElementById("userid").value;

        // fetchを使用してサーブレットに名前を送信
        await fetch('/bairs/reset?state=search&userid=' + encodeURIComponent(userid), {
            method: 'POST',
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
        // サーバーからの応答に基づいて条件分岐
        var form = document.getElementById("form");
        if(message==="true"){
            document.getElementById("hidden").value = "move1";
            form.submit();
        } else if(message==="hack"){
            document.getElementById("hidden").value = "hack";
            form.submit();
        } else{
            console.log("エラーだよ♡");
            // エラーメッセージを表示する
            document.getElementById('error-message').innerHTML = '<strong>'+ message +'</strong>';
        }
    }
}