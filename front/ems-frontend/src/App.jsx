import './App.css'
import EmployeeComponent from './componenets/EmployeeComponent.jsx'
import FooterComponenet from './componenets/FooterComponenet.jsx'
import HeaderComponent from './componenets/HeaderComponent.jsx'
import ListEmployeeComponenet from './componenets/ListEmployeeComponenet.jsx'
import {BrowserRouter, Routes, Route} from 'react-router-dom'
import SignUpComponenet from './componenets/SignUpComponenet.jsx'
import LogInComponenet from './componenets/LogInComponenet.jsx'


function App() {

  return (
    <>
    <BrowserRouter>
      <HeaderComponent/>
      <Routes>
        <Route path='/' element = {<ListEmployeeComponenet/>}></Route>
        <Route path='/employees' element = {<ListEmployeeComponenet/>}></Route>
        <Route path='/add-employee' element={<EmployeeComponent/>}></Route>
        <Route path='/edit-employee/:id' element={<EmployeeComponent/>}></Route>
        <Route path='/signup' element = {<SignUpComponenet/>}></Route>
        <Route path='/login' element={<LogInComponenet/>}></Route>
      </Routes>
      <FooterComponenet/>
    </BrowserRouter>
    </>
  )
}

export default App
