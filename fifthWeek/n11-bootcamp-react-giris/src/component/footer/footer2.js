import React, { Component } from "react";
import './footer2.css';
class Footer2 extends Component {
  constructor(props) {
    super(props);
    this.state = {
      clicked: "unclicked",
    };
    this.handleClick = this.handleClick.bind(this);
  }
  handleClick() {
    const clickedValue =
      this.state.clicked === "clicked" ? "unclicked" : "clicked";
    this.setState({
      clicked: clickedValue,
    });
  }
  render() {
    return (
      <div className="App">
        <button onClick={this.handleClick}>{this.props.text}</button>
        <h1>{this.state.clicked}</h1>
      </div>
    );
  }
}

// function handleClick() {
//   alert("clicked");
// }
export default Footer2;
