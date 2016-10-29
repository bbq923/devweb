INSERT INTO USER (ID, user_Id, password, name, email) VALUES (1, 'javajigi', 'pass', 'jaesung', 'javajigi@gmail.com');
INSERT INTO USER (ID, user_Id, password, name, email) VALUES (2, 'sanjigi', 'test', '산지기', 'javajigi2@gmail.com');
INSERT INTO QUESTION (id, contents, create_Date, title, writer_Id) VALUES ('1', 'transpiler란...',CURRENT_TIMESTAMP(), 'transpiler란 무엇인가요?', '1');
INSERT INTO ANSWER (contents, question_Id, writer_Id) VALUES ('이건 나도 잘 모르겠다.', '1', '1');
INSERT INTO ANSWER (contents, question_Id, writer_Id) VALUES ('구글 닥스에 전용우 교수님이 정리한 내용이 있다. 참고해라.', '1', '1');
