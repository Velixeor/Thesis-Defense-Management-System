import {DefaultPage} from "./layout/DefaultPage";
import {Col, Form, Row} from "react-bootstrap";
import {observer} from "mobx-react";
import {RootStoreContext, type RootStoreContextType} from "../../context/RootStoreContext";
import {IAuthenticated} from "../../models/user";
import {Component} from "react";
import {dateConverter} from "../../utils/converters";
import {IStudent} from "../../models/student";
import {makeObservable, observable} from "mobx";

@observer
class UserInfo extends Component<{user: IAuthenticated}> {
    @observable
    user = this.props.user;

    constructor(props: any) {
        super(props);
        makeObservable(this);
    }

    render() {
        return (
            <Row>
                <Col sm={6}>
                    <Form.Group className={"mt-2"}>
                        <Form.Label column={"sm"}>ФИО</Form.Label>
                        <Form.Control type="text" value={this.user.fullName} disabled={true}/>
                    </Form.Group>
                    <Form.Group className={"mt-2"}>
                        <Form.Label column={"sm"}>Имя пользователя</Form.Label>
                        <Form.Control type="text" value={this.user.login} disabled={true}/>
                    </Form.Group>
                    <Form.Group className={"mt-2"}>
                        <Form.Label column={"sm"}>Электронная почта</Form.Label>
                        <Form.Control type="email" value={this.user.email} disabled={true}/>
                    </Form.Group>
                    <Form.Group className={"mt-2"}>
                        <Form.Label column={"sm"}>Телефон</Form.Label>
                        {/* todo: format phone */}
                        <Form.Control type="text" value={this.user.phone} disabled={true}/>
                    </Form.Group>
                </Col>
                <Col sm={6}>
                    <Form.Group className={"mt-2"}>
                        <Form.Label column={"sm"}>Роли</Form.Label>
                        <Form.Control
                            type="text"
                            value={this.user.authorities?.map(a => a.name).join(', ')}
                            disabled={true}/>
                    </Form.Group>
                    <Form.Group className={"mt-2"}>
                        <Form.Label column={"sm"}>Дата создания</Form.Label>
                        <Form.Control type="text" value={dateConverter(this.user.createdAt)} disabled={true}/>
                    </Form.Group>
                    <Form.Group className={"mt-2"}>
                        <Form.Label column={"sm"}>Дата последней модификации</Form.Label>
                        <Form.Control type="text" value={dateConverter(this.user.updatedAt)} disabled={true}/>
                    </Form.Group>
                </Col>
            </Row>
        )
    }
}

@observer
class StudentInfo extends Component<{student: IStudent}> {
    @observable
    student = this.props.student;

    constructor(props: any) {
        super(props);
        makeObservable(this);
    }

    render() {
        let student = this.student;

        return (
            <Row className={"mt-4"}>
                <Col sm={6}>
                    <Form.Group className={"mt-2"}>
                        <Form.Label column={"sm"}>Тема дипломной работы</Form.Label>
                        <Form.Control type="text" value={student.diplomaTopic} disabled={true}/>
                    </Form.Group>
                    <Form.Group className={"mt-2"}>
                        <Form.Label column={"sm"}>Очередь защиты</Form.Label>
                        <Form.Control type="text" value={student.protectionOrder.toString()} disabled={true}/>
                    </Form.Group>
                    <Form.Group className={"mt-2"}>
                        <Form.Label column={"sm"}>Презентация в электронном формате</Form.Label>
                        <Form.Control type="text" value={student.digitalFormatPresent ? "Да" : "Нет"} disabled={true}/>
                    </Form.Group>
                    <Form.Group className={"mt-2"}>
                        <Form.Label column={"sm"}>Оценка за комментарий</Form.Label> {/* todo: обсудить с аналитиком */}
                        <Form.Control type="text" value={student.markComment.toString()} disabled={true}/>
                    </Form.Group>
                    <Form.Group className={"mt-2"}>
                        <Form.Label column={"sm"}>Оценка за практику</Form.Label> {/* todo: обсудить с аналитиком */}
                        <Form.Control type="text" value={student.markPractice.toString()} disabled={true}/>
                    </Form.Group>
                    <Form.Group className={"mt-2"}>
                        <Form.Label column={"sm"}>Комментарий к предзащите</Form.Label>
                        <Form.Control type="text" value={student.predefenceComment} disabled={true}/>
                    </Form.Group>
                    <Form.Group className={"mt-2"}>
                        <Form.Label column={"sm"}>Форма контроля</Form.Label> {/* todo: обсудить с аналитиком */}
                        <Form.Control type="text" value={student.normalControl} disabled={true}/>
                    </Form.Group>
                    <Form.Group className={"mt-2"}>
                        <Form.Label column={"sm"}>Антиплагиат (процент
                            уникальности)</Form.Label> {/* todo: обсудить с аналитиком */}
                        <Form.Control type="text" value={student.antiPlagiarism.toString()} disabled={true}/>
                    </Form.Group>
                    <Form.Group className={"mt-2"}>
                        <Form.Label column={"sm"}>Примечание</Form.Label>
                        <Form.Control type="text" value={student.note} disabled={true}/>
                    </Form.Group>
                </Col>
                <Col sm={6}>
                    <Form.Group className={"mt-2"}>
                        <Form.Label column={"sm"}>Группа</Form.Label>
                        <Form.Control type="text" value={student.group.name} disabled={true}/>
                    </Form.Group>
                    <Form.Group className={"mt-2"}>
                        <Form.Label column={"sm"}>Куратор</Form.Label>
                        <Form.Control type="text" value={student.group.principalUser.fullName} disabled={true}/>
                    </Form.Group>
                    <Form.Group className={"mt-2"}>
                        <Form.Label column={"sm"}>Форма обучения</Form.Label> {/* todo: обсудить с аналитиком */}
                        <Form.Control type="text" value={student.form ? "Очная" : "Заочная"} disabled={true}/>
                    </Form.Group>
                    <Form.Group className={"mt-2"}>
                        <Form.Label column={"sm"}>Научный руководитель</Form.Label>
                        <Form.Control type="text" value={student.mentorUser.fullName} disabled={true}/>
                    </Form.Group>
                    <Form.Group className={"mt-2"}>
                        <Form.Label column={"sm"}>Зачетная книжка сдана</Form.Label>
                        <Form.Control type="text" value={student.recordBookReturned ? "Да" : "Нет"} disabled={true}/>
                    </Form.Group>
                    <Form.Group className={"mt-2"}>
                        <Form.Label column={"sm"}>Работа</Form.Label> {/* todo: обсудить с аналитиком */}
                        <Form.Control type="text" value={student.work} disabled={true}/>
                    </Form.Group>
                    <Form.Group className={"mt-2"}>
                        <Form.Label column={"sm"}>Магистратура</Form.Label> {/* todo: обсудить с аналитиком */}
                        <Form.Control type="text" value={student.magistracy} disabled={true}/>
                    </Form.Group>
                </Col>
            </Row>
        );
    }
}

export default class UserProfile extends DefaultPage {
    declare context: RootStoreContextType;
    static contextType = RootStoreContext;

    get page() {
        let user = this.context.userStore.user;
        if (!user.authenticated) {
            // todo: implement login page with redirects
            this.context.routerStore.goTo('login', {redirect: 'profile'});
        }
        let student = this.context.userStore.student;

        return <Form>
            {
                user.authenticated &&
                    <UserInfo user={user}/>
            }
            {
                student && user.authenticated &&
                <StudentInfo student={student}/>
            }
        </Form>
    }
}
