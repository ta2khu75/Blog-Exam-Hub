import AnswerUserRequest from "../model/request/AnswerUserRequest";
import instance from "../util/apiInstance";
const basePath="profession"
export default class ProfessionService{
    static submitAnswer(examId:number,data:AnswerUserRequest[]){
       return instance.post(`${basePath}/answer-user/${examId}`,data)//,{headers: {'Content-Type': 'application/json'}})
    }

}