import React from 'react';
import './ReviewerDashboard.css'; // Import the CSS file

const ReviewerDashboard = () => {
  const handleLogout = () => {
    window.location.href = '/login';
  };

  const assignments = [
    { number: '#56789', type: 'spring boot service', userId: 'U452310' },
    { number: '#56789', type: 'spring boot service', userId: 'U452310' },
    { number: '#56789', type: 'spring boot service', userId: 'U452310' },
    { number: '#56789', type: 'spring boot service', userId: 'U452310' },
    { number: '#56789', type: 'spring boot service', userId: 'U452310' },
    { number: '#56789', type: 'spring boot service', userId: 'U452310' },
    { number: '#56789', type: 'spring boot service', userId: 'U452310' },
    { number: '#56789', type: 'spring boot service', userId: 'U452310' },
    { number: '#56789', type: 'spring boot service', userId: 'U452310' },
  ];

  return (
    <div className="dashboard-container">
      <h1 className="dashboard-title">reviewer dashboard</h1>
      <button className="logout-button" onClick={handleLogout}>logout</button>
      <div className="dashboard-section">
        <h2 className="section-title">in review</h2>
        <div className="assignments-container">
          {assignments.slice(0, 2).map((assignment, index) => (
            <div key={index} className="assignment-box">
              <p>Assignment Number: {assignment.number}</p>
              <p>Assignment Type: {assignment.type}</p>
              <p>User ID: {assignment.userId}</p>
              <button className="view-button">view</button>
            </div>
          ))}
        </div>
      </div>
      <div className="dashboard-section">
        <h2 className="section-title">submitted for review & resubmitted</h2>
        <div className="assignments-container">
          {assignments.slice(2, 6).map((assignment, index) => (
            <div key={index} className="assignment-box">
              <p>Assignment Number: {assignment.number}</p>
              <p>Assignment Type: {assignment.type}</p>
              <p>User ID: {assignment.userId}</p>
              <button className="view-button">view</button>
            </div>
          ))}
        </div>
      </div>
      <div className="dashboard-section">
        <h2 className="section-title">completed</h2>
        <div className="assignments-container">
          {assignments.slice(6, 9).map((assignment, index) => (
            <div key={index} className="assignment-box">
              <p>Assignment Number: {assignment.number}</p>
              <p>Assignment Type: {assignment.type}</p>
              <p>User ID: {assignment.userId}</p>
              <button className="view-button">view</button>
            </div>
          ))}
        </div>
      </div>
    </div>
  );
};

export default ReviewerDashboard;