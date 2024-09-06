import { Button } from "antd";
import { Link } from "react-router-dom";

type Props = {
    examResponse: ExamResponse;
    className?: string;
    handleViewClick?: (t: ExamResponse) => void;
    handleEditClick?: (t: ExamResponse) => void;
    handleDeleteClick?: (t: ExamResponse) => void;
}
const ExamCartElement = ({ examResponse, className, handleViewClick, handleDeleteClick, handleEditClick }: Props) => {
    return (
        <div className={`col-lg-4 col-md-6 col-12 mb-4 mb-lg-0 ${className}`} >
            <div className="custom-block bg-white shadow-lg">
                    <Link to={`/exam-about/${examResponse.id}`} preventScrollReset={true} >
                    <div className="d-flex">
                        <div>
                            <h6 className="mb-2">{examResponse.title}</h6>
                            <p className="mb-0">{examResponse.description}</p>
                        </div>
                        <span className={`badge bg-design rounded-pill ms-auto  ${examResponse.exam_level == "EASY" ? "bg-success" : examResponse.exam_level == "HARD" ? "bg-danger" : "bg-warning"}`}>{examResponse.exam_level}</span>
                    </div>
                    {/* <span className={`badge bg-design rounded ms-auto ${examResponse.exam_level == "EASY" ? "bg-success" : examResponse.exam_level == "HARD" ? "bg-danger" : "bg-warning"}`}>{examResponse.exam_type}</span> */}
                    <img src={examResponse.image_path} width={"356px"} height={"200px"} className="custom-block-image img-fluid" />
                    </Link>
                <div className="d-flex justify-content-around mt-4">
                {handleViewClick &&
                    <Button onClick={()=>handleViewClick(examResponse)} type='primary'>View</Button>
                    }
                    {handleEditClick &&
                    <Button danger onClick={()=>handleEditClick(examResponse)}>Edit</Button>
                    }{
                    handleDeleteClick &&
                    <Button type='primary' danger onClick={()=>handleDeleteClick(examResponse)}>Delete</Button>
                    }
                </div>
            </div>
        </div>
    )
}

export default ExamCartElement