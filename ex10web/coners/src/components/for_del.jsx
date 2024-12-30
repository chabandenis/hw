function MyComponent3() {
  const [users, setUsers] = useState();

  useEffect(() => {
    const getApiData = async () => {
      try {
        const response = await fetch(
          "http://localhost:8080/rest/admin-ui/users/1"
        );

        if (!response.ok) {
          throw new Error("Network response was not ok");
        }

        response
          .then((response) => response.json())
          .then((usersValue) => {
            setUsers(usersValue); // Обновляем состояние users
          });
      } catch (error) {
        console.error("Error fetching data:", error.message);
      }
    };

    getApiData();
  }, []);

  return (
    <div>
      {/* Отображаем данные */}
      {data.map((user) => (
        <div key={user.id}>{user.name}</div>
      ))}
    </div>
  );
}

export default MyComponent3;
