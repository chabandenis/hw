/*
  Выбрать игру
*/
import React, { useState, useEffect } from "react";

export default function SelectGame({ mainUser, seconUser, desk, setDesk }) {
  const [games, setGames] = useState([]);
  const [searchInput, setSearchInput] = useState("");
  const [selectedGame, setSelectedGame] = useState(null);

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
    const response = fetch(`/api/game/${mainUser.id}/${seconUser.id}`, {
      method: "GET", // Метод отправки
    })
      .then((response) => response.json())
      .then((games) => setGames(games));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    setDesk(selectedGame);
  };

  const filteredGames = games.filter((game) => {
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
        placeholder="Поиск ..."
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
            <th style={styles.personsTableItem}>id</th>
            <th style={styles.personsTableItem}>Дата</th>
            <th style={styles.personsTableItem}>За белых</th>
            <th style={styles.personsTableItem}>За черных</th>
          </tr>
        </thead>
        <tbody>
          {filteredGames.map((game, i) => (
            <tr
              style={styles.personsTableItem}
              key={i}
              onClick={() => {
                //setDesk(game);
                setSelectedGame(game);
                setSearchInput(game.dateGame);
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

        <button
          type="button"
          onClick={() => {
            // Обновление данных
            getApiData();
            setSearchInput("");
          }}
        >
          Обновить данные
        </button>
      </form>
    </div>
  );
}
