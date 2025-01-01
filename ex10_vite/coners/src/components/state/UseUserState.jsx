import React, { useState } from "react";

export default function UseUserState() {
  const [user, setUser] = useState({
    id: "",
    name: "",
    login: "",
    password: "",
  });

  return [user, setUser];
}
