import './App.css'
import ListTasks from "./components/ListTasks.jsx";
import Header from "./components/User Interface/Header.jsx";
import Footer from "./components/User Interface/Footer.jsx";
import {BrowserRouter, Routes, Route} from "react-router-dom";
import Task from "./components/Task.jsx";

function App() {

  return (
      <>
          <BrowserRouter>
              <Header/>

              <Routes>

                  {/* http:localhost:3000*/}

                  <Route path="/" element={<ListTasks />} />

                  {/* http:localhost:3000/tasks*/}
                  <Route path="/tasks" element={<ListTasks />} />

                  {/* http:localhost:3000/add-task*/}
                  <Route path="/add-task" element={<Task />} />

                  {/* http:localhost:3000/update-task*/}
                  <Route path="/update-task/:id" element={<Task />} />
              </Routes>

              <Footer/>
          </BrowserRouter>
      </>
  )
}

export default App
