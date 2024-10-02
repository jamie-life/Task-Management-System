// eslint-disable-next-line no-unused-vars
import React from 'react'
import {useState} from 'react'
import {loginUser, storeToken} from "../services/AuthService.jsx";
import {useNavigate} from "react-router-dom";

function Login() {

    const navigate = useNavigate();
    const [usernameOrEmail, setUsernameOrEmail] = useState('')
    const [password, setPassword] = useState('')

    console.log(usernameOrEmail + " HELLO")
    localStorage.clear();
    sessionStorage.clear();


    function LoginUser(e) {
        e.preventDefault()

        const user = {name, usernameOrEmail, password}


         loginUser(usernameOrEmail, password).then((response) => {
             console.log(response.data)

             const token = 'Basic ' + window.btoa(usernameOrEmail + ":" + password);
             storeToken(token);

             console.log(token)
             console.log(user)

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
