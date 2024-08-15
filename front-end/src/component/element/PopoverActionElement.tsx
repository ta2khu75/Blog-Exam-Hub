import { Popover } from "antd";
import ActionElement from "./ActionElement";
type Props<T> = {
  data: T;
  title?: string;
  children: React.ReactNode
  handleEditClick?: (data: T) => void;
  handleDeleteClick?: (data: T) => void;
  handleViewClick?: (data: T) => void;
};
const PopoverActionElement = <T,>({
  data,
  title,
  children,
  handleDeleteClick,
  handleEditClick,
  handleViewClick,
}: Props<T>) => {
  return (
    <Popover
      placement="left"
      title={title}
      content={
        <ActionElement
          data={data}
          handleViewClick={handleViewClick}
          handleEditClick={handleEditClick}
          handleDeleteClick={handleDeleteClick}
        />
      }
    >
      {children}
    </Popover>
  );
};

export default PopoverActionElement;
