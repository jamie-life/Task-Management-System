// eslint-disable-next-line no-unused-vars
import React, {useEffect, useState} from 'react'
import {completeTask, deleteTask, getAllTasks, incompleteTask} from "../services/TaskService.jsx";
import {useNavigate} from "react-router-dom";
import {isAdminUser} from "../services/AuthService.jsx";

// import {storeToken} from "../services/AuthService.jsx";

function ListTasks() {

    const [tasks, setTasks] = useState([])

    const navigate = useNavigate();

    const isAdmin = isAdminUser();

    useEffect(() => {
        getTasks()
    }, [])

    function getTasks() {
        getAllTasks().then((response) => {
            setTasks(response.data)
        }).catch(error => {
            console.log(error)
        })
    }


    function addNewTask() {
        navigate("/add-task")

    }

    function updateTask(id) {
        console.log(id)
        navigate(`/update-task/${id}`)
    }

    function removeTask(id) {
        deleteTask(id).then(() => {
            getTasks()
        }).catch(error => {console.log(error)})

    }

    function taskComplete(id) {
        completeTask(id).then(() => {
            getTasks()
        }).catch(error => {console.log(error)})
    }

    function taskIncomplete(id) {
        incompleteTask(id).then(() => {
            getTasks()
        }).catch(error => {console.log(error)})
    }

    return (
        <div className="container my-4">
            <h2 className="text-center mb-4">List of Tasks</h2>

            {isAdmin && (
                <button className="btn btn-outline-dark mb-3" onClick={addNewTask}>
                    Add Task
                </button>
            )}

            <div className="table-responsive shadow">
                <table className="table table-hover table-striped">
                    <thead className="table-dark">
                    <tr>
                        <th>Task Title</th>
                        <th>Description</th>
                        <th>Status</th>
                        <th>Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    {tasks.map((task) => (
                        <tr key={task.id}>
                            <td>{task.title}</td>
                            <td>{task.description}</td>
                            <td>
                <span className={`badge ${task.completed ? 'bg-success' : 'bg-warning'}`}>
                  {task.completed ? 'Completed' : 'In Progress'}
                </span>
                            </td>
                            <td>
                                <div className="d-flex gap-2">
                                    {isAdmin && (
                                        <button className="btn btn-info" onClick={() => updateTask(task.id)}>
                                            Update
                                        </button>
                                    )}

                                    <button className="btn btn-success" onClick={() => taskComplete(task.id)}>
                                        Complete
                                    </button>

                                    <button className="btn btn-primary" onClick={() => taskIncomplete(task.id)}>
                                        Incomplete
                                    </button>

                                    {isAdmin && (
                                        <button className="btn btn-danger" onClick={() => removeTask(task.id)}>
                                            Delete
                                        </button>
                                    )}
                                </div>
                            </td>
                        </tr>
                    ))}
                    </tbody>
                </table>
            </div>
        </div>
    );


}

export default ListTasks
