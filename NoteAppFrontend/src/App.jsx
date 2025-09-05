import './App.css'
import React from 'react'
import {Routes, Route, Link} from 'react-router-dom'
import Login from './Components/Login'
import Profile from './Components/Profile'
import Register from './Components/Register'

function App() {

  return (
    <div>
      {/* <nav>
        <Link to="/"><Login/></Link>
      </nav> */}
      {/* <Login/> */}

      <Routes>
        <Route path="/login" element={<Login/>}/>
        <Route path="/register" element={<Register/>} />
        <Route path="/profile" element={<Profile/>} />
      </Routes>
    </div>
  )
}

export default App
