// import { createSlice} from "@reduxjs/toolkit";
// import type { PayloadAction } from '@reduxjs/toolkit'
// import { SocialAccount } from "../../model/SocialAccount";
// import AuthenticationResponse from "../../response/AuthenticationResponse";
// export interface SocialAccountResponse{
//     social_account?:SocialAccount;
//     account?:AuthenticationResponse;
// }
// const initialState:SocialAccountResponse={

// }
// export const accessTokenSlice=createSlice({
//     name:"accessToken",
//     initialState,
//     reducers:{
//         setSocialAccount:(state={...initialState}, action:PayloadAction<SocialAccountResponse>)=>{
//             state.social_account=action.payload.social_account
//         },
//         resetSocialAccount:(state)=>{
//             state.social_account=initialState.social_account
//         }
//     }
// })
// export const {setSocialAccount, resetSocialAccount}=accessTokenSlice.actions
// export default accessTokenSlice.reducer