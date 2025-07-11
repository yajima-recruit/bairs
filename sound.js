// 効果音の再生関数
document.addEventListener("DOMContentLoaded", function () {
	// すべてのボタンを取得
	var buttons = document.querySelectorAll("button");

	// ボタンがクリックされたら効果音を再生
	buttons.forEach(function (button) {
		button.addEventListener("click", function () {
			playSound();
		});
	});

	function playSound() {
		var sound = document.getElementById("ClickSound");
		sound.currentTime = 0;
		sound.play();
	}
});