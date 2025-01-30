import React, { useState } from 'react';
import axios from 'axios';
import './AssignmentPage.css'; // Import the CSS file

const AssignmentPage = () => {
  const [assignmentType, setAssignmentType] = useState('');
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
        <p className="assignment-needs-save">needs save</p>
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
          <option value="Spring Boot Service">spring boot service</option>
          <option value="Spring Boot Data JPA">spring boot data jpa</option>
          <option value="Spring Boot PostgreSQL">spring boot postgresql</option>
          <option value="Docker Compose Setup">docker compose setup</option>
          <option value="React Frontend Hooks">react frontend hooks</option>
          <option value="Python Problems">python problems</option>
          <option value="Assembly Boot Sector Hello World">assembly boot sector hello world</option>
          <option value="CTF Buffer Overflow">ctf buffer overflow</option>
          <option value="Docker Build">docker build</option>
          <option value="AWS Buckets">aws buckets</option>
          <option value="HashMaps and Sets">hashmaps and sets</option>
          <option value="Computations Counting">computations counting</option>
          <option value="Data Driven Websites">data driven websites</option>
          <option value="SQL Reporting">sql reporting</option>
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