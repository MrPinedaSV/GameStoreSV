$(document).ready(function() {
  // Aquí simulamos datos de juegos (en la práctica los cargarás desde tu API)
  const juegosDestacados = [
    {
      id: 1,
      nombre: "The Legend of Zelda: Breath of the Wild",
      descripcion: "Aventura épica en un mundo abierto.",
      imagen: "https://example.com/zelda.jpg",
      precio: 59.99
    },
    {
      id: 2,
      nombre: "Cyberpunk 2077",
      descripcion: "RPG futurista con mucha acción.",
      imagen: "https://example.com/cyberpunk.jpg",
      precio: 49.99
    },
    {
      id: 3,
      nombre: "Minecraft",
      descripcion: "Construye y explora mundos infinitos.",
      imagen: "https://example.com/minecraft.jpg",
      precio: 26.95
    }
  ];

  const container = $('#game-list');
  juegosDestacados.forEach(juego => {
    const card = `
      <div class="col">
        <div class="card h-100">
          <img src="${juego.imagen}" class="card-img-top" alt="${juego.nombre}">
          <div class="card-body">
            <h5 class="card-title">${juego.nombre}</h5>
            <p class="card-text">${juego.descripcion}</p>
            <p class="card-text"><strong>$${juego.precio.toFixed(2)}</strong></p>
            <button class="btn btn-primary btn-agregar" data-id="${juego.id}">Agregar al carrito</button>
          </div>
        </div>
      </div>
    `;
    container.append(card);
  });

  // Evento para agregar al carrito (solo demo)
  $('.btn-agregar').click(function() {
    const idJuego = $(this).data('id');
    alert(`Juego con ID ${idJuego} agregado al carrito!`);
    // Aquí iría lógica para llamar a tu API y agregar al carrito real
  });
});
function logout() {
    localStorage.removeItem("jwt");
    localStorage.removeItem("rol");
    window.location.href = "login.html";
}
