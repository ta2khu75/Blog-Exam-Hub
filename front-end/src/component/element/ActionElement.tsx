import { Button } from "antd";
type Props<T> = {
  data: T;
  handles?: HandlesProps[]
  handleEditClick?: (data: T) => void;
  handleDeleteClick?: (data: T) => void;
  handleViewClick?: (data: T) => void;
};
const ActionElement = <T,>({
  data,
  handles,
  handleDeleteClick,
  handleEditClick,
  handleViewClick,
}: Props<T>) => {
  return (
    <div>
      {handleDeleteClick && (
        <Button danger type="primary" onClick={() => handleDeleteClick(data)}>
          Delete
        </Button>
      )}
      {handleEditClick && (
        <Button danger onClick={() => handleEditClick(data)}>
          Edit
        </Button>
      )}
      {handleViewClick && (
        <Button type="primary" onClick={() => handleViewClick(data)}>
          Delete
        </Button>
      )}
      {handles?.map((handle, index) => <button key={`button-${index}`} className={handle.className} onClick={() => handle.handle()}>{handle.action}</button>)}
    </div>
  );
};

export default ActionElement;
