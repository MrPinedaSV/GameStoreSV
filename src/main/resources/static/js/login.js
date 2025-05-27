$(document).ready(function () {
  $('#loginForm').submit(function (e) {
    e.preventDefault();

    const email = $('#email').val();
    const password = $('#password').val();

    // Petición AJAX al backend
    $.ajax({
      url: '/api/auth/login',
      type: 'POST',
      contentType: 'application/json',
      data: JSON.stringify({
        email: email,
        password: password
      }),
      success: function (data) {
        // Mostrar mensaje de éxito
        $('#message').html(
          `<div class="alert alert-success">¡Login exitoso!</div>`
        );

        // Guardar token y rol en localStorage
        localStorage.setItem('jwt', data.accessToken);
        localStorage.setItem('rol', data.rol);

        // Redirigir según el rol
        if (data.rol === 'ADMIN') {
          window.location.href = 'admin.html';
        } else if (data.rol === 'CLIENTE') {
          window.location.href = 'catalogo.html';
        } else {
          $('#message').html(
            `<div class="alert alert-warning">Rol no reconocido</div>`
          );
        }
      },
      error: function (xhr) {
        const errorMsg =
          xhr.responseJSON?.message || 'Credenciales incorrectas';
        $('#message').html(
          `<div class="alert alert-danger">Error: ${errorMsg}</div>`
        );
      }
    });
  });
});


