document.addEventListener("DOMContentLoaded", async () => {
  const carritoContainer = document.getElementById("carrito-container");
  const totalSpan = document.getElementById("total");
  const mensaje = document.getElementById("mensaje");

  let carrito = JSON.parse(localStorage.getItem("carrito")) || [];

  if (carrito.length === 0) {
    carritoContainer.innerHTML = `<p class="text-center text-gray-400 col-span-full">El carrito está vacío.</p>`;
    totalSpan.textContent = "0.00";
    return;
  }

  try {
    const response = await fetch("http://localhost:8080/api/juegos");
    const juegos = await response.json();
    let total = 0;

    carrito.forEach(juegoId => {
      const juego = juegos.find(j => j.id === juegoId);
      if (juego) {
        total += juego.precio;

        const juegoCard = document.createElement("div");
        juegoCard.className = "bg-[#2f3342] rounded-xl shadow-lg p-4 flex flex-col items-center text-center";
        juegoCard.innerHTML = `
          <img src="${juego.imagenUrl}" alt="${juego.nombre}" class="w-full h-48 object-cover rounded-lg mb-4 border border-gray-600" />
          <h3 class="text-lg font-bold text-white mb-2">${juego.nombre}</h3>
          <p class="text-lime-400 font-semibold mb-4">Precio: $${juego.precio.toFixed(2)}</p>
          <button onclick="eliminarDelCarrito(${juego.id})"
            class="bg-red-500 hover:bg-red-600 text-white px-4 py-1 rounded-full transition">
            Eliminar
          </button>
        `;
        carritoContainer.appendChild(juegoCard);
      }
    });

    totalSpan.textContent = total.toFixed(2);

  } catch (error) {
    console.error("Error:", error);
    mensaje.textContent = "No se pudo cargar el carrito.";
  }
});

function eliminarDelCarrito(id) {
  let carrito = JSON.parse(localStorage.getItem("carrito")) || [];
  carrito = carrito.filter(juegoId => juegoId !== id);
  localStorage.setItem("carrito", JSON.stringify(carrito));
  location.reload(); // Actualizar la página para reflejar los cambios
}

function realizarCompra() {
  let carrito = JSON.parse(localStorage.getItem("carrito")) || [];

  if (carrito.length === 0) {
    alert("El carrito está vacío.");
    return;
  }

  // Guardar en el historial
  let historial = JSON.parse(localStorage.getItem("historialCompras")) || [];
  historial.push(carrito);
  localStorage.setItem("historialCompras", JSON.stringify(historial));

  // Limpiar el carrito
  localStorage.removeItem("carrito");
  alert("Compra realizada con éxito");
  location.reload();
}
