import React, {useState} from 'react'

function ListTasksComponent() {

    const dummyTasks = [
        {
            "id": 1,
            "title": "Task 1",
            "description": "Task 1 Description",
            "completed": false
        },
        {
            "id": 2,
            "title": "Task 2",
            "description": "Task 2 Description",
            "completed": true
        },
        {
            "id": 3,
            "title": "Task 3",
            "description": "Task 3 Description",
            "completed": false
        }
    ]

    const [tasks, setTasks] = useState(dummyTasks)

    return (
        <div className={'container'}>
            <h2 className={'text-center'}>List of Tasks</h2>
            <div>
                <table className={'table table-bordered table-striped'}>
                    <thead>
                    <tr>
                        <th>Task Title</th>
                        <th>Description</th>
                        <th>Status</th>
                        <th>Action</th>
                    </tr>
                    </thead>
                    <tbody>
                        {
                            tasks.map((task) => (
                                <tr key={task.id}>
                                    <td>{task.title}</td>
                                    <td>{task.description}</td>
                                    <td>{task.completed ? 'Yes' : 'No'}</td>
                                </tr>
                            ))
                        }
                    </tbody>
                </table>
            </div>

        </div>
    )
}

export default ListTasksComponent
