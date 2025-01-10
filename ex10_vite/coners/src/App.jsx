import { useState } from "react";
import "./App.css";
import Header from "./components/Header";
import ListUsers from "./components/listUsers/ListUsers";
import Desk from "./components/desk/Desk";

import Authorized from "./components/authorized/Authorized";
import CreateUser from "./components/createUser/CreateUser";
import UpdateUser from "./components/updateUser/UpdateUser";

import UseUserState from "./components/state/UseUserState";
import DeleteUser from "./components/deleteUser/DeleteUser";
import SelectGame from "./components/selectGame/SelectGame";

export default function App() {
  // авторизованный основной пользователь
  const [mainUser, setMainUser] = UseUserState();

  // второй пользователь, с которым играем
  const [seconUser, setSecondUser] = UseUserState();

  // идентификтор игры
  const [gameId, setGameId] = useState("");

  // шахматная доска
  const [desk, setDesk] = useState([]);

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

        {/*выбрать игру*/}
        {seconUser.id != "" && (
          <SelectGame
            mainUser={mainUser}
            seconUser={seconUser}
            desk={desk}
            setDesk={setDesk}
          />
        )}

        {/* шахматная доска */}
        {seconUser.id != "" && <Desk desk={desk} setDesk={setDesk} />}

        {/* обновить основного пользователя */}
        {mainUser.id != "" && seconUser.id == "" && (
          <UpdateUser mainUser={mainUser} updateMainUser={setMainUser} />
        )}

        {/* удалить основного пользователя */}
        {mainUser.id != "" && seconUser.id == "" && (
          <DeleteUser mainUser={mainUser} updateMainUser={setMainUser} />
        )}
      </main>
    </div>
  );
}
