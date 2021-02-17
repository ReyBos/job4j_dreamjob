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
<script>
    $(document).ready(function() {
        let curCity = <c:out value="${candidate.cityId}"/>;
        $.ajax({
            type: 'GET',
            url: 'http://localhost:8080/dreamjob/city'
        }).done(function(response) {
            response.forEach(function (arrayItem)
            {
                let elem = "<option value='" + arrayItem.id + "' " + (arrayItem.id == curCity ? "selected" : "") + ">"
                    + arrayItem.name + "</option>";
                $(".js-city").append(elem);
            });
            $('.js-city').formSelect();
        }).fail(function(err){
            console.log(err);
        });
    });

    function saveCandidate() {
        let name = $(".js-name").val();
        let city = $(".js-city").val();
        let error = "";
        if (name == "") {
            error += "Введите имя \n";
        }
        if (city == 0) {
            error += "Выберите город \n";
        }
        if (error != "") {
            alert(error);
        } else {
            $(".js-candidate").submit();
        }
    }
</script>
<div>
    <%@ include file="../_menu.jsp" %>
    <h3 class="header center-align">
        <c:if test="${candidate.id == 0}">
            Новый кандидат
        </c:if>
        <c:if test="${candidate.id != 0}">
            Редактирование кандидата
        </c:if>
    </h3>
    <div class="container row">
        <div class="col s6 <c:if test="${candidate.id == 0}">offset-s3</c:if>">
            <div class="card horizontal">
                <div class="card-stacked row">
                    <form class="col js-candidate" action="<c:url value='/candidate/save?id=${candidate.id}'/>" method="post">
                        <div class="card-content">
                            <div class="row">
                                <div class="input-field col s12">
                                    <input placeholder="" id="name" type="text" name="name" class="validate js-name" value="<c:out value="${candidate.name}"/>">
                                    <label class="" for="name">Имя</label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="input-field col s12">
                                    <select class="js-city" name="cityId">
                                        <option value="0">Выберите город</option>
                                    </select>
                                    <label>Город</label>
                                </div>
                            </div>
                        </div>
                        <div class="card-action center-align">
                            <button type="button" class="waves-effect waves-light btn" onclick="saveCandidate();">
                                сохранить
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <c:if test="${candidate.id != 0}">
            <div class="col s6">
                <div class="card horizontal">
                    <div class="card-stacked row">
                        <c:if test="${not empty candidate.photoName}">
                            <div class="card-content center-align">
                                <span class="card-title">Фото кандидата</span>
                                <p>
                                    <br>
                                    <img src="<c:url value='/candidate/photo?name=${candidate.photoName}'/>" width="100px" height="100px"/>
                                    <br>
                                    <br>
                                </p>
                                <a class="waves-effect waves-light btn" href="<c:url value='/candidate/photo/delete?id=${candidate.photoName}&candidate_id=${candidate.id}'/>">Удалить</a>
                            </div>
                        </c:if>
                        <c:if test="${empty candidate.photoName}">
                            <form action="<c:url value='/candidate/photo?id=${candidate.id}'/>" method="post" enctype="multipart/form-data">
                                <div class="card-content center-align">
                                    <span class="card-title">Фото кандидата</span>
                                    <br>
                                    <div class="file-field input-field">
                                        <div class="btn">
                                            <span>Открыть</span>
                                            <input type="file" name="file">
                                        </div>
                                        <div class="file-path-wrapper">
                                            <input class="file-path validate" type="text">
                                        </div>
                                    </div>
                                    <br>
                                    <button type="submit" class="waves-effect waves-light btn">
                                        сохранить
                                    </button>
                                </div>
                            </form>
                        </c:if>
                    </div>
                </div>
            </div>
        </c:if>
    </div>
</div>
<script type="text/javascript" src="../js/materialize.min.js"></script>
</body>
</html>