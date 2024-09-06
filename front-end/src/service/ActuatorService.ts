import ApiResponse from "../model/response/ApiResponse";
import EndpointResponse from "../model/response/EndpointResponse";
import instance from "../util/apiInstance";
const basePath = "actuator";
export default class ActuatorService {
    static readEndpoints(): Promise<ApiResponse<EndpointResponse[]>> {
        return instance.get(`${basePath}/endpoints`);
    }
}