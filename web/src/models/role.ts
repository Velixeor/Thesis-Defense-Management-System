export enum Role {
    STUDENT = 'ROLE_STUDENT',
    TUTOR = 'ROLE_TUTOR',
    DIRECTOR = 'ROLE_DIRECTOR',
}

export interface IAuthority {
    authority: Role;
    name: string;
}