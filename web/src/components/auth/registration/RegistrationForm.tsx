import React from "react";
import {Button, Col, Form, Row} from "react-bootstrap";
import {IRegistration} from "../../../models/user.ts";
import {post} from "../../../utils/request.tsx";
import {DefaultPage} from "../../Page/DefaultPage.tsx";

class RegistrationForm extends DefaultPage {
    state = {
        fullName: "",
        firstName: "",
        lastName: "",
        middleName: "",
        login: "",
        password: "",
        confirmPassword: "",
        email: "",
        phone: "",
        phoneError: "",
        emailError: "",
        nameError: "",
    };

    handleSubmit = (event: React.FormEvent) => {
        event.preventDefault();
        const {
            password,
            confirmPassword,
            email,
            firstName,
            lastName,
            middleName,
            phone,
            login,
            phoneError,
            emailError,
            nameError,
        } = this.state;
        if (password !== confirmPassword) {
            alert("Пароли не совпадают. Пожалуйста, убедитесь, что вы правильно подтвердили пароль.");
            return;
        }
        if (phoneError || emailError || nameError) {
            alert("Пожалуйста, исправьте ошибки в форме.");
            return;
        }
        const fullName = `${lastName} ${firstName} ${middleName}`.trim();
        const userDTO: IRegistration = {
            email: email,
            fullName: fullName,
            password: password,
            phone: phone,
            login: login,
        };

        post("/user/registration", userDTO).then();
    };

    handleInputChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        const {name, value} = event.target;
        const phoneRegex = /^\+7\d{10}$/;
        const emailRegex = /^(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|"(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21\x23-\x5b\x5d-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])*")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21-\x5a\x53-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])+)\])/;
        const nameRegex = /^[A-Za-zА-Яа-яЁё]+$/;

        if (name === "phone") {
            this.setState({
                phone: value,
                phoneError: phoneRegex.test(value) ? "" : "Некорректный номер телефона",
            });
        } else if (name === "email") {
            this.setState({
                email: value,
                emailError: emailRegex.test(value) ? "" : "Некорректный email",
            });
        } else if (["firstName", "lastName", "middleName"].includes(name)) {
            if (!nameRegex.test(value)) {
                this.setState({nameError: "Имя может содержать только буквы", [name]: value});
            } else {
                this.setState({nameError: "", [name]: value});
            }
        } else {
            this.setState({[name]: value} as Pick<RegistrationForm["state"], keyof RegistrationForm["state"]>);
        }
    };

    get page() {
        return (
            <Row className="justify-content-center">
                <Col md={6}>
                    <h2 className="text-center mb-4">Регистрация</h2>
                    <Form onSubmit={this.handleSubmit}>
                        {/* Поля ввода для фамилии, имени и необязательного отчества */}
                        <Form.Group controlId="lastName" className="mb-3">
                            <Form.Label>Фамилия</Form.Label>
                            <Form.Control
                                type="text"
                                name="lastName"
                                placeholder="Введите вашу фамилию"
                                value={this.state.lastName}
                                onChange={this.handleInputChange}
                                required
                            />
                        </Form.Group>
                        <Form.Group controlId="firstName" className="mb-3">
                            <Form.Label>Имя</Form.Label>
                            <Form.Control
                                type="text"
                                name="firstName"
                                placeholder="Введите ваше имя"
                                value={this.state.firstName}
                                onChange={this.handleInputChange}
                                required
                            />
                        </Form.Group>
                        <Form.Group controlId="middleName" className="mb-3">
                            <Form.Label>Отчество (необязательно)</Form.Label>
                            <Form.Control
                                type="text"
                                name="middleName"
                                placeholder="Введите ваше отчество"
                                value={this.state.middleName}
                                onChange={this.handleInputChange}
                            />
                        </Form.Group>
                        {this.state.nameError && (
                            <Form.Text className="text-danger">{this.state.nameError}</Form.Text>
                        )}
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
                            {this.state.emailError && (
                                <Form.Text className="text-danger">{this.state.emailError}</Form.Text>
                            )}
                        </Form.Group>
                        <Form.Group controlId="phone" className="mb-3">
                            <Form.Label>Номер телефона</Form.Label>
                            <Form.Control
                                type="text"
                                name="phone"
                                placeholder="Введите ваш номер телефона"
                                value={this.state.phone}
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
