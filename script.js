import { initializeApp } from "https://www.gstatic.com/firebasejs/10.7.0/firebase-app.js";
import { 
  getAuth, 
  createUserWithEmailAndPassword,
  GoogleAuthProvider, 
  OAuthProvider, 
  signInWithPopup 
} from "https://www.gstatic.com/firebasejs/10.7.0/firebase-auth.js";

// 🔥 Config Firebase
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

// 🔥 Login email/password
document.addEventListener("DOMContentLoaded", () => {
    const form = document.querySelector("form");
    const emailInput = document.querySelector('input[type="email"]');
    const passwordInput = document.querySelector('input[type="password"]');
    const button = document.querySelector(".login-btn");

    const message = document.createElement("div");
    message.style.marginTop = "15px";
    message.style.textAlign = "center";
    message.style.fontSize = "14px";
    form.appendChild(message);

    form.addEventListener("submit", (e) => {
        e.preventDefault();
        message.textContent = "";
        button.disabled = true;
        button.textContent = "Connexion...";
        button.style.opacity = "0.8";

        const email = emailInput.value.trim();
        const password = passwordInput.value.trim();

        if (!email || !password) {
            showMessage("Veuillez remplir tous les champs.", false);
            button.disabled = false;
            button.textContent = "Create account";
            button.style.opacity = "1";
            return;
        }

        // 🔥 Firebase email/password
        createUserWithEmailAndPassword(auth, email, password)
            .then(() => {
                showMessage("Compte créé avec succès ✔", true);
                setTimeout(() => window.location.href = "dashboard.html", 1000);
            })
            .catch(err => {
                showMessage(err.message, false);
            })
            .finally(() => {
                button.disabled = false;
                button.textContent = "Create account";
                button.style.opacity = "1";
            });
    });

    function showMessage(text, success) {
        message.textContent = text;
        message.style.color = success ? "#00ffae" : "#ff6b6b";
    }

    // 🔥 Social login
    const googleProvider = new GoogleAuthProvider();
    const appleProvider = new OAuthProvider('apple.com');
    const microsoftProvider = new OAuthProvider('microsoft.com');

    document.getElementById("googleBtn").addEventListener("click", () => {
        signInWithPopup(auth, googleProvider)
            .then(() => window.location.href = "dashboard.html")
            .catch(err => alert(err.message));
    });

    document.getElementById("appleBtn").addEventListener("click", () => {
        signInWithPopup(auth, appleProvider)
            .then(() => window.location.href = "dashboard.html")
            .catch(err => alert(err.message));
    });

    document.getElementById("microsoftBtn").addEventListener("click", () => {
        signInWithPopup(auth, microsoftProvider)
            .then(() => window.location.href = "dashboard.html")
            .catch(err => alert(err.message));
    });
});
