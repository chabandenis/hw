import React, { useState } from "react";

export default function SendDataToServer(action, login, password) {
  //  const [loginUser, setLoginUser] = useState([]);

  const [responseData, setResponseData] = useState(null);
  const [error, setError] = useState(null);


  const data = {
    action,
    login,
    password,
  };

  console.log("JSON.stringify(data) ", JSON.stringify(data));

  let a = fetch("/api/rest/users/action", {
    method: "POST", // Метод отправки
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(data),
  })
  .then(response => {
    if (!response.ok) {
      throw new Error('Network response was not ok ' + response.statusText);
    }
    return response.json();
  })
  .then(data => {
    setResponseData(data);
  })
  .catch(error => {
    setError(error);
  });

  //.when((loginUser) => setLoginUser(loginUser));

  console.log("responseData ", responseData);

  return a;


}
