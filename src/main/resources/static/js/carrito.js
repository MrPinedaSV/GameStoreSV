$(document).ready(function () {
    const token = localStorage.getItem("jwt");
    if (!token) {
        alert("Por favor inicia sesión para ver tu carrito.");
        window.location.href = "login.html";
        return;
    }

    cargarCarrito();

    function cargarCarrito() {
        $.ajax({
            url: "/api/carrito-items",
            method: "GET",
            headers: {
                Authorization: "Bearer " + token
            },
            success: function (items) {
                $("#carrito-items").empty();
                let total = 0;

                if (items.length === 0) {
                    $("#carrito-items").html("<p>No hay videojuegos en tu carrito.</p>");
                    return;
                }

                items.forEach(item => {
                    const subtotal = item.videojuego.precio * item.cantidad;
                    total += subtotal;

                    $("#carrito-items").append(`
                        <div class="card mb-3 shadow-sm">
                            <div class="card-body d-flex justify-content-between align-items-center">
                                <div>
                                    <h5>${item.videojuego.nombre}</h5>
                                    <p class="mb-1">Precio: $${item.videojuego.precio.toFixed(2)}</p>
                                    <p class="mb-1">Cantidad:
                                        <input type="number" class="form-control d-inline w-auto cantidad-input" data-id="${item.id}" value="${item.cantidad}" min="1" style="width: 70px;" />
                                    </p>
                                    <p class="mb-0 fw-bold">Subtotal: $${subtotal.toFixed(2)}</p>
                                </div>
                                <button class="btn btn-danger eliminar-item" data-id="${item.id}">Eliminar</button>
                            </div>
                        </div>
                    `);
                });

                $("#total").text(total.toFixed(2));
            },
            error: function () {
                alert("Error al cargar los items del carrito.");
            }
        });
    }

    // Eliminar item del carrito
    $(document).on("click", ".eliminar-item", function () {
        const id = $(this).data("id");

        $.ajax({
            url: `/api/carrito-items/${id}`,
            method: "DELETE",
            headers: {
                Authorization: "Bearer " + token
            },
            success: function () {
                cargarCarrito();
            },
            error: function () {
                alert("No se pudo eliminar el item.");
            }
        });
    });

    // Actualizar cantidad
    $(document).on("change", ".cantidad-input", function () {
        const id = $(this).data("id");
        const nuevaCantidad = parseInt($(this).val());

        if (nuevaCantidad <= 0) {
            alert("La cantidad debe ser mayor que cero.");
            return;
        }

        $.ajax({
            url: `/api/carrito-items/${id}`,
            method: "PUT",
            contentType: "application/json",
            data: JSON.stringify({ cantidad: nuevaCantidad }),
            headers: {
                Authorization: "Bearer " + token
            },
            success: function () {
                cargarCarrito();
            },
            error: function () {
                alert("No se pudo actualizar la cantidad.");
            }
        });
    });

    // Finalizar pedido
    $("#finalizarPedido").click(function () {
        $.ajax({
            url: "/api/pedidos",
            method: "POST",
            headers: {
                Authorization: "Bearer " + token
            },
            success: function () {
                alert("¡Pedido realizado con éxito!");
                cargarCarrito(); // Para refrescar el carrito vacío
            },
            error: function () {
                alert("No se pudo finalizar el pedido.");
            }
        });
    });
});
