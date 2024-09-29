import {get, post} from "../utils/request.tsx";
import {makeObservable, observable, runInAction} from "mobx";
import {RootStore} from "./RootStore.ts";
import {IUser} from "../models/user.ts";

export class UserStore {
    rootStore: RootStore;
    user: IUser = {authenticated: false};

    constructor(rootStore: RootStore) {
        makeObservable(this, {
            user: observable,
        });
        this.rootStore = rootStore;
    }

    init() {
        get<IUser>('/user/current').then((response) => {
            runInAction(() => {
                this.user = response;
            });
        });
    }

    logout() {
        post('/user/logout').then(() => {
            this.init();
        });
    }
}