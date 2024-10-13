import {useState} from "react";
import {Button, Col, Container, Form, Row} from "react-bootstrap";
import {IRegistration} from "../../../models/user.ts";
import {post} from "../../../utils/request.tsx";

const RegistrationForm = () => {
    const [fullName, setFullName] = useState("");
    const [login, setLogin] = useState("");
    const [password, setPassword] = useState("");
    const [confirmPassword, setConfirmPassword] = useState("");
    const [email, setEmail] = useState("");
    const [phone, setPhone] = useState("");

    const handleSubmit = () => {
        if (password !== confirmPassword) {
            alert("Пароли не совпадают. Пожалуйста, убедитесь, что вы правильно подтвердили пароль.");
            return;
        }

        const userDTO: IRegistration = {
            email: email,
            fullName: fullName,
            password: password,
            phone: phone,
            login: login
        }

        post("/user/registration",userDTO).then()
    };

    return (
        <Container className="mt-3">
            <Row className="justify-content-center">
                <Col md={6}>
                    <h2 className="text-center mb-4">Регистрация</h2>
                    <Form>
                        {/* Поля ввода */}
                        <Form.Group controlId="fullName" className="mb-3">
                            <Form.Label>ФИО</Form.Label>
                            <Form.Control
                                type="text"
                                placeholder="Введите ваше полное имя"
                                value={fullName}
                                onChange={(e) => setFullName(e.target.value)}
                                required
                            />
                        </Form.Group>
                        <Form.Group controlId="login" className="mb-3">
                            <Form.Label>Логин</Form.Label>
                            <Form.Control
                                type="text"
                                placeholder="Введите логин"
                                value={login}
                                onChange={(e) => setLogin(e.target.value)}
                                required
                            />
                        </Form.Group>
                        <Form.Group controlId="password" className="mb-3">
                            <Form.Label>Пароль</Form.Label>
                            <Form.Control
                                type="password"
                                placeholder="Введите пароль"
                                value={password}
                                onChange={(e) => setPassword(e.target.value)}
                                required
                            />
                        </Form.Group>
                        <Form.Group controlId="confirmPassword" className="mb-3">
                            <Form.Label>Подтвердите пароль</Form.Label>
                            <Form.Control
                                type="password"
                                placeholder="Повторите пароль"
                                value={confirmPassword}
                                onChange={(e) => setConfirmPassword(e.target.value)}
                                required
                            />
                        </Form.Group>
                        <Form.Group controlId="mail" className="mb-3">
                            <Form.Label>Email</Form.Label>
                            <Form.Control
                                type="email"
                                placeholder="Введите ваш email"
                                value={email}
                                onChange={(e) => setEmail(e.target.value)}
                                required
                            />
                        </Form.Group>
                        <Form.Group controlId="numberPhone" className="mb-3">
                            <Form.Label>Номер телефона</Form.Label>
                            <Form.Control
                                type="tel"
                                placeholder="Введите ваш номер телефона"
                                value={phone}
                                onChange={(e) => setPhone(e.target.value)}
                                required
                            />
                        </Form.Group>
                        <div className="text-center">
                            <Button onClick={handleSubmit} variant="primary" type="submit">
                                Зарегистрироваться
                            </Button>
                        </div>
                    </Form>
                </Col>
            </Row>
        </Container>
    );
};

export default RegistrationForm;
