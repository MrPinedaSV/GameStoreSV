document.addEventListener("DOMContentLoaded", () => {
  const loginForm = document.getElementById("login-form");
  const registroForm = document.getElementById("registro-form");

  if (loginForm) {
    loginForm.addEventListener("submit", async (e) => {
      e.preventDefault();
      const email = document.getElementById("email").value;
      const password = document.getElementById("password").value;
      const errorMsg = document.getElementById("error-msg");

      try {
        const response = await fetch("http://localhost:8080/api/auth/login", {
          method: "POST",
          headers: {
            "Content-Type": "application/json"
          },
          body: JSON.stringify({ email, password })
        });

        if (response.ok) {
          const data = await response.json();
          localStorage.setItem("token", data.token);
          window.location.href = "catalogo.html";
        } else {
          errorMsg.textContent = "Correo o contraseña incorrectos";
        }
      } catch (error) {
        console.error("Error de conexión:", error);
        errorMsg.textContent = "Error de conexión con el servidor";
      }
    });
  }

  if (registroForm) {
    registroForm.addEventListener("submit", async (e) => {
      e.preventDefault();
      const nombre = document.getElementById("nombre").value;
      const email = document.getElementById("email").value;
      const password = document.getElementById("password").value;
      const errorMsg = document.getElementById("registro-error-msg");

      try {
        const response = await fetch("http://localhost:8080/api/auth/register", {
          method: "POST",
          headers: {
            "Content-Type": "application/json"
          },
          body: JSON.stringify({ nombre, email, password })
        });

        if (response.ok) {
          alert("Registro exitoso. Ahora puedes iniciar sesión.");
          window.location.href = "login.html";
        } else {
          errorMsg.textContent = "No se pudo registrar el usuario";
        }
      } catch (error) {
        console.error("Error de conexión:", error);
        errorMsg.textContent = "Error al conectar con el servidor";
      }
    });
  }
});
