import UserProfile from "./components/Page/UserProfile.tsx";
import React from "react";
import Root from "./components/Page/Root.tsx";
import Error from "./components/Page/Error.tsx";

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
    name: 'error',
    pattern: '/error',
    view: <Error/>,
}];