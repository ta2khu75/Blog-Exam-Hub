import axiosRetry from "axios-retry";
import { AuthRequest } from "../component/page/LoginPage";
import { setAccount } from "../redux/slice/accountSlice";
import { store } from "../redux/store";
import instance from "../util/apiInstance";
import axios from "axios";
const basePath = "/auth";
export default class AuthService {
  static login(authRequest: AuthRequest): Promise<ApiResponse<AuthResponse>> {
    return instance.post(`${basePath}/login`, authRequest);
  }
  static myAccount():Promise<ApiResponse<AccountDetailsResponse>> {
    return instance.get(`${basePath}/account`);
  }
  static refreshToken() {
    axiosRetry(axios, {
      retries: 3,
      retryDelay: (retryCount) => {
        return retryCount * 100;
      },
    });
    axios
      .get("http://localhost:8080/api/v1/auth/refresh-token", {
        withCredentials: true,
      })
      .then((d) => store.dispatch(setAccount(d.data.data)))
      .catch((error) => {
        console.error("Failed to refresh token after 3 retries:", error);
        window.location.href = "/login";
        return Promise.reject(error);
      });
  }
  static logout():Promise<ApiResponse<void>> {
    return instance.get(`${basePath}/logout`);
  }
  static refetchToken(){
    return axios.get("http://localhost:8080/api/v1/auth/refresh-token", {
      withCredentials: true,
    });
  }
}
