var slideshowImages = document.getElementById('slideshow-image');
var filename = ["boy_01","boy_02","boy_03","boy_04","boy_05","boy_06","boy_07","boy_08","boy_09","boy_10",
				"girl_01","girl_02","girl_03","girl_04","girl_05","girl_06","girl_07","girl_08","girl_09","girl_10",
				"animal_01","animal_02","animal_03","animal_04","animal_05","animal_06","animal_07","animal_08","animal_09","animal_10"];

function showImage(index) {
	slideshowImages.setAttribute('data-name', filename[index]);
	slideshowImages.alt = filename[index];
	slideshowImages.src = 'image/icon/' + filename[index] + '.png';

    // 隠しフィールドに画像のファイル名を設定
    document.getElementById('selectedImageField').value = filename[index];
}

function showNextImage() {
	currentImageIndex = (currentImageIndex + 1) % filename.length;
	showImage(currentImageIndex);
}

function showPrevImage() {
	currentImageIndex = (currentImageIndex - 1 + filename.length) % filename.length;
	showImage(currentImageIndex);
}

// 矢印をクリックしたときの処理
document.getElementById('prev').addEventListener('click', showPrevImage);
document.getElementById('next').addEventListener('click', showNextImage);


window.onload = function () {
	changeIconBackgroundColor(); // 初期値での背景色を適用
}

function changeIconBackgroundColor() {
	var colorPicker = document.getElementById('colorPicker');
	var iconContainer = document.getElementById('slideshow-image');
	iconContainer.style.backgroundColor = colorPicker.value;
}

function validateForm() {
	var name = document.getElementById("name").value;
	var snum = document.getElementById("snum").value;
	var snumpublic = document.querySelector('input[name="snumpublic"]:checked');
	var gender = document.querySelector('input[name="gender"]:checked');
	var bloodtype = document.querySelector('select[name="bloodtype"]').value;
	var hobbies = document.querySelectorAll('input[name="hobby"]:checked');

	// 名前、性別、血液型、趣味のいずれかの条件が満たされていない場合
	if (!name || !snum || !snumpublic || !gender || !bloodtype || hobbies.length === 0) {
		document.getElementById("error-message").innerHTML = "<strong>※必須項目が入力されていません</strong>";
	} else {
		document.getElementById("error-message").innerHTML = "";
		document.getElementById("form").submit();
	}
}

function submit(id){
    document.getElementById(id).submit();
}