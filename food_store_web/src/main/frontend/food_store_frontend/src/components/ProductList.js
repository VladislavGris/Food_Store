import React from "react";
import { Card, Col, Row, Button } from "react-bootstrap";
import "../App.css";
import ProductCard from "./ProductCard";
class ProductList extends React.Component {
  render() {
    return (
      <Card className={"bg-transparent text-white"}>
        <Card.Body>
          <Card.Title>Продукты</Card.Title>
          <Row>
            <ProductCard
              title="Картофель"
              price="1.5"
              src="https://db3pap001files.storage.live.com/y4mPdcE8hSX_Tyusbk7Jx5DhIiA-iC-dab1E7rrmtqWYFLIYIZ_V87RtDMYWzLObwuO7Qmhyol9D2_9r1_E3a_2F_C3QfiPRGtn_8mpI3tdTzrD1EXQTPrJa86-pofdPNjsEoHQM2QhGGWiP8dhibd7LglK8-TdjcYawvDP23ODhPSTWer-yBhEBkBVYeYuCCym?width=200&height=200&cropmode=none"
            />
            <ProductCard />
            <ProductCard />
            <ProductCard />
            <ProductCard />
            <ProductCard />
            <ProductCard />
            <ProductCard />
            <ProductCard />
            <ProductCard />
            <ProductCard />
            <ProductCard />
            <ProductCard />
            <ProductCard />
            <ProductCard />
          </Row>
        </Card.Body>
      </Card>
    );
  }
}

export default ProductList;
