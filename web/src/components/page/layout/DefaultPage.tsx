import {Component, ReactNode} from "react";
import {Container} from "react-bootstrap";
import Footer from "./Footer";
import Header from "./Header";
import {RootStoreContext, RootStoreContextType} from "../../../context/RootStoreContext";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {observer} from "mobx-react";

@observer
class DefaultPage extends Component<any> {
    get page(): ReactNode {
        throw new Error('This is not abstract method, ' +
            'because mobx cant handle abstract methods. ' +
            'Please override this method in child class. ' +
            'Do not call it directly.');
    }
    declare context: RootStoreContextType;
    static contextType = RootStoreContext;

    render() {
        let isLoading = this.context.userStore.isLoading;

        return <>
            <Header/>
            <Container className={"mt-5 mb-5"}>
                {
                    isLoading &&
                    <div id='fullscreen-loader'>
                        <FontAwesomeIcon icon='gear' size="4x" spin/>
                    </div>
                }
                {
                    !isLoading &&
                    this.page
                }
            </Container>
            <Footer/>
        </>
    }
}

export {DefaultPage};