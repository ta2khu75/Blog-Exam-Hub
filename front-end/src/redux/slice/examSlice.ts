import { createSlice, PayloadAction } from "@reduxjs/toolkit";
export interface ExamState {
   [key:string]:QuizDetailsResponse[];
}
const initialState:ExamState= {
}
// luu danh sach quiz
export const examSlice= createSlice({
  name: "exam",
  initialState,
  reducers: {
    setExam: (
      state = initialState,
      action: PayloadAction<{examId:string, quizzes:QuizDetailsResponse[]}>
    ) => {
      state[action.payload.examId]=action.payload.quizzes;
    },
    deleteExam:(state, action:PayloadAction<string>)=>{
      delete state[action.payload];
    },
    resetExam: () => {
      return initialState;
    },
  },
});
export const { setExam, resetExam, deleteExam} = examSlice.actions;
export default examSlice.reducer;