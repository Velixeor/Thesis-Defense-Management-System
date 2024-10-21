import React from 'react';
import ReactDOM from 'react-dom/client';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import MainPage from './components/MainPage'; // Главная страница
import DragAndDropStudent from './components/DragAndDropStudent'; // Страница со списком студентов
import './index.css';
import 'bootstrap/dist/css/bootstrap.min.css';

ReactDOM.createRoot(document.getElementById('root')!).render(
    <React.StrictMode>
        <Router>
            <Routes>
                {/* Главная страница */}
                <Route path="/" element={<MainPage />} />
                {/* Страница со списком студентов */}
                <Route path="/drag-and-drop-student" element={<DragAndDropStudent />} />
            </Routes>
        </Router>
    </React.StrictMode>
);
