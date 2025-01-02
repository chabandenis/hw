import React, { useState } from "react";

export default function DeleteUser({ mainUser, updateMainUser }) {
  const [serverError, setServerError] = useState("");

  const [responseData, setResponseData] = useState(null);

  function SendDataToServer() {
    let a = fetch(`/api/users/delete/${mainUser.id}`, {
      method: "POST", // Метод отправки
      headers: {
        "Content-Type": "application/json",
      },
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error("Network response was not ok " + response.statusText);
        }
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
      <p>___________</p>
      <p>удалить учетную запись </p>
      <form onSubmit={handleSubmit}>
        <button type="submit">Удалить</button>
        {serverError && <p>{serverError}</p>}
      </form>
    </>
  );
}
