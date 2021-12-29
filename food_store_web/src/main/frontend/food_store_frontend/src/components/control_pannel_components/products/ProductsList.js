import React from "react";
import { Row, Card, Table, ButtonGroup, Button } from "react-bootstrap";
import axios from "axios";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faEdit, faTrash } from "@fortawesome/free-solid-svg-icons";
import { Link } from "react-router-dom";
import AuthHeader from "../../../services/AuthHeader.js";
class ProductsList extends React.Component {
  constructor(props) {
    super(props);
    this.state = { products: [] };
  }

  componentDidMount() {
    axios
      .get("http://localhost:8080/api/products", { headers: AuthHeader() })
      .then((response) => response.data)
      .then((data) => this.setState({ products: data }));
  }

  deleteProduct = (productId) => {
    axios
      .delete("http://localhost:8080/api/products/" + productId, {
        headers: AuthHeader(),
      })
      .then((response) => {
        if (response.data != null) {
          this.setState({
            products: this.state.products.filter(
              (product) => product.id !== productId
            ),
          });
        }
      });
  };

  render() {
    return (
      <div>
        <Row>
          <Card className={"border border-dark bg-dark text-white"}>
            <Card.Header>
              Список продуктов{" "}
              <Link to={"/control"}>Назад к панели управления </Link>
              <Button size="sm" variant="success">
                <Link
                  to={"add_product"}
                  style={{ textDecoration: "none", color: "white" }}
                >
                  Добавить продукт
                </Link>
              </Button>
            </Card.Header>
            <Card.Body>
              <Table bordered hover striped variant="dark">
                <thead>
                  <tr>
                    <th>Id</th>
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
                      <td colSpan="3">Нет доступных продуктоы</td>
                    </tr>
                  ) : (
                    this.state.products.map((product) => (
                      <tr key={product.id}>
                        <td>{product.id}</td>
                        <td>{product.name}</td>
                        <td>{product.price}</td>
                        <td>{product.trademark.name}</td>
                        <td>{product.country.name}</td>
                        <td>{product.category.name}</td>
                        <td>
                          <ButtonGroup>
                            <Link
                              to={"update/" + product.id}
                              className="btn btn-sm btn-outline-primary"
                            >
                              <FontAwesomeIcon icon={faEdit} />
                            </Link>
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

export default ProductsList;
