import React from "react";

class CategoryMenu extends React.Component {
  constructor(props) {
    super(props);
  }
  render() {
    return (
         <>
        Ürünler
         {this.props.categoryList.map(category=>{
         return "Ürünler"
         // return  <span>{category.name}</span>
         })}
         </>
         );
  }
}

export default CategoryMenu;
