// perfil.js

document.addEventListener("DOMContentLoaded", () => {
  const contenedor = document.getElementById("datos-usuario");
  const usuario = localStorage.getItem("usuario");

  if (!usuario) {
    contenedor.innerHTML = `<p class="text-red-400 font-semibold">No has iniciado sesión.</p>`;
    return;
  }

  contenedor.innerHTML = `
    <p><span class="font-semibold text-white">Correo:</span> ${usuario}</p>
  `;
});

function cerrarSesion() {
  localStorage.removeItem("usuario");
  alert("Sesión cerrada");
  window.location.href = "index.html";
}
