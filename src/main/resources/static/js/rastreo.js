$(document).ready(function () {
    const token = localStorage.getItem("token");

    if (!token) {
        alert("Debes iniciar sesión para ver tus pedidos.");
        window.location.href = "login.html";
        return;
    }

    cargarPedidos();

    function cargarPedidos() {
        $.ajax({
            url: "/api/pedidos",
            method: "GET",
            headers: {
                Authorization: "Bearer " + token
            },
            success: function (pedidos) {
                $("#lista-pedidos").empty();

                if (pedidos.length === 0) {
                    $("#lista-pedidos").html("<p>No tienes pedidos aún.</p>");
                    return;
                }

                pedidos.forEach(pedido => {
                    let itemsHTML = "";
                    pedido.items.forEach(item => {
                        itemsHTML += `
                            <li>${item.videojuego.nombre} - Cantidad: ${item.cantidad}</li>
                        `;
                    });

                    $("#lista-pedidos").append(`
                        <div class="card mb-3 shadow-sm">
                            <div class="card-body">
                                <h5 class="card-title">Pedido #${pedido.id}</h5>
                                <p class="card-text">Fecha: ${new Date(pedido.fecha).toLocaleString()}</p>
                                <p class="card-text">Estado: <strong>${pedido.estado}</strong></p>
                                <p class="card-text">Total: $${pedido.total.toFixed(2)}</p>
                                <p class="card-text mb-1">Items:</p>
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
    }
});
