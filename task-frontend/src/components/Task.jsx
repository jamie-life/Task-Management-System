import React, {useEffect, useState} from 'react'
import {useNavigate, useParams} from "react-router-dom";
import {addTask, getTask, updateTask} from "../services/TaskService.jsx";

function Task() {

    const navigate = useNavigate();

    const [title, setTitle] = useState('')
    const [description, setDescription] = useState('')
    const [completed, setCompleted] = useState(false)

    const {id} = useParams()

    function saveOrUpdateTask(e) {
        e.preventDefault()
        const task = {title, description, completed}

        if (id){
            updateTask(id, task).then(response => {
                console.log(response)
                navigate("/tasks")
            }).catch((error) => {console.log(error)})
        } else {
            addTask(task).then((response) => {
                console.log(response)
                navigate("/tasks")
            }).catch((error) => {console.log(error)})
        }
    }

    function pageTitle() {
        if (id) {
            return <h2 className={'text-center'}>Update Task</h2>
        } else {
            return <h2 className={'text-center'}>Add Task</h2>
        }
    }

    useEffect(() => {
        if (id){
            getTask(id).then((response) => {
                console.log(response.data)
                setTitle(response.data.title)
                setDescription(response.data.description)
                setCompleted(response.data.completed)
            }).catch((error) => {console.log(error)})
        }
    }, [])

    return (
        <div className="container py-5">
            <div className="row justify-content-center">
                <div className="col-md-6">
                    <div className="card shadow-sm">
                        <div className="card-header text-center">
                            <h5>{pageTitle()}</h5>
                        </div>
                        <div className="card-body">
                            <form>
                                <div className="mb-3">
                                    <label className="form-label">Task Title:</label>
                                    <input
                                        className="form-control"
                                        type="text"
                                        placeholder="Enter Task Title"
                                        name="title"
                                        value={title}
                                        onChange={(e) => setTitle(e.target.value)}
                                    />
                                </div>

                                <div className="mb-3">
                                    <label className="form-label">Task Description:</label>
                                    <input
                                        className="form-control"
                                        type="text"
                                        placeholder="Enter Task Description"
                                        name="description"
                                        value={description}
                                        onChange={(e) => setDescription(e.target.value)}
                                    />
                                </div>

                                <div className="mb-3">
                                    <label className="form-label">Task Status:</label>
                                    <select
                                        className="form-select"
                                        value={completed}
                                        onChange={(e) => setCompleted(e.target.value)}
                                    >
                                        <option value="false">In Progress</option>
                                        <option value="true">Completed</option>
                                    </select>
                                </div>

                                <div className="text-center">
                                    <button
                                        className="btn btn-dark"
                                        onClick={(e) => saveOrUpdateTask(e)}
                                    >
                                        Submit
                                    </button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );

}

export default Task
