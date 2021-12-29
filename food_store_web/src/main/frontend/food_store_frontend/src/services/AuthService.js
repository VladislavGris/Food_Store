import axios from "axios";

class AuthSrvice {
  login(email, password) {
    return axios
      .post("http://localhost:8080/login", {
        email: email,
        password: password,
      })
      .then((response) => {
        if (response.data.token) {
          localStorage.setItem("user", JSON.stringify(response.data));
        }
      });
  }
}
