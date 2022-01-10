import logo from './logo.svg';
import './App.css';
import Footer from './component/footer/footer';
import Header from './component/header/header';
import Body from './component/body/body';
import Footer2 from './component/footer/footer2';

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <Header></Header>
        <Body></Body>
       
       <Footer2 text="n11" />
       <Footer2 text="button 2" />
       <Footer2 text="button 3" />
      </header>
    </div>
  );
}

export default App;
