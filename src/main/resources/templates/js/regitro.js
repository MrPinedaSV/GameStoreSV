document.getElementById("form-registro").addEventListener("submit", e => {
  e.preventDefault();
  const email = document.getElementById("email").value;
  alert("Cuenta creada (simulado)");
  localStorage.setItem("usuario", email);
  window.location.href = "index.html";
});
