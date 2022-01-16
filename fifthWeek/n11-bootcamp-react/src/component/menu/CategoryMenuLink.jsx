import React, { Component } from "react";
import { Nav } from "react-bootstrap";

export default class CategoryMenuLink extends Component {
  constructor(props) {
    super(props);
  }
  render() {
    return (
      <Nav.Link
        href={"/category/".concat(this.props.category.id)}
        key={"NavDropDown.Item" + this.props.category.id}
      >
        {this.props.category.name}
      </Nav.Link>
    );
  }
}
