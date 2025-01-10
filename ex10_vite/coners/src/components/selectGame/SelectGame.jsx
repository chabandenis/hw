import React, { useState, useEffect } from "react";

export default function SelectGame({
  mainUser,
  seconUser,
  desk,
  setDesk,
  updateSecondUser,
}) {
  const [games, setGames] = useState([]);
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
    const response = await fetch(
      "/api/games/" + mainUser.id + "/" + seconUser.id
    ).then((response) => response.json());
    //.then((games) => setGames(games));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    updateSecondUser(selectedUser);
  };

  const filteredUsers = games.filter((game) => {
    return Object.values(game)
      .join("")
      .toLowerCase()
      .includes(searchInput.toLowerCase());
  });

  return (
    <div>
      <p>==========================================================</p>
      <p>Выберите игру</p>

      <input
        type="text"
        placeholder="Поиск по дате ..."
        value={searchInput}
        onChange={(e) => setSearchInput(e.target.value)}
      />

      {/*     <div>
        <p>
          Выбранный пользователь:{" "}
          {selectedUser ? selectedUser.name : "Нет выбранного пользователя"}
        </p>
      </div> */}

      <table style={styles.personsTable}>
        <thead>
          <tr style={styles.personsTableItem}>
            <th style={styles.personsTableItem}>ID</th>
            <th style={styles.personsTableItem}>date</th>
            <th style={styles.personsTableItem}>white</th>
            <th style={styles.personsTableItem}>black</th>
          </tr>
        </thead>
        <tbody>
          {filteredUsers.map((game, i) => (
            <tr
              style={styles.personsTableItem}
              key={i}
              onClick={() => {
                setSelectedUser(game);
                setSearchInput(game.name);
              }}
            >
              <td style={styles.personsTableItem}>{game.id}</td>
              <td style={styles.personsTableItem}>{game.dateGame}</td>
              <td style={styles.personsTableItem}>{game.userWhite.name}</td>
              <td style={styles.personsTableItem}>{game.userBlack.name}</td>
            </tr>
          ))}
        </tbody>
      </table>
      <form onSubmit={handleSubmit}>
        <button type="submit">Выбрать игру</button>
      </form>
    </div>
  );
}
