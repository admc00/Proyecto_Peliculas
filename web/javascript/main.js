/* global bootstrap */

document.addEventListener("DOMContentLoaded", function () {

    const url = new URLSearchParams(window.location.search);

    const aviso = url.get('aviso');
    
    if (!aviso) {
        return;
    }
    
    const toastElement = document.getElementById('liveToast');
    const toastBody = document.getElementById('liveToastBody');

    if (!toastElement || !toastBody) {
        return;
    }

    let mensaje = "";
    
    switch(aviso){
        
        case "logoutExitoso":
            mensaje = "Has cerrado sesión correctamente.";
            break;
            
        case "registroExitoso": 
            mensaje = "Has cerrado sesión correctamente.";
            break;
            
        case "loginExitoso":
            mensaje = "Has iniciado sesion correctamente.";
            break;
    }
    
    if (mensaje) {

        toastBody.innerText = mensaje;
       
        const toast = new bootstrap.Toast(toastElement);
        toast.show();

        const cleanUrl = window.location.protocol + "//" + window.location.host + window.location.pathname;
        window.history.replaceState({path: cleanUrl}, '', cleanUrl);
    }
});