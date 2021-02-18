# Работа мечты
[![Build Status](https://travis-ci.org/ReyBos/job4j_dreamjob.svg?branch=master)](https://travis-ci.org/ReyBos/job4j_dreamjob) &nbsp;&nbsp;
[![codecov](https://codecov.io/gh/ReyBos/job4j_dreamjob/branch/master/graph/badge.svg?token=UZITBYY2VK)](https://codecov.io/gh/ReyBos/job4j_dreamjob)

<a name="menu"></a>
<ul>
    <li>
        <a href="#about">О проекте</a>
        <ul>
            <li><a href="#description">Описание</a></li>
            <li><a href="#technologies">Технологии</a></li>
            <li><a href="#structure">Структура</a></li>
        </ul>
    </li>
    <li>
        <a href="#links">Сборка проекта</a>
    </li>
    <li>
        <a href="#links">Полезные ссылки</a>
    </li>
    <li>
        <a href="#contact">Контакты</a>
    </li>
</ul>

<h2><a name="about">О проекте</a></h2>
<h4><a name="description">Описание</a> <a href="#menu">:arrow_up:</a></h4>
<p>
    Проект для изучения Java EE.
</p>
<p>
    Это приложение - биржа работы.<br>
    В системе будут два типа пользователей: кандидаты и менеджеры. Кандидаты будут публиковать резюме. Менеджеры будут публиковать вакансии о работе.<br>
    Кандидаты могут откликнуться на вакансию. Кадровик может пригласить на вакансию кандидата.
</p>
<h4><a name="technologies">Технологии</a> <a href="#menu">:arrow_up:</a></h4>
<ul>
    <li>Java 14</li>
    <li>Java EE Servlets, JSP, JSTL</li>
    <li>PostgreSQL, JDBC, Liquibase</li>
    <li>JUnit, Mockito, PowerMock</li>
    <li>Maven, Tomcat</li>
    <li>HTML, JavaScript, jQuery, JSON</li>
    <li><a href="https://materializecss.com">Material Design library</a></li>
</ul>
<h4><a name="structure">Структура</a> <a href="#menu">:arrow_up:</a></h4>
<pre><code>.
|-db
|---scripts
|-src
|---main
|-----java/ru/job4j/dream
|-------------filter
|-------------model
|-------------servlet
|-------------store
|-----resources
|-----webapp
|-------WEB-INF
|-------css
|-------js
|---test
|-----java/ru/job4j/dream
└─------------servlet</code></pre>

<h2><a name="build">Сборка проекта</a> <a href="#menu">:arrow_up:</a></h2>
<ol>
    <li>
        Для успешной сборки и работы проекта на вашем компьютере должны быть установлены:
        <ol>
            <li>JDK 14(+)</li>
            <li>Maven</li>
            <li>PostgreSQL</li>
            <li>Tomcat</li>
        </ol>
    </li>
    <li>
        В PostgreSQL создайте базу с именем "dream_job"
    </li>
    <li>
        Скачайте проект к себе на компьютер с помощью команды<br>
        <code>git clone https://github.com/ReyBos/job4j_dreamjob.git</code><br>
        перейдите в корень проекта
    </li>
    <li>
        Добавьте настройки для доступа к базе данных, для этого внесите соответствующие изменения в файле<br>
        <code>src/main/resources/db.properties</code><br>
        и в файле конфигурации проекта <code>pom.xml</code> в разделе
    
``` 
<profile>   
    <id>production</id>
    <properties>
        <db.url>jdbc:postgresql://127.0.0.1:5432/dream_job</db.url>
        <db.username>postgres</db.username>
        <db.password>password</db.password>
        <db.driver>org.postgresql.Driver</db.driver>
    </properties>
</profile>
```
</li>
    <li>
        Соберите проект выполнив команду в корне проекта<br>
        <code>mvn install -Pproduction</code><br>
        в случае успешной сборки появится файл <br>
        <code>target/job4j_dreamjob-1.0.war</code><br>
        переименуйте его в <code>dreamjob.war</code>
    </li>
    <li>
        Для запуска веб-приложения вам нужно скопировать <code>dreamjob.war</code> в папку <code>webapps</code> вашего Tomcat
    </li>
    <li>
        После запуска сервера приложение будет доступно по адресу<br>
        <a href="http://localhost:8080/dreamjob/">http://localhost:8080/dreamjob/</a>
    </li>
</ol>

<h2><a name="links">Полезные ссылки</a> <a href="#menu">:arrow_up:</a></h2>
<p>Ресурсы которые были полезны при создании проекта</p>
<ul>
    <li>
        <strong><a href="https://overcoder.net/q/5206/%D0%BA%D0%B0%D0%BA-%D0%B8%D1%81%D0%BF%D0%BE%D0%BB%D1%8C%D0%B7%D0%BE%D0%B2%D0%B0%D1%82%D1%8C-%D1%81%D0%B5%D1%80%D0%B2%D0%BB%D0%B5%D1%82%D1%8B-%D0%B8-ajax">link</a></strong>
        - сервлеты и Ajax
    </li>
    <li>
        <strong><a href="https://coderoad.ru/3455625/Linux-%D0%BA%D0%BE%D0%BC%D0%B0%D0%BD%D0%B4%D0%B0-%D0%B4%D0%BB%D1%8F-%D0%BF%D0%B5%D1%87%D0%B0%D1%82%D0%B8-%D1%81%D1%82%D1%80%D1%83%D0%BA%D1%82%D1%83%D1%80%D1%8B-%D0%BA%D0%B0%D1%82%D0%B0%D0%BB%D0%BE%D0%B3%D0%BE%D0%B2-%D0%B2-%D0%B2%D0%B8%D0%B4%D0%B5-%D0%B4%D0%B5%D1%80%D0%B5%D0%B2%D0%B0">link</a></strong>
        - вывод древовидной структуры папок в консоль
    </li>
    <li>
        <strong><a href="https://materializecss.com">link</a></strong>
        - готовый набор html элементов
    </li>
    <li>
        <strong><a href="https://medium.com/@fahimhossain_16989/installing-apache-tomcat-on-macos-mojave-using-homebrew-28ce039b4b2e">link</a></strong>
        - установка и запуск Tomcat на macOS
    </li>
    <li>
        <strong><a href="https://stackoverflow.com/questions/24623731/where-is-catalina-base-webapps-folder-for-tomcat-8-on-mac">link</a></strong>
        - расположение папки webapps (Tomcat)
    </li>
    <!--<li>
        <strong><a href="">link</a></strong>
        - сервлеты и Ajax
    </li>-->
</ul>

<h2><a name="contact">Контакты</a> <a href="#menu">:arrow_up:</a></h2>
<p>Связаться со мной по всем интересующим вопросам вы можете здесь:</p>

[![alt-text](https://img.shields.io/badge/-linkedin-283e4a?style=flat&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/reybos/)&nbsp;&nbsp;
[![alt-text](https://img.shields.io/badge/-ВКонтакте-blue?style=flat&logo=vk&logoColor=white  "vk.com")](https://vk.com/reybos)&nbsp;&nbsp;
[![alt-text](https://img.shields.io/badge/-instagram-E4405F?style=flat&logo=instagram&logoColor=white)](https://www.instagram.com/andreybossiy)&nbsp;&nbsp;
[![alt-text](https://img.shields.io/badge/-telegram-grey?style=flat&logo=telegram&logoColor=white)](https://t.me/reybos)&nbsp;&nbsp;