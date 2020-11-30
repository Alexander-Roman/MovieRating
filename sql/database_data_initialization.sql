USE movie_rating;

INSERT INTO movies (title, director, release_year, synopsis, poster_path, rating)
VALUES ('The Hitchhiker\'s Guide to the Galaxy', 'Garth Jennings', 2005,
        'Mere seconds before the Earth is to be demolished by an alien construction crew, journeyman Arthur Dent is swept off the planet by his friend Ford Prefect, a researcher penning a new edition of "The Hitchhiker\'s Guide to the Galaxy." ',
        '/static/img/poster/1.jpg', 7.9);
INSERT INTO movies (title, director, release_year, synopsis, poster_path, rating)
VALUES ('The Shawshank Redemption', 'Frank Darabont', 1994,
        'Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.',
        '/static/img/poster/2.jpg', 8.3);
INSERT INTO movies (title, director, release_year, synopsis, poster_path, rating)
VALUES ('The Godfather: Part II', 'Francis Ford Coppola', 1974,
        'The early life and career of Vito Corleone in 1920s New York City is portrayed, while his son, Michael, expands and tightens his grip on the family crime syndicate.',
        '/static/img/poster/3.jpg', 8.8);
INSERT INTO movies (title, director, release_year, synopsis, poster_path, rating)
VALUES ('The Dark Knight', 'Christopher Nolan', 2008,
        'When the menace known as the Joker wreaks havoc and chaos on the people of Gotham, Batman must accept one of the greatest psychological and physical tests of his ability to fight injustice.',
        '/static/img/poster/4.jpg', 7.9);
INSERT INTO movies (title, director, release_year, synopsis, poster_path, rating)
VALUES ('Schindler\'s List', 'Steven Spielberg', 1993,
        'In German-occupied Poland during World War II, industrialist Oskar Schindler gradually becomes concerned for his Jewish workforce after witnessing their persecution by the Nazis.',
        '/static/img/poster/5.jpg', 8.6);
INSERT INTO movies (title, director, release_year, synopsis, poster_path, rating)
VALUES ('Pulp Fiction', 'Quentin Tarantino', 1994,
        'The lives of two mob hitmen, a boxer, a gangster and his wife, and a pair of diner bandits intertwine in four tales of violence and redemption.',
        '/static/img/poster/6.jpg', 7.4);
INSERT INTO movies (title, director, release_year, synopsis, poster_path, rating)
VALUES ('Fight Club', 'David Fincher', 1999,
        'An insomniac office worker and a devil-may-care soapmaker form an underground fight club that evolves into something much, much more.',
        '/static/img/poster/7.jpg', 9.5);
INSERT INTO movies (title, director, release_year, synopsis, poster_path, rating)
VALUES ('Forrest Gump', 'Robert Zemeckis', 1994,
        'The presidencies of Kennedy and Johnson, the events of Vietnam, Watergate and other historical events unfold through the perspective of an Alabama man with an IQ of 75, whose only desire is to be reunited with his childhood sweetheart.',
        '/static/img/poster/8.jpg', 7.4);
INSERT INTO movies (title, director, release_year, synopsis, poster_path, rating)
VALUES ('Goodfellas', 'Martin Scorsese', 1990,
        'The story of Henry Hill and his life in the mob, covering his relationship with his wife Karen Hill and his mob partners Jimmy Conway and Tommy DeVito in the Italian-American crime syndicate.',
        '/static/img/poster/9.jpg', 7.3);
INSERT INTO movies (title, director, release_year, synopsis, poster_path, rating)
VALUES ('One Flew Over the Cuckoo\'s Nest', 'Milos Forman', 1975,
        'A criminal pleads insanity and is admitted to a mental institution, where he rebels against the oppressive nurse and rallies up the scared patients.',
        '/static/img/poster/10.jpg', 8.9);
INSERT INTO movies (title, director, release_year, synopsis, poster_path, rating)
VALUES ('Léon', 'Luc Besson', 1994,
        'Mathilda, a 12-year-old girl, is reluctantly taken in by Léon, a professional assassin, after her family is murdered. An unusual relationship forms as she becomes his protégée and learns the assassin\'s trade.',
        '/static/img/poster/11.jpg', 8.6);
INSERT INTO movies (title, director, release_year, synopsis, poster_path, rating)
VALUES ('The Usual Suspects', 'Bryan Singer', 1995,
        'A sole survivor tells of the twisty events leading up to a horrific gun battle on a boat, which began when five criminals met at a seemingly random police lineup.',
        '/static/img/poster/12.jpg', 7.6);
INSERT INTO movies (title, director, release_year, synopsis, poster_path, rating)
VALUES ('Whiplash', 'Damien Chazelle', 2014,
        'A promising young drummer enrolls at a cut-throat music conservatory where his dreams of greatness are mentored by an instructor who will stop at nothing to realize a student\'s potential.',
        '/static/img/poster/13.jpg', 7.7);
INSERT INTO movies (title, director, release_year, synopsis, poster_path, rating)
VALUES ('Apocalypse Now', 'Francis Ford Coppola', 1979,
        'A U.S. Army officer serving in Vietnam is tasked with assassinating a renegade Special Forces Colonel who sees himself as a god.',
        '/static/img/poster/14.jpg', 8.0);
INSERT INTO movies (title, director, release_year, synopsis, poster_path, rating)
VALUES ('Memento', 'Christopher Nolan', 2000,
        'A man with short-term memory loss attempts to track down his wife\'s murderer.', '/static/img/poster/15.jpg',
        7.6);
INSERT INTO movies (title, director, release_year, synopsis, poster_path, rating)
VALUES ('Eternal Sunshine of the Spotless Mind', 'Michel Gondry', 2004,
        'When their relationship turns sour, a couple undergoes a medical procedure to have each other erased from their memories.',
        '/static/img/poster/16.jpg', 7.5);
INSERT INTO movies (title, director, release_year, synopsis, poster_path, rating)
VALUES ('Snatch', 'Guy Ritchie', 2000,
        'Unscrupulous boxing promoters, violent bookmakers, a Russian gangster, incompetent amateur robbers and supposedly Jewish jewelers fight to track down a priceless stolen diamond.',
        '/static/img/poster/17.jpg', 7.7);
INSERT INTO movies (title, director, release_year, synopsis, poster_path, rating)
VALUES ('Scarface', 'Brian De Palma', 1983,
        'In 1980 Miami, a determined Cuban immigrant takes over a drug cartel and succumbs to greed.',
        '/static/img/poster/18.jpg', 8.5);


INSERT INTO accounts (user_name, password, role)
VALUES ('account1', 'password1', 'ADMIN');
INSERT INTO accounts (user_name, password, role)
VALUES ('account2', 'password2', 'EDITOR');
INSERT INTO accounts (user_name, password, role)
VALUES ('account3', 'password3', 'EDITOR');
INSERT INTO accounts (user_name, password, role)
VALUES ('account4', 'password4', 'USER');
INSERT INTO accounts (user_name, password, role)
VALUES ('account5', 'password5', 'USER');
INSERT INTO accounts (user_name, password, role)
VALUES ('account6', 'password6', 'USER');
INSERT INTO accounts (user_name, password, role)
VALUES ('account7', 'password7', 'USER');
INSERT INTO accounts (user_name, password, role)
VALUES ('account8', 'password8', 'USER');
INSERT INTO accounts (user_name, password, role)
VALUES ('account9', 'password9', 'USER');
INSERT INTO accounts (user_name, password, role)
VALUES ('account10', 'password10', 'USER');
INSERT INTO accounts (user_name, password, role)
VALUES ('account11', 'password11', 'USER');
INSERT INTO accounts (user_name, password, role)
VALUES ('account12', 'password12', 'USER');
INSERT INTO accounts (user_name, password, role)
VALUES ('account13', 'password13', 'USER');

INSERT INTO comments (movie_id, account_id, date_time, text)
VALUES (4, 9, '2020-09-14 13:25:00',
        'В своём стремлении улучшить пользовательский опыт мы упускаем, что реплицированные с зарубежных источников, современные исследования ограничены исключительно образом мышления.');
INSERT INTO comments (movie_id, account_id, date_time, text)
VALUES (2, 8, '2020-09-15 11:03:45',
        'Принимая во внимание показатели успешности, глубокий уровень погружения прекрасно подходит для реализации кластеризации усилий.');
INSERT INTO comments (movie_id, account_id, date_time, text)
VALUES (13, 3, '2020-05-12 15:18:13',
        'Господа, сплочённость команды профессионалов способствует подготовке и реализации поставленных обществом задач!');
INSERT INTO comments (movie_id, account_id, date_time, text)
VALUES (3, 10, '2020-03-17 12:23:06',
        'Противоположная точка зрения подразумевает, что явные признаки победы институционализации могут быть превращены в посмешище, хотя само их существование приносит несомненную пользу обществу.');
INSERT INTO comments (movie_id, account_id, date_time, text)
VALUES (12, 2, '2020-09-15 20:38:00',
        'Господа, граница обучения кадров не даёт нам иного выбора, кроме определения кластеризации усилий!');
INSERT INTO comments (movie_id, account_id, date_time, text)
VALUES (17, 5, '2020-07-18 04:21:34',
        'Господа, базовый вектор развития предопределяет высокую востребованность как самодостаточных, так и внешне зависимых концептуальных решений.');
INSERT INTO comments (movie_id, account_id, date_time, text)
VALUES (2, 12, '2020-02-19 05:14:32',
        'И нет сомнений, что действия представителей оппозиции, которые представляют собой яркий пример континентально-европейского типа политической культуры, будут разоблачены.');
INSERT INTO comments (movie_id, account_id, date_time, text)
VALUES (17, 6, '2020-09-06 11:21:37',
        'Противоположная точка зрения подразумевает, что представители современных социальных резервов призывают нас к новым свершениям, которые, в свою очередь, должны быть обнародованы.');
INSERT INTO comments (movie_id, account_id, date_time, text)
VALUES (9, 5, '2020-03-26 16:36:09',
        'С другой стороны, экономическая повестка сегодняшнего дня однозначно фиксирует необходимость как самодостаточных, так и внешне зависимых концептуальных решений.');
INSERT INTO comments (movie_id, account_id, date_time, text)
VALUES (12, 8, '2020-07-31 15:07:51',
        'Кстати, тщательные исследования конкурентов освещают чрезвычайно интересные особенности картины в целом, однако конкретные выводы, разумеется, функционально разнесены на независимые элементы.');
INSERT INTO comments (movie_id, account_id, date_time, text)
VALUES (3, 12, '2020-01-24 10:14:24',
        'Прежде всего, сложившаяся структура организации влечет за собой процесс внедрения и модернизации экономической целесообразности принимаемых решений.');
INSERT INTO comments (movie_id, account_id, date_time, text)
VALUES (8, 9, '2020-07-06 17:12:47',
        'Сложно сказать, почему непосредственные участники технического прогресса призывают нас к новым свершениям, которые, в свою очередь, должны быть описаны максимально подробно.');
INSERT INTO comments (movie_id, account_id, date_time, text)
VALUES (12, 12, '2020-04-22 11:29:29',
        'С учётом сложившейся международной обстановки, внедрение современных методик не даёт нам иного выбора, кроме определения поэтапного и последовательного развития общества.');
INSERT INTO comments (movie_id, account_id, date_time, text)
VALUES (8, 4, '2020-07-22 08:47:34',
        'Вот вам яркий пример современных тенденций - дальнейшее развитие различных форм деятельности выявляет срочную потребность анализа существующих паттернов поведения.');
INSERT INTO comments (movie_id, account_id, date_time, text)
VALUES (7, 10, '2020-11-03 18:09:22',
        'Элементы политического процесса будут объявлены нарушающими общечеловеческие нормы этики и морали.');
INSERT INTO comments (movie_id, account_id, date_time, text)
VALUES (8, 10, '2020-03-15 19:32:56',
        'Приятно, граждане, наблюдать, как многие известные личности, превозмогая сложившуюся непростую экономическую ситуацию, объявлены нарушающими общечеловеческие нормы этики и морали.');
INSERT INTO comments (movie_id, account_id, date_time, text)
VALUES (4, 8, '2020-11-13 18:15:29',
        'Как принято считать, явные признаки победы институционализации смешаны с не уникальными данными до степени совершенной неузнаваемости, из-за чего возрастает их статус бесполезности.');
INSERT INTO comments (movie_id, account_id, date_time, text)
VALUES (4, 12, '2020-09-29 00:22:39',
        'Ясность нашей позиции очевидна: разбавленное изрядной долей эмпатии, рациональное мышление в значительной степени обусловливает важность системы массового участия.');
INSERT INTO comments (movie_id, account_id, date_time, text)
VALUES (14, 12, '2020-11-27 17:02:59',
        'Значимость этих проблем настолько очевидна, что постоянное информационно-пропагандистское обеспечение нашей деятельности напрямую зависит от приоритизации разума над эмоциями.');
INSERT INTO comments (movie_id, account_id, date_time, text)
VALUES (1, 11, '2020-02-12 03:13:20',
        'Дальнейшее развитие различных форм деятельности, а также свежий взгляд на привычные вещи - безусловно открывает новые горизонты для вывода текущих активов.');
INSERT INTO comments (movie_id, account_id, date_time, text)
VALUES (2, 9, '2020-04-05 08:45:44',
        'Внезапно, некоторые особенности внутренней политики призывают нас к новым свершениям, которые, в свою очередь, должны быть ограничены исключительно образом мышления.');
INSERT INTO comments (movie_id, account_id, date_time, text)
VALUES (10, 8, '2020-11-24 22:59:43',
        'Ясность нашей позиции очевидна: перспективное планирование создаёт необходимость включения в производственный план целого ряда внеочередных мероприятий с учётом комплекса форм воздействия.');
INSERT INTO comments (movie_id, account_id, date_time, text)
VALUES (7, 3, '2020-03-25 13:32:46',
        'Как принято считать, независимые государства являются только методом политического участия и ассоциативно распределены по отраслям!');
INSERT INTO comments (movie_id, account_id, date_time, text)
VALUES (6, 13, '2020-06-15 10:18:35',
        'Банальные, но неопровержимые выводы, а также сторонники тоталитаризма в науке являются только методом политического участия и ассоциативно распределены по отраслям.');
INSERT INTO comments (movie_id, account_id, date_time, text)
VALUES (7, 3, '2020-04-21 18:31:43',
        'В частности, внедрение современных методик способствует повышению качества новых принципов формирования материально-технической и кадровой базы.');
INSERT INTO comments (movie_id, account_id, date_time, text)
VALUES (7, 9, '2020-10-17 07:45:20',
        'Предварительные выводы неутешительны: начало повседневной работы по формированию позиции играет важную роль в формировании своевременного выполнения сверхзадачи.');
INSERT INTO comments (movie_id, account_id, date_time, text)
VALUES (6, 4, '2020-09-29 14:28:56', 'Но интерактивные прототипы ограничены исключительно образом мышления.');
INSERT INTO comments (movie_id, account_id, date_time, text)
VALUES (12, 12, '2020-08-06 18:52:10',
        'В целом, конечно, новая модель организационной деятельности в значительной степени обусловливает важность системы массового участия.');
INSERT INTO comments (movie_id, account_id, date_time, text)
VALUES (8, 2, '2020-08-15 19:58:08',
        'Внезапно, действия представителей оппозиции, превозмогая сложившуюся непростую экономическую ситуацию, разоблачены.');
INSERT INTO comments (movie_id, account_id, date_time, text)
VALUES (9, 11, '2020-08-14 07:19:16',
        'В рамках спецификации современных стандартов, независимые государства, вне зависимости от их уровня, должны быть заблокированы в рамках своих собственных рациональных ограничений.');
INSERT INTO comments (movie_id, account_id, date_time, text)
VALUES (11, 8, '2020-03-03 18:15:26',
        'Повседневная практика показывает, что перспективное планирование способствует повышению качества модели развития.');
INSERT INTO comments (movie_id, account_id, date_time, text)
VALUES (18, 3, '2020-08-23 00:31:25',
        'Ясность нашей позиции очевидна: выбранный нами инновационный путь говорит о возможностях прогресса профессионального сообщества.');
INSERT INTO comments (movie_id, account_id, date_time, text)
VALUES (6, 8, '2020-06-17 01:57:19',
        'Сложно сказать, почему интерактивные прототипы, превозмогая сложившуюся непростую экономическую ситуацию, объективно рассмотрены соответствующими инстанциями.');
INSERT INTO comments (movie_id, account_id, date_time, text)
VALUES (3, 9, '2020-02-06 05:30:48',
        'Ясность нашей позиции очевидна: укрепление и развитие внутренней структуры однозначно фиксирует необходимость модели развития.');
INSERT INTO comments (movie_id, account_id, date_time, text)
VALUES (14, 3, '2020-06-16 05:19:31',
        'Вот вам яркий пример современных тенденций - синтетическое тестирование выявляет срочную потребность вывода текущих активов.');
INSERT INTO comments (movie_id, account_id, date_time, text)
VALUES (18, 7, '2020-10-25 04:03:45',
        'Предварительные выводы неутешительны: реализация намеченных плановых заданий, в своём классическом представлении, допускает внедрение распределения внутренних резервов и ресурсов.');

INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (14, 2, 10);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (17, 11, 7);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (17, 10, 6);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (15, 8, 6);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (9, 4, 8);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (13, 7, 9);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (4, 10, 8);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (10, 11, 9);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (2, 2, 6);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (5, 11, 10);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (9, 12, 7);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (13, 4, 8);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (3, 10, 10);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (4, 4, 10);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (4, 3, 6);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (12, 7, 8);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (8, 1, 7);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (3, 3, 6);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (9, 8, 6);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (12, 1, 6);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (8, 6, 6);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (3, 8, 9);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (13, 9, 8);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (5, 13, 6);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (4, 13, 6);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (14, 7, 6);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (1, 9, 10);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (18, 12, 10);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (2, 1, 10);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (11, 7, 8);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (9, 10, 9);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (15, 13, 10);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (2, 12, 8);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (11, 2, 10);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (18, 13, 10);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (10, 1, 10);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (18, 2, 8);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (14, 1, 7);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (4, 6, 6);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (11, 3, 10);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (16, 3, 10);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (10, 7, 6);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (15, 10, 6);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (6, 6, 6);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (11, 12, 9);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (2, 10, 10);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (9, 2, 7);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (5, 4, 9);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (16, 8, 7);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (10, 12, 10);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (13, 8, 6);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (5, 12, 8);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (6, 10, 8);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (17, 13, 8);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (16, 12, 6);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (9, 1, 6);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (16, 9, 8);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (13, 10, 6);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (12, 13, 6);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (1, 3, 6);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (8, 12, 9);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (12, 3, 6);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (6, 1, 10);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (8, 10, 8);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (6, 9, 8);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (10, 9, 8);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (6, 12, 7);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (11, 8, 7);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (4, 1, 7);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (2, 7, 8);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (14, 8, 8);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (4, 9, 9);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (16, 2, 8);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (7, 11, 9);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (10, 3, 9);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (9, 5, 6);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (18, 9, 10);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (12, 10, 9);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (5, 5, 10);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (11, 4, 6);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (17, 8, 10);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (1, 7, 7);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (17, 5, 6);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (18, 11, 10);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (1, 12, 7);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (18, 3, 8);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (13, 3, 9);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (16, 4, 6);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (9, 11, 10);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (16, 6, 8);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (18, 7, 6);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (14, 3, 10);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (6, 8, 6);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (10, 5, 6);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (15, 1, 10);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (7, 4, 10);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (5, 9, 7);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (3, 6, 9);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (18, 4, 8);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (12, 11, 8);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (4, 8, 7);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (12, 9, 9);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (8, 9, 6);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (17, 3, 10);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (15, 3, 6);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (11, 10, 9);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (7, 7, 9);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (1, 13, 8);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (7, 12, 10);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (6, 3, 6);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (7, 9, 10);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (2, 8, 9);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (2, 6, 6);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (3, 5, 10);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (18, 8, 8);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (4, 2, 10);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (9, 13, 7);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (6, 5, 8);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (12, 6, 7);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (12, 12, 9);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (10, 13, 6);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (5, 3, 10);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (2, 4, 10);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (14, 4, 9);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (4, 11, 10);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (17, 6, 7);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (16, 5, 7);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (6, 7, 8);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (14, 13, 9);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (9, 6, 7);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (8, 2, 10);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (8, 5, 6);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (14, 9, 6);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (18, 5, 7);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (2, 3, 8);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (1, 4, 9);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (1, 8, 8);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (11, 6, 10);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (10, 8, 7);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (14, 5, 7);
INSERT INTO user_ratings (movie_id, account_id, assessment)
VALUES (7, 2, 9);