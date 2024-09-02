import { useEffect, useState } from "react"
import ActuatorService from "../../../../service/ActuatorService"
import { Select } from "antd";
import EndpointResponse from "../../../../model/response/EndpointResponse";
import { Method } from "../../../../model/Method";

const ManagerPermission= () => {
    const [endpoints, setEndpoints] = useState<EndpointResponse[]>([])
    useEffect(() => {
        fetchMappings();
    }, [])
    const fetchMappings = () => {
        ActuatorService.readEndpoints().then((d) => {
            if (d.success) {
                setEndpoints(d.data);
            }
        });
    }
    return (
        <Select style={{ width: "400px" }} options={endpoints.map((endpoint, index) => {
            let action;
            const path=endpoint.path.substring(8);
            if (endpoint.method === Method.GET) {
                action= <span className="text-info">READ</span>
            }else if(endpoint.method === Method.POST){
                action= <span className="text-success">CREATE</span>
            }else if(endpoint.method === Method.PUT){
                action= <span className="text-warning">UPDATE</span>
            }else if(endpoint.method === Method.DELETE){
                action=<span className="text-danger">DELETE</span>
            }else{
                action=<span className="text-secondary">UNKNOWN</span>
            }
           const index_= path.indexOf("/")
            let name;
            if(index_===-1){
                name= "All "+path
            }else{
                const temp_name = path.substring(0,index_);
                name=` ${temp_name} by ${path.substring(index_+1)}`
            }

            return {
                value: index,
                label: <span>{action} - {name}</span>,
            }
        }
        )} />
        // [{ value: 'sample', label: <span>sample</span> }]} />
    )
}

export default ManagerPermission