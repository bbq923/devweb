INSERT INTO USER (ID, user_Id, password, name, email, create_date) VALUES (1, 'javajigi', 'pass', 'jaesung', 'javajigi@gmail.com', CURRENT_TIMESTAMP());
INSERT INTO USER (ID, user_Id, password, name, email, create_date) VALUES (2, 'sanjigi', 'test', '산지기', 'javajigi2@gmail.com', CURRENT_TIMESTAMP());
INSERT INTO QUESTION (id, contents, create_Date, title, writer_Id, count_of_answer) VALUES ('1', 'transpiler란...', CURRENT_TIMESTAMP(), 'transpiler란 무엇인가요?', '1', 0);
INSERT INTO ANSWER (create_Date, contents, question_Id, writer_Id) VALUES (CURRENT_TIMESTAMP(), '이건 나도 잘 모르겠다.', '1', '1');
INSERT INTO ANSWER (create_Date, contents, question_Id, writer_Id) VALUES (CURRENT_TIMESTAMP(), '구글 닥스에 전용우 교수님이 정리한 내용이 있다. 참고해라.', '1', '1');
