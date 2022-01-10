import React from 'react';
function Footer(){

    return(
        <div className="App">
            <button onClick={handleClick}>Click</button>
        </div>
    );
}

function handleClick(){
    alert('clicked');
}
export default Footer;