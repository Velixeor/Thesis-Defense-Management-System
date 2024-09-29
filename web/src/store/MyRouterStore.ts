import {browserHistory, createRouterState, HistoryAdapter, RouterStore} from "mobx-state-router";
import {routes} from "../routes.tsx";
import {RootStore} from "./RootStore.tsx";


export class MyRouterStore extends RouterStore {
    constructor(rootStore: RootStore) {
        super(MyRouterStore.makeRoutesMap(),
            createRouterState('error', {notFound: true}),
            {rootStore: rootStore});
    }

    static makeViewMap() {
        return routes.reduce<any>((map, route) => {
            map[route.name] = route.view;
            return map;
        }, {});
    }

    static makeRoutesMap() {
        return routes.map(route => {
            return {...route, view: undefined};
        });
    }

    init() {
        const historyAdapter = new HistoryAdapter(this, browserHistory);
        historyAdapter.observeRouterStateChanges();
    }
}