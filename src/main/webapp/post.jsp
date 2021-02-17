<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!doctype html>
<html lang="ru">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!--Import Google Icon Font-->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <!--Import materialize.css-->
    <link type="text/css" rel="stylesheet" href="css/materialize.min.css"  media="screen,projection"/>
    <title>Dream Job!</title>
</head>
<body>
<div>
    <%@ include file="_menu.jsp" %>
    <div class="container row">
        <div class="row valign-wrapper">
            <h3 class="col s6 left-align">Вакансии</h3>
            <p class="col s6 right-align">
                <a class="waves-effect waves-light btn" href='<c:url value="/post/edit.do" />'>Добавить вакансию</a>
            </p>
        </div>
        <div class="card">
            <div class="card-content">
                <table class="highlight">
                    <thead>
                        <tr>
                            <th s>Название</th>
                            <th>Описание</th>
                            <th>Дата создания</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="post" items="${posts}">
                        <tr>
                            <td><c:out value="${post.name}"/></td>
                            <td><c:out value="${post.description}"/></td>
                            <td><c:out value="${post.formattedCreated}"/></td>
                            <td class="right-align">
                                <a href='<c:url value="/post/edit.do?id=${post.id}"/>'>
                                    <i class="material-icons">edit</i>
                                </a>
                                <a href='<c:url value="/post/delete?id=${post.id}"/>'>
                                    <i class="material-icons">delete</i>
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
</html>