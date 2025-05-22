function renderizarJuegoAdmin(juego) {
  return `
    <div class="bg-[#2f3342] rounded-xl overflow-hidden shadow-lg hover:shadow-2xl transition-transform hover:scale-[1.03] duration-300 flex flex-col">
      <img src="${juego.imagen}" alt="${juego.nombre}" class="w-full h-48 object-cover" />
      <div class="p-4 flex-1 flex flex-col justify-between">
        <div>
          <h3 class="text-xl font-bold text-white mb-1">${juego.nombre}</h3>
          <p class="text-sm text-gray-300 mb-3">${juego.descripcion}</p>
        </div>
        <div>
          <p class="text-xl text-lime-400 font-semibold mb-4">$${juego.precio.toFixed(2)}</p>
          <div class="flex justify-between space-x-2">
            <button class="bg-blue-600 hover:bg-blue-500 text-white px-4 py-2 rounded-lg text-sm transition shadow-md">Editar</button>
            <button class="bg-red-600 hover:bg-red-500 text-white px-4 py-2 rounded-lg text-sm transition shadow-md">Eliminar</button>
          </div>
        </div>
      </div>
    </div>
  `;
}

document.addEventListener("DOMContentLoaded", () => {
  const juegos = [
    {
      nombre: "The Witcher 3",
      descripcion: "Una épica aventura en un mundo abierto.",
      precio: 29.99,
      imagen: "img/witcher3.jpg"
    },
    {
      nombre: "Cyberpunk 2077",
      descripcion: "Explora Night City con estilo futurista.",
      precio: 39.99,
      imagen: "img/cyberpunk2077.jpg"
    }
  ];

  const contenedor = document.getElementById("admin-juegos");
  juegos.forEach(juego => {
    contenedor.innerHTML += renderizarJuegoAdmin(juego);
  });
});

function agregarJuego() {
  alert("Funcionalidad de agregar juego aún no implementada.");
}
