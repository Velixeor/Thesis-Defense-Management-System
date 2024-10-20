import {MyRouterStore} from "./MyRouterStore";
import {UserStore} from "./UserStore";

export class RootStore {
    userStore = new UserStore(this);
    routerStore = new MyRouterStore(this);

    init() {
        this.userStore.init();
        this.routerStore.init();
        return this;
    }
}
