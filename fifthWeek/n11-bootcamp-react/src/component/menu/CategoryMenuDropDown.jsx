import React from 'react'
import { NavDropdown } from 'react-bootstrap';
import CategoryMenu from './CategoryMenu';

class CategoryMenuDropDown extends React.Component{
    constructor(props){
        super(props);
    }
    render(){
        return(
            <NavDropdown
                title={this.props.category.name}
                id="basic-nav-dropdown"
                key={"NavDropDown"+this.props.category.id}
                href={"/category/".concat(this.props.category.id)}
            >
                    <CategoryMenu categoryList={this.props.category.subPrdCategoryForMenuDtoList}></CategoryMenu>
            </NavDropdown>
        )

    }

}


export default CategoryMenuDropDown