import React from "react";
import {Button, Col, Form, Row} from "react-bootstrap";
import {post} from "../../../utils/request.tsx";
import {DefaultPage} from "../../Page/DefaultPage.tsx";

class LoginForm extends DefaultPage {
    state = {
        login: "",
        password: "",
    };

    handleSubmit = async (event: React.FormEvent) => {
        event.preventDefault();
        const {login, password} = this.state;
        try {
            post("/user/login",  new URLSearchParams({login: login, password: password}))
        } catch (error) {
            console.error("Ошибка при попытке входа:", error);
            alert("Произошла ошибка. Пожалуйста, попробуйте снова.");
        }
    };

    handleInputChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        const {name, value} = event.target;
        this.setState({[name]: value} as Pick<LoginForm["state"], keyof LoginForm["state"]>);
    };

    get page() {
        return (
            <Row className="justify-content-center">
                <Col md={6}>
                    <h2 className="text-center mb-4">Вход</h2>
                    <Form onSubmit={this.handleSubmit}>
                        {/* Поля ввода */}
                        <Form.Group controlId="login" className="mb-3">
                            <Form.Label>Логин</Form.Label>
                            <Form.Control
                                type="text"
                                name="login"
                                placeholder="Введите логин"
                                value={this.state.login}
                                onChange={this.handleInputChange}
                                required
                            />
                        </Form.Group>
                        <Form.Group controlId="password" className="mb-3">
                            <Form.Label>Пароль</Form.Label>
                            <Form.Control
                                type="password"
                                name="password"
                                placeholder="Введите пароль"
                                value={this.state.password}
                                onChange={this.handleInputChange}
                                required
                            />
                        </Form.Group>
                        <div className="text-center">
                            <Button variant="primary" type="submit">
                                Войти
                            </Button>
                        </div>
                    </Form>
                </Col>
            </Row>
        );
    }
}

export default LoginForm;
