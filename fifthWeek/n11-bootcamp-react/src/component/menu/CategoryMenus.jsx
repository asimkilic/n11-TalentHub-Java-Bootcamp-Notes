import React from "react";
import CategoryMenu from "./CategoryMenu";

class CategoryMenus extends React.Component {
  state = {
    categoryList: [],
  };
  constructor() {
    super();
  }

  componentDidMount() {
    fetch("http://localhost:8080/api/v1/categories/menu")
      .then((response) => response.json())
      .then((categoryList) => {
        this.setState({ categoryList: categoryList });
      });
  }

  render() {
    console.log(this.state.categoryList)
    return (
        <CategoryMenu categoryList={this.state.categoryList} />
    );
  }
}
export default CategoryMenus;
