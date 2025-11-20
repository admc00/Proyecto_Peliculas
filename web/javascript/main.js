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
    }
});

document.addEventListener("click", function () {

    const modalDetalle = document.getElementById('detallePeliculaModal');

    // Si el modal no existe en esta página, salimos de esta función.
    if (!modalDetalle) {
        return;
    }

    // Asignamos el evento al modal
    modalDetalle.addEventListener('show.bs.modal', event => {
        // Botón que activó el modal
        const button = event.relatedTarget;

        // Extraer información de los atributos data-*
        const titulo = button.getAttribute('data-titulo');
        const fecha = button.getAttribute('data-fecha');
        const puntuacion = button.getAttribute('data-puntuacion');
        const imagen = button.getAttribute('data-imagen');
        const descripcion = button.getAttribute('data-descripcion');

        // Actualizar el contenido del modal
        const elTitulo = modalDetalle.querySelector('#modalTitulo');
        const elFecha = modalDetalle.querySelector('#modalFecha');
        const elPuntuacion = modalDetalle.querySelector('#modalPuntuacion');
        const elImagen = modalDetalle.querySelector('#modalImagen');
        const elDescripcion = modalDetalle.querySelector('#modalDescripcion');

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


