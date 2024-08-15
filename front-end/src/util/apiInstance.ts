import axios, { AxiosError } from "axios";
import { store } from "../redux/store";
import axiosRetry from "axios-retry";
import AuthService from "../service/AuthService";
import { setAccount } from "../redux/slice/accountSlice";
import FailedQueueItem from "../request/FailedQueueItem";

const instance = axios.create({
  baseURL: "http://localhost:8080/api/v1/",
});
axiosRetry(instance, {
  retries: 3,
  retryDelay: (retryCount) => {
    return retryCount * 100;
  },
  retryCondition: (error) => {
    if (error.response?.status === 401) {
      return true;
    }
    return false;
  },
});
let isRefreshing = false;
let failedQueue: FailedQueueItem[] = [];

const processQueue = (
  error: AxiosError | undefined,
  token: string | undefined
) => {
  failedQueue.forEach((prom) => {
    if (error) {
      prom.reject(error);
    } else if (token) {
      prom.resolve(token);
    }
  });
  failedQueue = [];
};

instance.interceptors.request.use(
  function (config) {
    // Làm gì đó trước khi request dược gửi đi
    const account = store.getState().account;
    if (account.access_token) {
      config.headers.Authorization = "Bearer " + account.access_token;
    }
    return config;
  },
  function (error) {
    // Làm gì đó với lỗi request
    return Promise.reject(error);
  }
);

// Thêm một bộ đón chặn response
instance.interceptors.response.use(
  function (response) {
    // Bất kì mã trạng thái nào nằm trong tầm 2xx đều khiến hàm này được trigger
    // Làm gì đó với dữ liệu response
    console.log(response);
    if (response.status == 204) {
      return { status_code: 204, success: true };
    }
    return response?.data;
  },
  function (error) {
    // Bất kì mã trạng thái nào lọt ra ngoài tầm 2xx đều khiến hàm này được trigger\
    // Làm gì đó với lỗi response
    const originalRequest = error.config;
    if (error?.response?.status === 444 && !originalRequest._retry) {
      if (isRefreshing) {
        return new Promise(function (resolve, reject) {
          failedQueue.push({ resolve, reject });
        })
          .then((token) => {
            originalRequest.headers.Authorization = "Bearer " + token;
            return instance(originalRequest);
          })
          .catch((err) => Promise.reject(err));
      }

      originalRequest._retry = true;
      isRefreshing = true;

      return new Promise(function (resolve, reject) {
        AuthService.refetchToken()
          .then(({ data }) => {
            const account = data.data;
            store.dispatch(setAccount(account));
            // instance.defaults.headers.Authorization =
            //   "Bearer " + account.access_token;
            originalRequest.headers.Authorization =
              "Bearer " + account.access_token;
            processQueue(undefined, account.access_token);
            resolve(instance(originalRequest));
          })
          .catch((err) => {
            processQueue(err, undefined);
            reject(err);
            window.location.href = "/login";
          })
          .finally(() => {
            isRefreshing = false;
          });
      });
    }
    // if (error?.response?.data?.status_code === 444) {
    //   AuthService.refreshToken();
    // }
    console.log(error);
    return error?.response?.data ?? Promise.reject(error);
  }
);

export default instance;
