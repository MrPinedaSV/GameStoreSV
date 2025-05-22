document.addEventListener("DOMContentLoaded", async () => {
  const params = new URLSearchParams(window.location.search);
  const id = params.get("id");

  if (!id) return;

  try {
    const res = await fetch("http://localhost:8080/api/juegos/" + id);
    const juego = await res.json();

    const contenedor = document.getElementById("detalle-juego");
    contenedor.innerHTML = `
      <h2>${juego.nombre}</h2>
      <p>${juego.descripcion}</p>
      <p>Precio: $${juego.precio}</p>
    `;

    window.juegoDetalle = juego;
  } catch (e) {
    console.error("Error cargando juego:", e);
  }
});

function agregarAlCarritoDesdeDetalle() {
  const juego = window.juegoDetalle;
  if (!juego) return;

  let carrito = JSON.parse(localStorage.getItem("carrito")) || [];
  carrito.push(juego.id);
  localStorage.setItem("carrito", JSON.stringify(carrito));
  alert("Agregado al carrito");
}
