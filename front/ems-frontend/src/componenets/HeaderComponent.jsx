import { useLocation, useNavigate } from 'react-router-dom';
import React, { useState, useEffect } from 'react'

function HeaderComponent() {

  const navigator = useNavigate();
  const location = useLocation();

  const [isAuthenticated, setIsAuthenticated] = useState(!!localStorage.getItem("token"));

  useEffect(() => {
    const token = localStorage.getItem("token");
    setIsAuthenticated(!!token);
  }, [location]);


  const handleLogout = () => {
    localStorage.removeItem("token");
    localStorage.removeItem("roles");
    setIsAuthenticated(false); // Update UI
    navigator("/login");
  };
  

  return (
    <div>
      <header>
        <nav className='navbar navbar-dark bg-dark'>
            <a className='navbar-brand' href='#'>Employee Management System</a>
            <div>
              {!isAuthenticated && (
                <>
                  <button type="button" class="btn btn-light nav-item active m-2" onClick={() => navigator("/signup")}>Sing Up</button>;
                  <button type="button" class="btn btn-light nav-item active m-2" onClick={() => navigator("/login")}>Log in</button>;
                </>
              )}
              {isAuthenticated && (
                <button type="button" className="btn btn-light nav-item m-2" onClick={handleLogout}>Log Out</button>
              )}
            </div>
        </nav>
      </header>
    </div>
  )
}

export default HeaderComponent
