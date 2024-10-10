import './App.css'
import ListTasks from "./components/ListTasks.jsx";
import Header from "./components/User Interface/Header.jsx";
import Footer from "./components/User Interface/Footer.jsx";
import {BrowserRouter, Routes, Route, Navigate} from "react-router-dom";
import Task from "./components/Task.jsx";
import Register from "./components/Register.jsx";
import Login from "./components/Login.jsx";
import {isUserLoggedIn} from "./services/AuthService.jsx";

function App() {

    // eslint-disable-next-line react/prop-types
    function AuthenticatedRoute({children}) {
        const isAuth = isUserLoggedIn()

        if(isAuth){
            return children
        } else {
            return <Navigate to={"/"}/>
        }
    }

  return (

      <div>
          <BrowserRouter>
              <Header/>
              <div className={"App"}>
                  <Routes>

                      {/* http:localhost:3000*/}
                      <Route path="/" element={<Login/>}/>

                      {/* http:localhost:3000/tasks*/}
                      <Route path="/tasks" element={
                          <AuthenticatedRoute>
                              <ListTasks/>
                          </AuthenticatedRoute>}/>

                      {/* http:localhost:3000/add-task*/}
                      <Route path="/add-task" element={
                          <AuthenticatedRoute>
                              <Task/>
                          </AuthenticatedRoute>}/>

                      {/* http:localhost:3000/update-task*/}
                      <Route path="/update-task/:id" element={
                          <AuthenticatedRoute>
                              <Task/>
                          </AuthenticatedRoute>}/>

                      {/* http:localhost:3000/register*/}
                      <Route path="/register" element={<Register/>}/>

                      {/* http:localhost:3000/login*/}
                      <Route path="/login" element={<Login/>}/>
                  </Routes>


              </div>
              <Footer/>
          </BrowserRouter>
      </div>
)
}

export default App
