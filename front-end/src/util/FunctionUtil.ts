export default class FunctionUtil {
    static convertMaptoArray = <T,>(object: object | undefined): T[] => {
        return object ? Object.values(object) : [];
    }
}