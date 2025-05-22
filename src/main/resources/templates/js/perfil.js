document.addEventListener("DOMContentLoaded", () => {
  const usuario = localStorage.getItem("usuario");

  if (!usuario) {
    document.getElementById("datos-usuario").textContent = "No has iniciado sesión.";
    return;
  }

  document.getElementById("datos-usuario").innerHTML = `
    <p>Correo: ${usuario}</p>
  `;
});

function cerrarSesion() {
  localStorage.removeItem("usuario");
  alert("Sesión cerrada");
  window.location.href = "index.html";
}
