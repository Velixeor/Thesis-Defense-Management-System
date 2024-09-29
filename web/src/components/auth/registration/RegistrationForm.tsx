import { useState } from "react";
import { Form, Button, Container, Row, Col } from "react-bootstrap";

const RegistrationForm = () => {
    const [fullName, setFullName] = useState("");
    const [login, setLogin] = useState("");
    const [password, setPassword] = useState("");
    const [confirmPassword, setConfirmPassword] = useState("");
    const [email, setEmail] = useState("");
    const [phone, setPhone] = useState("");

    // Состояния для ролей
    const [isManager, setIsManager] = useState(false);
    const [isCurator, setIsCurator] = useState(false);
    const [isStudent, setIsStudent] = useState(false);

    const id = null;
    const createAt = null;
    const updateAt = null;
    const roleId: number[] = []; // Список ролей

    const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();

        // Проверка на совпадение паролей
        if (password !== confirmPassword) {
            alert("Пароли не совпадают. Пожалуйста, убедитесь, что вы правильно подтвердили пароль.");
            return;
        }

        // Логика для добавления ролей
        if (isManager) roleId.push(1); // Руководитель
        if (isCurator) roleId.push(2); // Куратор
        if (isStudent) roleId.push(3); // Студент

        const userDTO = {
            id,
            fullName,
            login,
            password,
            mail: email,
            numberPhone: phone,
            createAt,
            updateAt,
            roleId,
        };

        try {
            const response = await fetch("/api/v1/user/create", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(userDTO),
            });

            if (!response.ok) {
                const errorData = await response.json();
                console.error("Ошибка при регистрации:", errorData.message || "Неизвестная ошибка");
                alert(`Ошибка при регистрации: ${errorData.message || "Произошла ошибка"}`);
            } else {
                await response.json();
                alert("Пользователь успешно создан!");
            }
        } catch (error) {
            console.error("Ошибка при создании пользователя:", error);
            alert("Произошла ошибка при регистрации, попробуйте еще раз.");
        }
    };

    // Логика для управления выбором ролей
    const handleStudentChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setIsStudent(e.target.checked);
        if (e.target.checked) {
            setIsManager(false);
            setIsCurator(false);
        }
    };

    const handleManagerChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setIsManager(e.target.checked);
        if (e.target.checked) {
            setIsStudent(false);
        }
    };

    const handleCuratorChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setIsCurator(e.target.checked);
        if (e.target.checked) {
            setIsStudent(false);
        }
    };

    return (
        <Container className="mt-3">
            <Row className="justify-content-center">
                <Col md={6}>
                    <h2 className="text-center mb-4">Регистрация</h2>
                    <Form onSubmit={handleSubmit}>
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

                        {/* Галочки для выбора ролей */}
                        <Form.Group controlId="roles" className="mb-3">
                            <Form.Label>Выберите тип вашего аккаунта:</Form.Label>
                            <Form.Check
                                type="checkbox"
                                label="Руководитель"
                                checked={isManager}
                                onChange={handleManagerChange}
                                disabled={isStudent} // Заблокировано, если выбран студент
                            />
                            <Form.Check
                                type="checkbox"
                                label="Куратор"
                                checked={isCurator}
                                onChange={handleCuratorChange}
                                disabled={isStudent} // Заблокировано, если выбран студент
                            />
                            <Form.Check
                                type="checkbox"
                                label="Студент"
                                checked={isStudent}
                                onChange={handleStudentChange}
                            />
                        </Form.Group>

                        <div className="text-center">
                            <Button variant="primary" type="submit">
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
