// registro.js

document.getElementById("form-registro").addEventListener("submit", e => {
  e.preventDefault();

  const nombre = document.getElementById("nombre").value.trim();
  const email = document.getElementById("email").value.trim();
  const password = document.getElementById("password").value.trim();

  if (!nombre || !email || !password) {
    alert("Por favor, completa todos los campos.");
    return;
  }

  // Simulación de registro
  alert(`Cuenta creada para ${nombre} (simulado)`);
  localStorage.setItem("usuario", email);
  window.location.href = "index.html";
});
