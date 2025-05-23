document.addEventListener("DOMContentLoaded", () => {
  const loginForm = document.getElementById("login-form");
  const registroForm = document.getElementById("registro-form");

  if (loginForm) {
    loginForm.addEventListener("submit", async (e) => {
      e.preventDefault();
      const email = document.getElementById("email").value.trim();
      const password = document.getElementById("password").value.trim();
      const errorMsg = document.getElementById("error-msg");

      if (!email || !password) {
        mostrarError(errorMsg, "Por favor, completa todos los campos.");
        return;
      }

      try {
        const data = await fetchPost("http://localhost:8080/api/auth/login", { email, password });

        if (data.token) {
          localStorage.setItem("token", data.token);
          window.location.href = "catalogo.html";
        } else {
          mostrarError(errorMsg, data.mensaje || "Correo o contraseña incorrectos.");
        }
      } catch (err) {
        mostrarError(errorMsg, "Error de conexión con el servidor.");
        console.error(err);
      }
    });
  }

  if (registroForm) {
    registroForm.addEventListener("submit", async (e) => {
      e.preventDefault();
      const nombre = document.getElementById("nombre").value.trim();
      const email = document.getElementById("email").value.trim();
      const password = document.getElementById("password").value.trim();
      const errorMsg = document.getElementById("registro-error-msg");

      if (!nombre || !email || !password) {
        mostrarError(errorMsg, "Todos los campos son obligatorios.");
        return;
      }

      try {
        const data = await fetchPost("http://localhost:8080/api/auth/register", { nombre, email, password });

        if (data.success || data.ok || !data.error) {
          alert("Registro exitoso. Ahora puedes iniciar sesión.");
          window.location.href = "login.html";
        } else {
          mostrarError(errorMsg, data.mensaje || "No se pudo registrar el usuario.");
        }
      } catch (err) {
        mostrarError(errorMsg, "Error al conectar con el servidor.");
        console.error(err);
      }
    });
  }
});

// Función reutilizable para peticiones POST con JSON
async function fetchPost(url, body) {
  const response = await fetch(url, {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify(body)
  });

  return await response.json();
}

// Mostrar mensaje de error
function mostrarError(elemento, mensaje) {
  if (elemento) {
    elemento.textContent = mensaje;
  }
}
