/* global bootstrap */

document.addEventListener("DOMContentLoaded", function () {

    // 1. Lee los parámetros de la URL actual
    const urlParams = new URLSearchParams(window.location.search);

    // 2. Busca un parámetro llamado 'toast' o 'registro'
    const toastAction = urlParams.get('toast');
    const registroStatus = urlParams.get('registro');

    // 3. Selecciona los elementos del toast
    const toastElement = document.getElementById('liveToast');
    const toastBody = document.getElementById('liveToastBody');

    // Si no existen los elementos del toast en esta página, no hagas nada.
    if (!toastElement || !toastBody) {
        return;
    }

    let mensaje = ""; // Variable para guardar nuestro mensaje

    // 4. Comprueba qué acción debemos mostrar
    if (toastAction === 'logout') {
        mensaje = "Has cerrado sesión correctamente.";
    }

    // También he visto que tu UsuarioController redirige a login con "registro=exito"
    // ¡Podemos usar la misma lógica!
    else if (registroStatus === 'exito') {
        mensaje = "¡Te has registrado con éxito! Por favor, inicia sesión.";
    }

    // 5. Si encontramos un mensaje para mostrar...
    if (mensaje) {

        // 6. Ponemos el mensaje en el cuerpo del toast
        toastBody.innerText = mensaje;

        // 7. Inicializamos el toast de Bootstrap y lo mostramos
        const toast = new bootstrap.Toast(toastElement);
        toast.show();

        // 8. (Opcional) Limpia la URL para que el toast no reaparezca si el usuario recarga la página
        const cleanUrl = window.location.protocol + "//" + window.location.host + window.location.pathname;
        window.history.replaceState({path: cleanUrl}, '', cleanUrl);
    }
});