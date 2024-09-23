import React from 'react'
type Props = {
    into: string,
    content: string
}
const IntroductionElement = ({ into, content }: Props) => {
    return (
        <section className="hero-section d-flex justify-content-center align-items-center" style={{ color: 'white' }}>
            <div className="container text-center">
                <h2 className="text-white text-center">{into}</h2>
                <h6 className="text-dark">{content}</h6>
                <form method="get" className="custom-form mt-4">
                    <div className="input-group input-group-lg">
                        <span className="input-group-text bi-search"></span>
                        <input name="keyword" type="search" className="form-control" placeholder="Keyword to find" aria-label="Search" />
                        <button type="submit" className="btn btn-light">Search</button>
                    </div>
                </form>
            </div>
        </section>
    )
}

export default IntroductionElement;