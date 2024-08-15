import { createSlice, PayloadAction } from "@reduxjs/toolkit";
import AuthResponse from "../../response/AuthResponse";
const initialState: AuthResponse = {
  authenticated: false,
};
export const accountSlice = createSlice({
  name: "account",
  initialState,
  reducers: {
    setAccount: (
      state = { ...initialState },
      action: PayloadAction<AuthResponse>
    ) => {
      state.access_token = action.payload.access_token;
      state.account = action.payload.account;
      state.authenticated = action.payload.authenticated;
      state.refresh_token = action.payload.refresh_token;
    },
    resetAccount: (state) => {
      state.access_token = initialState.access_token;
      state.account = initialState.account;
      state.authenticated= initialState.authenticated;
      state.refresh_token = initialState.refresh_token;
    },
  },
});
export const { setAccount, resetAccount } = accountSlice.actions;
export default accountSlice.reducer;
