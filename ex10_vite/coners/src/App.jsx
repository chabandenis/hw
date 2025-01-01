import { useState } from "react";
import "./App.css";
import Header from "./components/Header";
import ListUsers from "./components/listUsers/ListUsers";
import Desk from "./components/desk/Desk";

import Authorized from "./components/authorized/Authorized";
import CreateUser from "./components/createUser/CreateUser";
import UpdateUser from "./components/updateUser/UpdateUser";

import UseUserState from "./components/state/UseUserState";

export default function App() {
  // авторизованный основной пользователь
  const [mainUser, setMainUser] = UseUserState();

  // авторизованный основной пользователь
  const [seconUser, setSecondUser] = UseUserState();

  return (
    <div>
      <main>
        {/* заголовок. отображаются пользователи игры и чей ход */}
        {mainUser.id != "" && (
          <Header mainUser={mainUser} seconUser={seconUser} />
        )}
        {/* пользователь отсутствует */}
        {/* логинимся */}
        {mainUser.id == "" && (
          <Authorized mainUser={mainUser} updateMainUser={setMainUser} />
        )}
        {/* пользователь отсутствует */}
        {/* создаем пользователя */}
        {mainUser.id == "" && (
          <CreateUser mainUser={mainUser} updateMainUser={setMainUser} />
        )}

        {/* выбрать второго пользователя */}
        {mainUser.id != "" && seconUser.id == "" && (
          <ListUsers seconUser={seconUser} updateSecondUser={setSecondUser} />
        )}

        {seconUser.id != "" && <Desk />}
        {mainUser.id != "" && (
          <UpdateUser mainUser={mainUser} updateMainUser={setMainUser} />
        )}
      </main>
    </div>
  );
}
