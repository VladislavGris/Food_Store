import React from "react";
import { Card, Col, Row, Button } from "react-bootstrap";
import "../App.css";
import ProductCard from "./ProductCard";
import axios from "axios";
class ProductList extends React.Component {
  constructor(props) {
    super(props);
    this.state = { products: [], cart: [] };
    this.addToCart = this.addToCart.bind(this);
  }

  componentDidMount() {
    axios
      .get("http://localhost:8080/api/products")
      .then((response) => response.data)
      .then((data) => this.setState({ products: data }));
    this.setState({
      cart: JSON.parse(localStorage.getItem("cart")),
    });
  }

  addToCart(productId) {
    let val = this.state.cart.find((el) => el.id === productId);
    if (val !== undefined) alert("Продукт уже в корзине");
    else {
      this.state.cart.push(
        this.state.products.filter((product) => product.id === productId)[0]
      );
    }
    localStorage.setItem("cart", JSON.stringify(this.state.cart));
    // this.props.updateCart(this.state.cart);
  }

  render() {
    return (
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
    );
  }
}

export default ProductList;
