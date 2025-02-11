import React, { useState } from "react";

export default function Header({ mainUser, seconUser, desk }) {

  return (
    <header>
      <h3>Уголки</h3>
      <p>Вы = "{mainUser.name}"</p>
      <p>Напарник = "{seconUser.name}"</p>
      <p>Id игры = "{desk.id}"</p>
    </header>
  );
}
