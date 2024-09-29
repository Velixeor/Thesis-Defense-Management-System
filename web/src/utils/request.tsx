import axios, {AxiosRequestConfig} from "axios";

export const apiUrl = "http://localhost:8080/api/v1/";

export const get = async (url: string, data?: any) => {
    return await request({
        url: url,
        method: 'GET',
        data: data,
    });
}

export const post = async (url: string, data?: any) => {
    return request({
        url: url,
        method: 'POST',
        data: data,
    });
}

export const request = async (config: AxiosRequestConfig<any>) => {
    return new Promise<any>((resolve, reject) => {
        console.debug(`${config.method} ${config.url} request: ${config.method === 'GET' ? JSON.stringify(config.params) : JSON.stringify(config.data)}`);
        axios.request({...config, baseURL: apiUrl}).then((response) => {
            console.debug(`${config.method} ${config.url} response: ${JSON.stringify(response.data)}`);
            resolve(response.data);
        }).catch((error) => {
            console.error(`${config.method} ${config.url} error: ${error}`);
            reject(error);
        });
    });
}
