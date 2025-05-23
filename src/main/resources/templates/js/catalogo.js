document.addEventListener("DOMContentLoaded", async () => {
  const token = localStorage.getItem("token");
  const catalogoContainer = document.getElementById("catalogo-container");
  const errorMsg = document.getElementById("mensaje-error");

  if (!token) {
    alert("Debes iniciar sesión para ver el catálogo.");
    window.location.href = "login.html";
    return;
  }

  try {
    const response = await fetch("http://localhost:8080/api/juegos", {
      headers: {
        "Authorization": `Bearer ${token}`
      }
    });

    if (!response.ok) throw new Error("Error al cargar el catálogo");

    let juegos = await response.json();
    if (juegos.length === 0) {
      juegos = getJuegosDemo();
    }

    juegos.forEach(juego => {
      const juegoCard = document.createElement("div");
      juegoCard.className = "bg-[#2f3342] rounded-xl overflow-hidden shadow-lg hover:shadow-2xl transition-transform hover:scale-[1.03] duration-300 flex flex-col";

      juegoCard.innerHTML = `
        <img src="${juego.imagenUrl}" alt="${juego.nombre}" class="w-full h-48 object-cover" />
        <div class="p-4 flex-1 flex flex-col justify-between text-center">
          <div>
            <h3 class="text-xl font-bold text-white mb-1">${juego.nombre}</h3>
            <p class="text-sm text-gray-300 mb-3">${juego.descripcion}</p>
          </div>
          <div>
            <p class="text-xl text-lime-400 font-semibold mb-4">$${juego.precio.toFixed(2)}</p>
            <button onclick="agregarAlCarrito(${juego.id})"
              class="bg-indigo-600 hover:bg-indigo-500 text-white px-4 py-2 rounded-lg text-sm transition shadow-md">
              Agregar al carrito
            </button>
          </div>
        </div>
      `;
      catalogoContainer.appendChild(juegoCard);
    });

  } catch (error) {
    console.error("Error:", error);
    errorMsg.textContent = "No se pudo cargar el catálogo. Intenta más tarde.";
  }
});

function agregarAlCarrito(juegoId) {
  let carrito = JSON.parse(localStorage.getItem("carrito")) || [];
  carrito.push(juegoId);
  localStorage.setItem("carrito", JSON.stringify(carrito));
  alert("Juego agregado al carrito");
}

function getJuegosDemo() {
  return [
    {
      id: 1,
      nombre: "The Legend of Zelda: Ocarina of Time",
      descripcion: "Un clásico de aventuras para Nintendo 64.",
      precio: 29.99,
      imagenUrl: "https://upload.wikimedia.org/wikipedia/en/5/57/The_Legend_of_Zelda_Ocarina_of_Time.jpg"
    },
    {
      id: 2,
      nombre: "Elden Ring",
      descripcion: "Un mundo abierto oscuro y desafiante de FromSoftware.",
      precio: 59.99,
      imagenUrl: "https://cdn.cloudflare.steamstatic.com/steam/apps/1245620/header.jpg"
    },
    {
      id: 3,
      nombre: "Super Mario Bros.",
      descripcion: "El juego que definió el género de plataformas.",
      precio: 9.99,
      imagenUrl: "https://upload.wikimedia.org/wikipedia/en/0/03/Super_Mario_Bros._box.png"
    },
    {
      id: 4,
      nombre: "Cyberpunk 2077",
      descripcion: "Un RPG futurista ambientado en Night City.",
      precio: 49.99,
      imagenUrl: "https://cdn.cloudflare.steamstatic.com/steam/apps/1091500/header.jpg"
    }
  ];
}
