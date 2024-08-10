export default class RandomUtil {
  static shuffleArray<T>(array: T[]): T[] {
    // Tạo bản sao của mảng để tránh làm thay đổi mảng gốc
    const shuffled = array.slice();

    for (let i = shuffled.length - 1; i > 0; i--) {
      // Tạo chỉ số ngẫu nhiên giữa 0 và i
      const j = Math.floor(Math.random() * (i + 1));

      // Hoán đổi các phần tử tại chỉ số i và j
      [shuffled[i], shuffled[j]] = [shuffled[j], shuffled[i]];
    }

    return shuffled;
  }
}
