import React from "react";
import { Link } from "react-router-dom";
import { Form, Button } from "react-bootstrap";
import AuthService from "../services/AuthService";
import { useNavigate } from "react-router-dom";
import axios from "axios";
class Login extends React.Component {
  constructor(props) {
    super(props);
    this.state = { email: "", password: "", linkToPage: "" };
    this.loginChange = this.loginChange.bind(this);
    this.submitLogin = this.submitLogin.bind(this);
  }

  submitLogin(event) {
    event.preventDefault();
    let req = {
      email: this.state.email,
      password: this.state.password,
    };
    axios
      .post("http://localhost:8080/login", req)
      .then((response) => {
        console.log(response.data);
        if (response.data.token) {
          localStorage.setItem("user", JSON.stringify(response.data));
        }
        if (response.data.clientRole === "ROLE_USER") {
          this.setState({
            linkToPage: "USER",
          });
          console.log("USER");
        } else {
          this.setState({
            linkToPage: "ADMIN",
          });
        }
      })
      .catch((error) => {
        alert("Пароль или логи указаны неверно");
      });
  }

  loginChange(event) {
    this.setState({
      [event.target.name]: event.target.value,
    });
  }

  render() {
    return (
      <div className="text-white text-center">
        Вход
        <Form id="loginForm" onSubmit={this.submitLogin}>
          <Form.Group className="mb-1" controlId="email">
            <Form.Label>Email адрес</Form.Label>
            <Form.Control
              required
              type="email"
              name="email"
              value={this.state.email}
              onChange={this.loginChange}
              placeholder="Enter email"
              className={"bg-dark text-white"}
            />
          </Form.Group>

          <Form.Group className="mb-1" controlId="password">
            <Form.Label>Пароль</Form.Label>
            <Form.Control
              required
              type="password"
              name="password"
              value={this.state.password}
              onChange={this.loginChange}
              placeholder="Password"
              className={"bg-dark text-white"}
            />
          </Form.Group>
          <Button size="sm" variant="success" type="submit">
            Войти
          </Button>
        </Form>
        <Link to={"/register"}>Регистрация</Link>
        {this.state.linkToPage !== null && this.state.linkToPage !== "" ? (
          this.state.linkToPage === "USER" ? (
            <div>
              <Link to={"/market"}>Магазин</Link>
            </div>
          ) : (
            <div>
              <Link to={"/control"}>Панель управления администратора</Link>
            </div>
          )
        ) : (
          <div>Вход еще не выполнен</div>
        )}
      </div>
    );
  }
}

export default Login;
