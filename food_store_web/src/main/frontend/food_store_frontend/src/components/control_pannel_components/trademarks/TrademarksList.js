import React from "react";
import { Row, Card, Table, ButtonGroup, Button } from "react-bootstrap";
import axios from "axios";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faEdit, faTrash } from "@fortawesome/free-solid-svg-icons";
import { Link } from "react-router-dom";
class TrademarksList extends React.Component {
  constructor(props) {
    super(props);
    this.state = { trademarks: [] };
  }

  componentDidMount() {
    axios
      .get("http://localhost:8080/api/trademarks")
      .then((response) => response.data)
      .then((data) => this.setState({ trademarks: data }));
  }

  render() {
    return (
      <div>
        <Row>
          <Card className={"border border-dark bg-dark text-white"}>
            <Card.Header>
              Список торговых марок{" "}
              <Link to={"/control"}>Назад к панели управления</Link>
            </Card.Header>
            <Card.Body>
              <Table bordered hover striped variant="dark">
                <thead>
                  <tr>
                    <th>Id</th>
                    <th>Торговая марка</th>
                    {/* <th>Операции</th> */}
                  </tr>
                </thead>
                <tbody>
                  {this.state.trademarks.length === 0 ? (
                    <tr align="center">
                      <td colSpan="3">Нет доступных торговых марок</td>
                    </tr>
                  ) : (
                    this.state.trademarks.map((trademark) => (
                      <tr key={trademark.id}>
                        <td>{trademark.id}</td>
                        <td>{trademark.name}</td>
                        {/* <td>
                          <ButtonGroup>
                            <Button size="sm" variant="outline-primary">
                              <FontAwesomeIcon icon={faEdit} />
                            </Button>
                            <Button size="sm" variant="outline-danger">
                              <FontAwesomeIcon icon={faTrash} />
                            </Button>
                          </ButtonGroup>
                        </td> */}
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

export default TrademarksList;
