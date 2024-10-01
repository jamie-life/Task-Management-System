import React from 'react'
import {NavLink} from "react-router-dom";


const Header = () => {
    return (
        <div>
            <header>
                <nav className={'navbar navbar-expand-lg navbar-dark bg-dark'}>
                    <div>
                        <a className={'navbar-brand '} href={"https://www.linkedin.com/in/jamielinked/"}> Task
                            Management System</a>
                    </div>
                    <div className={'collapse navbar-collapse'}>

                    </div>
                    <ul className={'navbar-nav'}>
                        <li className={'nav-item"'}>
                            <NavLink to={"/register"} className={'nav-link'}>
                                Register
                            </NavLink>
                        </li>
                    </ul>

                    <ul className={'navbar-nav'}>
                        <li className={'nav-item"'}>
                            <NavLink to={"/Login"} className={'nav-link'}>
                                Login
                            </NavLink>
                        </li>
                    </ul>
                </nav>
            </header>
        </div>
    )
}
export default Header
