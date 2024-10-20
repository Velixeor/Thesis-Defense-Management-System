import ReactDOM from 'react-dom/client'
import './index.css'
import 'bootstrap/dist/css/bootstrap.min.css';
import {RouterContext, RouterView} from "mobx-state-router";
import {initApp} from "./utils/init";
import {RootStoreContext} from './context/RootStoreContext';
import {viewMap} from "./router/viewMap";

const rootStore = initApp();

ReactDOM.createRoot(document.getElementById('root')!).render(
    <RootStoreContext.Provider value={rootStore}>
        <RouterContext.Provider value={rootStore.routerStore}>
            <RouterView viewMap={viewMap}/>
        </RouterContext.Provider>
    </RootStoreContext.Provider>
);
