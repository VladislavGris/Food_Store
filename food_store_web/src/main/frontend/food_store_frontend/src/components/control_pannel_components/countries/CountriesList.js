import React from "react";
import { Row, Col, Card, Table, ButtonGroup, Button } from "react-bootstrap";
import axios from "axios";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faEdit, faTrash } from "@fortawesome/free-solid-svg-icons";
import { Link } from "react-router-dom";
class CountriesList extends React.Component {
  constructor(props) {
    super(props);
    this.state = { countries: [] };
  }

  componentDidMount() {
    axios
      .get("http://localhost:8080/api/countries")
      .then((response) => response.data)
      .then((data) => this.setState({ countries: data }));
  }

  render() {
    return (
      <div>
        <Row>
          <Card className={"border border-dark bg-dark text-white"}>
            <Card.Header>
              Список стран производителей{" "}
              <Link to={"/control"}>Назад к панели управления</Link>
            </Card.Header>
            <Card.Body>
              <Table bordered hover striped variant="dark">
                <thead>
                  <tr>
                    <th>Id</th>
                    <th>Страна</th>
                    <th>Операции</th>
                  </tr>
                </thead>
                <tbody>
                  {this.state.countries.length == 0 ? (
                    <tr align="center">
                      <td colSpan="3">Нет доступных стран</td>
                    </tr>
                  ) : (
                    this.state.countries.map((country) => (
                      <tr key={country.id}>
                        <td>{country.id}</td>
                        <td>{country.name}</td>
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

export default CountriesList;
