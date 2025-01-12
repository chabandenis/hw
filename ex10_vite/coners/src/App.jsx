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
import NewGame from "./components/newGame/NewGame";
import DeleteGame from "./components/deleteGame/DeleteGame";

export default function App() {
  // авторизованный основной пользователь
  const [mainUser, setMainUser] = UseUserState();

  // второй пользователь, с которым играем
  const [secondUser, setSecondUser] = UseUserState();

  // шахматная доска
  const [desk, setDesk] = useState([]);

  return (
    <div>
      <main>
        {/* заголовок. отображаются пользователи игры и чей ход */}
        {mainUser.id != "" && (
          <Header mainUser={mainUser} seconUser={secondUser} desk={desk} />
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
        {mainUser.id != "" && secondUser.id == "" && (
          <ListUsers seconUser={secondUser} updateSecondUser={setSecondUser} />
        )}
        {/* создать игру */}
        {secondUser.id != "" && (
          <NewGame
            mainUser={mainUser}
            secondUser={secondUser}
            desk={desk}
            setDesk={setDesk}
          />
        )}
        {/* удалить игру */}
        {desk.id != "" && <DeleteGame desk={desk} setDesk={setDesk} />}
        {/*выбрать игру*/}
        {secondUser.id != "" && (
          <SelectGame
            mainUser={mainUser}
            seconUser={secondUser}
            desk={desk}
            setDesk={setDesk}
          />
        )}
        {/* шахматная доска */}
        {secondUser.id != "" && <Desk desk={desk} setDesk={setDesk} />}
        {/* обновить основного пользователя */}
        {mainUser.id != "" && secondUser.id == "" && (
          <UpdateUser mainUser={mainUser} updateMainUser={setMainUser} />
        )}
        {/* удалить основного пользователя */}
        {mainUser.id != "" && secondUser.id == "" && (
          <DeleteUser mainUser={mainUser} updateMainUser={setMainUser} />
        )}
      </main>
    </div>
  );
}
