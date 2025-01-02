import React, { useState, useEffect } from "react";

export default function Desk() {
  const [desk, setDesk] = useState([]);
  const [clickedRow, setClickedRow] = useState(-1);
  const [clickedCol, setClickedCol] = useState(-1);
  const [activeCell, setActiveCell] = useState({ row: -1, col: -1 });

  const styles = {
    personsTable: {
      padding: "5px",
      border: "1px solid steelblue",
      width: "120px",
      borderCollapse: "collapse",
      fontStyle: "normal",
      active: {
        background: "#99ff99",
        fontWeight: "bold",
      },
    },
  };

  useEffect(() => {
    getApiData();
  }, []);

  const getApiData = async () => {
    const response = await fetch("/api/games/1")
      .then((response) => response.json())
      .then((desk) => setDesk(desk));
  };

  const handleCellClick = (row, col) => {
    setClickedRow(row);
    setClickedCol(col);
    setActiveCell({ row, col });
  };

  //console.log("desk", desk);

  if (
    desk != null &&
    desk.chessFair != null &&
    desk.chessFair.desk &&
    desk.chessFair.desk.length > 0
  ) {
    return (
      <div>
        <p>___________</p>
        <p>Шахматная доска</p>

        <table style={styles.personsTable}>
          <thead>
            <tr style={styles.personsTable}>
              <th style={styles.personsTable}>ID</th>
              <th style={styles.personsTable}>A</th>
              <th style={styles.personsTable}>B</th>
              <th style={styles.personsTable}>C</th>
              <th style={styles.personsTable}>D</th>
              <th style={styles.personsTable}>E</th>
              <th style={styles.personsTable}>F</th>
              <th style={styles.personsTable}>G</th>
              <th style={styles.personsTable}>H</th>
            </tr>
          </thead>
          <tbody>
            {desk.chessFair.desk.map((user, i) => (
              <tr style={styles.personsTable} key={i}>
                <td style={styles.personsTable}>{user.leftClm}</td>

                <td
                  onClick={() => handleCellClick(i, 1)}
                  style={{
                    ...(activeCell.row === i && activeCell.col === 1
                      ? styles.personsTable.active
                      : styles.personsTable),
                  }}
                >
                  {user.arr[0].val}
                </td>

                <td
                  onClick={() => handleCellClick(i, 2)}
                  style={{
                    ...(activeCell.row === i && activeCell.col === 2
                      ? styles.personsTable.active
                      : styles.personsTable),
                  }}
                >
                  {user.arr[1].val}
                </td>

                <td
                  onClick={() => handleCellClick(i, 3)}
                  style={{
                    ...(activeCell.row === i && activeCell.col === 3
                      ? styles.personsTable.active
                      : styles.personsTable),
                  }}
                >
                  {user.arr[2].val}
                </td>
                <td
                  onClick={() => handleCellClick(i, 4)}
                  style={{
                    ...(activeCell.row === i && activeCell.col === 4
                      ? styles.personsTable.active
                      : styles.personsTable),
                  }}
                >
                  {user.arr[3].val}
                </td>
                <td
                  onClick={() => handleCellClick(i, 5)}
                  style={{
                    ...(activeCell.row === i && activeCell.col === 5
                      ? styles.personsTable.active
                      : styles.personsTable),
                  }}
                >
                  {user.arr[4].val}
                </td>
                <td
                  onClick={() => handleCellClick(i, 6)}
                  style={{
                    ...(activeCell.row === i && activeCell.col === 6
                      ? styles.personsTable.active
                      : styles.personsTable),
                  }}
                >
                  {user.arr[5].val}
                </td>
                <td
                  onClick={() => handleCellClick(i, 7)}
                  style={{
                    ...(activeCell.row === i && activeCell.col === 7
                      ? styles.personsTable.active
                      : styles.personsTable),
                  }}
                >
                  {user.arr[6].val}
                </td>
                <td
                  onClick={() => handleCellClick(i, 8)}
                  style={{
                    ...(activeCell.row === i && activeCell.col === 8
                      ? styles.personsTable.active
                      : styles.personsTable),
                  }}
                >
                  {user.arr[7].val}
                </td>
                <td style={styles.personsTable}>{user.rightClm}</td>
              </tr>
            ))}
          </tbody>
        </table>

        <div>
          <p>
            Координаты: {clickedCol} {clickedRow}
          </p>
          <p>
            Выделено: {activeCell.col} {activeCell.row}
          </p>
        </div>
      </div>
    );
  } else {
    // Можно добавить альтернативный контент или сообщение, если users.chessFair.desk пуст
    return <div>Ошибка при подгрузке шахматной доски</div>;
  }
}

/*


*/
