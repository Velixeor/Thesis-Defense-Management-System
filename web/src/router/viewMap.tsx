import {ViewMap} from "mobx-state-router";
import Home from "../components/page/Home";
import Error from "../components/page/Error";
import UserProfile from "../components/page/UserProfile";

export const viewMap: ViewMap = {
    root: <Home/>,
    profile: <UserProfile/>,
    error: <Error/>,
}