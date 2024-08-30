import { AxiosError } from "axios";

export default interface FailedQueueItem {
  resolve: (token: string) => void;
  reject: (error: AxiosError) => void;
}

