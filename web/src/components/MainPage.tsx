import {GitHubLogo} from './Svgs.tsx';
import {Container, Nav, Navbar, NavDropdown} from "react-bootstrap";

const Header = () =>
    <header>
        <Navbar className="bg-body-tertiary" fixed="top">
            <Container>
                <Navbar.Brand className="" href="#">TDMS</Navbar.Brand>
                <Nav>
                    <NavDropdown title="Группы">
                        <NavDropdown.Item href="#">Список</NavDropdown.Item>
                        <NavDropdown.Item href="#">Редактировать</NavDropdown.Item>
                    </NavDropdown>
                </Nav>

                <Nav className="ms-auto">
                    <Navbar.Text>Пользователь:</Navbar.Text>
                    <NavDropdown title="Фамилия И. О." id="basic-nav-dropdown">
                        <NavDropdown.Item href="#">Моя страница</NavDropdown.Item>
                        <NavDropdown.Divider/>
                        <NavDropdown.Item href="#">Выйти</NavDropdown.Item>
                    </NavDropdown>
                </Nav>
            </Container>
        </Navbar>
    </header>

const Footer = () => {
    return (
        <footer>
            <Navbar className="bg-body-tertiary">
                <Container>
                    <Navbar.Text>Thesis Defence Management System &copy;</Navbar.Text>
                    <Nav>
                        <Nav.Link href="https://github.com/Velixeor/Thesis-Defense-Management-System">
                            <GitHubLogo width={32} height={32}/>
                        </Nav.Link>
                    </Nav>
                </Container>
            </Navbar>
        </footer>
    )
}

const Content = () =>
    <article>
        <div className="text-center mt-5">There you can see some dashboards in future :)</div>
    </article>

function MainPage() {
    return (
        <>
            <Header/>
            <Content/>
            <Footer/>
        </>
    )
}

export default MainPage;
