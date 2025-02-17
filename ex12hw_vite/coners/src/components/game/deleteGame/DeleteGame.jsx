import React, { useState } from "react";

export default function DeleteGame({ desk, setDesk }) {
  const [serverError, setServerError] = useState("");

  const [responseData, setResponseData] = useState(null);

  function SendDataToServer() {
    const jwtToken = localStorage.getItem("jwt_token");

    let a = fetch(`/api/game/${desk.id}`, {
      method: "DELETE", // Метод отправки
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${jwtToken}`,
      },
      body: " sdf sdf sdf",
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error("Network response was not ok " + response.statusText);
        }

        // обнулены данные о выбранной игре
        setDesk([]);

        return response.json();
      })
      /*.then((data) => {
        setResponseData(data);
        updateMainUser(data);
      })*/
      .catch((error) => {
        setError(error);
      });

    console.log("responseData ", responseData);
    console.log("a  ", a);

    return a;
  }

  const handleSubmit = (e) => {
    e.preventDefault();
    // Отправка данных на сервер
    SendDataToServer()
      .then(() => {
        // Успешная отправка
        console.log("Данные успешно отправлены");
        updateMainUser("");
      })
      .catch((error) => {
        // Обработка ошибки
        setServerError(error.message);
      });
  };

  return (
    <>
      <p>==========================================================</p>
      <p>удалить игру </p>
      <form onSubmit={handleSubmit}>
        <button type="submit">Удалить</button>
        {serverError && <p>{serverError}</p>}
      </form>
    </>
  );
}
