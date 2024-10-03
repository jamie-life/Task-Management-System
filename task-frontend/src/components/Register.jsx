import {useState} from 'react'
import {registerUser} from "../services/AuthService.jsx";
import {useNavigate} from "react-router-dom";

function Register() {

    const navigate = useNavigate();
    const [name, setName] = useState('')
    const [username, setUsername] = useState('')
    const [email, setEmail] = useState('')
    const [password, setPassword] = useState('')


    function RegisterUser(e) {
        e.preventDefault()

        const user = {name, username, email, password}

        registerUser(user).then((response) => {
            console.log(response.data)
            navigate("/tasks")
        }).catch(error => {console.log(error)})
    }

    return (
        <div className={"container"}>
            <br /> <br />
            <div className="row">
                <div className={'col-md-6 offset-md-3 offset-md-3'}>
                    <div className={'card'}>
                        <div className={'card-header'}>
                            <h2 className={'text-center'}> Sign Up</h2>
                        </div>

                        <div className={'card-body'}>
                            <form>
                                <div className={'row mb-3'}>
                                    <label className={"col-md-3 col-form-label"}> Name</label>
                                    <div className={"col-md-9"}>
                                        <input type='text' name={"Name"} className={'form-control'}
                                               placeholder={"Enter Name"} value={name}
                                               onChange={(e) => setName(e.target.value)}>
                                        </input>
                                    </div>
                                </div>

                                <div className={'row mb-3'}>
                                    <label className={"col-md-3 col-form-label"}> Username</label>
                                    <div className={"col-md-9"}>
                                        <input type='text' name={"Username"} className={'form-control'}
                                               placeholder={"Enter Username"} value={username}
                                               onChange={(e) => setUsername(e.target.value)}>
                                        </input>
                                    </div>
                                </div>

                                <div className={'row mb-3'}>
                                    <label className={"col-md-3 col-form-label"}> Email</label>
                                    <div className={"col-md-9"}>
                                        <input type='text' name={"Email"} className={'form-control'}
                                               placeholder={"Enter Email Address"} value={email}
                                               onChange={(e) => setEmail(e.target.value)}>
                                        </input>
                                    </div>
                                </div>

                                <div className={'row mb-3'}>
                                    <label className={"col-md-3 col-form-label"}> Password</label>
                                    <div className={"col-md-9"}>
                                        <input type='password' name={"Password"} className={'form-control'}
                                               placeholder={"Enter Password"} value={password}
                                               onChange={(e) => setPassword(e.target.value)}>
                                        </input>
                                    </div>
                                </div>

                                <div className={'form-group mb-3'}>
                                    <button className={'btn btn-primary'}
                                            onClick={(e) => RegisterUser(e)}>Submit
                                    </button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default Register
