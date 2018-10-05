<%@ page session="false" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="../fragments/header.jsp" />

<body>

<div class="container">

    <c:if test="${not empty msg}">
        <div class="alert alert-${css} alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert"
                    aria-label="Close">
                <span aria-hidden="true">×</span>
            </button>
            <strong>${msg}</strong>
        </div>
    </c:if>

    <h1>All Contracts</h1>

    <table class="table table-striped">
        <thead>
        <tr>
            <th>Серия-номер</th>
            <th>Тип договора</th>
            <th>Дата подписания</th>
            <th>Начало действия</th>
            <th>Конец действия</th>
            <th>Сумма без НДС, руб</th>
            <th>Ставка НДС, %</th>
            <th>Сумма НДС, руб</th>
            <th>Сумма с НДС, руб</th>
            <th>Соответствие мин сумме</th>
            <th>Номер ТС</th>
            <th>Действия</th>
        </tr>
        </thead>
        <c:forEach var="contract" items="${contracts}">
            <tr>
                <td>${contract.serie}-${contract.number}</td>
                <td>${contract.type}</td>
                <td>${contract.signDate}</td>
                <td>${contract.openDate}</td>
                <td>${contract.expirationDate}</td>
                <td>${contract.sumNoNds}</td>
                <td>${contract.ndsRate}</td>
                <td>${contract.ndsSum}</td>
                <td>${contract.sumWithNds}</td>
                <td>${contract.minSumAccord}</td>
                <td>${contract.vehicleNumber}</td>
                <td>
                    <spring:url value="/contracts/${contract.id}" var="contractUrl" />
                    <spring:url value="/contracts/${contract.id}/remove" var="removeUrl" />
                    <spring:url value="/contracts/${contract.id}/update" var="updateUrl" />
                    <button class="btn btn-info"
                            onclick="location.href='${contractUrl}'">Просмотреть</button>
                    <button class="btn btn-primary"
                            onclick="location.href='${updateUrl}'">Изменить</button>
                    <button class="btn btn-danger"
                            onclick="this.disabled=true;$.post('${removeUrl}')">Удалить</button>
                </td>
            </tr>
        </c:forEach>
    </table>

</div>

<jsp:include page="../fragments/footer.jsp" />

</body>
</html>