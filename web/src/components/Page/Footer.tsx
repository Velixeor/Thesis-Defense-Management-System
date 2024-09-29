import {Container, Nav, Navbar} from "react-bootstrap";
import {GitHubLogo} from "../../utils/svg.tsx";

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

export default Footer;