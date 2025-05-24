// login.js
document.getElementById("form-login").addEventListener("submit", e => {
  e.preventDefault();

  const email = document.getElementById("email").value.trim();
  const password = document.getElementById("password").value.trim();

  if (email && password) {
    localStorage.setItem("usuario", email);
    alert("Login exitoso. ¡Bienvenido!");
    window.location.href = "index.html";
  } else {
    alert("⚠️ Por favor, completa ambos campos.");
  }
});
