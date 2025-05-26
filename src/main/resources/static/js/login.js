$(document).ready(function () {
  $('#loginForm').submit(function (e) {
    e.preventDefault();

    const email = $('#email').val();
    const password = $('#password').val();

    // Aquí iría la llamada AJAX para autenticar contra el backend
    $.ajax({
      url: '/api/auth/login', // ajusta al endpoint real
      type: 'POST',
      contentType: 'application/json',
      data: JSON.stringify({ email: email, password: password }),
      success: function (data) {
        $('#message').html(
          `<div class="alert alert-success">Login exitoso! Token: ${data.accessToken}</div>`
        );
        // Guarda el token y redirige o haz lo que necesites
        localStorage.setItem('token', data.accessToken);
        // Redirige a la página principal o dashboard
                window.location.href = 'index.html'; // o dashboard.html, como prefieras
      },
      error: function (xhr) {
        $('#message').html(
          `<div class="alert alert-danger">Error: ${xhr.responseJSON.message || 'Credenciales incorrectas'}</div>`
        );
      },
    });
  });
});
