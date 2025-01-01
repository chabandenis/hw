import React, { useState } from "react";
import UseUserState from "./state/UseUserState";

export default function Header({ mainUser, seconUser }) {
  //  const [mainUser, setMainUser] = UseUserState();

  return (
    <header>
      <h3>Уголки</h3>
      <p>Игрок 1 = "{mainUser.name}"</p>
      <p>Игрок 2 = "{seconUser.name}"</p>
    </header>
  );
}
