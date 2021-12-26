import React, { Component } from "react";

import { Navbar, Nav } from "react-bootstrap";
import { Link } from "react-router-dom";

export default class NavigationBar extends Component {
  render() {
    return (
      <Navbar bg="dark" variant="dark">
        <Link to={"/market"} className="navbar-brand">
          Food Store
        </Link>
        <Nav className="mr-auto">
          <Link to={"/market"} className="nav-link">
            Продукты
          </Link>
          <Link to={"/cart"} className="nav-link">
            Корзина
          </Link>
        </Nav>
      </Navbar>
    );
  }
}
