<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<ul class="nav">
    <c:if test="${empty user}">
        <li class="nav-item">
            <a class="nav-link" href='<c:url value="/auth.do" />'>Авторизация</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href='<c:url value="/reg.do" />'>Регистрация</a>
        </li>
    </c:if>
    <c:if test="${not empty user}">
        <li class="nav-item">
            <a class="nav-link" href='<c:url value="/index.do" />'>Главная</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href='<c:url value="/post.do" />'>Вакансии</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href='<c:url value="/candidate.do" />'>Кандидаты</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href='<c:url value="/post/edit.do" />'>Добавить вакансию</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href='<c:url value="/candidate/edit.do" />'>Добавить кандидата</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="<c:url value="/auth.do" />"> <c:out value="${user.name}"/> | Выйти</a>
        </li>
    </c:if>
</ul>