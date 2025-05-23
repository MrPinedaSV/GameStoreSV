document.addEventListener("DOMContentLoaded", async () => {
  const carritoContainer = document.getElementById("carrito-container");
  const totalSpan = document.getElementById("total");
  const mensaje = document.getElementById("mensaje");
  const comprarBtn = document.getElementById("comprarBtn");

  let carrito = getCarrito();

  if (carrito.length === 0) {
    mostrarCarritoVacio();
    return;
  }

  try {
    const juegos = await obtenerJuegos();
    let total = 0;

    carrito.forEach(juegoId => {
      const juego = juegos.find(j => j.id === juegoId);
      if (juego) {
        total += juego.precio;
        mostrarJuegoEnCarrito(juego);
      }
    });

    totalSpan.textContent = total.toFixed(2);
  } catch (error) {
    console.error("Error al cargar juegos:", error);
    mensaje.textContent = "No se pudo cargar el carrito.";
  }

  comprarBtn.addEventListener("click", realizarCompra);
});

function getCarrito() {
  return JSON.parse(localStorage.getItem("carrito")) || [];
}

function mostrarCarritoVacio() {
  document.getElementById("carrito-container").innerHTML = `
    <p class="text-center text-gray-400 col-span-full">El carrito está vacío.</p>`;
  document.getElementById("total").textContent = "0.00";
}

async function obtenerJuegos() {
  const response = await fetch("http://localhost:8080/api/juegos");
  if (!response.ok) throw new Error("Error al obtener juegos");
  return await response.json();
}

function mostrarJuegoEnCarrito(juego) {
  const container = document.getElementById("carrito-container");

  const juegoCard = document.createElement("div");
  juegoCard.className = "bg-[#2f3342] rounded-xl shadow-lg p-4 flex flex-col items-center text-center";
  juegoCard.innerHTML = `
    <img src="${juego.imagenUrl}" alt="${juego.nombre}" class="w-full h-48 object-cover rounded-lg mb-4 border border-gray-600" />
    <h3 class="text-lg font-bold text-white mb-2">${juego.nombre}</h3>
    <p class="text-lime-400 font-semibold mb-4">Precio: $${juego.precio.toFixed(2)}</p>
    <button class="bg-red-500 hover:bg-red-600 text-white px-4 py-1 rounded-full transition">
      Eliminar
    </button>
  `;

  juegoCard.querySelector("button").addEventListener("click", () => eliminarDelCarrito(juego.id, juegoCard));
  container.appendChild(juegoCard);
}

function eliminarDelCarrito(id, juegoCardElement) {
  const carrito = getCarrito().filter(juegoId => juegoId !== id);
  localStorage.setItem("carrito", JSON.stringify(carrito));
  juegoCardElement.remove();
  actualizarTotal();
}

function actualizarTotal() {
  const carrito = getCarrito();

  if (carrito.length === 0) {
    mostrarCarritoVacio();
    return;
  }

  fetch("http://localhost:8080/api/juegos")
    .then(res => res.json())
    .then(juegos => {
      const total = carrito.reduce((acc, id) => {
        const juego = juegos.find(j => j.id === id);
        return juego ? acc + juego.precio : acc;
      }, 0);
      document.getElementById("total").textContent = total.toFixed(2);
    });
}

function realizarCompra() {
  const carrito = getCarrito();

  if (carrito.length === 0) {
    alert("El carrito está vacío.");
    return;
  }

  const historial = JSON.parse(localStorage.getItem("historialCompras")) || [];
  historial.push(carrito);
  localStorage.setItem("historialCompras", JSON.stringify(historial));

  localStorage.removeItem("carrito");
  alert("Compra realizada con éxito.");
  location.reload();
}
