import React, { useState, useEffect } from "react";

export default function ListUsers() {
  const [users, setUsers] = useState([]);
  const [searchInput, setSearchInput] = useState("");
  const [selectedUser, setSelectedUser] = useState(null);

  const styles = {
    personsTable: {
      border: "1px solid steelblue",
      width: "300px",
      borderCollapse: "collapse",
    },

    personsTableItem: {
      padding: "5px",
      border: "1px solid steelblue",
      cursor: "pointer",
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

  const handleSearch = (e) => {
    setSearchInput(e.target.value);
  };

  const handleSelectUser = (user) => {
    setSelectedUser(user);
  };

  const filteredUsers = users.filter((user) => {
    return Object.values(user)
      .join("")
      .toLowerCase()
      .includes(searchInput.toLowerCase());
  });

  return (
    <div>
      <p>___________</p>
      <p>Выберите второго пользователя</p>

      <input
        type="text"
        placeholder="Поиск по имени ..."
        value={searchInput}
        onChange={(e) => setSearchInput(e.target.value)}
      />

      <table style={styles.personsTable}>
        <thead>
          <tr style={styles.personsTableItem}>
            <th style={styles.personsTableItem}>ID</th>
            <th style={styles.personsTableItem}>Name</th>
            <th style={styles.personsTableItem}>login</th>
          </tr>
        </thead>
        <tbody>
          {filteredUsers.map((user, i) => (
            <tr
              style={styles.personsTableItem}
              key={i}
              onClick={() => handleSelectUser(user)}
            >
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
      <div>
        <p>
          Выбранный пользователь:{" "}
          {selectedUser ? selectedUser.name : "Нет выбранного пользователя"}
        </p>
      </div>
    </div>
  );
}
