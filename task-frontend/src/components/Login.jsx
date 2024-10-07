// eslint-disable-next-line no-unused-vars
import React from 'react'
import {useState} from 'react'
import {loginUser, savedLogin, storeToken} from "../services/AuthService.jsx";
import {useNavigate} from "react-router-dom";

function Login() {

    const navigate = useNavigate();
    const [usernameOrEmail, setUsernameOrEmail] = useState('')
    const [password, setPassword] = useState('')


    async function LoginUser(e) {
        e.preventDefault()

         await loginUser(usernameOrEmail, password).then((response) => {
             console.log(response.data)

             // const token = 'Basic ' + window.btoa(usernameOrEmail + ":" + password);
             const token = 'Bearer ' + response.data.accessToken;
             const role = response.data.role;
             const username = response.data.username;

             storeToken(token)
             savedLogin(usernameOrEmail, role, username)
             navigate("/tasks")

             window.location.reload(false)
        }).catch(error => {console.log(error)})
    }

    return (
        <div className="container py-5">
            <div className="row justify-content-center">
                <div className="col-md-6">
                    <div className="card shadow-sm">
                        <div className="card-header text-center">
                            <h2>Log In</h2>
                        </div>

                        <div className="card-body">
                            <form>
                                <div className="mb-3">
                                    <label className="form-label">Username / Email</label>
                                    <input
                                        type="text"
                                        name="usernameOrEmail"
                                        className="form-control"
                                        placeholder="Email or Username"
                                        value={usernameOrEmail}
                                        onChange={(e) => setUsernameOrEmail(e.target.value)}
                                    />
                                </div>

                                <div className="mb-3">
                                    <label className="form-label">Password</label>
                                    <input
                                        type="password"
                                        name="password"
                                        className="form-control"
                                        placeholder="Enter Password"
                                        value={password}
                                        onChange={(e) => setPassword(e.target.value)}
                                    />
                                </div>

                                <div className="text-center">
                                    <button
                                        className="btn btn-dark"
                                        onClick={(e) => LoginUser(e)}
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

export default Login
