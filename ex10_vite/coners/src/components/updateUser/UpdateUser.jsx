import React, { useState } from "react";
import UseUserState from "../state/UseUserState";

export default function UpdateUser({
  mainUser,
  getCurrentMainUser,
  updateMainUser,
}) {
  const [name, setName] = useState("");
  const [login, setLogin] = useState("");
  const [password, setPassword] = useState("");
  const [serverError, setServerError] = useState("");

  // первоначальные значения
  if (name == "") {
    setName(mainUser.name);
  }

  if (login == "") {
    setLogin(mainUser.login);
  }

  if (password == "") {
    setPassword(mainUser.password);
  }

  const [responseData, setResponseData] = useState(null);
  const [error, setError] = useState(null);

  function SendDataToServer(id, name, login, password) {
    const data = {
      id,
      name,
      login,
      password,
    };

    console.log("JSON.stringify(data) ", JSON.stringify(data));

    let a = fetch("/api/users/update", {
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
        updateMainUser(data);
      })
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
    SendDataToServer(mainUser.id, name, login, password)
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
      <p>___________</p>
      <p>изменить пользователя</p>
      <form onSubmit={handleSubmit}>
        <input
          type="text"
          placeholder="Имя"
          value={name}
          onChange={(e) => setName(e.target.value)}
        />
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
}
