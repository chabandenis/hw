import React, { useState, useEffect } from "react";

export default function ListUsers() {
  const [users, setUsers] = useState([]);

  const styles = {
    personsTable: {
      border: "1px solid steelblue",
      width: "300px",
      borderCollapse: "collapse",
    },

    personsTableItem: {
      padding: "5px",
      border: "1px solid steelblue",
    },
  };

  useEffect(() => {
    getApiData();
  }, []);

  const getApiData = async () => {
    const response = await fetch("/api/rest/admin-ui/users")
      .then((response) => response.json())
      .then((users) => setUsers(users));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    getApiData();
  };

  return (
    <div>
      <p>Пользователи</p>

      <table style={styles.personsTable}>
        <thead>
          <tr style={styles.personsTableItem}>
            <th style={styles.personsTableItem}>ID</th>
            <th style={styles.personsTableItem}>Name</th>
            <th style={styles.personsTableItem}>login</th>
          </tr>
        </thead>
        <tbody>
          {users.map((user, i) => (
            <tr style={styles.personsTableItem} key={i}>
              <td style={styles.personsTableItem}>{user.id}</td>
              <td style={styles.personsTableItem}>{user.name}</td>
              <td style={styles.personsTableItem}>{user.login}</td>
            </tr>
          ))}
        </tbody>
      </table>

      <form onSubmit={handleSubmit}>
        <button type="submit">Обновить</button>
      </form>
    </div>
  );
}
