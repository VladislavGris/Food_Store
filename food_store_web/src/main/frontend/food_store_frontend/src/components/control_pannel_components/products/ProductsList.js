import React from "react";
import {
  Row,
  Card,
  Table,
  ButtonGroup,
  Button,
  InputGroup,
  FormControl,
} from "react-bootstrap";
import axios from "axios";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faEdit,
  faTrash,
  faStepBackward,
  faFastBackward,
  faStepForward,
  faFastForward,
} from "@fortawesome/free-solid-svg-icons";
import { Link } from "react-router-dom";
import AuthHeader from "../../../services/AuthHeader.js";
class ProductsList extends React.Component {
  constructor(props) {
    super(props);
    this.state = { products: [], cart: [], productsPerPage: 9, currentPage: 1 };
  }

  componentDidMount() {
    this.findAll(this.state.currentPage);
  }

  findAll(currentPage) {
    currentPage -= 1;
    axios
      .get(
        `http://localhost:8080/api/products?pageNumber=${currentPage}&pageSize=${this.state.productsPerPage}&sortBy=id&sortDir=asc`,
        { headers: AuthHeader() }
      )
      .then((response) => response.data)
      .then((data) =>
        this.setState({
          products: data.content,
          totalPages: data.totalPages,
          totalElements: data.totalElements,
          currentPage: data.number + 1,
        })
      );
  }

  changePage = (event) => {
    let targetPage = parseInt(event.target.value);
    if (targetPage > this.state.totalPages) targetPage = this.state.totalPages;
    this.findAll(targetPage);
    this.setState({
      [event.target.name]: targetPage,
    });
  };

  firstPage = () => {
    let firstPage = 1;
    if (this.state.currentPage > firstPage) {
      this.findAll(firstPage);
    }
  };

  prevPage = () => {
    let prevPage = 1;
    if (this.state.currentPage > prevPage) {
      this.findAll(this.state.currentPage - prevPage);
    }
  };

  lastPage = () => {
    let condition = Math.ceil(
      this.state.totalElements / this.state.productsPerPage
    );
    if (this.state.currentPage < condition) {
      this.findAll(condition);
    }
  };

  nextPage = () => {
    console.log("next page");
    if (
      this.state.currentPage <
      Math.ceil(this.state.totalElements / this.state.productsPerPage)
    ) {
      this.findAll(this.state.currentPage + 1);
    }
  };

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
    const { products, currentPage, totalPages } = this.state;

    const pageNumCss = {
      width: "45px",
      border: "1px solid #17A2B8",
      color: "#17A2B8",
      textAlign: "center",
      fontWeight: "bold",
    };

    return (
      <div>
        <Row>
          <Card className={"border border-dark bg-dark text-white"}>
            <Card.Header>
              ???????????? ??????????????????{" "}
              <Link to={"/control"}>?????????? ?? ???????????? ???????????????????? </Link>
              <Button size="sm" variant="success">
                <Link
                  to={"add_product"}
                  style={{ textDecoration: "none", color: "white" }}
                >
                  ???????????????? ??????????????
                </Link>
              </Button>
            </Card.Header>
            <Card.Body>
              <Table bordered hover striped variant="dark">
                <thead>
                  <tr>
                    <th>Id</th>
                    <th>????????????????</th>
                    <th>????????</th>
                    <th>???????????????? ??????????</th>
                    <th>???????????? ??????????????????????????</th>
                    <th>??????????????????</th>
                    <th>????????????????</th>
                  </tr>
                </thead>
                <tbody>
                  {this.state.products.length == 0 ? (
                    <tr align="center">
                      <td colSpan="3">?????? ?????????????????? ??????????????????</td>
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
            {this.state.products.length > 0 ? (
              <Card.Footer>
                <div style={{ float: "left" }}>
                  Showing Page {currentPage} of {totalPages}
                </div>
                <div style={{ float: "right" }}>
                  <Button
                    type="button"
                    variant="outline-info"
                    disabled={this.state.currentPage === 1 ? true : false}
                    onClick={this.firstPage}
                  >
                    <FontAwesomeIcon icon={faFastBackward} /> First
                  </Button>
                  <Button
                    type="button"
                    variant="outline-info"
                    disabled={this.state.currentPage === 1 ? true : false}
                    onClick={this.prevPage}
                  >
                    <FontAwesomeIcon icon={faStepBackward} /> Prev
                  </Button>
                  <Button
                    type="button"
                    variant="outline-info"
                    disabled={currentPage === totalPages ? true : false}
                    onClick={this.nextPage}
                  >
                    <FontAwesomeIcon icon={faStepForward} /> Next
                  </Button>
                  <Button
                    type="button"
                    variant="outline-info"
                    disabled={currentPage === totalPages ? true : false}
                    onClick={this.lastPage}
                  >
                    <FontAwesomeIcon icon={faFastForward} /> Last
                  </Button>
                  <InputGroup size="sm">
                    <FormControl
                      style={pageNumCss}
                      className={"bg-dark"}
                      name="currentPage"
                      value={currentPage}
                      onChange={this.changePage}
                    />
                  </InputGroup>
                </div>
              </Card.Footer>
            ) : (
              <div>???????????????? ???? ??????????????</div>
            )}
          </Card>
        </Row>
      </div>
    );
  }
}

export default ProductsList;
