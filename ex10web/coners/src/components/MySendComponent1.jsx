import React, { useState } from "react";
import SendDataToServer from "./Send";

export default function MySendComponent1() {
  const [login, setLogin] = useState("");
  const [password, setPassword] = useState("");
  //  const [message, setMessage] = useState("");
  const [serverError, setServerError] = useState("");



  const handleSubmit = (e) => {
    e.preventDefault();
    // Отправка данных на сервер
    let x = SendDataToServer("login", login, password)
      .then(() => {
        // Успешная отправка
        console.log("Данные успешно отправлены");
      })
      .catch((error) => {
        // Обработка ошибки
        setServerError(error.message);
      });

  };

  return (
    <>
      <p>авторизация пользователя</p>
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

  //<p>{loginUser.name}</p>
}
