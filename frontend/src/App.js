import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import './App.css';
import LoginPage from './components/LoginPage';
import AssignmentPage from './components/AssignmentPage';
import ReviewerDashboard from './components/ReviewerDashboard';

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/login" element={<LoginPage />} />
        <Route path="/assignment" element={<AssignmentPage />} />
        <Route path="/reviewer-dashboard" element={<ReviewerDashboard />} />

      </Routes>
    </Router>
  );
}

export default App;