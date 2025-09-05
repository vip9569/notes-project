import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import './Login.css'

export default function Login() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");

  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError("");

    try {
      const response = await axios.post(
        "http://localhost:8080/api/auth/login",
        null, // because we send params
        { params: { email, password } }
      );

      if (response.status === 200) {
        // ✅ Store token if backend sends it
        if (response.data.token) {
          localStorage.setItem("token", response.data.token);
        }
        // Navigate to profile page
        navigate("/profile");
      }
    } catch (err) {
      if (err.response) {
        setError("Login failed: " + err.response.status);
      } else {
        setError("Network error");
      }
    }
  };

  return (
    <div className="login-container">
  <h2>Login</h2>
  {error && <p className="error">{error}</p>}
  <form onSubmit={handleSubmit}>
    <div>
      <label>Email:</label>
      <input
        type="email"
        value={email}
        onChange={(e) => setEmail(e.target.value)}
        required
      />
    </div>
    <div>
      <label>Password:</label>
      <input
        type="password"
        value={password}
        onChange={(e) => setPassword(e.target.value)}
        required
      />
    </div>
    <button type="submit">Login</button>
  </form>
  <p>if already have no account ? <a href="/register">Create Account</a></p>
</div>
  );
}

