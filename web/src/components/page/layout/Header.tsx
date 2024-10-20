import {Container, Nav, Navbar, NavDropdown} from "react-bootstrap";
import {Component} from "react";
import {RouterLink} from "mobx-state-router";
import {IAuthenticated} from "../../../models/user";
import {makeObservable} from "mobx";
import {RootStoreContext, RootStoreContextType} from "../../../context/RootStoreContext";
import {observer} from "mobx-react";

@observer
class Header extends Component {
    declare context: RootStoreContextType;
    static contextType = RootStoreContext;

    constructor(props: any) {
        super(props);
        makeObservable(this);
    }

    render() {
        const userStore = this.context.userStore;
        const routerStore = this.context.routerStore;
        const user = userStore.user;

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
                                    <NavDropdown.Item onClick={() => {routerStore.goTo('profile')}}>Моя страница</NavDropdown.Item>
                                    <NavDropdown.Divider/>
                                    <NavDropdown.Item>Выйти</NavDropdown.Item>
                                </NavDropdown>
                            </>
                        }

                        {
                            !user.authenticated &&
                            <Nav.Link as={RouterLink} routeName='login'>Войти</Nav.Link>
                        }
                    </Nav>
                </Container>
            </Navbar>
        </header>
    }
}

export default Header;