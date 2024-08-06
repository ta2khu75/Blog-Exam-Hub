import { QuizRequest } from "../component/page/admin/form/QuizForm";
import ApiResponse from "../response/ApiResponse";
import QuizResponse from "../response/QuizResponse";
import instance from "../util/apiInstance";
const basePath ="quiz"
export default class QuizService{
    static readByExamId(id:number):Promise<ApiResponse<QuizResponse[]>>{
        return instance.get(`${basePath}/exam/${id}`)
    }
    static readById(id:number):Promise<ApiResponse<QuizResponse>>{
        return instance.get(`${basePath}/${id}`)
    }
    static create(quiz:QuizRequest):Promise<ApiResponse<QuizResponse>>{
        return instance.post(basePath, quiz)
    }
    static update(id:number, quiz:QuizRequest):Promise<ApiResponse<QuizResponse>>{
        return instance.put(`${basePath}/${id}`, quiz)
    }
    static delete(id:number):Promise<ApiResponse<void>>{
        return instance.delete(`${basePath}/${id}`)
    }
    static readAllQuizType():Promise<ApiResponse<string[]>>{
        return instance.get(`${basePath}/quiz-type`)
    }
}