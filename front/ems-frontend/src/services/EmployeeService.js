import API from "./API";


const EMPLOYEE_API = '/employees';

export const listEmployees = () => API.get(EMPLOYEE_API);

export const createEmployee = (employee) => API.post(EMPLOYEE_API, employee);

export const getEmployee = (employeeId) =>  API.get(`${EMPLOYEE_API}/${employeeId}`);

export const updateEmployee = (employeeId, employee) => API.put(`${EMPLOYEE_API}/${employeeId}`, employee);

export const deleteEmployee = (employeeId) => API.delete(`${EMPLOYEE_API}/${employeeId}`);