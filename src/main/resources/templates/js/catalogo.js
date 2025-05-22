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

    // Si no hay juegos, cargar datos ficticios para desarrollo
    if (juegos.length === 0) {
      juegos = getJuegosDemo();
    }

    juegos.forEach(juego => {
      const juegoCard = document.createElement("div");
      juegoCard.className = "bg-[#2f3342] rounded-xl shadow-md p-4 flex flex-col items-center text-center";

      juegoCard.innerHTML = `
        <img src="${juego.imagenUrl}" alt="${juego.nombre}" class="w-full h-48 object-cover rounded-lg mb-4 border border-gray-600" />
        <h3 class="text-lg font-bold text-white mb-2">${juego.nombre}</h3>
        <p class="text-sm text-gray-400 mb-2">${juego.descripcion}</p>
        <p class="text-lime-400 font-semibold mb-4">Precio: $${juego.precio.toFixed(2)}</p>
        <button onclick="agregarAlCarrito(${juego.id})"
          class="bg-indigo-600 hover:bg-indigo-700 text-white px-4 py-1 rounded-full transition">
          Agregar al carrito
        </button>
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

// Datos demo para desarrollo y pruebas
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
    },
    {
      id: 5,
      nombre: "Tetris",
      descripcion: "El rompecabezas más famoso del mundo.",
      precio: 4.99,
      imagenUrl: "https://upload.wikimedia.org/wikipedia/en/7/7c/Tetris_Boxshot.jpg"
    },
    {
      id: 6,
      nombre: "God of War: Ragnarök",
      descripcion: "Kratos regresa en una épica aventura nórdica.",
      precio: 69.99,
      imagenUrl: "https://upload.wikimedia.org/wikipedia/en/3/34/God_of_War_Ragnar%C3%B6k_cover.jpg"
    },
    {
      id: 7,
      nombre: "DOOM (1993)",
      descripcion: "El FPS que lo empezó todo.",
      precio: 5.99,
      imagenUrl: "https://upload.wikimedia.org/wikipedia/en/5/57/Doom_cover_art.jpg"
    },
    {
      id: 8,
      nombre: "Hogwarts Legacy",
      descripcion: "Explora el mundo mágico como nunca antes.",
      precio: 59.99,
      imagenUrl: "https://cdn.cloudflare.steamstatic.com/steam/apps/990080/header.jpg"
    }
  ];
}
