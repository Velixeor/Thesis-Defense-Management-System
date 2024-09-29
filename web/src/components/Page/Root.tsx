import {DefaultPage} from "./DefaultPage.tsx";

export default class Root extends DefaultPage {
    get page() {
        return <h1>Home</h1>
    }
}