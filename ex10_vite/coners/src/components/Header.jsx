import React, { useState } from "react";
import UseUserState from "./state/UseUserState";

export default function Header({ userId, getCurrentUserId }) {
  const [mainUser, setMainUser] = UseUserState();

  return (
    <header>
      <h3>Уголки</h3>
      <span>
        игрок 1 = "{userId.name}" Игрок 2 = "{userId.id}"
        {/*await axios.get('http://localhost:8080/api/rest/admin-ui/users/1')*/}
      </span>
    </header>
  );
}
