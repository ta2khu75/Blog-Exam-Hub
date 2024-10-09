import { createSlice, PayloadAction } from "@reduxjs/toolkit";
const initialState = {
    value: ""
}
// luu router bi chuyen huong sang login
export const routerRedirectSlice = createSlice({
    name: "routerRedirect",
    initialState,
    reducers: {
        setRouterRedirect: (
            state = initialState,
            action: PayloadAction<string>
        ) => {
            state.value = action.payload
        },
        resetRouterRedirect: () => {
            return initialState;
        },
    },
});
export const { resetRouterRedirect, setRouterRedirect } = routerRedirectSlice.actions;
export default routerRedirectSlice.reducer;