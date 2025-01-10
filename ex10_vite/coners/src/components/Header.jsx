import React, { useState } from "react";
import UseUserState from "./state/UseUserState";

export default function Header({ mainUser, seconUser, desk }) {
  //  const [mainUser, setMainUser] = UseUserState();

  return (
    <header>
      <h3>Уголки</h3>
      <p>Вы = "{mainUser.name}"</p>
      <p>Напарник = "{seconUser.name}"</p>
      <p>Id игры = "{desk.id}"</p>
    </header>
  );
}
