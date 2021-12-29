import React from "react";
import { Row, Card, Table, ButtonGroup, Button } from "react-bootstrap";
import axios from "axios";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faEdit, faTrash } from "@fortawesome/free-solid-svg-icons";
import { Link } from "react-router-dom";
class Cart extends React.Component {
  constructor(props) {
    super(props);
    this.state = { products: [] };
  }

  componentDidMount() {}

  render() {
    return (
      <div>
        <Row>
          <Card className={"border border-dark bg-dark text-white"}>
            <Card.Header>
              Корзина{" "}
              <Link to={"/market"} className="btn btn-sm btn-outline-primary">
                Назад к каталогу
              </Link>
              <Button size="sm" variant="outline-danger">
                Оформить заказ
              </Button>
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
                  {this.state.products.length == 0 ? (
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
      </div>
    );
  }
}

export default Cart;
