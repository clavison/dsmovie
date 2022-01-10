import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Listing from 'pages/listing';
import Form from 'pages/form';
import Navbar from 'components/Navbar';

function App() {
  return (
    <BrowserRouter>
      <Navbar />
      <Routes>
        <Route path="/" element={<Listing />} />
        <Route path="/form">
          <Route path=":movieId" element={<Form />} />
        </Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
