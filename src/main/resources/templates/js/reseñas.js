// reseñas.js

document.addEventListener("DOMContentLoaded", () => {
  const lista = document.getElementById("lista-reseñas");
  const form = document.getElementById("form-reseña");

  // Reseñas de ejemplo
  const reseñas = [
    { nombre: "Carlos", comentario: "¡Excelente servicio y precios competitivos!" },
    { nombre: "Lucía", comentario: "Me encanta la variedad de juegos retro y modernos." }
  ];

  function renderizarReseña({ nombre, comentario }) {
    return `
      <div class="bg-[#2a2d32] p-4 rounded shadow-md">
        <p class="text-gray-100 font-semibold mb-1">${nombre}</p>
        <p class="text-gray-300 text-sm">${comentario}</p>
      </div>
    `;
  }

  reseñas.forEach(r => {
    lista.innerHTML += renderizarReseña(r);
  });

  // Agregar una nueva reseña desde formulario
  form.addEventListener("submit", (e) => {
    e.preventDefault();
    const nombre = document.getElementById("nombre").value.trim();
    const comentario = document.getElementById("comentario").value.trim();

    if (nombre && comentario) {
      const nuevaReseñaHTML = renderizarReseña({ nombre, comentario });
      lista.innerHTML = nuevaReseñaHTML + lista.innerHTML;
      form.reset();
    }
  });
});
