import { getDownloadURL, ref, uploadBytes, UploadResult } from "firebase/storage";
import { fireBaseStorage } from "../../../config/FirebaseConfig";
import { v4 as uuidv4 } from 'uuid';
import FileUtil from "../../../util/FileUtil";
import PopoverActionElement from "../../element/PopoverActionElement";
import { useAppDispatch, useAppSelector } from "../../../redux/hooks";
import { addImages, deleteImages } from "../../../redux/slice/imageSlice";
type Props = {
    setContent?: React.Dispatch<React.SetStateAction<string>>
    setOpen: React.Dispatch<React.SetStateAction<boolean>>
}
const BlogUploadImage = ({ setContent, setOpen }: Props) => {
    const images = useAppSelector(state => state.image.value)
    const dispatch = useAppDispatch()
    const handleUploadChange = async (e: React.ChangeEvent<HTMLInputElement>) => {
        const files = e.target.files ? Array.from(e.target.files) : [];
        files.forEach(async (file: File) => {
            const imgRef = ref(fireBaseStorage, `/frontend/images/${uuidv4()}.${FileUtil.getFileExtension(file)}`);
            const uploadResult: UploadResult = await uploadBytes(imgRef, file);
            const downloadUrl = await getDownloadURL(uploadResult.ref);
            console.log(uploadResult);
            dispatch(addImages(downloadUrl))
        });
    }
    const handleDeleteClick = (index: number) => {
        dispatch(deleteImages(index))
    }
    const handleAddImageToContent = (image: string) => {
        if (setContent)
            setContent(content => content + `![Image](${image})`);
        setOpen(false)
    }
    return (
        <>
            {images.map((image, index) => <PopoverActionElement handles={[{ handle: () => handleAddImageToContent(image), action: "Add to Content", className: "btn btn-info" }]} placement="top" data={image} handleDeleteClick={() => handleDeleteClick(index)} key={`modal-image-${index}`}><img className="me-4" style={{ width: "300px", height: "200px" }} src={image} /></PopoverActionElement>)}
            <input onChange={e => handleUploadChange(e)} type="file" multiple />
        </>
    );
};

export default BlogUploadImage;