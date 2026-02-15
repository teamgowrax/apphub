<script>
import { initializeApp } from "https://www.gstatic.com/firebasejs/10.7.0/firebase-app.js";
import { 
  getAuth, 
  GoogleAuthProvider, 
  OAuthProvider, 
  signInWithPopup 
} from "https://www.gstatic.com/firebasejs/10.7.0/firebase-auth.js";

const firebaseConfig = {
  apiKey: "AIzaSyDSekwBtU3u3DquxJIm9N7urL1_8zdrm34",
  authDomain: "apphub-94be4.firebaseapp.com",
  projectId: "apphub-94be4",
  storageBucket: "apphub-94be4.firebasestorage.app",
  messagingSenderId: "86249833874",
  appId: "1:86249833874:web:d83466be51eaf27a4a2d4b",
  measurementId: "G-B16MB4RCTQ"
};

const app = initializeApp(firebaseConfig);
const auth = getAuth(app);

document.addEventListener("DOMContentLoaded", () => {
    const form = document.querySelector("form");
    const usernameInput = document.querySelector('input[type="text"]');
    const passwordInput = document.querySelector('input[type="password"]');
    const button = document.querySelector(".login-btn");

    // Message dynamique
    const message = document.createElement("div");
    message.style.marginTop = "15px";
    message.style.textAlign = "center";
    message.style.fontSize = "14px";
    form.appendChild(message);

    form.addEventListener("submit", (e) => {
        e.preventDefault();
        message.textContent = "";
        message.style.color = "#fff";

        const username = usernameInput.value.trim();
        const password = passwordInput.value.trim();

        // Validation simple
        if (!username || !password) {
            showMessage("Veuillez remplir tous les champs.", false);
            return;
        }

        // Loading state
        button.disabled = true;
        button.textContent = "Connexion...";
        button.style.opacity = "0.8";

        // Simulation requête serveur
        setTimeout(() => {
            // Exemple logique (à remplacer par un vrai backend)
            if (username === "admin" && password === "admin123") {
                showMessage("Connexion réussie ✔", true);

                // Exemple redirection
                setTimeout(() => {
                    window.location.href = "dashboard.html";
                }, 1000);
            } else {
                showMessage("Identifiants incorrects ❌", false);
            }

            button.disabled = false;
            button.textContent = "Login";
            button.style.opacity = "1";
        }, 1200);
    });

    function showMessage(text, success) {
        message.textContent = text;
        message.style.color = success ? "#00ffae" : "#ff6b6b";
    }
});
// 🔥 Google
const googleProvider = new GoogleAuthProvider();
document.querySelector("#googleBtn").addEventListener("click", () => {
    signInWithPopup(auth, googleProvider)
        .then(() => {
            alert("Connexion Google réussie ✔");
            window.location.href = "dashboard.html";
        })
        .catch(err => alert(err.message));
});

// 🔥 Apple
const appleProvider = new OAuthProvider('apple.com');
document.querySelector("#appleBtn").addEventListener("click", () => {
    signInWithPopup(auth, appleProvider)
        .then(() => {
            alert("Connexion Apple réussie ✔");
            window.location.href = "dashboard.html";
        })
        .catch(err => alert(err.message));
});

// 🔥 Microsoft
const microsoftProvider = new OAuthProvider('microsoft.com');
document.querySelector("#microsoftBtn").addEventListener("click", () => {
    signInWithPopup(auth, microsoftProvider)
        .then(() => {
            alert("Connexion Microsoft réussie ✔");
            window.location.href = "dashboard.html";
        })
        .catch(err => alert(err.message));
</script>
