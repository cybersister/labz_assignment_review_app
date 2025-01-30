import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import './App.css';
import LoginPage from './components/LoginPage';
import AssignmentPage from './components/AssignmentPage';

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/login" element={<LoginPage />} />
        <Route path="/assignment" element={<AssignmentPage />} />
      </Routes>
    </Router>
  );
}

export default App;