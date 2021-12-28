import React from "react";
import { Form, Button } from "react-bootstrap";
import axios from "axios";
class AddProduct extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      product: {
        name: "",
        price: "",
        country: "",
        categoty: "",
        trademark: "",
        imgRef: "",
      },
      trademarks: [],
      countries: [],
      categoties: [],
    };
  }

  componentDidMount() {
    axios
      .get("http://localhost:8080/api/categories")
      .then((response) => response.data)
      .then((data) => this.setState({ categories: data }));
    axios
      .get("http://localhost:8080/api/countries")
      .then((response) => response.data)
      .then((data) => this.setState({ countries: data }));
    axios
      .get("http://localhost:8080/api/trademarks")
      .then((response) => response.data)
      .then((data) => this.setState({ trademarks: data }));
  }

  render() {
    return (
      <div>
        <Form id="addProductForm" onSubmit={this.addProduct}>
          <Form.Group className="mb-1" controlId="name">
            <Form.Label>Именование</Form.Label>
            <Form.Control
              required
              type="text"
              name="name"
              value={this.state.product.name}
              onChange={this.registrationChange}
              placeholder="Введите именование продукта"
              className={"bg-dark text-white"}
            />
          </Form.Group>

          <Form.Group className="mb-1" controlId="pirice">
            <Form.Label>Цена</Form.Label>
            <Form.Control
              required
              type="text"
              name="surname"
              onChange={this.registrationChange}
              value={this.state.surname}
              placeholder="Укажите цену продукта"
              className={"bg-dark text-white"}
            />
          </Form.Group>

          <Form.Group className="mb-1" controlId="trademark">
            <Form.Label>Торговая марка</Form.Label>
            <Form.Control
              required
              type="email"
              name="email"
              onChange={this.registrationChange}
              value={this.state.email}
              placeholder="Введите свой адрес электронной почти"
              className={"bg-dark text-white"}
            />
          </Form.Group>

          <Form.Group className="mb-1" controlId="country">
            <Form.Label>Страна производитель</Form.Label>
            <Form.Control
              required
              type="password"
              name="password"
              onChange={this.registrationChange}
              value={this.state.password}
              placeholder="Введите пароль"
              className={"bg-dark text-white"}
            />
          </Form.Group>

          <Form.Group className="mb-1" controlId="category">
            <Form.Label>Категория</Form.Label>
            <Form.Control
              required
              type="text"
              name="address"
              onChange={this.registrationChange}
              value={this.state.address}
              placeholder="Введите адрес"
              className={"bg-dark text-white"}
            />
          </Form.Group>

          <Form.Group className="mb-1" controlId="imgRef">
            <Form.Label>Ссылка на изображение</Form.Label>
            <Form.Control
              type="text"
              name="imgRef"
              onChange={this.registrationChange}
              value={this.state.product.imgRef}
              placeholder="Добавьте ссылку на изображение"
              className={"bg-dark text-white"}
            />
          </Form.Group>
          <Button size="sm" variant="success" type="submit">
            Добавить
          </Button>
        </Form>
      </div>
    );
  }
}

export default AddProduct;
