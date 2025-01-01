import { useState } from "react";
import "./App.css";
import Header from "./components/Header";
("./components/Header");
import MyComponent3 from "./components/listUsers/ListUsers";
import Desk from "./components/desk/Desk";

import Authorized from "./components/authorized/Authorized";
import CreateUser from "./components/createUser/CreateUser";
import UpdateUser from "./components/updateUser/UpdateUser";

export default function App() {


  // авторизованный пользователь
  const idAutorisedUser = "";

  return (
    <div>
      <Header />

      <main>
        {true && <h1>Hellow world!</h1>}
        <MyComponent3 />
        //
        <Authorized />
        <Desk />
        <CreateUser />
        <UpdateUser userId={idAutorisedUser} />
      </main>
    </div>
  );
}
