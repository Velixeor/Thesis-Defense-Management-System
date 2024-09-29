import {Component, ReactNode} from "react";
import Header from "./Header.tsx";
import {Container} from "react-bootstrap";
import Footer from "./Footer.tsx";

export abstract class DefaultPage extends Component {
    abstract get page(): ReactNode;
    // declare context: ContextType<typeof RootStoreContext>

    render() {
        return <>
            <Header/>
            <Container className={"mt-5 mb-5"}>
                {this.page}
            </Container>
            <Footer/>
        </>
    }
}
