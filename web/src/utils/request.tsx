import axios, {AxiosRequestConfig} from "axios";

export const apiUrl = "http://localhost:8080/api/v1/";

export const get = async <T,> (url: string, data?: any) => {
    return await request<T>({
        url: url,
        method: 'GET',
        data: data,
    });
}

export const post = async <T,> (url: string, data?: any) => {
    return await request<T>({
        url: url,
        method: 'POST',
        data: data,
    });
}

export const request = async <T,> (config: AxiosRequestConfig<any>) => {
    return new Promise<T>((resolve, reject) => {
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
