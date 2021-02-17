<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!doctype html>
<html lang="en">
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
        <div class="col s6 offset-s3">
            <h4 class="header center-align">Регистрация</h4>
            <div class="card horizontal">
                <div class="card-stacked row">
                    <form class="col s12" action="<c:url value="/reg.do" />" method="post">
                        <div class="card-content">
                            <div class="row">
                                <div class="input-field col s12">
                                    <input placeholder="" id="name" type="text" name="name" class="validate" required>
                                    <label class="active" for="name">Имя</label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="input-field col s12">
                                    <input placeholder="" id="email" type="email" name="email" class="validate" required>
                                    <label class="active" for="email">Почта</label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="input-field col s12">
                                    <input placeholder="" id="password" type="password" name="password" class="validate" required>
                                    <label class="active" for="password">Пароль</label>
                                </div>
                                <c:if test="${not empty error}">
                                    <span class="helper-text" data-error="wrong" data-success="right">
                                        <c:out value="${error}"/>
                                    </span>
                                </c:if>
                            </div>
                        </div>
                        <div class="card-action right-align">
                            <button type="submit" class="waves-effect waves-light btn">
                                зарегистироваться<i class="material-icons right">send</i>
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>