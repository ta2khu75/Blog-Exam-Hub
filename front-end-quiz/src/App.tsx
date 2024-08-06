import './App.css'
import HeaderFragment from './component/fragment/HeaderFragment'
import FooterFragment from './component/fragment/FooterFragment'
import { Outlet } from 'react-router-dom'

function App() {
  return (<>
      <main>
        <HeaderFragment/>
        <Outlet/>
      </main>
      <FooterFragment/>
  </>
  )
}

export default App
