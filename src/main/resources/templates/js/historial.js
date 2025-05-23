document.addEventListener("DOMContentLoaded", async () => {
  const historialContainer = document.getElementById("historial-container");
  const mensaje = document.getElementById("mensaje");

  const historial = JSON.parse(localStorage.getItem("historialCompras")) || [];

  if (historial.length === 0) {
    mensaje.textContent = "No hay compras registradas.";
    return;
  }

  try {
    const response = await fetch("http://localhost:8080/api/juegos");
    if (!response.ok) throw new Error("Error al cargar los juegos");

    const juegos = await response.json();

    historial.forEach((pedido, index) => {
      const pedidoDiv = document.createElement("div");
      pedidoDiv.className = "bg-[#1e293b] rounded-xl p-6 shadow-lg hover:shadow-2xl transition-transform hover:scale-[1.02] duration-300";

      let total = 0;
      let contenidoHTML = `<h3 class="text-xl font-semibold text-lime-400 mb-4">🧾 Pedido #${index + 1}</h3>`;

      pedido.forEach(juegoId => {
        const juego = juegos.find(j => j.id === juegoId);
        if (juego) {
          total += juego.precio;
          contenidoHTML += `
            <p class="text-gray-300 mb-1">${juego.nombre} - <span class="text-lime-300 font-medium">$${juego.precio.toFixed(2)}</span></p>
          `;
        }
      });

      contenidoHTML += `<p class="mt-4 text-right font-bold text-white">Total: <span class="text-lime-400">$${total.toFixed(2)}</span></p>`;

      pedidoDiv.innerHTML = contenidoHTML;
      historialContainer.appendChild(pedidoDiv);
    });

  } catch (error) {
    console.error("Error al cargar historial:", error);
    mensaje.textContent = "Error al cargar el historial de compras.";
  }
});
