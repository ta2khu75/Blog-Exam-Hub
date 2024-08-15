import { createSlice, PayloadAction } from "@reduxjs/toolkit";
interface ExamListQuiz{
  id:number;
  value:number;
}
export interface ExamState {
   [key:number]:number;
}
const initialState:ExamState= {
}
export const quizExamSlice= createSlice({
  name: "quizNumber",
  initialState,
  reducers: {
    setQuizExam: (
      state = initialState,
      action: PayloadAction<ExamListQuiz>
    ) => {
      state[action.payload.id]=action.payload.value;
    },
    deleteQuizExam:(state, action:PayloadAction<number>)=>{
      delete state[action.payload];
    },
    resetQuizExam: () => {
      return initialState;
    },
  },
});
export const { setQuizExam, deleteQuizExam, resetQuizExam} = quizExamSlice.actions;
export default quizExamSlice.reducer;