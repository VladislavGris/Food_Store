import React from "react";
import { Row, Card, Table, ButtonGroup, Button } from "react-bootstrap";
import axios from "axios";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faEdit,
  faTrash,
  faStopCircle,
  faPlayCircle,
} from "@fortawesome/free-solid-svg-icons";
import { Link } from "react-router-dom";
import AuthHeader from "../../../services/AuthHeader.js";
class UsersList extends React.Component {
  constructor(props) {
    super(props);
    this.state = { users: [] };
  }

  componentDidMount() {
    this.getUsers();
  }

  getUsers() {
    axios
      .get("http://localhost:8080/api/clients", { headers: AuthHeader() })
      .then((response) => response.data)
      .then((data) => {
        console.log(data);
        this.setState({ users: data });
      });
  }

  activateUser = (userId) => {
    axios
      .get("http://localhost:8080/api/clients/activate/" + userId, {
        headers: AuthHeader(),
      })
      .then((response) => this.getUsers());
  };

  deactivateUser = (userId) => {
    axios
      .get("http://localhost:8080/api/clients/deactivate/" + userId, {
        headers: AuthHeader(),
      })
      .then((response) => this.getUsers());
  };

  render() {
    return (
      <div>
        <Row>
          <Card className={"border border-dark bg-dark text-white"}>
            <Card.Header>
              Список зарегистрированных пользователей{" "}
              <Link to={"/control"}>Назад к панели управления</Link>
            </Card.Header>
            <Card.Body>
              <Table bordered hover striped variant="dark">
                <thead>
                  <tr>
                    <th>Id</th>
                    <th>Имя</th>
                    <th>Фамилия</th>
                    <th>Email</th>
                    <th>Роль</th>
                    <th>Адрес</th>
                    <th>Телефон</th>
                    <th>Активен</th>
                    <th>Операции</th>
                  </tr>
                </thead>
                <tbody>
                  {this.state.users.length === 0 ? (
                    <tr align="center">
                      <td colSpan="8">Нет зарегистрированных пользователей</td>
                    </tr>
                  ) : (
                    this.state.users.map((user) => (
                      <tr key={user.id}>
                        <td>{user.id}</td>
                        <td>{user.name}</td>
                        <td>{user.surname}</td>
                        <td>{user.email}</td>
                        <td>{user.role}</td>
                        <td>{user.address}</td>
                        <td>{user.phone}</td>
                        <td>{user.active ? "Активен" : "Не активен"}</td>
                        <td>
                          {user.role === "ROLE_USER" ? (
                            <ButtonGroup>
                              <Button
                                size="sm"
                                variant="outline-success"
                                onClick={this.activateUser.bind(this, user.id)}
                              >
                                <FontAwesomeIcon icon={faPlayCircle} />
                              </Button>
                              <Button
                                size="sm"
                                variant="outline-danger"
                                onClick={this.deactivateUser.bind(
                                  this,
                                  user.id
                                )}
                              >
                                <FontAwesomeIcon icon={faStopCircle} />
                              </Button>
                            </ButtonGroup>
                          ) : null}
                        </td>
                      </tr>
                    ))
                  )}
                </tbody>
              </Table>
            </Card.Body>
          </Card>
        </Row>
      </div>
    );
  }
}

export default UsersList;
