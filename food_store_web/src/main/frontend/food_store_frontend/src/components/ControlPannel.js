import React from "react";
import { Stack, Button } from "react-bootstrap";
import { Link } from "react-router-dom";
import AuthService from "../services/AuthService";
class ControlPannel extends React.Component {
  logout() {
    AuthService.logout();
    localStorage.removeItem("cart");
  }

  render() {
    return JSON.parse(localStorage.getItem("user")) === null ||
      JSON.parse(localStorage.getItem("user")).clientRole === "ROLE_USER" ? (
      <div>
        <div className="text-white">Вы не являетесь администратором</div>
        <Button onClick={this.logout} className="text-white" size="sm">
          <Link to={"/"}>Выход</Link>
        </Button>
      </div>
    ) : (
      <div className="text-white text-center">
        <div>Панель управления администратора</div>

        <Stack direction="vertical" className="mt=3">
          {/* <div className="bg-dark border">
            <Link to={"users"}>Пользователи</Link>
          </div> */}
          <div className="bg-dark border">
            <Link to={"categories"}>Категории</Link>
          </div>
          <div className="bg-dark border">
            <Link to={"countries"}>Страны</Link>
          </div>
          <div className="bg-dark border">
            <Link to={"orders"}>Заказы</Link>
          </div>
          <div className="bg-dark border">
            <Link to={"trademarks"}>Торговые марки</Link>
          </div>
          <div className="bg-dark border">
            <Link to={"products"}>Продукты</Link>
          </div>
        </Stack>
        <Button
          onClick={this.logout}
          className="btn btn-sm btn-danger text-white"
          size="sm"
        >
          <Link to={"/"} className="text-white">
            Выход
          </Link>
        </Button>
      </div>
    );
  }
}

export default ControlPannel;
