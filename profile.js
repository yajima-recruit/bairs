function subumit(id){
    document.getElementById(id).submit();
}

function ajax_send(id){
    fetch('/bairs/'+id, {
        method: 'POST'
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.text(); // テキストデータを取得
    })
    .then(result => {            
        window.location.href = "index.html";
    })
    .catch(error => {
        console.error('Error:', error);
    });
}

function confirmation(id){
    // アラートで確認メッセージを表示
    var result = confirm("本当にアカウントを削除しますか？");
    
    // ユーザーが "yes" を選択した場合
    if (result) {
        // submit() 関数を呼び出し
        ajax_send(id);
    }
}