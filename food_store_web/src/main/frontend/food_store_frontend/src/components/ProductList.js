import React from "react";
import {
  Card,
  Col,
  Row,
  Button,
  InputGroup,
  FormControl,
} from "react-bootstrap";
import "../App.css";
import ProductCard from "./ProductCard";
import axios from "axios";
import authHeader from "../services/AuthHeader";
import MyToast from "./MyToast.js";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faStepBackward,
  faFastBackward,
  faStepForward,
  faFastForward,
} from "@fortawesome/free-solid-svg-icons";
class ProductList extends React.Component {
  constructor(props) {
    super(props);
    this.state = { products: [], cart: [], productsPerPage: 9, currentPage: 1 };
    this.state.show = false;
    this.state.message = "Message";
    this.addToCart = this.addToCart.bind(this);
  }

  componentDidMount() {
    console.log(authHeader());
    this.findAll(this.state.currentPage);
  }

  findAll(currentPage) {
    currentPage -= 1;
    console.log("Current Page: ", currentPage);
    axios
      .get(
        `http://localhost:8080/api/products?page=${currentPage}&size=${this.state.productsPerPage}`,
        {
          headers: authHeader(),
        }
      )
      .then((response) => response.data)
      .then((data) => {
        this.setState({
          products: data.content,
          totalPages: data.totalPages,
          totalElements: data.totalElements,
          currentPage: data.number + 1,
        });
        console.log(this.state.products.length);
      });
    console.log(JSON.parse(localStorage.getItem("cart")));
    if (JSON.parse(localStorage.getItem("cart")) !== null) {
      this.setState({
        cart: JSON.parse(localStorage.getItem("cart")),
      });
    }
  }

  changePage = (event) => {
    let targetPage = parseInt(event.target.value);
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
    if (
      this.state.currentPage <
      Math.ceil(this.state.totalElements / this.state.productsPerPage)
    ) {
      this.findAll(this.state.currentPage + 1);
    }
  };

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
    // this.props.updateCart(this.state.cart);
  }

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
              {products.map((product) => (
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
            <div>Продукты не найдены</div>
          )}
        </Card>
      </div>
    );
  }
}

export default ProductList;
