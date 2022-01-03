import React, { useState, useEffect } from "react";
import axios from "axios";
import { Link } from "react-router-dom";
import AuthHeader from "../services/AuthHeader.js";
import { Row, Card, Table, ButtonGroup, Button } from "react-bootstrap";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faCheck, faTrash } from "@fortawesome/free-solid-svg-icons";
export default function MyOrders() {
  const [orders, setOrders] = useState([]);
  const client = JSON.parse(localStorage.getItem("user"));

  useEffect(() => {
    if (orders.length === 0) {
      loadOrders();
    }
  });

  function loadOrders() {
    axios
      .get("http://localhost:8080/api/orders/client/" + client.clientId, {
        headers: AuthHeader(),
      })
      .then((response) => response.data)
      .then((data) => {
        setOrders(data);
        console.log(data);
      });
  }

  function deleteOrder(orderId) {
    axios
      .delete("http://localhost:8080/api/orders/" + orderId, {
        headers: AuthHeader(),
      })
      .then((response) => loadOrders());
  }

  function completeOrder(orderId) {
    axios
      .get("http://localhost:8080/api/orders/complete/" + orderId, {
        headers: AuthHeader(),
      })
      .then((response) => loadOrders());
  }

  return (
    <div>
      <Row>
        <Card className={"border border-dark bg-dark text-white"}>
          <Card.Header>
            Список заказов <Link to={"/market"}>Назад к каталогу</Link>
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
                  <th>Операции</th>
                </tr>
              </thead>
              <tbody>
                {orders.length === 0 ? (
                  <tr align="center">
                    <td colSpan="7">Нет доступных заказов</td>
                  </tr>
                ) : (
                  orders.map((order) => (
                    <tr key={order.id}>
                      <td>{order.id}</td>
                      <td>{order.date}</td>
                      <td>{order.time}</td>
                      <td>{order.placedAt}</td>
                      <td
                        className={
                          order.state === "Placed"
                            ? "text-danger"
                            : order.state === "Approved"
                            ? "text-info"
                            : "text-success"
                        }
                      >
                        {order.state}
                      </td>
                      <td>{order.client.id}</td>
                      <td>
                        {order.products.map((product) => (
                          <div>
                            {product.name} {product.count}
                          </div>
                        ))}
                      </td>
                      <td>
                        {order.state === "Placed" ? (
                          <Button
                            size="sm"
                            variant="danger"
                            onClick={() => deleteOrder(order.id)}
                          >
                            <FontAwesomeIcon icon={faTrash} /> Удалить
                          </Button>
                        ) : null}
                        {order.state === "Approved" ? (
                          <Button
                            size="sm"
                            variant="success"
                            onClick={() => completeOrder(order.id)}
                          >
                            <FontAwesomeIcon icon={faCheck} /> Подтвердить
                          </Button>
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
