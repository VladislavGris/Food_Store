import React from "react";
import { Row, Card, Table, ButtonGroup, Button, Form } from "react-bootstrap";
import axios from "axios";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faEdit, faTrash } from "@fortawesome/free-solid-svg-icons";
import { Link } from "react-router-dom";
import authHeader from "../services/AuthHeader";
import MyToast from "./MyToast.js";
class Cart extends React.Component {
  constructor(props) {
    super(props);
    this.state = { products: [], date: "", time: "" };
    this.state.show = false;
    this.state.message = "Заказ сохранен";
    this.processOrder = this.processOrder.bind(this);
    this.formChange = this.formChange.bind(this);
  }

  componentDidMount() {
    console.log(JSON.parse(localStorage.getItem("cart")));
    if (JSON.parse(localStorage.getItem("cart") !== null)) {
      this.setState({
        products: JSON.parse(localStorage.getItem("cart")),
        date: "",
        time: "",
      });
    }
  }

  initalState = {
    products: [],
    date: "",
    time: "",
  };

  deleteProduct = (productId) => {
    let products = this.state.products;
    products.splice(
      products.findIndex((e) => e.id === productId),
      1
    );
    this.setState({
      products: products,
    });
    localStorage.setItem("cart", JSON.stringify(this.state.products));
  };

  processOrder(event) {
    event.preventDefault();
    if (this.state.products.length == 0) {
      this.setState({
        show: true,
        message: "Корзина пуста",
      });
      setTimeout(() => this.setState({ show: false }), 3000);
      return;
    }
    let order = {
      date: this.state.date,
      time: this.state.time,
      client: JSON.parse(localStorage.getItem("user")).clientId,
      products: this.state.products,
    };
    axios
      .post("http://localhost:8080/api/orders", order, {
        headers: authHeader(),
      })
      .then((response) => {
        if (response.data != null) {
          this.setState({
            show: true,
            message: "Заказ сохранен",
          });
          setTimeout(() => this.setState({ show: false }), 3000);
          this.setState(this.initalState);
          localStorage.setItem("cart", JSON.stringify(this.state.products));
        }
      });
  }

  formChange(event) {
    console.log(event.target.name);
    console.log(event.target.value);
    this.setState({
      [event.target.name]: event.target.value,
    });
  }

  render() {
    return (
      <div>
        <div>
          <MyToast
            children={{
              show: this.state.show,
              message: this.state.message,
            }}
          />
        </div>
        <Row>
          <Card className={"border border-dark bg-dark text-white"}>
            <Card.Header>
              Корзина{" "}
              <Link to={"/market"} className="btn btn-sm btn-outline-primary">
                Назад к каталогу
              </Link>
            </Card.Header>
            <Card.Body>
              <Table bordered hover striped variant="dark">
                <thead>
                  <tr>
                    <th>Название</th>
                    <th>Цена</th>
                    <th>Торговая марка</th>
                    <th>Страна производитель</th>
                    <th>Категория</th>
                    <th>Операции</th>
                  </tr>
                </thead>
                <tbody>
                  {this.state.products.length === 0 ? (
                    <tr align="center">
                      <td colSpan="5">Корзина пуста</td>
                    </tr>
                  ) : (
                    this.state.products.map((product) => (
                      <tr key={product.id}>
                        <td>{product.name}</td>
                        <td>{product.price}</td>
                        <td>{product.trademark.name}</td>
                        <td>{product.country.name}</td>
                        <td>{product.category.name}</td>
                        <td>
                          <ButtonGroup>
                            <Button
                              size="sm"
                              variant="outline-danger"
                              onClick={this.deleteProduct.bind(
                                this,
                                product.id
                              )}
                            >
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
        <div>
          <Form
            id="orderForm"
            className="text-white"
            onSubmit={this.processOrder}
          >
            <Form.Group className="mb-1" controlId="date">
              <Form.Label>Дата доставки</Form.Label>
              <Form.Control
                required
                type="date"
                name="date"
                value={this.state.date}
                onChange={this.formChange}
                placeholder="Укажите дату доставки"
                className={"bg-dark text-white"}
              />
            </Form.Group>

            <Form.Group className="mb-1" controlId="time">
              <Form.Label>Пароль</Form.Label>
              <Form.Control
                required
                type="time"
                name="time"
                value={this.state.time}
                onChange={this.formChange}
                placeholder="Укажите время доставки"
                className={"bg-dark text-white"}
              />
            </Form.Group>
            <Button size="sm" variant="success" type="submit">
              Оформить заказ
            </Button>
          </Form>
        </div>
      </div>
    );
  }
}

export default Cart;
