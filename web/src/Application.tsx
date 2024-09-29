import React from "react";
import ReactDOM from 'react-dom/client'
import './index.css'
import 'bootstrap/dist/css/bootstrap.min.css';
import {RouterContext, RouterView} from "mobx-state-router";
import {initApp} from "./utils/init.ts";
import {MyRouterStore} from "./store/MyRouterStore.ts";
import { RootStoreContext } from "./store/RootStore.tsx";

const rootStore = initApp();

ReactDOM.createRoot(document.getElementById('root')!).render(
    <React.StrictMode>
        <RootStoreContext.Provider value={rootStore}>
            <RouterContext.Provider value={rootStore.routerStore}>
                <RouterView viewMap={MyRouterStore.makeViewMap()} />
            </RouterContext.Provider>
        </RootStoreContext.Provider>
    </React.StrictMode>
);
