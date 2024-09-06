import { useEffect, useState } from 'react'
import ExamCategoryService from '../../../../service/ExamCategoryService'
import TableElement from '../../../element/TableElement'
import ModalElement from '../../../element/ModalElement'
import { Button } from 'antd'
import ExamCategoryForm from '../form/ExamCategoryForm'
import { toast } from 'react-toastify'

const ManagerExamCategory = () => {
    const [examCategory, setExamCategory] = useState<ExamCategoryResponse>()
    const [examCategories, setExamCategories] = useState<ExamCategoryResponse[]>([])
    const [open, setOpen] = useState(false)
    useEffect(() => {
        fetchAllExamCategory()
    }, [])
    const fetchAllExamCategory = () => {
        ExamCategoryService.readAll().then(response => {
            if (response.success) {
                setExamCategories(response.data)
            }
        })
    }
    const handleCancelClick = () => {
        setOpen(false)
    }
    const handleCreateClick = () => {
        setOpen(true)
        setExamCategory(undefined)
    }
    const handleEditClick = (data: ExamCategoryResponse) => {
        setExamCategory(data)
        setOpen(true)
    }
    const handleDeleteClick = (data: ExamCategoryResponse) => {
        ExamCategoryService.delete(data.id).then(response => {
            if (response.success) {
                setExamCategories(examCategories.filter(examCategory => examCategory.id !== data.id))
                toast.success("Delete successfully")
            } else {
                toast.error(response.message_error)
            }
        })
    }
    return (
        <>
            <Button onClick={() => handleCreateClick()}> Create Exam Category</Button>
            <ModalElement handleCancel={handleCancelClick} open={open} >
                <ExamCategoryForm examCategory={examCategory} setExamCategories={setExamCategories} />
            </ModalElement>
            <TableElement array={examCategories} handleEditClick={handleEditClick} handleDeleteClick={handleDeleteClick} />
        </>
    )
}

export default ManagerExamCategory