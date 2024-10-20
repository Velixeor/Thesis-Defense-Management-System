import {get} from "../utils/request";
import {makeObservable, observable, runInAction} from "mobx";
import {RootStore} from "./RootStore";
import type {IUser} from "../models/user";
import {IStudent} from "../models/student";
import {Role} from "../models/role";

export class UserStore {
    rootStore: RootStore;
    @observable
    user: IUser = {authenticated: false};
    @observable
    student: IStudent | undefined;
    @observable
    isLoading: boolean = true;

    constructor(rootStore: RootStore) {
        makeObservable(this);
        this.rootStore = rootStore;
    }

    fetchCurrentUserData() {
        // todo: store token in localStorage
        get<IUser>('/user/current').then((response) => {
            runInAction(() => {
                this.user = response;
            });
            if(response.authenticated && response.authorities.some(a => a.authority === Role.STUDENT)) {
                get<IStudent>('/student/current').then((student) => {
                    runInAction(() => {
                        this.student = student;
                    });
                });
            }
        }).finally(() => {
            runInAction(() => {
                this.isLoading = false;
            });
        });
    }

    init() {
        this.fetchCurrentUserData();
        return this;
    }
}