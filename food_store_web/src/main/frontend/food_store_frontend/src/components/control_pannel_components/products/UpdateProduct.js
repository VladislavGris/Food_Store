import React, { useState, useEffect } from "react";
import { Form, Button } from "react-bootstrap";
import axios from "axios";
import { Link, useParams, useNavigate } from "react-router-dom";
import AuthHeader from "../../../services/AuthHeader.js";

export default function UpdateProduct() {
  const { id } = useParams();
  const [name, setName] = useState("");
  const [price, setPrice] = useState("");
  const [country, setCountry] = useState("");
  const [category, setCategory] = useState("");
  const [trademark, setTrademark] = useState("");
  const [imageRef, setImage] = useState("");
  const [trademarks, setTrademarks] = useState("");
  const [countries, setCountries] = useState("");
  const [categories, setCategories] = useState("");
  const [wasLoaded, setWasLoaded] = useState(false);
  const navigate = useNavigate();

  useEffect(() => {
    if (trademarks === "") {
      axios
        .get("http://localhost:8080/api/trademarks", { headers: AuthHeader() })
        .then((response) => response.data)
        .then((data) => {
          setTrademarks(data);
          setTrademark(data[0].name);
        });
    }
    if (countries === "") {
      axios
        .get("http://localhost:8080/api/countries", { headers: AuthHeader() })
        .then((response) => response.data)
        .then((data) => {
          setCountries(data);
          setCountry(data[0].name);
        });
    }
    if (categories === "") {
      axios
        .get("http://localhost:8080/api/categories", { headers: AuthHeader() })
        .then((response) => response.data)
        .then((data) => {
          setCategories(data);
          setCategory(data[0].name);
        });
    }
    if (
      !wasLoaded &&
      (id !== null || id !== undefined) &&
      trademarks !== "" &&
      categories !== "" &&
      countries !== ""
    ) {
      axios
        .get("http://localhost:8080/api/products/" + id, {
          headers: AuthHeader(),
        })
        .then((response) => response.data)
        .then((data) => {
          setName(data.name);
          setPrice(data.price);
          setImage(data.imageRef);
          setTrademark(data.trademark.name);
          setCountry(data.country.name);
          setCategory(data.category.name);
        });
      setWasLoaded(true);
    }
  });

  function submitForm(event) {
    event.preventDefault();
    console.log("Submit");
    const product = {
      name: name,
      price: price,
      country: country,
      category: category,
      trademark: trademark,
      imageRef: imageRef,
    };
    axios
      .put("http://localhost:8080/api/products/" + id, product, {
        headers: AuthHeader(),
      })
      .then((response) => response.data)
      .then((data) => {
        console.log(data);
        navigate("/control/products");
      });
  }

  function onNameChange(event) {
    setName(event.target.value);
  }

  function onPriceChange(event) {
    setPrice(event.target.value);
  }

  function onTrademarkChange(event) {
    setTrademark(event.target.value);
  }

  function onCountryChange(event) {
    setCountry(event.target.value);
  }

  function onCategoryChange(event) {
    setCategory(event.target.value);
  }

  function onImageChange(event) {
    setImage(event.target.value);
  }

  return (
    <div>
      <h2 className="text-white">Обновить продукт </h2>
      <Link to={"/control/products"}>Назад к списку продуктов</Link>
      <Form id="addProductForm" onSubmit={submitForm} className="text-white">
        <Form.Group className="mb-1" controlId="name">
          <Form.Label>Именование</Form.Label>
          <Form.Control
            autoComplete="off"
            required
            type="text"
            name="name"
            value={name}
            onChange={onNameChange}
            placeholder="Введите именование продукта"
            className={"bg-dark text-white"}
          />
        </Form.Group>

        <Form.Group className="mb-1" controlId="pirice">
          <Form.Label>Цена</Form.Label>
          <Form.Control
            autoComplete="off"
            required
            type="number"
            name="price"
            onChange={onPriceChange}
            value={price}
            min={0.1}
            step={0.01}
            placeholder="Укажите цену продукта"
            className={"bg-dark text-white"}
          />
        </Form.Group>

        <Form.Group className="mb-1" controlId="trademark">
          <Form.Label>Торговая марка</Form.Label>
          <Form.Select
            required
            name="trademark"
            value={trademark}
            onChange={onTrademarkChange}
            className={"bg-dark text-white"}
          >
            {trademarks !== ""
              ? trademarks.map((trademark) => (
                  <option key={trademark.id} value={trademark.Id}>
                    {trademark.name}
                  </option>
                ))
              : null}
          </Form.Select>
        </Form.Group>

        <Form.Group className="mb-1" controlId="trademark">
          <Form.Label>Страна производитель</Form.Label>
          <Form.Select
            required
            name="country"
            value={country}
            onChange={onCountryChange}
            className={"bg-dark text-white"}
          >
            {countries !== ""
              ? countries.map((country) => (
                  <option key={country.id} value={country.Id}>
                    {country.name}
                  </option>
                ))
              : null}
          </Form.Select>
        </Form.Group>

        <Form.Group className="mb-1" controlId="trademark">
          <Form.Label>Категория</Form.Label>
          <Form.Select
            required
            name="category"
            value={category}
            onChange={onCategoryChange}
            className={"bg-dark text-white"}
          >
            {categories !== ""
              ? categories.map((category) => (
                  <option key={category.id} value={category.Id}>
                    {category.name}
                  </option>
                ))
              : null}
          </Form.Select>
        </Form.Group>

        <Form.Group className="mb-1" controlId="imgRef">
          <Form.Label>Ссылка на изображение</Form.Label>
          <Form.Control
            autoComplete="off"
            type="text"
            name="imageRef"
            onChange={onImageChange}
            value={imageRef}
            placeholder="Добавьте ссылку на изображение"
            className={"bg-dark text-white"}
          />
        </Form.Group>
        <Button size="sm" variant="success" type="submit">
          Обновить
        </Button>
      </Form>
    </div>
  );
}
