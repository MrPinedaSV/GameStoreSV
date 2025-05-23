// reseñas.js

document.addEventListener("DOMContentLoaded", () => {
  const lista = document.getElementById("lista-reseñas");
  const form = document.getElementById("form-reseña");

  const reseñas = [
    { nombre: "Carlos", comentario: "¡Excelente servicio y precios competitivos!" },
    { nombre: "Lucía", comentario: "Me encanta la variedad de juegos retro y modernos." }
  ];

  const renderizarReseña = ({ nombre, comentario }) => `
    <div class="bg-[#2a2d32] p-4 rounded-xl shadow-md border border-gray-700/40">
      <p class="text-white font-semibold mb-1">${nombre}</p>
      <p class="text-gray-300 text-sm">${comentario}</p>
    </div>
  `;

  reseñas.forEach(r => {
    lista.innerHTML += renderizarReseña(r);
  });

  form.addEventListener("submit", e => {
    e.preventDefault();
    const nombre = document.getElementById("nombre").value.trim();
    const comentario = document.getElementById("comentario").value.trim();

    if (nombre && comentario) {
      lista.innerHTML = renderizarReseña({ nombre, comentario }) + lista.innerHTML;
      form.reset();
    }
  });
});
