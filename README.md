# Проект petstore-api-tests
## :cherry_blossom:	Содержание
> ➠ [Используемые технологии](#Используемые-технологии)
>
> ➠ [Описание проекта](#Описание-проекта)
>
> ➠ [Список проверок](#список-проверок-реализованных-в-автотестах)
>
> ➠ [Пример ручного выполнения тестов с помощью Postman](#Пример-ручного-выполнения-тестов-с-помощью-Postman)
>
> ➠ [Структура проекта](#Структура-проекта)
>
> ➠ [Запуск автотестов выполняется на сервере Jenkins](#Запуск-автотестов-выполняется-на-сервере-Jenkins)
>
> ➠ [Отчёты о результатах сборок в Allure TestOps](#отчёты-о-результатах-сборок-списки-тесткейсов-аналитические-dashboards-хранятся-в-allure-testops)
>
> ➠ [Оповещения](#Настроено-автоматическое-оповещение-о-результатах-сборки-Jenkins-в-Telegram-бот)

## Используемые технологии
![This is an image](/design/icons/ChatGPT_logo.png)![This is an image](/design/icons/Java.png)![This is an image](/design/icons/Gradle.png)![This is an image](/design/icons/Rest-Assured.png)![This is an image](/design/icons/Intelij_IDEA.png)![This is an image](/design/icons/JUnit5.png)![This is an image](/design/icons/Jenkins.png)![This is an image](/design/icons/Allure_Report.png)![This is an image](/design/icons/AllureTestOps.png)![This is an image](/design/icons/Telegram.png)
## Описание проекта
Дипломный проект реализации автотестирования **Rest Api**.<br/>
>В качестве объекта тестирования выбран сайт https://restful-booker.herokuapp.com/apidoc/index.html с открытым api.<br/>

![This is an image](/design/images/booker.png)

Сайт позволяет бронировать поездку, изменять полностью и частично бронирование, удалять бронирование, получать информацию о бронировании, а так же получить информацию о доступности сервиса.

#### Особенности реализации тестового проекта
- Модели получаемых данных описаны с помощью библиотеки Lombok.
- Использованы спецификации для запросов и ответов
- Использованы шаблоны форматирования логов запросов.
- Использованы кастомные шаблоны запросов и ответов для отчета Allure
- Реализован полный CRUD с подготовкой тестовых данных, а так же полной очисткой после теста

## Список проверок, реализованных в автотестах
- [x] Проверка получения токена несуществующим пользователем
- [x] Проверка получения токена без username
- [x] Проверка успешного получения токена
- [x] Проверка получения токена без password
- [x] Проверка доступности сервиса
- [x] Попытка получения несуществующего бронирования (должен быть 404)
- [x] Получение бронирований по диапазону дат (ожидается пустой список)
- [x] Получение информации о конкретном бронировании
- [x] Создание бронирования
- [x] Создание бронирования - без полезной нагрузки
- [x] Получение бронирований по диапазону дат (должны быть результаты)
- [x] Создание бронирования - без totalPrice
- [x] Создание бронирования - без lastname
- [x] Создание бронирования - без firstname
- [x] Обновление бронирования
- [x] Удаление бронирования

## Пример ручного выполнения тестов с помощью Postman
![This is an image](/design/images/postman.png)

## Структура проекта
- [x] postman - коллекция со всеми запросами
- [x] helpers - подключение кастомных шаблонов для Allure Report и client для CRUD
- [x] models - модели данных для тестов
- [x] spec - спецификации, необходимые для тестов
- [x] resources - кастомные шаблоны для Allure Report и настройка многопоточного запуска

![This is an image](/design/images/str.png)

#### Пример запуска из командной строки
```bash
gradle clean test
```
## Запуск автотестов выполняется на сервере Jenkins
> <a target="_blank" href="https://jenkins.autotests.cloud/job/10-azavrichko-diplom_api/">Ссылка на проект в Jenkins</a>

![This is an image](/design/images/jenkins.png)

Для запуска тестов необходимо выбрать пункт **"Собрать сейчас"**

## Отчёты о результатах сборок, списки тесткейсов, аналитические dashboards хранятся в Allure TestOps
> <a target="_blank" href="https://allure.autotests.cloud/project/1177/dashboards">Сссылка на проект в AllureTestOps</a> (запрос доступа admin@qa.guru)

### Итоговые dashboard по результатам сборок
![This is an image](/design/images/dashboard_overview.png)
### Список результата выполнения тест-кейсов в Allure TestOps
![This is an image](/design/images/allure_report_features.png)
### Пример автоматически сгенерированными тест-кейсами в Allure TestOps
![This is an image](/design/images/allure_testcases.png)
### Пример выполнения тестов в Allure TestOps
![This is an image](/design/images/launches.png)

### Итоговые dashboard в Allure Report
![This is an image](/design/images/allure_report_dashboard.png)
### Список тест-кейсов в Allure Report
![This is an image](/design/images/testcases.png)
### Графики Dashboards в Allure Report
![This is an image](/design/images/graph.png)
### Графики Dashboards в Allure Report
![This is an image](/design/images/graph2.png)

## Настроено автоматическое оповещение о результатах сборки Jenkins в Telegram-бот
![This is an image](/design/images/bot.png)


:heart: <a target="_blank" href="https://qa.guru">qa.guru</a><br/>
:blue_heart: <a target="_blank" href="https://t.me/qa_automation">t.me/qa_automation</a>