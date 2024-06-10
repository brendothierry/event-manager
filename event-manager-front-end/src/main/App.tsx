import {
  BrowserRouter,
  Route,
  Routes,
} from 'react-router-dom';
import EventForm from '../views/EventForm';

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<EventForm/>}>
        </Route>
      </Routes>
    </BrowserRouter>
  )
}

export default App;