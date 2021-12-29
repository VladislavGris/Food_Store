import React from "react";
import { Form, Button } from "react-bootstrap";
import axios from "axios";
import { Link } from "react-router-dom";
import AuthHeader from "../../../services/AuthHeader.js";
class UpdateProduct extends React.Component {
  constructor(props) {
    super(props);
    this.state = this.initialState;
    this.addFormChange = this.addFormChange.bind(this);
    this.submitAdd = this.submitAdd.bind(this);
  }

  initialState = {
    id: "",
    name: "",
    price: "",
    country: "",
    category: "",
    trademark: "",
    imageRef: "",
    trademarks: [],
    countries: [],
    categories: [],
  };

  submitAdd(event) {
    event.preventDefault();
    const product = {
      name: this.state.name,
      price: this.state.price,
      country: this.state.country,
      category: this.state.category,
      trademark: this.state.trademark,
      imageRef: this.state.imageRef,
    };
    axios
      .post("http://localhost:8080/api/products", product, {
        headers: AuthHeader(),
      })
      .then((response) => response.data)
      .then((data) => {
        this.state = this.initialState;
      });
  }

  addFormChange(event) {
    this.setState({
      [event.target.name]: event.target.value,
    });
  }

  componentDidMount() {
    axios
      .get("http://localhost:8080/api/categories", { headers: AuthHeader() })
      .then((response) => response.data)
      .then((data) =>
        this.setState({ categories: data, category: data[0].name })
      );
    axios
      .get("http://localhost:8080/api/countries", { headers: AuthHeader() })
      .then((response) => response.data)
      .then((data) =>
        this.setState({ countries: data, country: data[0].name })
      );
    axios
      .get("http://localhost:8080/api/trademarks", { headers: AuthHeader() })
      .then((response) => response.data)
      .then((data) =>
        this.setState({ trademarks: data, trademark: data[0].name })
      );
    console.log(this.props.match);
    // const id = +this.props.match.params.id;
    // if (id) {
    //   axios
    //     .get("http://localhost:8080/api/products")
    //     .then((response) => response.data)
    //     .then((data) =>
    //       this.setState({
    //         name: data.name,
    //         price: data.price,
    //         id: data.id,
    //         country: data.country.name,
    //         trademark: data.trademark.name,
    //         category: data.category.name,
    //         imageRef: data.imageRef,
    //       })
    //     );
    // }
  }

  render() {
    return (
      <div>
        <h2 className="text-white">Обновить продукт </h2>
        <Link to={"/control/products"}>Назад к списку продуктов</Link>
        <Form
          id="addProductForm"
          onSubmit={this.submitAdd}
          className="text-white"
        >
          <Form.Group className="mb-1" controlId="name">
            <Form.Label>Именование</Form.Label>
            <Form.Control
              autoComplete="off"
              required
              type="text"
              name="name"
              value={this.state.name}
              onChange={this.addFormChange}
              placeholder="Введите именование продукта"
              className={"bg-dark text-white"}
            />
          </Form.Group>

          <Form.Group className="mb-1" controlId="pirice">
            <Form.Label>Цена</Form.Label>
            <Form.Control
              autoComplete="off"
              required
              type="number"
              name="price"
              onChange={this.addFormChange}
              value={this.state.price}
              min={0}
              step={0.01}
              placeholder="Укажите цену продукта"
              className={"bg-dark text-white"}
            />
          </Form.Group>

          <Form.Group className="mb-1" controlId="trademark">
            <Form.Label>Торговая марка</Form.Label>
            <Form.Select
              required
              name="trademark"
              value={this.state.trademark}
              onChange={this.addFormChange}
              className={"bg-dark text-white"}
            >
              {this.state.trademarks.map((trademark) => (
                <option key={trademark.id} value={trademark.Id}>
                  {trademark.name}
                </option>
              ))}
            </Form.Select>
          </Form.Group>

          <Form.Group className="mb-1" controlId="trademark">
            <Form.Label>Страна производитель</Form.Label>
            <Form.Select
              required
              name="country"
              value={this.state.country}
              onChange={this.addFormChange}
              className={"bg-dark text-white"}
            >
              {this.state.countries.map((country) => (
                <option key={country.id} value={country.Id}>
                  {country.name}
                </option>
              ))}
            </Form.Select>
          </Form.Group>

          <Form.Group className="mb-1" controlId="trademark">
            <Form.Label>Категория</Form.Label>
            <Form.Select
              required
              name="category"
              value={this.state.category}
              onChange={this.addFormChange}
              className={"bg-dark text-white"}
            >
              {this.state.categories.map((category) => (
                <option key={category.id} value={category.Id}>
                  {category.name}
                </option>
              ))}
            </Form.Select>
          </Form.Group>

          <Form.Group className="mb-1" controlId="imgRef">
            <Form.Label>Ссылка на изображение</Form.Label>
            <Form.Control
              autoComplete="off"
              type="text"
              name="imageRef"
              onChange={this.addFormChange}
              value={this.state.imageRef}
              placeholder="Добавьте ссылку на изображение"
              className={"bg-dark text-white"}
            />
          </Form.Group>
          <Button size="sm" variant="success" type="submit">
            Обновить
          </Button>
        </Form>
      </div>
    );
  }
}

export default UpdateProduct;
