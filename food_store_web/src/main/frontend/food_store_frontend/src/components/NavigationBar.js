import React, { Component } from "react";

import { Navbar, Nav, Button } from "react-bootstrap";
import { Link } from "react-router-dom";
import AuthService from "../services/AuthService";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faSignOutAlt } from "@fortawesome/free-solid-svg-icons";
export default class NavigationBar extends Component {
  logout() {
    AuthService.logout();
    localStorage.removeItem("cart");
  }

  floatRight = {
    float: "right",
  };

  render() {
    return (
      <Navbar bg="dark" variant="dark">
        <Link to={"/market"} className="navbar-brand">
          Food Store
        </Link>
        <Nav className="ml-auto">
          <Link to={"/market"} className="nav-link">
            Продукты
          </Link>
          <Link to={"/cart"} className="nav-link">
            Корзина
          </Link>
          <Button
            onClick={this.logout}
            size="sm"
            className="btn btn-danger floatRight"
          >
            <Link to={"/"} className="nav-link">
              <FontAwesomeIcon icon={faSignOutAlt} /> Выход
            </Link>
          </Button>
        </Nav>
      </Navbar>
    );
  }
}
