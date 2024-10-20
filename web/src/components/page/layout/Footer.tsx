import {Container, Nav, Navbar} from "react-bootstrap";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {findIconDefinition} from "@fortawesome/fontawesome-svg-core";

const Footer = () => {
    return (
        <footer>
            <Navbar className="bg-body-tertiary">
                <Container>
                    <Navbar.Text>Thesis Defence Management System &copy;</Navbar.Text>
                    <Nav>
                        <Nav.Link href="https://github.com/Velixeor/Thesis-Defense-Management-System">
                            <FontAwesomeIcon icon={findIconDefinition({iconName:'github', prefix:'fab'})} size="xl"/>
                        </Nav.Link>
                    </Nav>
                </Container>
            </Navbar>
        </footer>
    )
}

export default Footer;