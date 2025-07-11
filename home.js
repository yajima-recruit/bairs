function submit(id){
    document.getElementById(id).submit();
}

window.onload = function() {
    var currentURL = window.location.href;
    loaded(currentURL);
};

function loaded(url) {
    var parts = url.split('/');
    var lastPart = parts[parts.length - 1];
    var fileName = lastPart.split('?')[0];

    if(fileName.trim()!='home'){
        submit("home");
    } else {
        var elements = document.getElementsByClassName("card");
        for(var i=0;i<elements.length;i++){
            elements[i].style.display = "inline-block";
        }
    }
}