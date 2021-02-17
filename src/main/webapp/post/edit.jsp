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
    <link type="text/css" rel="stylesheet" href="../css/materialize.min.css"  media="screen,projection"/>
    <script src="https://code.jquery.com/jquery-3.4.1.min.js" ></script>
    <title>Dream Job!</title>
</head>
<body>
<div>
    <%@ include file="../_menu.jsp" %>
    <div class="container row">
        <div class="col s6 offset-s3">
            <h3 class="header center-align">
                <c:if test="${post.id == 0}">
                    Новая вакансия
                </c:if>
                <c:if test="${post.id != 0}">
                    Редактирование вакансии
                </c:if>
            </h3>
            <div class="card horizontal">
                <div class="card-stacked row">
                    <form class="col s12" action='<c:url value="/post/save?id=${post.id}"/>' method="post">
                        <div class="card-content">
                            <div class="row">
                                <div class="input-field col s12">
                                    <input placeholder="" id="name" type="text" name="name" class="validate" value="<c:out value="${post.name}"/>">
                                    <label class="" for="name">Название</label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="input-field col s12">
                                    <textarea placeholder="" id="description" name="description" class="materialize-textarea" rows="3"><c:out value="${post.description}"/></textarea>
                                    <label class="" for="description">Описание вакансии</label>
                                </div>
                            </div>
                        </div>
                        <div class="card-action right-align">
                            <button type="submit" class="waves-effect waves-light btn">
                                сохранить
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="../js/materialize.min.js"></script>
</body>
</html>