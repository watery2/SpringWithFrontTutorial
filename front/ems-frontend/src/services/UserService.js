import axios from "axios"

const REST_API_BASE_URL = 'http://localhost:8080/api/v1/auth/';

export const singup = (user) => axios.post(REST_API_BASE_URL+ "signup", user);
export const signin = (user) => axios.post(REST_API_BASE_URL+ "signin", user);