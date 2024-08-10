import { Popover } from "antd";
import ActionElement from "./ActionElement";
type Props<T> = {
  data: T;
  children: JSX.Element;
  handleEditClick?: (data: T) => void;
  handleDeleteClick?: (data: T) => void;
  handleViewClick?: (data: T) => void;
};
const PopoverActionElement = <T,>({
  data,
  children,
  handleDeleteClick,
  handleEditClick,
  handleViewClick,
}: Props<T>) => {
  return (
    <Popover
      placement="left"
      title={"Action Answer"}
      content={
        <ActionElement
          data={data}
          handleDeleteClick={handleDeleteClick}
          handleEditClick={handleEditClick}
          handleViewClick={handleViewClick}
        />
      }
    >
      {children}
    </Popover>
  );
};

export default PopoverActionElement;
