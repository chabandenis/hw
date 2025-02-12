/*
  авторизация пользователя
*/

import React, { useState } from "react";
import UseUserState from "../state/UseUserState";

export default function Authorized({ mainUser, updateMainUser }) {
  const [login, setLogin] = useState("");
  const [password, setPassword] = useState("");
  const [serverError, setServerError] = useState("");
  const [error, setError] = useState(null);

  // первоначальные значения
  if (login == "") {
    setLogin("user1");
  }

  if (password == "") {
    setPassword("1");
  }

  function SendDataToServer(login, password) {
    const data = {
      login,
      password,
    };

    let a = fetch("/api/user/login", {
      method: "PUT", // Метод отправки
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
      .then((mainUser) => {
        updateMainUser(mainUser);
      })
      .catch((error) => {
        setError(error);
      });

    return a;
  }

  const handleSubmit = (e) => {
    updateMainUser({
      id: "",
      name: "",
      login: "",
      password: "",
    });

    e.preventDefault();

    // Отправка данных на сервер
    SendDataToServer(login, password)
      .then(() => {
        // Успешная отправка
        //console.log("Данные успешно отправлены");
      })
      .catch((error) => {
        // Обработка ошибки
        setServerError(error.message);
      });
    //      .then((mainUser) => updateMainUser(mainUser))
  };

  return (
    <>
      <p>==========================================================</p>
      <p>авторизация пользователя </p>

      <form onSubmit={handleSubmit}>
        <input
          type="text"
          placeholder="Логин"
          value={login}
          onChange={(e) => setLogin(e.target.value)}
        />
        <input
          type="text"
          placeholder="Пароль"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />
        <button type="submit">Отправить</button>
        {serverError && <p>{serverError}</p>}
      </form>
    </>
  );
}
