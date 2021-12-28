import React from "react";
import { Row, Col, Card, Table, ButtonGroup, Button } from "react-bootstrap";
import axios from "axios";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faEdit, faTrash } from "@fortawesome/free-solid-svg-icons";
import { Link } from "react-router-dom";
class CategoriesList extends React.Component {
  constructor(props) {
    super(props);
    this.state = { categories: [] };
  }

  componentDidMount() {
    axios
      .get("http://localhost:8080/api/categories")
      .then((response) => response.data)
      .then((data) => this.setState({ categories: data }));
  }

  render() {
    return (
      <div>
        <Row>
          <Card className={"border border-dark bg-dark text-white"}>
            <Card.Header>
              Список категорий{" "}
              <Link to={"/control"}>Назад к панели управления</Link>
              <Button>Добавить категорию</Button>
            </Card.Header>
            <Card.Body>
              <Table bordered hover striped variant="dark">
                <thead>
                  <tr>
                    <th>Id</th>
                    <th>Категория</th>
                    <th>Операции</th>
                  </tr>
                </thead>
                <tbody>
                  {this.state.categories.length == 0 ? (
                    <tr align="center">
                      <td colSpan="3">Нет доступных категорий</td>
                    </tr>
                  ) : (
                    this.state.categories.map((category) => (
                      <tr key={category.id}>
                        <td>{category.id}</td>
                        <td>{category.name}</td>
                        <td>
                          <ButtonGroup>
                            <Button size="sm" variant="outline-primary">
                              <FontAwesomeIcon icon={faEdit} />
                            </Button>
                            <Button size="sm" variant="outline-danger">
                              <FontAwesomeIcon icon={faTrash} />
                            </Button>
                          </ButtonGroup>
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

export default CategoriesList;
