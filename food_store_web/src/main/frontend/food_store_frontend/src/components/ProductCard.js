import React from "react";
import { Card, Col, Button } from "react-bootstrap";
class ProductCard extends React.Component {
  render() {
    return (
      <Col lg={4}>
        <Card className={"bg-transparent text-white card-background mb-3"}>
          <Card.Img variant="top" src={this.props.src} />
          <Card.Body>
            <Card.Title>{this.props.title}</Card.Title>
            <Card.Text>Цена:{this.props.price}</Card.Text>
            <Button variant="primary" size="sm">
              В корзину
            </Button>
          </Card.Body>
        </Card>
      </Col>
    );
  }
}

export default ProductCard;
