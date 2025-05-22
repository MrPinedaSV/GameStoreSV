// util.js

function renderizarJuego(juego) {
  return `
    <div class="bg-[#2f3342] rounded-xl overflow-hidden shadow-lg hover:shadow-2xl transition-transform hover:scale-[1.03] duration-300 flex flex-col">
      <img src="${juego.imagen}" alt="${juego.nombre}" class="w-full h-48 object-cover" />
      <div class="p-4 flex-1 flex flex-col justify-between">
        <div>
          <h3 class="text-xl font-bold text-white mb-1">${juego.nombre}</h3>
          <p class="text-sm text-gray-300 mb-3">${juego.descripcion}</p>
        </div>
        <div class="mt-auto">
          <p class="text-xl text-lime-400 font-semibold mb-3">$${juego.precio.toFixed(2)}</p>
          <button onclick='agregarAlCarrito(${JSON.stringify(juego)})' class="w-full bg-gradient-to-r from-indigo-500 to-fuchsia-500 hover:from-pink-500 hover:to-yellow-500 text-white font-semibold py-2 rounded-lg shadow-md transition">
            Comprar
          </button>
        </div>
      </div>
    </div>
  `;
}

function agregarAlCarrito(juego) {
  let carrito = JSON.parse(localStorage.getItem("carrito")) || [];
  carrito.push(juego);
  localStorage.setItem("carrito", JSON.stringify(carrito));
  alert(`"${juego.nombre}" ha sido agregado al carrito 🎮`);
}

function insertarNavbar() {
  const navbar = `
    <nav class="bg-[#1b1e24] text-gray-300 shadow-md p-4 flex justify-center space-x-8 text-sm font-medium mb-6 sticky top-0 z-50 backdrop-blur-sm">
      <a href="index.html" class="hover:text-white">Inicio</a>
      <a href="admin_panel.html" class="hover:text-white">Admin</a>
      <a href="pedido_estado.html" class="hover:text-white">Pedidos</a>
      <a href="reseñas.html" class="hover:text-white">Reseñas</a>
      <a href="login.html" class="hover:text-white">Login</a>
    </nav>
  `;
  document.body.insertAdjacentHTML("afterbegin", navbar);
}

document.addEventListener("DOMContentLoaded", insertarNavbar);
