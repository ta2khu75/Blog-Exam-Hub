export default class FileUtil {
    static getFileExtension(file: File): string {
        return file.name.split('.').pop() || '';
    }
}