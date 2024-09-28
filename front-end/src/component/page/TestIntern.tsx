import { useState } from "react";

const TestIntern = () => {
  const [input, setInput] = useState()
  const [array, setArray] = useState<[]>([])
  return (
    <div>
      <form onSubmit={(e) => {
        e.preventDefault();
        setArray([])
        for (let i = 0; i < input; i++) {
          setArray(array => {
            return [...array, 1]
          })
        }
      }}>
        <input value={input} onChange={(e) => setInput(e.target.value)} type="text" />
      </form>
      <div style={{position:"relative"}}>
        {array.map((a, index) => {
          return <div style={{
            background: "#456BD9",
            height: "100px",
            width: "100px",
            borderRadius: "50%",
            borderColor:"red",
            border:"1px solid",
            position:"absolute",
            left:index*50+"px",
            top:index*25+"px"
          }}>
          </div>
        })}
      </div>
    </div >
  )
}

export default TestIntern;