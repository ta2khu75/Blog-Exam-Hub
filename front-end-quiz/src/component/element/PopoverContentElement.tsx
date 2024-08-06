import { Button } from 'antd'
import React from 'react'
type Props<T>={
    data:T
    handleEditClick?:(data:T)=>void;
    handleDeleteClick?:(data:T)=>void;
    handleViewClick?:(data:T)=>void;
}
const PopoverContentElement = <T,>({data,handleDeleteClick, handleEditClick, handleViewClick}:Props<T>) => {
  return (
    <div>
        { handleViewClick && <Button type='primary' onClick={()=>handleViewClick(data)}>Delete</Button>}
        { handleEditClick && <Button danger onClick={()=>handleEditClick(data)}>Edit</Button>}
        { handleDeleteClick && <Button danger type='primary' onClick={()=>handleDeleteClick(data)}>Delete</Button>}
  </div>
  )
}

export default PopoverContentElement