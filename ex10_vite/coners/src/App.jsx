import { useState } from "react";
import "./App.css";
import Header from "./components/Header";
("./components/Header");
import MyComponent3 from "./components/MyComponent3";
import Desk from "./components/desk/Desk";

import MySendComponent1 from "./components/MySendComponent1";

export default function App() {
  const [count, setCount] = useState(0);

  return (
    <div>
      <Header />

      <main>
        {true && <h1>Hellow world!</h1>}
        <MyComponent3 />
        //
        <MySendComponent1 />
        <Desk />
      </main>
    </div>
  );
}
