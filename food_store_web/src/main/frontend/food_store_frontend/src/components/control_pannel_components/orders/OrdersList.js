import React from "react";
import { Row, Card, Table, ButtonGroup, Button } from "react-bootstrap";
import axios from "axios";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faEdit, faTrash } from "@fortawesome/free-solid-svg-icons";
import { Link } from "react-router-dom";
import AuthHeader from "../../../services/AuthHeader.js";
class OrdersList extends React.Component {
  constructor(props) {
    super(props);
    this.state = { orders: [] };
  }

  componentDidMount() {
    axios
      .get("http://localhost:8080/api/orders", { headers: AuthHeader() })
      .then((response) => response.data)
      .then((data) => {
        this.setState({ orders: data });
        console.log(data);
      });
  }

  render() {
    return (
      <div>
        <Row>
          <Card className={"border border-dark bg-dark text-white"}>
            <Card.Header>
              Список заказов{" "}
              <Link to={"/control"}>Назад к панели управления</Link>
            </Card.Header>
            <Card.Body>
              <Table bordered hover striped variant="dark">
                <thead>
                  <tr>
                    <th>Id</th>
                    <th>Дата доставки</th>
                    <th>Время доставки</th>
                    <th>Дата размещения</th>
                    <th>Состояние</th>
                    <th>Клиент</th>
                    <th>Заказанные товары</th>
                    {/* <th>Операции</th> */}
                  </tr>
                </thead>
                <tbody>
                  {this.state.orders.length == 0 ? (
                    <tr align="center">
                      <td colSpan="3">Нет доступных продуктоы</td>
                    </tr>
                  ) : (
                    this.state.orders.map((order) => (
                      <tr key={order.id}>
                        <td>{order.id}</td>
                        <td>{order.date}</td>
                        <td>{order.time}</td>
                        <td>{order.placedAt}</td>
                        <td>{order.state}</td>
                        <td>{order.client.id}</td>
                        <td>
                          {order.products.map((product) => (
                            <div>
                              {product.name} {product.count}
                            </div>
                          ))}
                        </td>
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

export default OrdersList;
