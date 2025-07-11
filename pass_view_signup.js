window.addEventListener('DOMContentLoaded', function(){

    // パスワード入力欄とボタンのHTMLを取得
    let btn_passview = document.getElementById("btn_passview");
    let btn_passview_re= document.getElementById("btn_passview_again");
    let input_pass = document.getElementById("pass");
    let input_pass_re = document.getElementById("pass-again");

    // ボタンのイベントリスナーを設定
    btn_passview.addEventListener("click", (e)=>{
        e.preventDefault(); // ボタンの通常の動作をキャンセル
        togglePasswordVisibility(input_pass, btn_passview);
    });

    // 再入力欄に対するボタンのイベントリスナーを設定
    btn_passview_re.addEventListener("click", (e)=>{
        e.preventDefault(); // ボタンの通常の動作をキャンセル
        togglePasswordVisibility(input_pass_re, btn_passview_re);
    });

    // 関数を使ってコードを整理
    function togglePasswordVisibility(inputElement, buttonElement) {
        // パスワード入力欄のtype属性を確認
        if (inputElement.type === 'password') {
            // パスワードを表示する
            inputElement.type = 'text';
            buttonElement.textContent = '非表示';
        } else {
            // パスワードを非表示にする
            inputElement.type = 'password';
            buttonElement.textContent = '表示';
        }
    }

});
