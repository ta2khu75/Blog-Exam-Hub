import AnswerUserRequest from "../types/request/UserAnswerRequest";
import instance from "../util/apiInstance";
const basePath="profession"
export default class ProfessionService{
    static submitAnswer(examId:number,data:AnswerUserRequest[]){
       return instance.post(`${basePath}/answer-user/${examId}`,data)
    }

}