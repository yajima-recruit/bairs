function register(){
    fetch('/bairs/register', {
        method: 'POST'
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.text(); // テキストデータを取得
    })
    .then(result => {
        var form = document.getElementById('form');
        // サーバーからの応答に基づいて条件分岐
        if (result.trim() === 'true') {
            form.action = '/bairs/home';
        } else {
            form.action = '/bairs/error';
        }
        form.method = 'POST';
        
        // フォームを送信
        form.submit();
    })
    .catch(error => {
        console.error('Error:', error);
    });
}