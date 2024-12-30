import axios from "axios";

export default function Header() {
  return (
    <header>
      <h3>Уголки</h3>
      <span>
        ФИО игрока{" "}
        {/*await axios.get('http://localhost:8080/api/rest/admin-ui/users/1')*/}
      </span>
    </header>
  );
}
