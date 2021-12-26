import React from "react";
import { Link } from "react-router-dom";
import { Form, Button } from "react-bootstrap";
class Register extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      name: "",
      surname: "",
      email: "",
      password: "",
      address: "",
      phone: "",
    };
    this.submitRegistration = this.submitRegistration.bind(this);
    this.registrationChange = this.registrationChange.bind(this);
  }

  submitRegistration(event) {
    event.preventDefault();
  }

  registrationChange(event) {
    this.setState({
      [event.target.name]: event.target.value,
    });
  }

  render() {
    return (
      <div className="text-white text-center">
        Регистрация
        <Form id="registrationForm" onSubmit={this.submitRegistration}>
          <Form.Group className="mb-1" controlId="name">
            <Form.Label>Имя</Form.Label>
            <Form.Control
              required
              type="text"
              name="name"
              value={this.state.name}
              onChange={this.registrationChange}
              placeholder="Введите свое имя"
              className={"bg-dark text-white"}
            />
          </Form.Group>

          <Form.Group className="mb-1" controlId="surname">
            <Form.Label>Фамлиля</Form.Label>
            <Form.Control
              required
              type="text"
              name="surname"
              onChange={this.registrationChange}
              value={this.state.surname}
              placeholder="Введите свою фпмилию"
              className={"bg-dark text-white"}
            />
          </Form.Group>

          <Form.Group className="mb-1" controlId="email">
            <Form.Label>Email адрес</Form.Label>
            <Form.Control
              required
              type="email"
              name="email"
              onChange={this.registrationChange}
              value={this.state.email}
              placeholder="Введите свой адрес электронной почти"
              className={"bg-dark text-white"}
            />
          </Form.Group>

          <Form.Group className="mb-1" controlId="password">
            <Form.Label>Пароль</Form.Label>
            <Form.Control
              required
              type="password"
              name="password"
              onChange={this.registrationChange}
              value={this.state.password}
              placeholder="Введите пароль"
              className={"bg-dark text-white"}
            />
          </Form.Group>

          <Form.Group className="mb-1" controlId="address">
            <Form.Label>Адрес</Form.Label>
            <Form.Control
              required
              type="text"
              name="address"
              onChange={this.registrationChange}
              value={this.state.address}
              placeholder="Введите адрес"
              className={"bg-dark text-white"}
            />
            <Form.Text className="text-muted">
              По указанному адресу будет производиться доставка заказов
            </Form.Text>
          </Form.Group>

          <Form.Group className="mb-1" controlId="phone">
            <Form.Label>Телефон</Form.Label>
            <Form.Control
              required
              type="tel"
              name="phone"
              onChange={this.registrationChange}
              value={this.state.phone}
              placeholder="Введите свой номер телефона"
              className={"bg-dark text-white"}
            />
            <Form.Text className="text-muted">
              По указанному телефону может позвонить курьер для уточнения места
              доставки
            </Form.Text>
          </Form.Group>
          <Button size="sm" variant="success" type="submit">
            Зарегистрироваться
          </Button>
        </Form>
        <Link to={"/login"}>Вход</Link>
      </div>
    );
  }
}

export default Register;
