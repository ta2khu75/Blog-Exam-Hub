import { Button, Space, Table } from "antd";
import type { TableProps } from "antd";
type Props<T> = {
  array: T[];
  handleViewClick?: (t: T) => void;
  handleEditClick?: (t: T) => void;
  handleDeleteClick?: (t: T) => void;
  visiableColumns?: string[];
};
const TableElement = <T extends object>({
  array,
  visiableColumns,
  handleViewClick,
  handleDeleteClick,
  handleEditClick,
}: Props<T>) => {
  let columns: TableProps<T>["columns"] = [];
  const visiableColumnSet = visiableColumns
    ? new Set(visiableColumns)
    : undefined;
  if (array?.length) {
    columns = Object.keys(array[0])
      .filter((key) => {
        if (visiableColumnSet) {
          return visiableColumnSet.has(key);
        }
        return false;
      })
      .map((key) => ({
        title: key.charAt(0).toUpperCase() + key.slice(1), // Capitalize the first letter of the key for the column title
        dataIndex: key,
        key: key,
      }));
    if (handleViewClick || handleDeleteClick || handleEditClick) {
      columns.push({
        title: "Action",
        key: "action",
        render: (_, record) => (
          <Space size="middle">
            {handleViewClick && (
              <Button onClick={() => handleViewClick(record)} type="primary">
                View
              </Button>
            )}
            {handleEditClick && (
              <Button danger onClick={() => handleEditClick(record)}>
                Edit
              </Button>
            )}
            {handleDeleteClick && (
              <Button
                type="primary"
                danger
                onClick={() => handleDeleteClick(record)}
              >
                Delete
              </Button>
            )}
          </Space>
        ),
      });
    }
  }
  return <Table columns={columns} dataSource={array} />;
};

export default TableElement;
