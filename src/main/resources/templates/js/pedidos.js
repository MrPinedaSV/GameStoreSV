// pedidos.js

document.addEventListener("DOMContentLoaded", () => {
  const estado = document.getElementById("estado-pedido");

  const pedido = {
    numero: "#124578",
    entrega: "21 de mayo de 2025",
    transportista: "ExpressGame Logistics"
  };

  estado.innerHTML = `
    <h2 class="text-2xl font-bold text-lime-400 mb-4">¡Tu pedido está en camino! 🚚</h2>
    <div class="space-y-2 text-sm text-gray-300">
      <p>Número de pedido: <span class="font-semibold text-white">${pedido.numero}</span></p>
      <p>Fecha estimada de entrega: <span class="font-semibold text-white">${pedido.entrega}</span></p>
      <p>Transportista: <span class="font-semibold text-white">${pedido.transportista}</span></p>
    </div>
    <div class="mt-6">
      <button class="bg-gradient-to-r from-indigo-500 to-fuchsia-500 hover:from-pink-500 hover:to-yellow-500 text-white font-semibold py-2 px-6 rounded-full transition">
        Ver más detalles
      </button>
    </div>
  `;
});
