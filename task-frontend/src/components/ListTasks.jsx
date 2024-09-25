import React, {useEffect, useState} from 'react'
import {completeTask, deleteTask, getAllTasks, incompleteTask} from "../services/TaskService.jsx";
import {useNavigate} from "react-router-dom";

function ListTasks() {

    const [tasks, setTasks] = useState([])

    const navigate = useNavigate();

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
        <div className={'container'}>
            <h2 className={'text-center'}>List of Tasks</h2>
            <button className={'btn btn-primary mb-2'} onClick={(addNewTask)} >Add Task</button>
            <div>
                <table className={'table table-bordered table-striped'}>
                    <thead>
                        <tr>
                            <th>Task Title</th>
                            <th>Description</th>
                            <th>Status</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            tasks.map((task) => (
                                <tr key={task.id}>
                                    <td>{task.title}</td>
                                    <td>{task.description}</td>
                                    <td>{task.completed ? 'Completed' : 'In Progress'}</td>
                                    <td>
                                        <button className={'btn btn-info'}
                                                onClick={() => updateTask(task.id)}>Update
                                        </button>

                                        <button style={{marginLeft: "10px"}}
                                                className={'btn btn-success'}
                                                onClick={() => taskComplete(task.id)}>Complete
                                        </button>

                                        <button style={{marginLeft: "10px"}}
                                                className={'btn btn-primary'}
                                                onClick={() => taskIncomplete(task.id)}>Incomplete
                                        </button>

                                        <button style={{marginLeft: "10px"}}
                                                className={'btn btn-danger'}
                                                onClick={() => removeTask(task.id)}>Delete
                                        </button>
                                    </td>

                                </tr>
                            ))
                        }
                    </tbody>
                </table>
            </div>

        </div>
    )
}

export default ListTasks
