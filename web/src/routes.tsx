import UserProfile from "./components/Page/UserProfile.tsx";
import React from "react";
import Root from "./components/Page/Root.tsx";
import Error from "./components/Page/Error.tsx";
import RegistrationForm from "./components/auth/registration/RegistrationForm.tsx";
import LoginForm from "./components/auth/login/LoginForm.tsx";

interface Route {
    name: string;
    pattern: string;
    view: React.ReactElement;
}

export const routes: Route[] = [{
    name: 'root',
    pattern: '/',
    view: <Root/>,
}, {
    name: 'profile',
    pattern: '/profile',
    view: <UserProfile/>,
}, {
    name: 'register',
    pattern: '/register',
    view: <RegistrationForm/>,
}, {
    name: 'login',
    pattern: '/login',
    view: <LoginForm/>,
},{
    name: 'error',
    pattern: '/error',
    view: <Error/>,
}];
