import React from "react";
import { Col, Row } from "react-bootstrap";
import { Link } from "react-router-dom";

class Welcome extends React.Component {
  render() {
    return (
      <div className="text-white text-center">
        <h1>Добро пожаловать в Food Store!</h1>
        <p>Food Store - удобный сервис для заказов продуктов питания</p>
        <Row>
          <Col>
            <Link to={"register"}>Регистрация</Link>
          </Col>
          <Col>
            <Link to={"login"}>Вход</Link>
          </Col>
        </Row>
      </div>
    );
  }
}

export default Welcome;
