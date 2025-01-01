import React, { useState } from "react";

export default function CreateUser({ mainUser, updateMainUser }) {
  const [serverError, setServerError] = useState("");
  const [error, setError] = useState(null);

  function SendDataToServer(name, login, password) {
    const data = {
      name,
      login,
      password,
    };

    console.log("JSON.stringify(data) ", JSON.stringify(data));

    let a = fetch("/api/users/insert", {
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
        updateMainUser(data);
      })
      .catch((error) => {
        setError(error);
      });

    return a;
  }

  const handleSubmit = (e) => {
    e.preventDefault();
    // Отправка данных на сервер
    SendDataToServer(mainUser.name, mainUser.login, mainUser.password)
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
      <p>Создать пользователя</p>

      <form onSubmit={handleSubmit}>
        <input
          type="text"
          placeholder="Имя"
          value={mainUser.name}
          onChange={(e) =>
            updateMainUser({ ...mainUser, name: e.target.value })
          }
        />
        <input
          type="text"
          placeholder="Логин"
          value={mainUser.login}
          onChange={(e) =>
            updateMainUser({ ...mainUser, login: e.target.value })
          }
        />
        <input
          type="text"
          placeholder="Пароль"
          value={mainUser.password}
          onChange={(e) =>
            updateMainUser({ ...mainUser, password: e.target.value })
          }
        />
        <button type="submit">Отправить</button>
      </form>
    </>
  );
}
