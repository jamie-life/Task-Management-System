// eslint-disable-next-line no-unused-vars
import React from 'react'
import {NavLink, useNavigate} from "react-router-dom";
import {isUserLoggedIn, logout} from "../../services/AuthService.jsx";


const Header = () => {

    const isAuth = isUserLoggedIn();
    const navigate = useNavigate();
    const username = sessionStorage.getItem("authenticatedUserUsername");

    function handleLogout() {
        logout()
        navigate("/login")
    }

    return (
        <div>
            <header>
                <nav className={'navbar navbar-expand-lg navbar-dark bg-dark'}>
                    <div>
                        <a className={'navbar-brand '}
                           href={"https://www.linkedin.com/in/jamielinked/"}>
                            Task Management System </a>
                    </div>
                    <div className={'collapse navbar-collapse'}>
                        <ul className={'navbar-nav'}>
                            {
                                !isAuth &&
                                <li className={'nav-item"'}>
                                    <NavLink to={"/tasks"} className={'nav-link'}> Task </NavLink>
                                </li>
                            }
                        </ul>
                    </div>
                    <ul className={'navbar-nav'}>
                        {
                            isAuth &&
                            <li className={'nav-item"'}>
                                <NavLink to={"/register"} className={'nav-link'}> Register </NavLink>
                            </li>
                        }

                        {
                            isAuth &&
                            <li className={'nav-item"'}>
                                <NavLink to={"/Login"} className={'nav-link'}> Login </NavLink>
                            </li>
                        }

                        {
                            !isAuth &&
                            <li className={'nav-item'}>
                                <span className={'nav-link'}>Welcome, {username}!</span> {/* Display username */}
                            </li>
                        }

                        {
                            !isAuth &&
                            <li className={'nav-item"'}>
                                <NavLink to={"/Login"} className={'nav-link'}
                                onClick={handleLogout}> Sign Out </NavLink>
                            </li>
                        }

                    </ul>
                </nav>
            </header>
        </div>
    )
}
export default Header
