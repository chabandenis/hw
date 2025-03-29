/*
  авторизация пользователя
*/
export default function Login(login, password, updateMainUser, setError) {
  console.log("Отправляем 1 ", login, password);

  const data2 = {
    login,
    password,
  };

  const encodedCredentials = btoa(`${login}:${password}`);

  fetch("/api/token", {
    method: "POST", // Метод отправки
    headers: {
      //"Content-Type": "application/x-www-form-urlencoded",
      Authorization: `Basic ${encodedCredentials}`,
    },
  })
    .then((response) => {
      if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`);
      }
      return response.text();
    })
    .then((data) => {
      console.log("Получаем данные от сервера: ", data);

      localStorage.setItem("jwt_token", data);

      const jwtToken = localStorage.getItem("jwt_token");

      fetch("/api/user/login", {
        method: "PUT", // Метод отправки
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${jwtToken}`,
        },
        body: JSON.stringify(data2),
      })
        .then((response) => {
          if (!response.ok) {
            throw new Error(
              "Network response was not ok " + response.statusText
            );
          }
          return response.json();
        })
        .then((mainUser) => {
          console.log("Отправляем 3");
          updateMainUser(mainUser);
        })
        .catch((error) => {
          setError(error);
        });
    })
    .catch((error) => {
      console.error("Ошибка при отправке запроса: ", error);
      setError(error.message);
    });

  //  });

  //    const data = await response;
  //    console.log("data ", data);
  //  };

  //return a;

  //  handleTokenRequest();
}
