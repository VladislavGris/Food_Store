import React, { useState } from "react";
import { Row, Col } from "react-bootstrap";
import Filters from "./Filters";
import NavigationBar from "./NavigationBar";
import ProductList from "./ProductList";

export default function Market() {
  const [productList, setProductList] = useState();

  function onFilter(filters) {
    productList.doFilter(filters);
  }

  return (
    <div>
      <NavigationBar />
      <Row>
        <Col lg={3}>
          <Filters doFilter={onFilter} />
        </Col>
        <Col lg={9}>
          <ProductList
            ref={(list) => {
              setProductList(list);
            }}
          />
        </Col>
      </Row>
    </div>
  );
}
