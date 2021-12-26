import React from "react";
import { Stack } from "react-bootstrap";
import { Link } from "react-router-dom";
class ControlPannel extends React.Component {
  render() {
    return (
      <div className="text-white text-center">
        <div>Панель управления администратора</div>

        <Stack direction="vertical" className="mt=3">
          <div className="bg-dark border">
            <Link to={"users"}>Пользователи</Link>
          </div>
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
      </div>
    );
  }
}

export default ControlPannel;
