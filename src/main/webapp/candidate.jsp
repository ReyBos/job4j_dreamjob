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
            <h3 class="col s6 left-align">Кандидаты</h3>
            <p class="col s6 right-align">
                <a class="waves-effect waves-light btn" href='<c:url value="/candidate/edit.do" />'>Добавить кандидата</a>
            </p>
        </div>
        <div class="card">
            <div class="card-content">
                <table class="highlight">
                    <thead>
                    <tr>
                        <th>Имя</th>
                        <th>Город</th>
                        <th class="center-align">Фото</th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="candidate" items="${candidates}">
                        <tr>
                            <td><c:out value="${candidate.name}"/></td>
                            <td><c:out value="${candidate.cityName}"/></td>
                            <td class="center-align">
                                <c:if test="${not empty candidate.photoName}">
                                    <a href="<c:url value='/candidate/photo?name=${candidate.photoName}'/>">
                                        <img src="<c:url value='/candidate/photo?name=${candidate.photoName}'/>" width="100px" height="100px"/>
                                    </a>
                                </c:if>
                            </td>
                            <td class="right-align">
                                <a href='<c:url value="/candidate/edit.do?id=${candidate.id}"/>'>
                                    <i class="material-icons">edit</i>
                                </a>
                                <a href='<c:url value="/candidate/delete?id=${candidate.id}"/>'>
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