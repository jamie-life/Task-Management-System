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
        <div className={"container"}>
            <br /> <br />
            <div className="row">
                <div className={'col-md-6 offset-md-3 offset-md-3'}>
                    <div className={'card'}>
                        <div className={'card-header'}>
                            <h2 className={'text-center'}> Log In</h2>
                        </div>

                        <div className={'card-body'}>
                            <form>
                                <div className={'row mb-3'}>
                                    <label className={"col-md-3 col-form-label"}> Username / Email</label>
                                    <div className={"col-md-9"}>
                                        <input type='text' name={"usernameOrEmail"} className={'form-control'}
                                               placeholder={"Email or Username"} value={usernameOrEmail}
                                               onChange={(e) => setUsernameOrEmail(e.target.value)}>
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
                                            onClick={(e) => LoginUser(e)}>Submit
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

export default Login
