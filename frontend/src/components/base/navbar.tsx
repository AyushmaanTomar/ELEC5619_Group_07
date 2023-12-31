import {NavLink} from 'react-router-dom';
import { useAuth } from '../usermanagement/AuthProvider';
import { Button } from '@mui/material';

export function Navbar() {

    const { loggedIn, logout } = useAuth();

    const handleLogoutClick = () => {
        logout();
    };

    return(
        <header className="navbar relative bg-secondary text-secondary-content border-b-2 border-b-solid border-b-primary flex justify-center items-center">

            <div className="flex-1 flex">
            <a href="/" className="unix-intro">
                <span><img src="/assets/unix_logo.png" className='relative right-10' alt='home-logo' width="200" height="100" /></span>
            </a>
            </div>

            <div className="flex-1 flex justify-center items-center relative right-40">
                <ul className="menu menu-horizontal font-semibold space-x-3">
                    <li>
                        <NavLink to="/" className="transition ease-in-out delay-150 hover:-translate-y-1 hover:scale-110 hover:bg-red-500 duration-300">Home</NavLink>
                    </li>
                    <li>
                        <NavLink to="/products/" className="transition ease-in-out delay-150 hover:-translate-y-1 hover:scale-110 hover:bg-red-500 duration-300">Products</NavLink>
                    </li>
                    {loggedIn ? (
                        <>
                        <li>
                            <NavLink to="/add" className="transition ease-in-out delay-150 hover:-translate-y-1 hover:scale-110 hover:bg-red-500 duration-300">Add Products</NavLink>
                        </li>
                        <li>
                            <NavLink to="/user" className="transition ease-in-out delay-150 hover:-translate-y-1 hover:scale-110 hover:bg-red-500 duration-300">Users List</NavLink>
                        </li>
                        </>
                    ) : <> </>}
                </ul>
            </div>


            <div className="flex-2">
            {loggedIn ? (
                <ul className="menu menu-horizontal font-semibold space-x-2">
                    <li>
                    <NavLink
                        className="transition ease-in-out delay-150 hover:-translate-y-1 hover:scale-110 hover:bg-red-500 duration-300 font-semibold "
                        to="/profile"> Profile </NavLink>
                    </li>
                    <li>
                    <Button className="transition ease-in-out delay-150 hover:-translate-y-1 hover:scale-110 hover:bg-blue-500 duration-300 font-bold"
                        variant='contained'
                        onClick={handleLogoutClick}
                        href="/"> Logout </Button>
                    </li>
                </ul>
            ) : (
                <ul className="menu menu-horizontal font-semibold space-x-2"> {/*(border-b-2 border-b-primary) To add underline*/} 
                    <li>
                    <NavLink
                        className="transition ease-in-out delay-150 hover:-translate-y-1 hover:scale-110 hover:bg-blue-500 duration-300"
                        to="/login"> Login </NavLink>
                    </li>

                    <li>
                    <NavLink
                        className="transition ease-in-out delay-150 hover:-translate-y-1 hover:scale-110 hover:bg-blue-500 duration-300"
                        to="register"> Register </NavLink>
                    </li>
                </ul>
            )}
            </div>
        </header>
    );
}

export default Navbar;