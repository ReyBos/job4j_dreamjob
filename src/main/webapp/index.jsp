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
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
            integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>

    <title>Dream Job!</title>
</head>
<body>
    <div class="container pt-3">
        <div class="row">
            <%@ include file="_menu.jsp" %>
        </div>
        <div class="row pt-3">
            <div class="card" style="width: 100%">
                <div class="card-header">
                    Сегодняшние вакансии.
                </div>
                <div class="card-body">
                    <table class="table">
                        <thead>
                        <tr>
                            <th scope="col">Объявления</th>
                            <th scope="col">Описание</th>
                            <th scope="col">Дата создания</th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="post" items="${posts}">
                            <tr>
                                <td><c:out value="${post.name}"/></td>
                                <td><c:out value="${post.description}"/></td>
                                <td><c:out value="${post.formattedCreated}"/></td>
                                <td>
                                    <a href='<c:url value="/post/edit.do?id=${post.id}"/>'>
                                        <i class="fa fa-edit mr-3"></i>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <div class="row pt-3">
            <div class="card" style="width: 100%">
                <div class="card-header">
                    Сегодняшние кандидаты.
                </div>
                <div class="card-body">
                    <table class="table">
                        <thead>
                        <tr>
                            <th scope="col">Имя</th>
                            <th scope="col">Фото</th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="candidate" items="${candidates}">
                            <tr>
                                <td><c:out value="${candidate.name}"/></td>
                                <td>
                                    <c:if test="${not empty candidate.photoName}">
                                        <a href="<c:url value='/candidate/photo?name=${candidate.photoName}'/>">
                                            <img src="<c:url value='/candidate/photo?name=${candidate.photoName}'/>" width="100px" height="100px"/>
                                        </a>
                                    </c:if>
                                </td>
                                <td>
                                    <a href='<c:url value="/candidate/edit.do?id=${candidate.id}"/>'>
                                        <i class="fa fa-edit mr-3"></i>
                                    </a>
                                    <a href='<c:url value="/candidate/delete?id=${candidate.id}"/>'>
                                        <i class="fa fa-trash mr-3"></i>
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