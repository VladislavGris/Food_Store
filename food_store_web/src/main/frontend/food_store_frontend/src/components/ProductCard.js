import React from "react";
import { Card, Col, Button, Row, Stack } from "react-bootstrap";
import "../App.css";
class ProductCard extends React.Component {
  constructor(props) {
    super(props);
    this.state = { prop: props };
  }

  render() {
    return (
      <Col lg={4}>
        <Row className="justify-content-md-center">
          <Col xs={12} sm={12} md={12}>
            <Card className={"bg-transparent text-white card-background mb-3"}>
              <Card.Img
                className="image-size"
                variant="top"
                src={this.props.src}
              />
              <Card.Body>
                <Card.Title>{this.props.title}</Card.Title>
                <Card.Text>
                  <Stack>
                    <div>
                      Цена: <u>{this.props.price}</u>
                    </div>
                    <div>
                      Страна: <u>{this.props.country}</u>
                    </div>
                    <div>
                      Категория: <u>{this.props.category}</u>
                    </div>
                    <div>
                      Торговая марка: <u>{this.props.trademark}</u>
                    </div>
                  </Stack>
                </Card.Text>
                <Button
                  variant="primary"
                  size="sm"
                  onClick={() => this.props.onClick(this.props.productId)}
                >
                  В корзину
                </Button>
              </Card.Body>
            </Card>
          </Col>
        </Row>
      </Col>
    );
  }
}

export default ProductCard;
