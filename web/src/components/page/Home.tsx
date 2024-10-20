import {DefaultPage} from "./layout/DefaultPage";
import {RootStoreContext, RootStoreContextType} from "../../context/RootStoreContext";

export default class Home extends DefaultPage {
    declare context: RootStoreContextType;
    static contextType = RootStoreContext;

    get page() {
        return <h1>Home</h1>
    }
}
