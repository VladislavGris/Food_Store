import React from "react";
import { Row, Col } from "react-bootstrap";
import Filters from "./Filters";
import NavigationBar from "./NavigationBar";
import ProductList from "./ProductList";
class Market extends React.Component {
  render() {
    return (
      <div>
        <NavigationBar />
        <Row>
          {/* <Col lg={0}>
            <Filters />
          </Col> */}
          <Col lg={12}>
            <ProductList />
          </Col>
        </Row>
      </div>
    );
  }
}

export default Market;
