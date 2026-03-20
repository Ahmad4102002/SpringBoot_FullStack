import { useState, useEffect } from "react";

function App() {
  const [people, setPeople] = useState([]);
  const [name, setName] = useState("");
  const [age, setAge] = useState("");

  // GET all people on page load
  useEffect(() => {
    fetchPeople();
  }, []);

  // fetch all people from Spring Boot
  const fetchPeople = async () => {
    const response = await fetch("http://localhost:8080/people");
    const data = await response.json();
    setPeople(data);
  };

  // POST new person
  const addPerson = async () => {
    if (!name || !age) return;
    await fetch("http://localhost:8080/people", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ name: name, age: parseInt(age) })
    });
    fetchPeople();
    setName("");
    setAge("");
  };

  // DELETE person
  const deletePerson = async (id) => {
    await fetch(`http://localhost:8080/people/${id}`, {
      method: "DELETE"
    });
    fetchPeople();
  };

  return (
    <div style={{ padding: "20px" }}>
      <h1>People App 🚀</h1>

      {/* Add person form */}
      <div>
        <input
          placeholder="Name"
          value={name}
          onChange={(e) => setName(e.target.value)}
        />
        <input
          placeholder="Age"
          value={age}
          onChange={(e) => setAge(e.target.value)}
        />
        <button onClick={addPerson}>Add Person</button>
      </div>

      {/* People list */}
      <ul>
        {people.map(person => (
          <li key={person.id}>
            {person.name} - Age: {person.age}
            <button onClick={() => deletePerson(person.id)}>
              Delete
            </button>
          </li>
        ))}
      </ul>
    </div>
  );
}

export default App;
