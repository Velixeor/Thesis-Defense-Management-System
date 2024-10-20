import {MyRouterStore} from "../store/MyRouterStore";

export interface IRouterOptions {
    redirect: string;
}

export class RouterService {
    private static router: MyRouterStore;

    static init(router: MyRouterStore) {
        this.router = router;
    }

    static redirect(state: string, options?: IRouterOptions) {
        this.router.goTo(state, options);
    }
}
