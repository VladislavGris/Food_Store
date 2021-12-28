import React from "react";
import "./App.css";
import { Container, Row, Col } from "react-bootstrap";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Welcome from "./components/Welcome";
import Footer from "./components/Footer";
import Login from "./components/Login";
import Register from "./components/Register";
import Market from "./components/Market";
import ControlPannel from "./components/ControlPannel";
import CategoriesList from "./components/control_pannel_components/categories/CategoriesList";
import CountriesList from "./components/control_pannel_components/countries/CountriesList";
import TrademarksList from "./components/control_pannel_components/trademarks/TrademarksList";
import UsersList from "./components/control_pannel_components/users/UsersList";
import ProductsList from "./components/control_pannel_components/products/ProductsList";
import OrdersList from "./components/control_pannel_components/orders/OrdersList";
import AddProduct from "./components/control_pannel_components/products/AddProduct";
function App() {
  const marginTop = {
    marginTop: "40px",
  };

  return (
    <Router>
      <Container>
        <Row>
          <Col lg={12} style={marginTop}>
            <Routes>
              <Route exact path="/" element={<Welcome />} />
              <Route exact path="/login" element={<Login />} />
              <Route exact path="/register" element={<Register />} />
              <Route exact path="/market" element={<Market />} />
              <Route exact path="/control" element={<ControlPannel />} />
              <Route
                exact
                path="/control/categories"
                element={<CategoriesList />}
              />
              <Route
                exact
                path="/control/countries"
                element={<CountriesList />}
              />
              <Route
                exact
                path="/control/trademarks"
                element={<TrademarksList />}
              />
              <Route exact path="/control/users" element={<UsersList />} />
              <Route
                exact
                path="/control/products"
                element={<ProductsList />}
              />
              <Route exact path="/control/orders" element={<OrdersList />} />
              <Route
                exact
                path="/control/products/add_product"
                element={<AddProduct />}
              />
            </Routes>
          </Col>
        </Row>
      </Container>
      <Footer />
    </Router>
  );
}

export default App;
