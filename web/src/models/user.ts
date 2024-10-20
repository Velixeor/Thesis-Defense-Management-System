import {IAuthority} from "./role";

export interface IAuthenticated {
    authenticated: true,
    login: string,
    password: string,
    fullName: string,
    email: string,
    phone: string,
    createdAt: string,
    updatedAt: string,
    authorities: IAuthority[],
}

export declare type IUser = {authenticated: false} | IAuthenticated;
