import axios from "axios";
const instance = axios.create({
  baseURL: 'http://localhost:8080/api/v1/',
  // timeout: 1000,
  headers: {'X-Custom-Header': 'foobar'}
});
instance.interceptors.request.use(function (config) {
    // Làm gì đó trước khi request dược gửi đi
    return config;
  }, function (error) {
    // Làm gì đó với lỗi request
    return Promise.reject(error);
  });

// Thêm một bộ đón chặn response
instance.interceptors.response.use(function (response) {
    // Bất kì mã trạng thái nào nằm trong tầm 2xx đều khiến hàm này được trigger
    // Làm gì đó với dữ liệu response
    console.log(response);
    if(response.status==204){
      return {status_code:204, success:true}
    }
    return response?.data;
  }, function (error) {
    // Bất kì mã trạng thái nào lọt ra ngoài tầm 2xx đều khiến hàm này được trigger\
    // Làm gì đó với lỗi response
    console.log(error);
    return error?.response?.data ?? Promise.reject(error);
  });
export default instance;