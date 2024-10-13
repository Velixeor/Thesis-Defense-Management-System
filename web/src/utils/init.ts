import {configure} from "mobx";
import {RootStore} from "../store/RootStore.ts";


export const initMobX = () => {
    configure({enforceActions: 'observed'});
}

export const initApp = () => {
    initMobX();
    return new RootStore();
}
