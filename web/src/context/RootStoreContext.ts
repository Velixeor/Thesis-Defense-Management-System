import {RootStore} from "../store/RootStore";
import {ContextType, createContext} from "react";

export const RootStoreContext = createContext<RootStore>(new RootStore());
export type RootStoreContextType = ContextType<typeof RootStoreContext>;