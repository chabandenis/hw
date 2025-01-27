import { useState } from "react";
import "./App.css";
import Header from "./components/Header";
import Desk from "./components/game/desk/Desk";

import ListUsers from "./components/user/listUsers/ListUsers";
import Authorized from "./components/user/authorized/Authorized";
import CreateUser from "./components/user/createUser/CreateUser";
import UpdateUser from "./components/user/updateUser/UpdateUser";
import UseUserState from "./components/user/state/UseUserState";
import DeleteUser from "./components/user/deleteUser/DeleteUser";

import SelectGame from "./components/game/selectGame/SelectGame";
import NewGame from "./components/game/newGame/NewGame";
import DeleteGame from "./components/game/deleteGame/DeleteGame";

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
        {desk.id >= 0 && <DeleteGame desk={desk} setDesk={setDesk} />}
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
