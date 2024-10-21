import React from 'react';
import { Container, Nav, Navbar, NavDropdown } from "react-bootstrap";
import { GitHubLogo } from "./Svgs.js";
import { DndProvider, useDrag, useDrop } from 'react-dnd';
import { HTML5Backend } from 'react-dnd-html5-backend';

const ItemTypes = {
    BOX: 'box' as const,
};

interface Box {
    id: number;
    text: string;
}

interface DraggableBoxProps {
    id: number;
    text: string;
    moveBox: (fromId: number, toId: number) => void;
}

const DraggableBox: React.FC<DraggableBoxProps> = ({ id, text, moveBox }) => {
    const [, ref] = useDrag({
        type: ItemTypes.BOX,
        item: { id },
    });

    const [, drop] = useDrop({
        accept: ItemTypes.BOX,
        hover: (item: { id: number }) => {
            if (item.id !== id) {
                moveBox(item.id, id);
            }
        },
    });

    return (
        <div ref={(node) => ref(drop(node))} style={styles.box}>
            {text}
        </div>
    );
};

const Content: React.FC = () => {
    const [boxes, setBoxes] = React.useState<Box[]>([
        { id: 1, text: 'Box 1' },
        { id: 2, text: 'Box 2' },
        { id: 3, text: 'Box 3' },
    ]);

    const moveBox = (fromId: number, toId: number) => {
        const fromIndex = boxes.findIndex((box) => box.id === fromId);
        const toIndex = boxes.findIndex((box) => box.id === toId);
        const updatedBoxes = [...boxes];
        const [movedBox] = updatedBoxes.splice(fromIndex, 1);
        updatedBoxes.splice(toIndex, 0, movedBox);
        setBoxes(updatedBoxes);
    };

    return (
        <article>
            <DndProvider backend={HTML5Backend}>
                <Container className="mt-5" style={styles.container}>
                    {boxes.map((box) => (
                        <DraggableBox key={box.id} id={box.id} text={box.text} moveBox={moveBox} />
                    ))}
                </Container>
            </DndProvider>
        </article>
    );
};

const Header = () => (
    <header>
        <Navbar className="bg-body-tertiary" fixed="top">
            <Container>
                <Navbar.Brand href="#">TDMS</Navbar.Brand>
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
                        <NavDropdown.Divider />
                        <NavDropdown.Item href="#">Выйти</NavDropdown.Item>
                    </NavDropdown>
                </Nav>
            </Container>
        </Navbar>
    </header>
);

const Footer = () => (
    <footer>
        <Navbar className="bg-body-tertiary">
            <Container>
                <Navbar.Text>Thesis Defence Management System &copy;</Navbar.Text>
                <Nav>
                    <Nav.Link href="https://github.com/Velixeor/Thesis-Defense-Management-System">
                        <GitHubLogo width={32} height={32} />
                    </Nav.Link>
                </Nav>
            </Container>
        </Navbar>
    </footer>
);

function DragAndDropStudentList() {
    return (
        <>
            <Header />
            <Content />
            <Footer />
        </>
    );
}

const styles = {
    container: {
        width: '200px',
        display: 'flex' as 'flex',
        flexDirection: 'column' as 'column',
    },
    box: {
        padding: '8px',
        margin: '4px',
        backgroundColor: '#e0e0e0',
        textAlign: 'center' as 'center',
        cursor: 'move' as 'move',
    },
};

export default DragAndDropStudentList;
