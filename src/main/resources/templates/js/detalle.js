document.addEventListener("DOMContentLoaded", async () => {
  const params = new URLSearchParams(window.location.search);
  const id = params.get("id");

  if (!id) return;

  try {
    const res = await fetch(`http://localhost:8080/api/juegos/${id}`);
    if (!res.ok) throw new Error("No se pudo cargar el juego");

    const juego = await res.json();
    const contenedor = document.getElementById("detalle-juego");

    contenedor.innerHTML = `
      <img src="${juego.imagenUrl}" alt="${juego.nombre}" class="w-full md:w-1/2 rounded-xl object-cover shadow-lg max-h-96" />
      <div class="flex-1 flex flex-col justify-between text-center md:text-left">
        <div>
          <h2 class="text-4xl font-bold text-white mb-4">${juego.nombre}</h2>
          <p class="text-gray-300 mb-6">${juego.descripcion}</p>
        </div>
        <div class="mt-auto">
          <p class="text-2xl text-lime-400 font-semibold mb-6">Precio: $${juego.precio.toFixed(2)}</p>
          <button onclick="agregarAlCarritoDesdeDetalle()"
            class="bg-gradient-to-r from-indigo-600 to-purple-600 hover:from-indigo-500 hover:to-purple-500 text-white px-6 py-3 rounded-xl text-base font-semibold shadow-lg transition duration-200">
            🛒 Agregar al carrito
          </button>
        </div>
      </div>
    `;

    window.juegoDetalle = juego;

  } catch (e) {
    console.error("Error cargando juego:", e);
    const contenedor = document.getElementById("detalle-juego");
    contenedor.innerHTML = `
      <p class="text-red-400 text-center w-full">No se pudo cargar el detalle del juego.</p>
    `;
  }
});

function agregarAlCarritoDesdeDetalle() {
  const juego = window.juegoDetalle;
  if (!juego) return;

  let carrito = JSON.parse(localStorage.getItem("carrito")) || [];
  carrito.push(juego.id);
  localStorage.setItem("carrito", JSON.stringify(carrito));

  alert("✅ Juego agregado al carrito");
}
