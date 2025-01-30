import React, { useState } from 'react';
import axios from 'axios';
import './AssignmentPage.css'; // Import the CSS file

const AssignmentPage = () => {
  const [assignmentType, setAssignmentType] = useState('homework');
  const [githubUrl, setGithubUrl] = useState('');
  const [branchName, setBranchName] = useState('');
  const [error, setError] = useState('');
  const [success, setSuccess] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const newAssignment = {
        assignmentType,
        githubUrl,
        branchName,
      };
      await axios.post('/api/assignments', newAssignment);
      setSuccess('assignment submitted successfully!');
      setError('');
    } catch (err) {
      setError('failed to submit assignment');
      setSuccess('');
    }
  };

  return (
    <div className="assignment-container">
      <form onSubmit={handleSubmit} className="assignment-form">
        <h1 className="assignment-title">submit assignment</h1>
        <h2 className="assignment-number">#12345</h2>
        {error && <p className="assignment-error">{error}</p>}
        {success && <p className="assignment-success">{success}</p>}
        <label htmlFor="assignmentType" className="assignment-label">assignment type:</label>
        <select
          id="assignmentType"
          value={assignmentType}
          onChange={(e) => setAssignmentType(e.target.value)}
          className="assignment-input"
        >
          <option value="homework">homework</option>
          <option value="project">project</option>
          <option value="quiz">quiz</option>
        </select>
        <input
          type="url"
          placeholder="github url"
          value={githubUrl}
          onChange={(e) => setGithubUrl(e.target.value)}
          className="assignment-input"
          required
        />
        <input
          type="text"
          placeholder="branch name"
          value={branchName}
          onChange={(e) => setBranchName(e.target.value)}
          className="assignment-input"
          required
        />
        <div className="button-container">
          <button type="submit" className="assignment-button">submit</button>
          <button
            type="button"
            className="assignment-button"
            onClick={() => window.location.href = 'dashboard.html'}
          >
            back to dashboard
          </button>
        </div>
      </form>
    </div>
  );
};

export default AssignmentPage;