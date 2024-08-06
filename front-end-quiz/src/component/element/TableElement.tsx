import { Button, Space, Table} from 'antd';
import type { TableProps } from 'antd';
type Props<T> = {
    array: T[];
    handleViewClick?:(t:T)=>void;
    handleEditClick?:(t:T)=>void;
    handleDeleteClick?:(t:T)=>void;
}
const TableElement = <T extends object>({ array, handleViewClick, handleDeleteClick, handleEditClick}: Props<T>) => {
    let columns: TableProps<T>['columns'] = [];
    if (array?.length) {
        columns = Object.keys(array[0]).map((key) => ({
            title: key.charAt(0).toUpperCase() + key.slice(1), // Capitalize the first letter of the key for the column title
            dataIndex: key,
            key: key,
            // render: (text) => <a>{text}</a>, // Example render function, you can customize this
        }));
        if(handleViewClick || handleDeleteClick || handleEditClick){
        columns.push({
            title: 'Action',
            key: 'action',
            render: (_, record) => (
                <Space size="middle">
                    {handleViewClick && 
                    <Button onClick={()=>handleViewClick(record)} type='primary'>View</Button>
                    }
                    {handleEditClick &&
                    <Button danger onClick={()=>handleEditClick(record)}>Edit</Button>
                    }{
                    handleDeleteClick &&
                    <Button type='primary' danger onClick={()=>handleDeleteClick(record)}>Delete</Button>
                    }
                </Space>
            ),
        },)
        }
    }
    return (
        <Table columns={columns} dataSource={array} />
    )
}

export default TableElement
