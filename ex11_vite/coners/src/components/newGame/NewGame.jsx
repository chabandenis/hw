import React, { useState } from "react";

export default function NewGame({ mainUser, secondUser, desk, setDesk }) {
  const [serverError, setServerError] = useState("");
  const [error, setError] = useState(null);

  function SendDataToServer() {
    //    console.log("JSON.stringify(data) ", JSON.stringify(data));

    //console.log("secondUser", secondUser);

    let a = fetch("/api/games/new/" + mainUser.id + "/" + secondUser.id, {
      method: "POST", // Метод отправки
      headers: {
        "Content-Type": "application/json",
      },
      body: "",
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
        //console.log("Данные успешно отправлены");
      })
      .catch((error) => {
        // Обработка ошибки
        setServerError(error.message);
      });
  };

  return (
    <>
      <p>==========================================================</p>
      <p>Создать новую игру</p>

      <form onSubmit={handleSubmit}>
        <button type="submit">Создать новую игру</button>
      </form>
    </>
  );
}
