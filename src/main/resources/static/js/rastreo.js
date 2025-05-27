$(document).ready(function () {
    const token = localStorage.getItem("jwt");
    const rol = localStorage.getItem("rol");

    if (!token) {
        alert("Debes iniciar sesión para ver tus pedidos.");
        window.location.href = "login.html";
        return;
    }

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
                let rastreoHTML = "";

                if (pedido.rastreo && pedido.rastreo.length > 0) {
                    pedido.rastreo.forEach(etapa => {
                        rastreoHTML += `<li>${etapa.estado} - ${new Date(etapa.fecha).toLocaleString()}</li>`;
                    });
                } else {
                    rastreoHTML = "<li>Sin historial de rastreo</li>";
                }

                // Select para admin
                let selectHTML = "";
                if (rol === "ADMIN") {
                    selectHTML = `
                        <select class="form-select form-select-sm mt-2 estado-select" data-id="${pedido.idPedido}">
                            <option disabled selected>Cambiar estado...</option>
                            <option value="Pago procesado">Pago procesado</option>
                            <option value="En preparación">En preparación</option>
                            <option value="Enviado">Enviado</option>
                            <option value="Entregado">Entregado</option>
                            <option value="Cancelado">Cancelado</option>
                        </select>
                    `;
                }

                $("#lista-pedidos").append(`
                    <div class="card mb-3 shadow-sm">
                        <div class="card-body">
                            <h5 class="card-title">Pedido #${pedido.idPedido}</h5>
                            <p class="card-text">Fecha: ${new Date(pedido.fecha).toLocaleString()}</p>
                            <p class="card-text">Estado actual: <strong>${pedido.estado}</strong></p>
                            <p class="card-text">Total: $${pedido.total.toFixed(2)}</p>
                            <p class="card-text">Historial de rastreo:</p>
                            <ul>${rastreoHTML}</ul>
                            ${selectHTML}
                        </div>
                    </div>
                `);
            });

            // Evento para cambiar estado
            $(".estado-select").change(function () {
                const idPedido = $(this).data("id");
                const nuevoEstado = $(this).val();

                if (!confirm(`¿Confirmas cambiar el estado del pedido #${idPedido} a "${nuevoEstado}"?`)) return;

                $.ajax({
                    url: "/api/rastreo",
                    method: "POST",
                    headers: {
                        Authorization: "Bearer " + token,
                        "Content-Type": "application/json"
                    },
                    data: JSON.stringify({
                        estado: nuevoEstado,
                        idPedido: idPedido
                    }),
                    success: function () {
                        alert("Estado actualizado correctamente.");
                        location.reload(); // Recargar para mostrar actualización
                    },
                    error: function () {
                        alert("Error al actualizar el estado.");
                    }
                });
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



