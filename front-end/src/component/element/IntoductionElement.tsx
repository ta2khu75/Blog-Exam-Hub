import { useState } from 'react'
type Props = {
    into: string,
    content: string,
    keyword?: string,
    setKeyword?: React.Dispatch<React.SetStateAction<string>>
}
const IntroductionElement = ({ into, content, keyword, setKeyword }: Props) => {
    const [search, setSearch] = useState(keyword);
    const handleSubmitSearch = (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        if (search && setKeyword) setKeyword(search);
    }
    const handleChangeSearch = (e: React.ChangeEvent<HTMLInputElement>) => {
        setSearch(e.target.value);
    }
    return (
        <section className="hero-section d-flex justify-content-center align-items-center" style={{ color: 'white' }}>
            <div className="container text-center">
                <h2 className="text-white text-center">{into}</h2>
                <h6 className="text-dark">{content}</h6>
                <form onSubmit={(e) => handleSubmitSearch(e)} className="custom-form mt-4">
                    <div className="input-group input-group-lg">
                        <span className="input-group-text bi-search"></span>
                        <input value={search} type='search' onChange={e => handleChangeSearch(e)} className="form-control" placeholder="Keyword to find" aria-label="Search" />
                        <button type="submit" className="btn btn-light">Search</button>
                    </div>
                </form>
            </div>
        </section>
    )
}

export default IntroductionElement;