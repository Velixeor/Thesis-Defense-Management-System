import {browserHistory, createRouterState, HistoryAdapter, RouterStore} from "mobx-state-router";
import {RootStore} from "./RootStore";
import {routes} from "../router/routes";

export class MyRouterStore extends RouterStore {
    constructor(rootStore: RootStore) {
        super(routes,
            createRouterState('error', {notFound: true}),
            {rootStore: rootStore});
    }

    init() {
        const historyAdapter = new HistoryAdapter(this, browserHistory);
        historyAdapter.observeRouterStateChanges();
        return this;
    }
}