import { React, useState } from "react";
import { Form, Button } from "react-bootstrap";

export default function Filters({ doFilter }) {
  const [priceFilter, setPriceFilter] = useState("asc");

  function onPriceFilterChange(event) {
    setPriceFilter(event.target.value);
  }

  return (
    <div className="text-white text-center card-background mt-3">
      <div>Фильтры</div>
      <hr />
      <Form id="filterForm">
        <Form.Group controlId="priceGroup"></Form.Group>
        <Form.Label>Цена</Form.Label>
        <Form.Select
          name="priceFilter"
          value={priceFilter}
          onChange={onPriceFilterChange}
          className={"bg-dark text-white"}
        >
          <option value="asc">По возростанию цены</option>
          <option value="desc">По убыванию цены</option>
        </Form.Select>
        <hr />
        <Button
          size="sm"
          variant="success"
          className="m-3"
          onClick={() => {
            doFilter(priceFilter);
          }}
        >
          Применить фильтры
        </Button>
      </Form>
    </div>
  );
}
