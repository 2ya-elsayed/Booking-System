function register() {
    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;
  
    if (!email || !password) {
      showMessage("Please enter email and password");
      return;
    }
  
    const users = JSON.parse(localStorage.getItem("users")) || {};
    if (users[email]) {
      showMessage("User already exists. Please login.");
      return;
    }
  
    users[email] = { password: password, role: "user" }; // Default role
    localStorage.setItem("users", JSON.stringify(users));
    showMessage("Registration successful. You can now log in.", "success");
  }
  
  function login() {
    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;
  
    const users = JSON.parse(localStorage.getItem("users")) || {};
    if (users[email] && users[email].password === password) {
      sessionStorage.setItem("loggedInUser", email);
      sessionStorage.setItem("role", users[email].role);
      window.location.href = "index.html"; // redirect to homepage
    } else {
      showMessage("Invalid email or password.");
    }
  }
  
  function showMessage(msg, type = "danger") {
    const messageDiv = document.getElementById("message");
    messageDiv.className = `mt-3 text-${type}`;
    messageDiv.innerText = msg;
  }
  