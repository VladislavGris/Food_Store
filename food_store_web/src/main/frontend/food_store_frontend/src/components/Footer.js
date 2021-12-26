import React from "react";
import { Col, Container, Navbar } from "react-bootstrap";

class Footer extends React.Component {
  render() {
    return (
      <Navbar fixed="bottom" bg="dark" variant="dark">
        <Container>
          <Col lg={12} className="text-center text-muted">
            <div>By Vladislav Grishkevich</div>
          </Col>
        </Container>
      </Navbar>
    );
  }
}

export default Footer;
