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
  faSearch,
  faTimes,
} from "@fortawesome/free-solid-svg-icons";
class ProductList extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      products: [],
      cart: [],
      productsPerPage: 9,
      currentPage: 1,
      priceDir: "asc",
      search: "",
      filters: null,
    };
    this.state.show = false;
    this.state.message = "Message";
    this.addToCart = this.addToCart.bind(this);
  }

  componentDidMount() {
    this.findAll(this.state.currentPage);
    if (JSON.parse(localStorage.getItem("cart")) !== null) {
      this.setState({
        cart: JSON.parse(localStorage.getItem("cart")),
      });
    }
  }

  findAll(currentPage) {
    currentPage -= 1;
    console.log("Find all");
    axios
      .get(
        `http://localhost:8080/api/products?pageNumber=${currentPage}&pageSize=${this.state.productsPerPage}&sortBy=price&sortDir=${this.state.priceDir}`,
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
      });
  }

  filterAll(currentPage) {
    currentPage -= 1;
    axios
      .get(
        `http://localhost:8080/api/products/filter?category=${this.state.filters.category}&country=${this.state.filters.country}&trademark=${this.state.filters.trademark}&page=${currentPage}&size=${this.state.productsPerPage}`,
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
      });
  }

  changePage = (event) => {
    let targetPage = parseInt(event.target.value);
    if (targetPage > this.state.totalPages) targetPage = this.state.totalPages;
    if (this.state.search) {
      this.searchData(targetPage);
    } else if (this.state.filters) {
      this.filterAll(targetPage);
    } else {
      this.findAll(targetPage);
    }

    this.setState({
      [event.target.name]: targetPage,
    });
  };

  firstPage = () => {
    let firstPage = 1;
    if (this.state.currentPage > firstPage) {
      if (this.state.search) {
        this.searchData(firstPage);
      } else if (this.state.filters) {
        this.filterAll(firstPage);
      } else {
        this.findAll(firstPage);
      }
    }
  };

  prevPage = () => {
    let prevPage = 1;
    if (this.state.currentPage > prevPage) {
      if (this.state.search) {
        this.searchData(this.state.currentPage - prevPage);
      } else if (this.state.filters) {
        this.filterAll(this.state.currentPage - prevPage);
      } else {
        this.findAll(this.state.currentPage - prevPage);
      }
    }
  };

  lastPage = () => {
    let condition = Math.ceil(
      this.state.totalElements / this.state.productsPerPage
    );
    if (this.state.currentPage < condition) {
      if (this.state.search) {
        this.searchData(condition);
      } else if (this.state.filters) {
        this.filterAll(condition);
      } else {
        this.findAll(condition);
      }
    }
  };

  nextPage = () => {
    if (
      this.state.currentPage <
      Math.ceil(this.state.totalElements / this.state.productsPerPage)
    ) {
      if (this.state.search) {
        this.searchData(this.state.currentPage + 1);
      } else if (this.state.filters) {
        this.filterAll(this.state.currentPage + 1);
      } else {
        this.findAll(this.state.currentPage + 1);
      }
    }
  };

  addToCart(productId) {
    let val = this.state.cart.find((el) => el.id === productId);
    if (val !== undefined) {
      this.setState({ show: true, message: "?????????????? ?????? ?? ??????????????" });
    } else {
      this.setState({ show: true, message: "?????????????? ???????????????? ?? ??????????????" });
      this.state.cart.push(
        this.state.products.filter((product) => product.id === productId)[0]
      );
    }

    setTimeout(() => this.setState({ show: false }), 3000);
    localStorage.setItem("cart", JSON.stringify(this.state.cart));
    // this.props.updateCart(this.state.cart);
  }

  doFilter(filters) {
    this.setState(
      {
        priceDir: filters.price,
        filters: filters,
        search: "",
      },
      () => {
        this.filterAll(this.state.currentPage);
      }
    );
  }

  searchChange = (event) => {
    this.setState({
      [event.target.name]: event.target.value,
    });
  };

  cancelSearch = () => {
    this.setState({ search: "" });
    this.findAll(this.state.currentPage);
  };

  dropFilters = () => {
    this.setState({ filters: null });
    this.findAll(this.state.currentPage);
  };

  searchData = (currentPage) => {
    this.setState({ filters: null });
    if (this.state.search) {
      currentPage -= 1;
      console.log(
        `http://localhost:8080/api/products/search/${this.state.search}?page=${currentPage}&size=${this.state.productsPerPage}`
      );
      axios
        .get(
          `http://localhost:8080/api/products/search/${this.state.search}?page=${currentPage}&size=${this.state.productsPerPage}`,
          {
            headers: authHeader(),
          }
        )
        .then((response) => response.data)
        .then((data) => {
          console.log(data);
          this.setState({
            products: data.content,
            totalPages: data.totalPages,
            totalElements: data.totalElements,
            currentPage: data.number + 1,
          });
        });
    }
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
        <div>
          <MyToast
            children={{
              show: this.state.show,
              message: this.state.message,
            }}
          />
        </div>
        <Card className={"bg-transparent text-white"}>
          <Card.Header>
            <div style={{ float: "left" }}>
              <Card.Title>????????????????</Card.Title>
            </div>
            {this.state.filters ? (
              <Button
                size="sm"
                variant="info"
                className="mx-2"
                onClick={this.dropFilters}
              >
                ?????????????????? ??????????????
              </Button>
            ) : null}
            <div style={{ float: "right" }}>
              <InputGroup size="sm">
                <FormControl
                  placeholder="??????????"
                  name="search"
                  value={this.state.search}
                  className={"info-border bg-dark text-white"}
                  onChange={this.searchChange}
                />
                <Button
                  size="sm"
                  variant="outline-info"
                  type="button"
                  onClick={this.searchData}
                >
                  <FontAwesomeIcon icon={faSearch} />
                </Button>
                <Button
                  size="sm"
                  variant="outline-danger"
                  type="button"
                  onClick={this.cancelSearch}
                >
                  <FontAwesomeIcon icon={faTimes} />
                </Button>
              </InputGroup>
            </div>
          </Card.Header>
          <Card.Body>
            <Row>
              {products.map((product) => (
                <ProductCard
                  onClick={this.addToCart}
                  key={product.id}
                  title={product.name}
                  price={product.price}
                  src={product.imageRef}
                  productId={product.id}
                  category={product.category.name}
                  country={product.country.name}
                  trademark={product.trademark.name}
                />
              ))}
            </Row>
          </Card.Body>
          {this.state.products.length > 0 ? (
            <Card.Footer>
              <div style={{ float: "left" }}>
                ???? ???????????????? {currentPage} ???? {totalPages}
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
      </div>
    );
  }
}

export default ProductList;
