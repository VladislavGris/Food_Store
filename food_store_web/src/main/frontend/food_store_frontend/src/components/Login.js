import React, { useState } from "react";
import { Link } from "react-router-dom";
import { Form, Button } from "react-bootstrap";
import AuthService from "../services/AuthService";
import axios from "axios";
import { useNavigate } from "react-router-dom";

export default function Login() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();

  function setDefaultState() {
    setEmail("");
    setPassword("");
  }

  function onEmailChange(event) {
    setEmail(event.target.value);
  }

  function onPasswordChange(event) {
    setPassword(event.target.value);
  }

  function onLoginSubmit(event) {
    event.preventDefault();
    let request = {
      email: email,
      password: password,
    };
    axios
      .post("http://localhost:8080/login", request)
      .then((response) => {
        if (response.data !== undefined && response.data !== null) {
          if (response.data.active === false) {
            alert("Пользователь не активирован");
            return;
          }
          localStorage.setItem("user", JSON.stringify(response.data));
          setDefaultState();
          switch (response.data.clientRole) {
            case "ROLE_USER":
              navigate("/market");
              break;
            case "ROLE_ADMIN":
              navigate("/control");
              break;
            default:
              alert("Не удалось определить привилегии пользователя");
              break;
          }
        }
      })
      .catch((error) => {
        alert(error.response.data.message);
      });
  }

  return (
    <div className="text-white text-center">
      Вход
      <Form id="loginForm" onSubmit={onLoginSubmit}>
        <Form.Group className="mb-1" controlId="email">
          <Form.Label>Email адрес</Form.Label>
          <Form.Control
            required
            type="email"
            name="email"
            value={email}
            onChange={onEmailChange}
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
            value={password}
            onChange={onPasswordChange}
            placeholder="Password"
            className={"bg-dark text-white"}
          />
        </Form.Group>
        <Button size="sm" variant="success" type="submit">
          Войти
        </Button>
      </Form>
      <Link to={"/register"}>Регистрация</Link>
      {/* {this.state.linkToPage !== null && this.state.linkToPage !== "" ? (
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
      )} */}
    </div>
  );
}
