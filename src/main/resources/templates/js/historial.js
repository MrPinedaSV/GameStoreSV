document.addEventListener("DOMContentLoaded", async () => {
  const historialContainer = document.getElementById("historial-container");
  const mensaje = document.getElementById("mensaje");

  let historial = JSON.parse(localStorage.getItem("historialCompras")) || [];

  if (historial.length === 0) {
    mensaje.textContent = "No hay compras registradas.";
    return;
  }

  try {
    const response = await fetch("http://localhost:8080/api/juegos");
    const juegos = await response.json();

    historial.forEach((pedido, index) => {
      const pedidoDiv = document.createElement("div");
      pedidoDiv.className = "pedido";

      pedidoDiv.innerHTML = `<h3>Pedido #${index + 1}</h3>`;
      let total = 0;

      pedido.forEach(juegoId => {
        const juego = juegos.find(j => j.id === juegoId);
        if (juego) {
          total += juego.precio;
          pedidoDiv.innerHTML += `
            <p>${juego.nombre} - $${juego.precio}</p>
          `;
        }
      });

      pedidoDiv.innerHTML += `<strong>Total: $${total.toFixed(2)}</strong>`;
      historialContainer.appendChild(pedidoDiv);
    });

  } catch (error) {
    console.error("Error al cargar historial:", error);
    mensaje.textContent = "Error al cargar el historial de compras.";
  }
});
