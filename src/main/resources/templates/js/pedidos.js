// pedidos.js
document.addEventListener("DOMContentLoaded", () => {
  const estado = document.getElementById("estado-pedido");

  const pedidoHTML = `
    <h2 class="text-2xl font-bold mb-4 text-lime-400">¡Tu pedido está en camino! 🚚</h2>
    <div class="space-y-2 text-gray-300 text-sm">
      <p>Número de pedido: <span class="font-semibold text-white">#124578</span></p>
      <p>Fecha estimada de entrega: <span class="font-semibold text-white">21 de mayo de 2025</span></p>
      <p>Transportista: <span class="font-semibold text-white">ExpressGame Logistics</span></p>
    </div>
    <div class="mt-6">
      <button class="bg-gradient-to-r from-indigo-500 to-fuchsia-500 hover:from-pink-500 hover:to-yellow-500 text-white font-semibold py-2 px-6 rounded-full transition">
        Ver más detalles
      </button>
    </div>
  `;

  estado.innerHTML = pedidoHTML;
});
