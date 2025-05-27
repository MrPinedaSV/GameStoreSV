$(document).ready(function () {
    // Obtener el token del localStorage
    const token = localStorage.getItem("jwt");

    // Si no hay token, redirigir a login
    if (!token) {
        alert("Por favor inicia sesión para ver el catálogo.");
        window.location.href = "login.html";
        return;
    }

    // Función para cargar los videojuegos desde la API
    function cargarCatalogo() {
        $.ajax({
            url: "/api/videojuegos",
            method: "GET",
            headers: {
                Authorization: "Bearer " + token
            },
            success: function (videojuegos) {
                // Vaciar catálogo antes de mostrar (por si se recarga)
                $("#catalogo").empty();

                videojuegos.forEach(juego => {
                    $("#catalogo").append(`
                        <div class="col">
                            <div class="card h-100 shadow-sm">
                                <div class="card-body">
                                    <h5 class="card-title">${juego.titulo}</h5>
                                    <p class="card-text">${juego.descripcion || "Sin descripción."}</p>
                                    <p class="card-text fw-bold">Precio: $${juego.precio.toFixed(2)}</p>
                                    <button class="btn btn-primary agregar-carrito" data-id="${juego.idVideojuego}">Agregar al carrito</button>
                                </div>
                            </div>
                        </div>
                    `);
                });
            },
            error: function () {
                alert("Error al cargar el catálogo. Verifica si el token sigue siendo válido.");
            }
        });
    }

    // Llamar para cargar el catálogo al cargar la página
    cargarCatalogo();

    // Evento para agregar videojuego al carrito
    $(document).on("click", ".agregar-carrito", function () {
        const idVideojuego = $(this).data("id");

        $.ajax({
            url: "/api/carrito-items",
            method: "POST",
            contentType: "application/json",
            data: JSON.stringify({
                videojuego: { idVideojuego: idVideojuego },
                cantidad: 1
                // Aquí puedes agregar más datos si tu API lo requiere
            }),
            headers: {
                Authorization: "Bearer " + token
            },
            success: function () {
                alert("Videojuego agregado al carrito.");
            },
            error: function () {
                alert("No se pudo agregar al carrito.");
            }
        });
    });
});
function logout() {
    localStorage.removeItem("jwt");
    localStorage.removeItem("rol");
    window.location.href = "login.html";
}

