/*
  авторизация пользователя
*/

import React, { useState } from "react";
import Login from "../authorized/login";

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

  const handleSubmit = (e) => {
    updateMainUser({
      id: "",
      name: "",
      login: "",
      password: "",
    });

    e.preventDefault();

    console.log("вызвать отправку");
    // Отправка данных на сервер
    Login(login, password, updateMainUser, setError);
    //      .then(() => {
    //        // Успешная отправка
    //        //console.log("Данные успешно отправлены");
    //      })
    //      .catch((error) => {
    // Обработка ошибки
    //        setServerError(error.message);
    //      });
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
