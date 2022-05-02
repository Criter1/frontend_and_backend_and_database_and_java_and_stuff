import {BrowserRouter, Link, Route, Routes, useLocation} from "react-router-dom";
import MainMenu from "./main-menu";
import ClientSearch from "./client-search";
import ClientPage from "./client-page";
import FilmSearch from "./film-search";
import FilmPage from "./film-page";
import React from "react";

function SiteWrapper() {
    const {pathname} = useLocation();
    return (
        <div>
                {pathname != '/' ? <Link to={'/'}>На главную страницу</Link> : ''}
            <Routes>
                <Route path="/" element={<MainMenu/>}/>
                <Route path="/clients" element={<ClientSearch/>}/>
                <Route path="/client/:filmId" element={<ClientPage/>}/>
                <Route path="/films" element={<FilmSearch/>}/>
                <Route path='/film/:filmId' element={<FilmPage/>} />
            </Routes>
        </div>
    );
}

export default SiteWrapper;