import React, { useState } from "react";

export default function SendDataToServer(action, login, password) {
  //  const [loginUser, setLoginUser] = useState([]);

  const data = {
    action,
    login,
    password,
  };

  console.log("JSON.stringify(data) ", JSON.stringify(data));

  /*
  return fetch("http://localhost:8080/rest/users/action", {
    method: "POST", // Метод отправки
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(data),
    mode: "no-cors",
  });
  */

  let a = fetch("/api/rest/users/action", {
    method: "POST", // Метод отправки
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(data),
//    mode: "no-cors",
  });
  //.when((loginUser) => setLoginUser(loginUser));

  console.log(a);

  return a;

  /*
  setLoginUser(x);

  console.log("x ", x);*/

  /*axios.post(
    "http://localhost:8080/rest/users/action",
    JSON.stringify(data),
    {
      headers: {
        "Content-Type": "application/json",
      },
      mode: "no-cors", // Обратите внимание, что этот режим может не поддерживаться axios
    }
  );
  */
}
