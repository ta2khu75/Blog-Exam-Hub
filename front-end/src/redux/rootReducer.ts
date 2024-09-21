import { combineReducers } from '@reduxjs/toolkit';
// import  socialAccountReducer  from './slice/socialAccountSlice';
// import counterReducer from './slice/counterSlice';
import accountReducer from './slice/accountSlice';
import examReducer from './slice/examSlice';
import userExamReducer from './slice/userExamSlice';
import quizExamReducer from './slice/quizExamSlice';
import quizReducer from './slice/quizSlice';
import imageReducer from './slice/imageSlice';
// Import other slices as needed

// Combine all your slices into one root slice
const rootReducer = combineReducers({
//   shoppingCart: shoppingCardReducer,
  // count: counterReducer,
  account: accountReducer,
  exams: examReducer,
  userExams: userExamReducer,
  quizExam: quizExamReducer,
  quiz:quizReducer,
  image: imageReducer
  // socialAccount: socialAccountReducer
  // Add other slices here
});

export default rootReducer;