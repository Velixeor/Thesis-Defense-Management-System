import React from "react";
import { Button, Col, Form, Row } from "react-bootstrap";
import { IRegistration } from "../../../models/user.ts";
import { post } from "../../../utils/request.tsx";
import { DefaultPage } from "../../Page/DefaultPage.tsx";

class RegistrationForm extends DefaultPage {
    state = {
        fullName: "",
        login: "",
        password: "",
        confirmPassword: "",
        email: "",
        phoneNumber: "",
        phoneError: "",
    };

    handleSubmit = (event: React.FormEvent) => {
        event.preventDefault();

        const { password, confirmPassword, email, fullName, phoneNumber, login, phoneError } = this.state;

        if (password !== confirmPassword) {
            alert("Пароли не совпадают. Пожалуйста, убедитесь, что вы правильно подтвердили пароль.");
            return;
        }

        if (phoneError) {
            alert("Пожалуйста, исправьте ошибки в форме.");
            return;
        }

        const userDTO: IRegistration = {
            email: email,
            fullName: fullName,
            password: password,
            phoneNumber: phoneNumber,
            login: login,
        };

        post("/user/registration", userDTO).then();
    };

    handleInputChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        const { name, value } = event.target;
        const phoneRegex = /^\+7\d{10}$/;
        if (name === "phoneNumber") {
            this.setState({
                phoneNumber: value,
                phoneError: phoneRegex.test(value) ? "" : "Некорректный номер телефона",
            });
        } else {
            this.setState({ [name]: value } as Pick<RegistrationForm["state"], keyof RegistrationForm["state"]>);
        }
    };

    get page() {
        return (
            <Row className="justify-content-center">
                <Col md={6}>
                    <h2 className="text-center mb-4">Регистрация</h2>
                    <Form onSubmit={this.handleSubmit}>
                        {/* Поля ввода */}
                        <Form.Group controlId="fullName" className="mb-3">
                            <Form.Label>ФИО</Form.Label>
                            <Form.Control
                                type="text"
                                name="fullName"
                                placeholder="Введите ваше полное имя"
                                value={this.state.fullName}
                                onChange={this.handleInputChange}
                                required
                            />
                        </Form.Group>
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
                        <Form.Group controlId="confirmPassword" className="mb-3">
                            <Form.Label>Подтвердите пароль</Form.Label>
                            <Form.Control
                                type="password"
                                name="confirmPassword"
                                placeholder="Повторите пароль"
                                value={this.state.confirmPassword}
                                onChange={this.handleInputChange}
                                required
                            />
                        </Form.Group>
                        <Form.Group controlId="mail" className="mb-3">
                            <Form.Label>Email</Form.Label>
                            <Form.Control
                                type="email"
                                name="email"
                                placeholder="Введите ваш email"
                                value={this.state.email}
                                onChange={this.handleInputChange}
                                required
                            />
                        </Form.Group>
                        <Form.Group controlId="phoneNumber" className="mb-3">
                            <Form.Label>Номер телефона</Form.Label>
                            <Form.Control
                                type="text"
                                name="phoneNumber"
                                placeholder="Введите ваш номер телефона"
                                value={this.state.phoneNumber}
                                onChange={this.handleInputChange}
                                required
                            />
                            {this.state.phoneError && (
                                <Form.Text className="text-danger">{this.state.phoneError}</Form.Text>
                            )}
                        </Form.Group>
                        <div className="text-center">
                            <Button variant="primary" type="submit">
                                Зарегистрироваться
                            </Button>
                        </div>
                    </Form>
                </Col>
            </Row>
        );
    }
}

export default RegistrationForm;
