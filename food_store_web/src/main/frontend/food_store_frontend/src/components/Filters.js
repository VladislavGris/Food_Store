import { React, useState, useEffect } from "react";
import { Form, Button } from "react-bootstrap";
import axios from "axios";
import AuthHeader from "../services/AuthHeader.js";

export default function Filters({ doFilter }) {
  const [priceFilter, setPriceFilter] = useState("asc");
  const [categoryFilter, setCategoryFilter] = useState("");
  const [countryFilter, setCountryFilter] = useState("");
  const [trademarkFilter, setTrademarkFilter] = useState("");
  const [categories, setCategories] = useState([]);
  const [countries, setCountries] = useState([]);
  const [trademarks, setTrademarks] = useState([]);

  useEffect(() => {
    if (trademarks.length === 0) {
      axios
        .get("http://localhost:8080/api/trademarks", { headers: AuthHeader() })
        .then((response) => response.data)
        .then((data) => {
          setTrademarks(data);
        });
    }
    if (countries.length === 0) {
      axios
        .get("http://localhost:8080/api/countries", { headers: AuthHeader() })
        .then((response) => response.data)
        .then((data) => {
          setCountries(data);
        });
    }
    if (categories.length === 0) {
      axios
        .get("http://localhost:8080/api/categories", { headers: AuthHeader() })
        .then((response) => response.data)
        .then((data) => {
          setCategories(data);
        });
    }
  });

  function onPriceFilterChange(event) {
    setPriceFilter(event.target.value);
  }
  function onCategoryFilterChange(event) {
    setCategoryFilter(event.target.value);
  }
  function onCountryFilterChange(event) {
    setCountryFilter(event.target.value);
  }
  function onTrademarkFilterChnage(event) {
    setTrademarkFilter(event.target.value);
  }

  return (
    <div className="text-white text-center card-background mt-3">
      <div>Фильтры</div>
      <hr />
      <Form id="filterForm">
        <Form.Group controlId="priceGroup">
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
        </Form.Group>

        <hr />
        <Form.Group controlId="categoryGroup">
          <Form.Label>Категория</Form.Label>
          <Form.Select
            name="categoryFilter"
            value={categoryFilter}
            onChange={onCategoryFilterChange}
            className={"bg-dark text-white"}
          >
            <option value="">Любая</option>
            {categories.length !== 0
              ? categories.map((category) => (
                  <option key={category.id} value={category.name}>
                    {category.name}
                  </option>
                ))
              : null}
          </Form.Select>
        </Form.Group>
        <hr />
        <Form.Group controlId="countryGroup">
          <Form.Label>Страна производитель</Form.Label>
          <Form.Select
            name="countryFilter"
            value={countryFilter}
            onChange={onCountryFilterChange}
            className={"bg-dark text-white"}
          >
            <option value="">Любая</option>
            {countries.length !== 0
              ? countries.map((country) => (
                  <option key={country.id} value={country.name}>
                    {country.name}
                  </option>
                ))
              : null}
          </Form.Select>
        </Form.Group>
        <hr />
        <Form.Group controlId="trademarkGroup">
          <Form.Label>Торговая марка</Form.Label>
          <Form.Select
            name="trademarkFilter"
            value={trademarkFilter}
            onChange={onTrademarkFilterChnage}
            className={"bg-dark text-white"}
          >
            <option value="">Любая</option>
            {trademarks.length !== 0
              ? trademarks.map((trademark) => (
                  <option key={trademark.id} value={trademark.name}>
                    {trademark.name}
                  </option>
                ))
              : null}
          </Form.Select>
        </Form.Group>
        <hr />
        <Button
          size="sm"
          variant="success"
          className="m-3"
          onClick={() => {
            doFilter({
              price: priceFilter,
              category: categoryFilter,
              country: countryFilter,
              trademark: trademarkFilter,
            });
          }}
        >
          Применить фильтры
        </Button>
      </Form>
    </div>
  );
}
