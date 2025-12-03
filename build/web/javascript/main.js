/* global bootstrap */

document.addEventListener("DOMContentLoaded", function () {

    const url = new URLSearchParams(window.location.search);

    const aviso = url.get('aviso');

    if (aviso) {
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
    }
});

document.addEventListener("DOMContentLoaded", function () {

    const modalDetalle = document.getElementById('detallePeliculaModal');

    if (modalDetalle) {
        modalDetalle.addEventListener('show.bs.modal', event => {

            const trigger = event.relatedTarget;

            const urlDetalles = trigger.getAttribute('data-url-detalles');
            const titulo = trigger.getAttribute('data-titulo');
            const fecha = trigger.getAttribute('data-fecha');
            const puntuacion = trigger.getAttribute('data-puntuacion');
            const imagen = trigger.getAttribute('data-imagen');
            const descripcion = trigger.getAttribute('data-descripcion');


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
    }
});

document.addEventListener("DOMContentLoaded", function () {
    const modalEditar = document.getElementById('editarResenaModal');

    if (modalEditar) {
        modalEditar.addEventListener('show.bs.modal', event => {

            const button = event.relatedTarget;


            const idResena = button.getAttribute('data-id');
            const texto = button.getAttribute('data-texto');
            const puntuacion = button.getAttribute('data-puntuacion');


            const editId = modalEditar.querySelector('#editIdResena');
            const editTexto = modalEditar.querySelector('#editTexto');
            const editPuntuacion = modalEditar.querySelector('#editPuntuacion');

            if (editId)
                editId.value = idResena;

            if (editTexto)
                editTexto.value = texto;

            if (editPuntuacion)
                editPuntuacion.value = puntuacion;

        });
    }
});


