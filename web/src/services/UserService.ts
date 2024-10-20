import {Role} from "../models/role";
import {IAuthenticated, IUser} from "../models/user";

export class UserService {
    static isUserInRole(user: IUser, role: Role): boolean {
        if (!user.authenticated) {
            return false;
        }

        user = user as IAuthenticated;
        return user.authorities.some(a => a.authority === role);
    }
}
