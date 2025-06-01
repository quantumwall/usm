Система управления подписками.
Для запуска необходимо иметь установленный на ПК Docker и JDK 17 (т.к. докер-контейнер содержит JRE 17)
1. Клонировать репозиторий на ПК.
2. Находясь в корне проект скомпилировать его командой из консоли: ./mvnw clean && ./mvnw package (./mvnw.cmd clean, ./mvnw.cmd package для Windows)
3. Находясь там же запустить docker compose (docker compose up). Это поднимет базу данных и само приложение.

Рест будет доступен на порту 8080.
Доступные эндпойнты:
<li>POST: /api/v1/users создать пользователя. В теле запроса передается json: <br />
<code>
  {
    "username": ...
    "firstname": ...
    "lastname": ...
  }
</code><br /></li>
<li>PUT: /api/v1/users/{id} обновить информацию пользователя. В теле запроса передается json:<br />
<code>
  {
    "username": ... не подлежит изменению, уникальный, игнорируется
    "firstname": ...
    "lastname": ...
  }
  </code><br /></li>
<li>GET: /api/v1/users/{id} получить информацию о пользователе</li>
<li>GET: /api/v1/users/{id}/subscriptions получить подписки пользователя</li>
<li>POST: /api/v1/users/{id}/subscriptions создать подписку пользователю. В теле запроса передается json:<br />
    <code>
    {
      "subscriptionId": ...
      "expirationDate": ... дата окончания подписки в формате yyyy-MM-dd
    }
  </code><br /></li>
<li>GET: /api/v1/users/{id}/subscriptions/{id} удалить подписку пользователя</li>
<li>DELETE: /api/v1/users/{id} удалить пользователя</li>
<li>GET: /api/v1/subscriptions получить список доступных подписок (список на данный момент жестко предопределен)</li>
<li>GET: /api/v1/subscriptions/top получить топ-3 подписок</li><br />
  
  <strong>Схема базы данных<strong><br />
![Схема базы данных](https://github.com/quantumwall/usm/blob/main/db_schema.png)
