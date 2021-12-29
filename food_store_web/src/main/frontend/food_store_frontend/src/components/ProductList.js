import React from "react";
import { Card, Col, Row, Button } from "react-bootstrap";
import "../App.css";
import ProductCard from "./ProductCard";
import axios from "axios";
import authHeader from "../services/AuthHeader";
import MyToast from "./MyToast.js";
class ProductList extends React.Component {
  constructor(props) {
    super(props);
    this.state = { products: [], cart: [] };
    this.state.show = false;
    this.state.message = "Message";
    this.addToCart = this.addToCart.bind(this);
  }

  componentDidMount() {
    console.log(authHeader());
    axios
      .get("http://localhost:8080/api/products", { headers: authHeader() })
      .then((response) => response.data)
      .then((data) => this.setState({ products: data }));
    console.log(JSON.parse(localStorage.getItem("cart")));
    if (JSON.parse(localStorage.getItem("cart")) !== null) {
      this.setState({
        cart: JSON.parse(localStorage.getItem("cart")),
      });
    }
  }
  addToCart(productId) {
    let val = this.state.cart.find((el) => el.id === productId);
    if (val !== undefined) {
      this.setState({ show: true, message: "Продукт уже в корзине" });
    } else {
      this.setState({ show: true, message: "Продукт добавлен в корзину" });
      this.state.cart.push(
        this.state.products.filter((product) => product.id === productId)[0]
      );
    }

    setTimeout(() => this.setState({ show: false }), 3000);
    localStorage.setItem("cart", JSON.stringify(this.state.cart));
    console.log(this.state.cart);
    // this.props.updateCart(this.state.cart);
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
        <Card className={"bg-transparent text-white"}>
          <Card.Body>
            <Card.Title>Продукты</Card.Title>
            <Row>
              {this.state.products.map((product) => (
                <ProductCard
                  onClick={this.addToCart}
                  key={product.id}
                  title={product.name}
                  price={product.price}
                  src={product.imageRef}
                  productId={product.id}
                />
              ))}
            </Row>
          </Card.Body>
        </Card>
      </div>
    );
  }
}

export default ProductList;
