import { createSlice, PayloadAction } from "@reduxjs/toolkit";
export interface ExamState {
   [key:string]:number;
}
const initialState:ExamState= {
}
// luu vi tri quiz dang lam trong exam
export const quizExamSlice= createSlice({
  name: "quizExam",
  initialState,
  reducers: {
    setQuizExam: (
      state = initialState,
      action: PayloadAction<{examId:string, quizIndex:number }>
    ) => {
      state[action.payload.examId]=action.payload.quizIndex;
    },
    deleteQuizExam:(state, action:PayloadAction<string>)=>{
      delete state[action.payload];
    },
    resetQuizExam: () => {
      return initialState;
    },
  },
});
export const { setQuizExam, deleteQuizExam, resetQuizExam} = quizExamSlice.actions;
export default quizExamSlice.reducer;