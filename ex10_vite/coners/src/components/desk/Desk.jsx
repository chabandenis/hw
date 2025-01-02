import React, { useState, useEffect } from "react";

// массив со стилями
let cssDeskArray;

// должно быть выбрано две точки
let pointSelected;

// исходная координата
let firstPoint = {
  row: null,
  col: null,
};

// конечная координата
let secondPoint = {
  row: null,
  col: null,
};

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

  // заполняется один раз при запуске
  if (cssDeskArray == null) {
    cssDeskArray = Array.from({ length: 9 }, () => new Array(9).fill(""));
    pointSelected = 0;
  }

  // создать пустой массив для хранения выбранных ячеек
  // будет нужен для хранения разных стилей для подсвечивания фигур на доске
  const clearArray = () => {
    for (let i = 0; i < 9; i++) {
      cssDeskArray[i].fill("");
    }
    pointSelected = 0;
  };

  /*
  useEffect(() => {
    console.log("111");
    setInterval(getApiData(), 1000);
    }, 1000);
  }, []);
*/

  useEffect(() => {
    const intervalId = setInterval(getApiData, 3000); // Вызов каждые 1000 мс (1 секунда)

    return () => {
      clearInterval(intervalId); // Очистка интервала при размонтировании компонента
    };
  });

  const getApiData = async () => {
    console.log("getApiData");
    const response = await fetch("/api/games/1")
      .then((response) => response.json())
      .then((desk) => setDesk(desk));
  };

  const handleCellClick = (row, col) => {
    if (pointSelected >= 2) {
      clearArray();
    }

    pointSelected++;

    cssDeskArray[row][col] = "1";

    if (pointSelected == 1) {
      firstPoint = { row: 8 - row, col: String.fromCharCode(col + 64) };
    } else if (pointSelected == 2) {
      secondPoint = { row: 8 - row, col: String.fromCharCode(col + 64) };
    }

    console.log("координаты ", firstPoint, secondPoint, cssDeskArray);

    setClickedRow(row);
    setClickedCol(col);
  };

  function SendDataToServer() {
    const data = {
      gameId: 1,
      x1: firstPoint.col,
      y1: firstPoint.row,
      x2: secondPoint.col,
      y2: secondPoint.row,
    };

    console.log("JSON.stringify(data) ", JSON.stringify(data));

    let a = fetch("/api/games/step", {
      method: "POST", // Метод отправки
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(data),
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error("Network response was not ok " + response.statusText);
        }
        return response.json();
      })
      .then((desk) => {
        setDesk(desk);
      })
      .catch((error) => {
        setError(error);
      });

    return a;
  }

  const handleSubmit = (e) => {
    e.preventDefault();

    // Отправка данных на сервер
    SendDataToServer()
      .then(() => {
        // Успешная отправка
        console.log("Данные успешно отправлены");
      })
      .catch((error) => {
        // Обработка ошибки
        setServerError(error.message);
      });

    //почистить стили, шаг сделан
    clearArray();

    //почистить шаг
    firstPoint = {
      row: null,
      col: null,
    };

    //почистить шаг
    secondPoint = {
      row: null,
      col: null,
    };
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
        <p>==========================================================</p>
        <p>Шахматная доска</p>

        <table style={styles.personsTable}>
          <thead>
            <tr style={styles.personsTable}>
              <th style={styles.personsTable}></th>
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
                    ...(cssDeskArray[i][1] == "1"
                      ? styles.personsTable.active
                      : styles.personsTable),
                  }}
                >
                  {user.arr[0].val}
                </td>

                <td
                  onClick={() => handleCellClick(i, 2)}
                  style={{
                    ...(cssDeskArray[i][2] == "1"
                      ? styles.personsTable.active
                      : styles.personsTable),
                  }}
                >
                  {user.arr[1].val}
                </td>

                <td
                  onClick={() => handleCellClick(i, 3)}
                  style={{
                    ...(cssDeskArray[i][3] == "1"
                      ? styles.personsTable.active
                      : styles.personsTable),
                  }}
                >
                  {user.arr[2].val}
                </td>
                <td
                  onClick={() => handleCellClick(i, 4)}
                  style={{
                    ...(cssDeskArray[i][4] == "1"
                      ? styles.personsTable.active
                      : styles.personsTable),
                  }}
                >
                  {user.arr[3].val}
                </td>
                <td
                  onClick={() => handleCellClick(i, 5)}
                  style={{
                    ...(cssDeskArray[i][5] == "1"
                      ? styles.personsTable.active
                      : styles.personsTable),
                  }}
                >
                  {user.arr[4].val}
                </td>
                <td
                  onClick={() => handleCellClick(i, 6)}
                  style={{
                    ...(cssDeskArray[i][6] == "1"
                      ? styles.personsTable.active
                      : styles.personsTable),
                  }}
                >
                  {user.arr[5].val}
                </td>
                <td
                  onClick={() => handleCellClick(i, 7)}
                  style={{
                    ...(cssDeskArray[i][7] == "1"
                      ? styles.personsTable.active
                      : styles.personsTable),
                  }}
                >
                  {user.arr[6].val}
                </td>
                <td
                  onClick={() => handleCellClick(i, 8)}
                  style={{
                    ...(cssDeskArray[i][8] == "1"
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
            Начальная точка: {firstPoint.col}
            {firstPoint.row}
          </p>
          <p>
            Конечная точка: {secondPoint.col}
            {secondPoint.row}
          </p>
        </div>

        <form onSubmit={handleSubmit}>
          <button type="submit">Шаг</button>
        </form>
      </div>
    );
  } else {
    // Можно добавить альтернативный контент или сообщение, если users.chessFair.desk пуст
    return <div>Ошибка при подгрузке шахматной доски</div>;
  }
}
