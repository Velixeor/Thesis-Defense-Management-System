import React from "react";
import ReactDOM from 'react-dom/client'
import MainPage from './components/MainPage.tsx'
import './index.css'

import 'bootstrap/dist/css/bootstrap.min.css';

ReactDOM.createRoot(document.getElementById('root')!).render(
    <React.StrictMode>
        <MainPage/>
    </React.StrictMode>,
)
