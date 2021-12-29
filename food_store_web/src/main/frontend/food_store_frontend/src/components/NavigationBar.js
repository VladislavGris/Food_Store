import React, { Component } from "react";

import { Navbar, Nav, Button } from "react-bootstrap";
import { Link } from "react-router-dom";
import AuthService from "../services/AuthService";
export default class NavigationBar extends Component {
  logout() {
    AuthService.logout();
    localStorage.removeItem("cart");
    console.log("logout");
  }

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
          <Button onClick={this.logout} size="sm">
            <Link to={"/"} className="nav-link">
              Выход
            </Link>
          </Button>
        </Nav>
      </Navbar>
    );
  }
}
