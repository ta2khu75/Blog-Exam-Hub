import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import HeaderFragment from './component/fragment/HeaderFragment'
import HomeComponent from './component/HomeComponent'
import FooterFragment from './component/fragment/FooterFragment'

function App() {
  const [count, setCount] = useState(0)

  return (<>
      <main>
        <HeaderFragment/>
        <HomeComponent/>
      </main>
      <FooterFragment/>
  </>
  )
}

export default App
