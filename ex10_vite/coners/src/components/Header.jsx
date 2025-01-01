import React, { useState } from "react";
import UseUserState from "./state/UseUserState";

export default function Header({ userId, getCurrentUserId }) {
  const [mainUser, setMainUser] = UseUserState();

  return (
    <header>
      <h3>Уголки</h3>
      <p>игрок 1 = "{userId.name}"</p>
      <p>игрок 2 = "{userId.id}"</p>
    </header>
  );
}
