import { useState } from "react";
import "./App.css";
import Header from "./components/Header";
("./components/Header");
import ListUsers from "./components/listUsers/ListUsers";
import Desk from "./components/desk/Desk";

import Authorized from "./components/authorized/Authorized";
import CreateUser from "./components/createUser/CreateUser";
import UpdateUser from "./components/updateUser/UpdateUser";

import UseUserState from "./components/state/UseUserState";

export default function App() {
  // авторизованный основной пользователь
  const [mainUser, setMainUser] = UseUserState();

  const getCurrentMainUser = () => {
    return mainUser;
  };

  const updateMainUser = (newMainUser) => {
    setMainUser(newMainUser);
  };

  //  setIdAutorisedUser(1);

  // <p>sss {getCurrentUserId == null}</p>
  //<p>ees {getCurrentUserId}</p>

  //{(getCurrentUserId()) != null && <ListUsers />}
  /*
  {getCurrentUserId != null && <ListUsers />}
  {getCurrentUserId != null && <Desk />}

  */

  return (
    <div>
      <Header userId={mainUser} getCurrentUserId={getCurrentMainUser} />
      <main>
        <Authorized
          mainUser={mainUser}
          updateMainUser={updateMainUser}
        />

        {mainUser.id != "" && <ListUsers />}
        {mainUser.id != "" && <Desk />}
        {mainUser.id != "" && <CreateUser />}

        {mainUser.id != "" && (
          <UpdateUser
            mainUser={mainUser}
            updateMainUser={updateMainUser}
          />
        )}
      </main>
    </div>
  );
}

/*
        
        
        

        



*/
