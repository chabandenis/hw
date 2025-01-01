import React, { useState } from "react";

export default function Authorized() {
  const [login, setLogin] = useState("");
  const [password, setPassword] = useState("");
  const [serverError, setServerError] = useState("");
  const [loginUser, setLoginUser] = useState([]);

  const [responseData, setResponseData] = useState(null);
  const [error, setError] = useState(null);

  function SendDataToServer(login, password) {
    const data = {
      //      action,
      login,
      password,
    };

    console.log("JSON.stringify(data) ", JSON.stringify(data));

    let a = fetch("/api/users/login", {
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
      .then((data) => {
        setResponseData(data);
      })
      .catch((error) => {
        setError(error);
      });

    //.when((loginUser) => setLoginUser(loginUser));

    console.log("responseData ", responseData);
    console.log("a  ", a);

    return a;
  }

  const handleSubmit = (e) => {
    e.preventDefault();
    // Отправка данных на сервер
    SendDataToServer(login, password)
      .then(() => {
        // Успешная отправка
        console.log("Данные успешно отправлены");
      })
      .catch((error) => {
        // Обработка ошибки
        setServerError(error.message);
      })
      .then((loginUser) => setLoginUser(loginUser));

    console.log("loginUser", loginUser);
  };

  return (
    <>
      <p>авторизация пользователя {loginUser}</p>

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

      {responseData == null && <p>Имя</p>}
      {responseData != null && <p>Имя = {responseData.name}</p>}
    </>
  );

  //<p>{loginUser.name}</p>
}
