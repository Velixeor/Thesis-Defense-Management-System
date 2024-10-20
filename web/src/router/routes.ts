import {Route} from "mobx-state-router";

export const routes: Route[] = [{
    name: 'root',
    pattern: '/',
}, {
    name: 'profile',
    pattern: '/profile',
}, {
    name: 'error',
    pattern: '/error',
}];