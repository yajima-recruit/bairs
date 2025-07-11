async function editform() {
	var name = document.getElementById("name").value;
	var snum = document.getElementById("snum").value;
	var snumpublic = document.querySelector('input[name="snumpublic"]:checked');
	var gender = document.querySelector('input[name="gender"]:checked');
	var bloodtype = document.querySelector('select[name="bloodtype"]').value;
	var hobbies = document.querySelectorAll('input[name="hobby"]:checked');

	// 名前、性別、血液型、趣味のいずれかの条件が満たされていない場合
	var error = document.getElementById("error-message");
	if (!name || !snum || !snumpublic || !gender || !bloodtype || hobbies.length === 0) {
		error.innerHTML = "※必須項目が入力されていません";
	} else {
		error.innerHTML = "";

		var hobby = Array.from(hobbies).map(checkbox => checkbox.value);
		var data = {
			hobby: hobby
		};

		await fetch('/bairs/edit_profile', {
			method: 'POST',
			headers: {'Content-Type': 'application/json'},
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

		
	}
}