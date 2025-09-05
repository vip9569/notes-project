import { useState } from "react";
import Login from "./Login.jsx"
import "./Home.css";


export default function App() {
const [showLogin, setShowLogin] = useState(false);


return (
<div className="app-container">
{!showLogin ? (
// Landing Page
<div className="glass-card">
<h1 className="title">Welcome to NoteApp</h1>
<p className="subtitle">
Organize your thoughts, capture ideas, and stay productive.
</p>
<button onClick={() => setShowLogin(true)} className="btn">
Login
</button>
</div>
) : (
// Login Page
// <div className="glass-card">
// <h2 className="title">Login</h2>
// <form className="form">
// <input type="email" placeholder="Email" className="input" />
// <input type="password" placeholder="Password" className="input" />
// <button type="submit" className="btn">
// Sign In
// </button>
// </form>
// </div>
  <Login/>
)}
</div>
);
}
