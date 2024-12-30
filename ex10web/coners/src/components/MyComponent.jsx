import React, { useState, useEffect } from "react";
import axios from "axios";

//import cors from 'cors';
//app.use(cors());

const MyComponent = () => {
  const [data, setData] = useState(null);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchData = async () => {
      setLoading(true);

      try {
        const response = await axios.get(
          "http://localhost:8080/rest/admin-ui/users/1"/*,
          {
            crossDomain: true,
//            withCredentials: true, // Если необходимо передавать учетные данные
          }*/
        );

        console.log("!!! " + response);

        setData(response.data);
      } catch (error) {
        setError(error);
      } finally {
        setLoading(false);
      }
    };

    fetchData();
  }, []);

  return (
    <div>
      {loading ? (
        <p>Loading...</p>
      ) : error ? (
        <p>{error.message}</p>
      ) : (
        <div>
          <h2>Data</h2>
          <pre>{JSON.stringify(data, null, 2)}</pre>
        </div>
      )}
    </div>
  );
};

export default MyComponent;
