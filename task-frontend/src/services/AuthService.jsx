import axios from "axios";

const AUTH_API_BASE_URL = 'https://task-env.eba-6nub22wh.eu-west-2.elasticbeanstalk.com/api/auth'

export const registerUser = (user) => {
    return axios.post(AUTH_API_BASE_URL + '/register', user)
}

export const loginUser = (usernameOrEmail, password) => {
    return axios.post(AUTH_API_BASE_URL + '/login', { usernameOrEmail, password})
}

export const storeToken = (token) => localStorage.setItem("token", token)

export const getToken = () => localStorage.getItem("token")

export const savedLogin = (usernameOrEmail, role, username) => {
    sessionStorage.setItem("authenticatedUser", usernameOrEmail)
    sessionStorage.setItem("authenticatedUserRole", role)
    sessionStorage.setItem("authenticatedUserUsername", username)
}

export const isUserLoggedIn = () => {
    const username = sessionStorage.getItem("authenticatedUser")
    return username == null;
}

export const getLoggedInUser = () => {
    return sessionStorage.getItem("authenticatedUser")
}

export const logout = () => {
    localStorage.clear()
    sessionStorage.clear()
}

export const isAdminUser = () => {
    let role = sessionStorage.getItem("authenticatedUserRole")
    return role != null && role === 'ROLE_ADMIN';
}