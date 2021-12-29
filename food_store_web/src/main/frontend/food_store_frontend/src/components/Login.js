import React from "react";
import { Link } from "react-router-dom";
import { Form, Button } from "react-bootstrap";
import AuthService from "../services/AuthService";
class Login extends React.Component {
  constructor(props) {
    super(props);
    this.state = { email: "", password: "" };
    this.loginChange = this.loginChange.bind(this);
    this.submitLogin = this.submitLogin.bind(this);
  }

  submitLogin(event) {
    event.preventDefault();
    AuthService.login(this.state.email, this.state.password).then(() => {
      const { history } = this.props.history;
      history.push("/market");
      window.location.reload();
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
      </div>
    );
  }
}

export default Login;
