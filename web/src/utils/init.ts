import {configure} from "mobx";
import {RootStore} from "../store/RootStore";
import {RouterService} from "../services/RouterService";
import { library } from '@fortawesome/fontawesome-svg-core';
import { fas } from '@fortawesome/free-solid-svg-icons';
import {fab} from "@fortawesome/free-brands-svg-icons";
import {far} from "@fortawesome/free-regular-svg-icons";

const initMobX = () => {
    configure({enforceActions: 'observed'});
}

const initFontAwesome = () => {
    library.add(fas);
    library.add(fab);
    library.add(far);
}

export const initApp = () => {
    initMobX();
    initFontAwesome();
    let rootStore = new RootStore().init();
    RouterService.init(rootStore.routerStore);
    return rootStore;
}
