/* global bootstrap */

document.addEventListener("DOMContentLoaded", function () {

    const url = new URLSearchParams(window.location.search);

    const aviso = url.get('aviso');

    if (!aviso) {
        return;
    }

    const toastElement = document.getElementById('liveToast');
    const toastBody = document.getElementById('liveToastBody');

    if (toastElement && toastBody) {
        let mensaje = "";

        switch (aviso) {

            case "logoutExitoso":
                mensaje = "Has cerrado sesión correctamente.";
                break;

            case "registroExitoso":
                mensaje = "Has creado una cuenta correctamente.";
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
    }
});

document.addEventListener("click", function () {

    const modalDetalle = document.getElementById('detallePeliculaModal');

    if (!modalDetalle) {
        return;
    }

    modalDetalle.addEventListener('show.bs.modal', event => {

        const button = event.relatedTarget;

        const urlDetalles = button.getAttribute('data-url-detalles');
        const titulo = button.getAttribute('data-titulo');
        const fecha = button.getAttribute('data-fecha');
        const puntuacion = button.getAttribute('data-puntuacion');
        const imagen = button.getAttribute('data-imagen');
        const descripcion = button.getAttribute('data-descripcion');


        const elDetalles = modalDetalle.querySelector('#modalDetalles');
        const elId = modalDetalle.querySelector('#modalId');
        const elTitulo = modalDetalle.querySelector('#modalTitulo');
        const elFecha = modalDetalle.querySelector('#modalFecha');
        const elPuntuacion = modalDetalle.querySelector('#modalPuntuacion');
        const elImagen = modalDetalle.querySelector('#modalImagen');
        const elDescripcion = modalDetalle.querySelector('#modalDescripcion');


        if (elDetalles) 
            elDetalles.href = urlDetalles; 
        if (elTitulo)
            elTitulo.textContent = titulo;
        if (elFecha)
            elFecha.textContent = fecha;
        if (elPuntuacion)
            elPuntuacion.textContent = puntuacion;
        if (elImagen)
            elImagen.src = imagen;
        if (elDescripcion)
            elDescripcion.textContent = descripcion || "No hay descripción disponible.";
    });
});


