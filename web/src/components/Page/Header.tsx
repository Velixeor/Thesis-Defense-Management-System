import {Container, Nav, Navbar, NavDropdown} from "react-bootstrap";
import {FC} from "react";
import {RouterLink} from "mobx-state-router";
import {useRootStore} from "../../store/RootStore.ts";
import {IAuthenticated} from "../../models/user.ts";
import {observer} from "mobx-react";

export const Header: FC = observer(() => {
    const store = useRootStore();
    const user = store.userStore.user;

    return <header>
        <Navbar className="bg-body-tertiary" fixed="top">
            <Container>
                <Navbar.Brand>
                    <Nav.Link as={RouterLink} routeName='root'>TDMS</Nav.Link>
                </Navbar.Brand>
                <Nav>
                    <NavDropdown title="Группы">
                        <NavDropdown.Item>Список</NavDropdown.Item>
                        <NavDropdown.Item>Редактировать</NavDropdown.Item>
                    </NavDropdown>
                </Nav>

                <Nav className="ms-auto">
                    {
                        user.authenticated &&
                        <>
                            <Navbar.Text>Пользователь:</Navbar.Text>
                            <NavDropdown
                                title={(user as IAuthenticated).fullName}>
                                <NavDropdown.Item>Моя страница</NavDropdown.Item>
                                <NavDropdown.Divider/>
                                <NavDropdown.Item onClick={() => store.userStore.logout()}>Выйти</NavDropdown.Item>
                            </NavDropdown>
                        </>
                    }

                    {
                        !user.authenticated &&
                        <>
                            <Nav.Link as={RouterLink} routeName='login'>Войти</Nav.Link>
                            <Nav.Link as={RouterLink} routeName='register'>Зарегистрироваться</Nav.Link>
                        </>
                    }
                </Nav>
            </Container>
        </Navbar>
    </header>
});

export default Header;
