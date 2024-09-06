import { Button, Space, Table } from "antd";
import type { TableProps } from "antd";
import { CloseOutlined, CheckOutlined } from '@ant-design/icons';
import { useMemo } from "react";
type ColumnValue = boolean | string | number| { name: string };
type Props<T> = {
  array: T[];
  showIndex?: boolean;
  handleViewClick?: (t: T) => void;
  handleEditClick?: (t: T) => void;
  handleDeleteClick?: (t: T) => void;
  visiableColumns?: string[];
};
const TableElement = <T extends object>({
  array,
  showIndex = false,
  visiableColumns,
  handleViewClick,
  handleDeleteClick,
  handleEditClick,
}: Props<T>) => {
  const visiableColumnSet = useMemo(() => visiableColumns
    ? new Set(visiableColumns)
    : undefined, [visiableColumns])
  const getColumns = () => {
    let computedColumns: TableProps<T>["columns"] = [];
    if (array?.length) {
      computedColumns = Object.keys(array[0])
        .filter((key) => {
          if (visiableColumnSet) {
            return visiableColumnSet.has(key);
          }
          return true;
        })
        .map((key) => {
          return {
            title: key.charAt(0).toUpperCase() + key.slice(1),
            dataIndex: key,
            key: key,
            render: (value: ColumnValue) => {
              if (typeof value === 'boolean') {
                return <span>{value ? <CheckOutlined className="text-success fw-bold" /> : <CloseOutlined className="text-danger" />}</span>
              } else if (typeof value === 'object' && value !== null && 'name' in value) {
                return <span>{value?.name}</span>;
              }
              else {
                return <span>{value}</span>
              }
            }
          }
        });
      if (handleViewClick || handleDeleteClick || handleEditClick) {
        computedColumns.push({
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
      if (showIndex) {
        computedColumns.unshift({
          title: 'Index',
          key: 'index',
          render: (_, record, index) => index + 1,
        });
      }
    } return computedColumns;
  }
  const columns =
    useMemo<TableProps<T>["columns"]>(
      () => getColumns(), [array, visiableColumnSet]
    )
  return <Table columns={columns} dataSource={array} pagination={false} />;
};

export default TableElement;
