import React from "react";
import { Card, Col, Row, Button } from "react-bootstrap";
import "../App.css";
import ProductCard from "./ProductCard";
import axios from "axios";
class ProductList extends React.Component {
  constructor(props) {
    super(props);
    this.state = { products: [] };
  }

  componentDidMount() {
    axios
      .get("http://localhost:8080/api/products")
      .then((response) => response.data)
      .then((data) => this.setState({ products: data }));
  }
  render() {
    return (
      <Card className={"bg-transparent text-white"}>
        <Card.Body>
          <Card.Title>Продукты</Card.Title>
          <Row>
            {this.state.products.map((product) => (
              <ProductCard
                key={product.id}
                title={product.name}
                price={product.price}
                src={product.imageRef}
              />
            ))}
          </Row>
        </Card.Body>
      </Card>
    );
  }
}

export default ProductList;
