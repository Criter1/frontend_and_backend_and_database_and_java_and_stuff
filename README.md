### Схема базы данных ###
![Схема базы данных](https://github.com/Criter1/frontend_and_backend_and_database_and_java_and_stuff/blob/main/images/DB.jpg)

### Схема страниц ###
![Схема страниц](https://github.com/Criter1/frontend_and_backend_and_database_and_java_and_stuff/blob/main/images/pages.jpg)

### Описание страниц ###

1. Лэндинг с описанием проекта и приглашением начать поиск по клиентам или фильмам.
2. Поиск по фильмам представляет с собой боковое меню с фильтрами и список найденных фильмов в основной части страницы. Предусмотрена возможность перехода на лэндинг. Поиск осуществляется по полям Name, Author, Year и Company.
3. Поиск по клиентам повторяет страницу поиска по фильмам, но с другими фильтрами и другим отображением списка результатов. Предусмотрена возможность перехода на лэндинг. Поиск осуществляется по полю Name, по имени конкретного фильма, который пользователь когда либо брал, а также по дате последней транзакци пользователя.
4. Страница фильма содержит информацию о фильме из базы данных с возможностью её отредактировать, а также о типе и количестве свободных физических носителей и списка клиентов, когда-либо бравших фильм (с фильтром по временному промежутку и всё это тоже с возможностью редактирования). Предусмотрена возможность перехода на лэндинг.
5. Страница клиента содержит информацию о клиенте из базы данных с возможностью редактирования, а также историю его покупок с возможностью перехода на страницу конкретного купленного фильма (тоже с возможностью редактирования). Предусмотрена возможность перехода на лэндинг.

### Примеры сценариев ###

Получение списка клиентов:

	1. Перейти на вкладку поиска по клиентам.
Получение списка фильмов:

	1. Перейти на вкладку поиска по фильмам.
Получение списка клиентов бравших фильм за определённый промежуток времени:

	1. Перейти на вкладку поиска по фильмам.
	2. Отфильтровать список фильмов с помощью фильтров для поиска нужного фильма и перейти на его страницу.
	3. Выбрать нужный промежуток времени в фильтре.
Внесение информации о выдаче фильма клиенту:

	1. Перейти на вкладку поиска по клиентам.
	2. Отфильтровать список клиентов с помощью фильтров для поиска нужного клиента.
	3.  Перейти на страницу нужного клиента.
	4. Нажать на "+" и ввести нужные данные в появившемся меню.
Добавление клиента:

	1. Перейти на вкладку поиска по клиентам.
	2. Нажать на "+" и заполнить необходимые данные на открывшейся странице вновь созданного клиента.
Удаление клиента:

	1. Перейти на вкладку поиска по клиентам.
	2. Отфильтровать список клиентов с помощью фильтров для поиска нужного клиента.
	3. Нажать на "-" рядом с клиентом.
Редактирование клиента:

	1. Перейти на вкладку поиска по клиентам.
	2. Отфильтровать список клиентов с помощью фильтров для поиска нужного клиента.
	3. Перейти на страницу нужного клиента.
	4. Изменить необходимые данные.
Добавление и удаление фильма и отдельных экземпляров, чтение и редактирование данных о фильмах и их экземплярах:

	1. Аналогично этим действиям с клиентом, но на первом шаге необходимо перейти на страницу поиска по фильмам.
