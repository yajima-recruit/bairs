function error(id) {
    var value = document.getElementById(id).value;
    var errorMessage = document.getElementById("error-message");

    if (value.trim() === ""){
        // エラーメッセージの表示
        errorMessage.innerHTML = '<strong>'+ errormessage(id) +'</strong>';

        // サーブレットへの送信を中止
        return false;
    } else {
        // エラーメッセージをクリア
        errorMessage.innerHTML = "";
        
        // サーブレットへ送信
        return true;
    }
}

function errormessage(id){
    var message ="";
    switch(id){
        case "userid":
            message = "ユーザーIDを入力してください";
            break;
        case "pass":
            message = "パスワードを入力してください";
            break;
        case "pass-again":
            message = "もう１度パスワードを入力してください";
            break;
        case "answer":
            message = "答えを入力してください";
            break;
        default:
            message = "引数エラーです"
    }
    return message;
}