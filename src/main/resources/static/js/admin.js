$(document).ready(function () {
    const token = localStorage.getItem("jwt");
    if (!token) {
        alert("Debes iniciar sesión como administrador.");
        window.location.href = "login.html";
        return;
    }

    // Cargar datos iniciales
    cargarUsuarios();
    cargarVideojuegos();

    // --- Usuarios ---
    function cargarUsuarios() {
        $.ajax({
            url: "/api/usuarios",
            method: "GET",
            headers: { Authorization: "Bearer " + token },
            success: function (usuarios) {
                const tbody = $("#tablaUsuarios").empty();
                usuarios.forEach(u => {
                    tbody.append(`
                        <tr>
                            <td>${u.id}</td>
                            <td>${u.nombre}</td>
                            <td>${u.email}</td>
                            <td>${u.rol}</td>
                            <td>
                                <button class="btn btn-sm btn-primary btnEditarUsuario" data-id="${u.id}">Editar</button>
                                <button class="btn btn-sm btn-danger btnEliminarUsuario" data-id="${u.id}">Eliminar</button>
                            </td>
                        </tr>
                    `);
                });
            },
            error: function () {
                alert("Error al cargar usuarios");
            }
        });
    }

    $("#btnAgregarUsuario").click(function () {
        limpiarModalUsuario();
        $("#modalUsuarioLabel").text("Agregar Usuario");
        $("#modalUsuario").modal("show");
    });

    $(document).on("click", ".btnEditarUsuario", function () {
        const id = $(this).data("id");
        $.ajax({
            url: `/api/usuarios/${id}`,
            method: "GET",
            headers: { Authorization: "Bearer " + token },
            success: function (usuario) {
                $("#usuarioId").val(usuario.id);
                $("#usuarioNombre").val(usuario.nombre);
                $("#usuarioEmail").val(usuario.email);
                $("#usuarioRol").val(usuario.rol);
                $("#usuarioPassword").val("");
                $("#modalUsuarioLabel").text("Editar Usuario");
                $("#modalUsuario").modal("show");
            },
            error: function () {
                alert("Error al cargar usuario");
            }
        });
    });

    $("#formUsuario").submit(function (e) {
        e.preventDefault();
        const id = $("#usuarioId").val();
        const usuario = {
            nombre: $("#usuarioNombre").val(),
            email: $("#usuarioEmail").val(),
            rol: $("#usuarioRol").val()
        };

        const pwd = $("#usuarioPassword").val();
        if (pwd) usuario.password = pwd;

        const method = id ? "PUT" : "POST";
        const url    = id ? `/api/usuarios/${id}` : "/api/usuarios";

        $.ajax({
            url,
            method,
            contentType: "application/json",
            data: JSON.stringify(usuario),
            headers: { Authorization: "Bearer " + token },
            success: function () {
                $("#modalUsuario").modal("hide");
                cargarUsuarios();
            },
            error: function () {
                alert(`Error al ${id ? "actualizar" : "crear"} usuario`);
            }
        });
    });

    $(document).on("click", ".btnEliminarUsuario", function () {
        if (!confirm("¿Estás seguro de eliminar este usuario?")) return;
        const id = $(this).data("id");
        $.ajax({
            url: `/api/usuarios/${id}`,
            method: "DELETE",
            headers: { Authorization: "Bearer " + token },
            success: function () {
                cargarUsuarios();
            },
            error: function () {
                alert("Error al eliminar usuario");
            }
        });
    });

    function limpiarModalUsuario() {
        $("#usuarioId, #usuarioNombre, #usuarioEmail, #usuarioPassword").val("");
        $("#usuarioRol").val("CLIENTE");
    }

    // --- Videojuegos ---
    function cargarVideojuegos() {
        $.ajax({
            url: "/api/videojuegos",
            method: "GET",
            headers: { Authorization: "Bearer " + token },
            success: function (videojuegos) {
                const tbody = $("#tablaVideojuegos").empty();
                videojuegos.forEach(v => {
                    tbody.append(`
                        <tr>
                            <td>${v.idVideojuego}</td>
                            <td>${v.titulo}</td>
                            <td>$${v.precio.toFixed(2)}</td>
                            <td>${v.stock}</td>
                            <td>
                                <button class="btn btn-sm btn-primary btnEditarVideojuego" data-id="${v.idVideojuego}">Editar</button>
                                <button class="btn btn-sm btn-danger btnEliminarVideojuego" data-id="${v.idVideojuego}">Eliminar</button>
                            </td>
                        </tr>
                    `);
                });
            },
            error: function () {
                alert("Error al cargar videojuegos");
            }
        });
    }

    $("#btnAgregarVideojuego").click(function () {
        limpiarModalVideojuego();
        $("#modalVideojuegoLabel").text("Agregar Videojuego");
        $("#modalVideojuego").modal("show");
    });

    $(document).on("click", ".btnEditarVideojuego", function () {
        const id = $(this).data("id");
        $.ajax({
            url: `/api/videojuegos/${id}`,
            method: "GET",
            headers: { Authorization: "Bearer " + token },
            success: function (videojuego) {
                 $("#videojuegoId").val(videojuego.idVideojuego);
                    $("#videojuegoNombre").val(videojuego.titulo);
                    $("#videojuegoDescripcion").val(videojuego.descripcion);
                    $("#videojuegoPlataforma").val(videojuego.plataforma);
                    $("#videojuegoPrecio").val(videojuego.precio);
                    $("#videojuegoStock").val(videojuego.stock);
                    $("#videojuegoImagen").val(videojuego.imagenUrl);
                    $("#modalVideojuegoLabel").text("Editar Videojuego");
                    $("#modalVideojuego").modal("show");
            },
            error: function () {
                alert("Error al cargar videojuego");
            }
        });
    });

    $("#formVideojuego").submit(function (e) {
        e.preventDefault();
        const id = $("#videojuegoId").val();
        const videojuego = {
           titulo: $("#videojuegoNombre").val(),
               descripcion: $("#videojuegoDescripcion").val(),
               plataforma: $("#videojuegoPlataforma").val(),
               precio: parseFloat($("#videojuegoPrecio").val()),
               stock: parseInt($("#videojuegoStock").val()),
               imagenUrl: $("#videojuegoImagen").val()
        };

        // Validación básica antes de enviar
            if (
                !videojuego.titulo || !videojuego.descripcion || !videojuego.plataforma ||
                isNaN(videojuego.precio) || isNaN(videojuego.stock) || !videojuego.imagenUrl
            ) {
                alert("Por favor, completa todos los campos correctamente.");
                return;
            }

        const method = id ? "PUT" : "POST";
        const url = id ? `/api/videojuegos/${id}` : "/api/videojuegos";

        $.ajax({
            url,
            method,
            contentType: "application/json",
            data: JSON.stringify(videojuego),
            headers: { Authorization: "Bearer " + token },
            success: function () {
                $("#modalVideojuego").modal("hide");
                cargarVideojuegos();
            },
            error: function () {
                alert(`Error al ${id ? "actualizar" : "crear"} videojuego`);
            }
        });
    });

    $(document).on("click", ".btnEliminarVideojuego", function () {
        if (!confirm("¿Estás seguro de eliminar este videojuego?")) return;
        const id = $(this).data("id");
        $.ajax({
            url: `/api/videojuegos/${id}`,
            method: "DELETE",
            headers: { Authorization: "Bearer " + token },
            success: function () {
                cargarVideojuegos();
            },
            error: function () {
                alert("Error al eliminar videojuego");
            }
        });
    });

    function limpiarModalVideojuego() {
        $("#videojuegoId, #videojuegoNombre, #videojuegoDescripcion, #videojuegoPlataforma, #videojuegoPrecio, #videojuegoStock, #videojuegoImagen").val("");
    }
});
function logout() {
    localStorage.removeItem("jwt");
    localStorage.removeItem("rol");
    window.location.href = "login.html";
}

