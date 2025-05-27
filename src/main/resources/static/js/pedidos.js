$(document).ready(function () {
    const token = localStorage.getItem("jwt");
    const rol = localStorage.getItem("rol");

    if (!token) {
        alert("Debes iniciar sesión.");
        window.location.href = "login.html";
        return;
    }
    if (rol === "ADMIN") {
            $("h2").text("Todos los Pedidos");
        } else {
            $("h2").text("Mis Pedidos");
        }

    $.ajax({
            url: "/api/pedidos",
            method: "GET",
            headers: {
                Authorization: "Bearer " + token
            },
            success: function (pedidos) {
                if (pedidos.length === 0) {
                    $("#listaPedidos").html("<p>No hay pedidos registrados.</p>");
                    return;
                }

                pedidos.forEach(p => {
                    const fecha = new Date(p.fecha).toLocaleString();
                    const itemsHTML = p.items.map(i => {
                        const titulo = i.videojuego ? i.videojuego.titulo : "[Juego eliminado]";
                        return `<li>${titulo} x${i.cantidad} - $${i.precioUnitario.toFixed(2)}</li>`;
                    }).join("");

                    $("#listaPedidos").append(`
                        <div class="card mb-3 shadow-sm">
                            <div class="card-body">
                                <h5 class="card-title">Pedido #${p.idPedido}</h5>
                                <p class="card-text">Fecha: ${fecha}</p>
                                <p class="card-text">Estado: <strong>${p.estado}</strong></p>
                                <p class="card-text">Total: <strong>$${p.total.toFixed(2)}</strong></p>
                                <p class="card-text">Ítems:</p>
                                <ul>${itemsHTML}</ul>
                            </div>
                        </div>
                    `);
                });
            },
            error: function () {
                alert("Error al cargar los pedidos.");
            }
    });
});

function logout() {
    localStorage.removeItem("jwt");
    localStorage.removeItem("rol");
    window.location.href = "login.html";
}
