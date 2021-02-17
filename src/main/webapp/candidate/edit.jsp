<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!doctype html>
<html lang="ru">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
            integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
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

<div class="container pt-3">
    <div class="row">
        <%@ include file="../_menu.jsp" %>
    </div>
    <div class="row pt-3">
        <div class="card" style="width: 100%">
            <div class="card-header">
                <c:if test="${candidate.id == 0}">
                    Новый кандидат.
                </c:if>
                <c:if test="${candidate.id != 0}">
                    Редактирование кандидата.
                </c:if>
            </div>
            <div class="card-body row">
                <div class="col-5">
                    <form class="js-candidate" action="<c:url value='/candidate/save?id=${candidate.id}'/>" method="post">
                        <div class="form-group">
                            <label>Имя</label>
                            <input type="text" name="name" class="form-control js-name" value="<c:out value="${candidate.name}"/>">
                        </div>
                        <div class="form-group">
                            <label>Город</label>
                            <select class="form-control js-city" name="cityId">
                                <option value="0">Выберите город</option>
                            </select>
                        </div>
                        <button type="button" class="btn btn-primary" onclick="saveCandidate()">Сохранить</button>
                    </form>
                </div>
                <div class="col-5">
                    <c:if test="${not empty candidate.photoName}">
                        <p class="card-text">
                            <img src="<c:url value='/candidate/photo?name=${candidate.photoName}'/>" width="100px" height="100px"/>
                        </p>
                        <a class="btn btn-primary" href="<c:url value='/candidate/photo/delete?id=${candidate.photoName}&candidate_id=${candidate.id}'/>">Удалить фото</a>
                    </c:if>
                    <c:if test="${empty candidate.photoName}">
                        <form action="<c:url value='/candidate/photo?id=${candidate.id}'/>" method="post" enctype="multipart/form-data">
                            <div class="form-group">
                                <label>Фото</label>
                                <input type="file" name="file" class="form-control">
                            </div>
                            <button type="submit" class="btn btn-primary">Сохранить</button>
                        </form>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>