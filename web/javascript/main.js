/* global bootstrap */

var toastTrigger = document.getElementById('liveToastBtn');
var toastLiveExample = document.getElementById('liveToast');
if (toastTrigger) {

    toastTrigger.addEventListener('click',show());



}

function show() {
    var toast = new bootstrap.Toast(toastLiveExample);

    toast.show();
}