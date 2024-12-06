export default class FunctionUtil {
    static convertMaptoArray = <T,>(object: object | undefined): T[] => {
        return object ? Object.values(object) : [];
    }
    static isType<T extends object>(object: object, field: string): object is T {
        return field in object
    }
}