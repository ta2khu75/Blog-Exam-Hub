export default class StringUtil {
    static replaceMarkdownWithImgTag(content: string): string {
        // Regular expression to find the markdown pattern ![Image](url)
        const imageMarkdownRegex = /!\[Image\]\((.*?)\)/g;

        // Replace the markdown image syntax with <img> tags
        const htmlContent = content.replace(imageMarkdownRegex, (match, p1) => {
            return `<div style="text-align: center;"><img class="img-fluid rounded" src="${p1}" style="max-width: 1000px; width: 100%; height: auto;" alt="Image" /></div>`;
        });

        return htmlContent;
    }
    static checkEmpty(str: string): boolean {
        return /^\s*$/.test(str);
    }
}