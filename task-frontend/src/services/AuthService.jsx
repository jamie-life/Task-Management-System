import axios from "axios";

const AUTH_API_BASE_URL = 'http://localhost:8080/api/auth'

export const registerUser = (user) => {
    return axios.post(AUTH_API_BASE_URL + '/register', user)
}

export const loginUser = (user) => {
    return axios.post(AUTH_API_BASE_URL + '/login', user)
}

export const storeToken = (token) => localStorage.setItem("token", token)

export const getToken = () => localStorage.getItem("token")