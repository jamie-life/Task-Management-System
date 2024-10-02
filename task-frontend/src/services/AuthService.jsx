import axios from "axios";

const AUTH_API_BASE_URL = 'http://localhost:8080/api/auth'

export const registerUser = (user) => {
    return axios.post(AUTH_API_BASE_URL + '/register', user)
}

export const loginUser = (usernameOrEmail, password) => {
    return axios.post(AUTH_API_BASE_URL + '/login', { usernameOrEmail, password})
}

export const storeToken = (token) => localStorage.setItem("token", token)

export const getToken = () => localStorage.getItem("token")

export const savedLogin = (username) => {
    sessionStorage.setItem("authenticatedUser", username)
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