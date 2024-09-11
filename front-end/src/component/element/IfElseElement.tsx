import React from 'react'
type Props = {
    children: React.ReactNode;
    condition: boolean;
    caseFalse?: React.ReactNode;
}
const IfElseElement = ({ caseFalse, children, condition }: Props) => {
    if (condition) {
        return children
    } else {
        return caseFalse;
    }
}

export default IfElseElement