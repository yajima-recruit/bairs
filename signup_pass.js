function togglepassword() {
    var input_pass = document.getElementById("input_pass");
    var btn_passview = document.getElementById("btn_passview");

    if (input_pass.type === "password") {
        input_pass.type = "text";
        btn_passview.textContent = "非表示";
    } else {
        input_pass.type = "password";
        btn_passview.textContent = "表示";
    }
}