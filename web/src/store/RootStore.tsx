import {UserStore} from "./UserStore.ts";
import {MyRouterStore} from "./MyRouterStore.ts";
import {createContext, useContext} from "react";

export class RootStore {
    userStore = new UserStore(this);
    routerStore = new MyRouterStore(this);

    constructor() {
        this.init();
    }

    init() {
        this.userStore.init();
        this.routerStore.init();
        return this;
    }
}

export const RootStoreContext = createContext<RootStore | undefined>(
    undefined
);

export function useRootStore(): RootStore {
    const rootStore = useContext(RootStoreContext);
    if (rootStore === undefined) {
        throw new Error('useRootStore must be used within a RootStoreProvider');
    }

    return rootStore;
}
