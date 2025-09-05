import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";

export default function Register() {
  const [username, setUsername] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [mobile, setMobile] = useState("");
  const [error, setError] = useState("");

  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError("");

    try {
      const response = await axios.post("http://localhost:8080/api/auth/register", {
        username,
        email,
        password,
        mobile,
      });

      if (response.status === 201) {
        alert("Registration successful! Please login.");
        navigate("/login"); // ✅ Navigate to Login page
      }
    } catch (err) {
      if (err.response) {
        if (err.response.status === 409) {
          setError("Email already exists.");
        } else {
          setError("Registration failed: " + err.response.status);
        }
      } else {
        setError("Network error. Try again.");
      }
    }
  };

  return (
    <div className="login-container">
  <h2>Register</h2>
  {error && <p className="error">{error}</p>}
  <form onSubmit={handleSubmit}>
    <div>
      <label>User Name:</label>
      <input
        type="text"
        value={username}
        onChange={(e) => setUsername(e.target.value)}
        required
      />
    </div>
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
      <label>Mobile Number:</label>
      <input
        type="tel"
        value={mobile}
        onChange={(e) => setMobile(e.target.value)}
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
    <button type="submit">Register</button>
  </form>
  <p>if already have account ? <a href="/login">Login</a></p>
</div>
  );
}

