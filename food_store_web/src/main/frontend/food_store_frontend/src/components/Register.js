import React from "react";
import { Link } from "react-router-dom";
import { Form, Button } from "react-bootstrap";
import axios from "axios";
import IntlTelInput from "react-bootstrap-intl-tel-input";
class Register extends React.Component {
  constructor(props) {
    super(props);
    this.state = this.initialState;
    this.submitRegistration = this.submitRegistration.bind(this);
    this.registrationChange = this.registrationChange.bind(this);
  }

  initialState = {
    name: "",
    surname: "",
    email: "",
    password: "",
    address: "",
    phone: "",
    nameMsg: "",
    surnameMsg: "",
    emailMsg: "",
    passwordMsg: "",
    addressMsg: "",
    phoneMsg: "",
  };

  initialMessages = {
    nameMsg: "",
    surnameMsg: "",
    emailMsg: "",
    passwordMsg: "",
    addressMsg: "",
    phoneMsg: "",
  };

  submitRegistration(event) {
    event.preventDefault();
    let body = {
      name: this.state.name,
      surname: this.state.surname,
      email: this.state.email,
      password: this.state.password,
      address: this.state.address,
      phone: this.state.phone,
    };
    axios
      .post("http://localhost:8080/register", body)
      .then((response) => {
        if (response.data.id !== null || response.data.id !== undefined) {
          alert(
            "Вы успешно зарегистрировались. Проверьте указанный адрес электронной почты"
          );
        }
        this.setState(this.initialState);
      })
      .catch((error) => {
        this.setState(this.initialMessages);
        console.log(error.response.data);
        if (error.response.data.fieldErrors !== undefined) {
          console.log(error.response.data.fieldErrors);
          error.response.data.fieldErrors.forEach((element) => {
            switch (element.field) {
              case "name":
                this.setState({ nameMsg: element.message });
                break;
              case "surname":
                this.setState({ surnameMsg: element.message });
                break;
              case "email":
                this.setState({ emailMsg: element.message });
                break;
              case "password":
                this.setState({ passwordMsg: element.message });
                break;
              case "address":
                this.setState({ addressMsg: element.message });
                break;
              case "phone":
                this.setState({ phoneMsg: element.message });
                break;
              default:
                console.log("default");
            }
          });
        }
        if (error.response.data.message !== undefined) {
          this.setState({ emailMsg: error.response.data.message });
        }
        alert("Регистрация не выполнена. Проверьте введенные данные");
      });
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
            <Form.Text
              className={
                this.state.nameMsg !== "" ? "visible text-danger" : "invisible"
              }
            >
              {this.state.nameMsg}
            </Form.Text>
          </Form.Group>

          <Form.Group className="mb-1" controlId="surname">
            <Form.Label>Фамлиля</Form.Label>
            <Form.Control
              required
              type="text"
              name="surname"
              onChange={this.registrationChange}
              value={this.state.surname}
              placeholder="Введите свою фамилию"
              className={"bg-dark text-white"}
            />
            <Form.Text
              className={
                this.state.surnameMsg !== ""
                  ? "visible text-danger"
                  : "invisible"
              }
            >
              {this.state.surnameMsg}
            </Form.Text>
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
            <Form.Text
              className={
                this.state.emailMsg !== "" ? "visible text-danger" : "invisible"
              }
            >
              {this.state.emailMsg}
            </Form.Text>
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
            <Form.Text
              className={
                this.state.passwordMsg !== ""
                  ? "visible text-danger"
                  : "invisible"
              }
            >
              {this.state.passwordMsg}
            </Form.Text>
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
            <Form.Text
              className={
                this.state.addressMsg !== ""
                  ? "visible text-danger"
                  : "invisible"
              }
            >
              {this.state.addressMsg}
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
            <Form.Text
              className={
                this.state.phoneMsg !== "" ? "visible text-danger" : "invisible"
              }
            >
              {this.state.phoneMsg}
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
