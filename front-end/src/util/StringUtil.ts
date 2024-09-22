export default class StringUtil {
    static replaceMarkdownWithImgTag(content: string): string {
        // Regular expression to find the markdown pattern ![Image](url)
        const imageMarkdownRegex = /!\[Image\]\((.*?)\)/g;

        // Replace the markdown image syntax with <img> tags
        const htmlContent = content.replace(imageMarkdownRegex, (match, p1) => {
            return `<div style="text-align: center;"><img src="${p1}" width="1000px" alt="Image" /></div>`;
        });
        return htmlContent;
    }
}