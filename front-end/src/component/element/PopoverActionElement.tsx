import { Popover } from "antd";
import ActionElement from "./ActionElement";
import { TooltipPlacement } from "antd/es/tooltip";
type Props<T> = {
  data: T;
  title?: string;
  children: React.ReactNode
  placement?: TooltipPlacement
  handles?: HandlesProps[];
  handleEditClick?: (data: T) => void;
  handleDeleteClick?: (data: T) => void;
  handleViewClick?: (data: T) => void;
};
const PopoverActionElement = <T,>({
  data,
  title,
  children,
  handles,
  placement,
  handleDeleteClick,
  handleEditClick,
  handleViewClick,
}: Props<T>) => {
  return (
    <Popover
      placement={placement ?? "left"}
      title={title}
      content={
        <ActionElement
          data={data}
          handleViewClick={handleViewClick}
          handleEditClick={handleEditClick}
          handleDeleteClick={handleDeleteClick}
          handles={handles}
        />
      }
    >
      {children}
    </Popover>
  );
};

export default PopoverActionElement;
