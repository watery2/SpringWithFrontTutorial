import './App.css'
import EmployeeComponent from './componenets/EmployeeComponent.jsx'
import FooterComponenet from './componenets/FooterComponenet.jsx'
import HeaderComponent from './componenets/HeaderComponent.jsx'
import ListEmployeeComponenet from './componenets/ListEmployeeComponenet.jsx'
import {BrowserRouter, Routes, Route} from 'react-router-dom'


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
      </Routes>
      <FooterComponenet/>
    </BrowserRouter>
    </>
  )
}

export default App
