import React, { useState, useEffect } from "react";

export default function Desk() {
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
    const response = await fetch("/api/games/1")
      .then((response) => response.json())
      .then((users) => setUsers(users));
  };

  console.log("desk", users);

  if (
    users != null &&
    users.chessFair != null &&
    users.chessFair.desk &&
    users.chessFair.desk.length > 0
  ) {
    return (
      <div>
        <p>Пользователи</p>

        <table style={styles.personsTable}>
          <thead>
            <tr style={styles.personsTableItem}>
              <th style={styles.personsTableItem}>ID</th>
              <th style={styles.personsTableItem}>A</th>
              <th style={styles.personsTableItem}>B</th>
              <th style={styles.personsTableItem}>C</th>
              <th style={styles.personsTableItem}>D</th>
              <th style={styles.personsTableItem}>E</th>
              <th style={styles.personsTableItem}>F</th>
              <th style={styles.personsTableItem}>G</th>
              <th style={styles.personsTableItem}>H</th>
            </tr>
          </thead>
          <tbody>
            {users.chessFair.desk.map((user, i) => (
              <tr style={styles.personsTableItem} key={i}>
                <td style={styles.personsTableItem}>{user.leftClm}</td>
                <td style={styles.personsTableItem}>{user.arr[0].val}</td>
                <td style={styles.personsTableItem}>{user.arr[1].val}</td>
                <td style={styles.personsTableItem}>{user.arr[2].val}</td>
                <td style={styles.personsTableItem}>{user.arr[3].val}</td>
                <td style={styles.personsTableItem}>{user.arr[4].val}</td>
                <td style={styles.personsTableItem}>{user.arr[5].val}</td>
                <td style={styles.personsTableItem}>{user.arr[6].val}</td>
                <td style={styles.personsTableItem}>{user.arr[7].val}</td>
                <td style={styles.personsTableItem}>{user.rightClm}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    );
  } else {
    // Можно добавить альтернативный контент или сообщение, если users.chessFair.desk пуст
    return <div>Ошибка при подгрузки</div>;
  }
}

/*


*/
