// login.js
document.getElementById("form-login").addEventListener("submit", e => {
  e.preventDefault();

  const email = document.getElementById("email").value.trim();
  const password = document.getElementById("password").value.trim();

  // vamos a simular un login aceptando cualquier usuario
  if (email && password) {
    localStorage.setItem("usuario", email);

    alert("Login exitoso (Aceptado)");
    window.location.href = "index.html";
  } else {
    alert("Por favor, completa ambos campos.");
  }
});
