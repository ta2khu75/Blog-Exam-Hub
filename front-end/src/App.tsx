import './App.css'
import HeaderFragment from './component/fragment/HeaderFragment'
import FooterFragment from './component/fragment/FooterFragment'
import { Outlet } from 'react-router-dom'
import ScrollToTop from './component/element/ScrollToTop'

function App() {
  return (<>
    <main id='top'>
      <HeaderFragment />
      <Outlet />
    </main>
    <FooterFragment />
    <ScrollToTop/>
  </>
  )
}

export default App
