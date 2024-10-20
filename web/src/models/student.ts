import {IAuthenticated, IUser} from "./user";

export interface IGRoup {
    name: string;
    principalUser: IAuthenticated;
}

export interface IStudent {
    form: boolean;
    protectionOrder: number;
    magistracy: string;
    digitalFormatPresent: boolean;
    markComment: number;
    markPractice: number;
    predefenceComment: string;
    normalControl: string;
    antiPlagiarism: number;
    note: string;
    recordBookReturned: boolean;
    work: string;
    user: IUser;
    diplomaTopic: string;
    mentorUser: IAuthenticated;
    group: IGRoup;
}