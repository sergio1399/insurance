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

    <table id="listTable" class="table table-striped">
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
        <tr>
            <td><input type="text" class="input-filter" name="filterSN" value="" id="filterSN" /></td>
            <td>
                <select class="input-filter" id="filterType">
                    <option>ALL</option>
                    <c:forEach var="contractType" items="${typeList.keySet()}">
                        <option>${contractType}</option>
                    </c:forEach>
                </select>
            </td>
            <td><input type="text" class="input-filter" name="filterSign" value="" id="filterSign" /></td>
            <td><input type="text" class="input-filter" name="filterOpen" value="" id="filterOpen" /></td>
            <td><input type="text" class="input-filter" name="filterExp" value="" id="filterExp" /></td>
            <td><input type="text" class="input-filter" name="filterSumNoNds" value="" id="filterSumNoNds" /></td>
            <td><input type="text" class="input-filter" name="filterRste" value="" id="filterRate" /></td>
            <td><input type="text" class="input-filter" name="filterSumNds" value="" id="filterSumNds" /></td>
            <td><input type="text" class="input-filter" name="filterSumWithNds" value="" id="filterSumWithNds" /></td>
            <td><input type="text" class="input-filter" name="filterAccord" value="" id="filterAccord" /></td>
            <td><input type="text" class="input-filter" name="filterTS" value="" id="filterTS" /></td>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="contract" items="${contracts}">
            <tr>
                <td id="seriaNumber">${contract.serie}-${contract.number}</td>
                <td id="contractType">${contract.type}</td>
                <td id="signDate">${contract.signDate}</td>
                <td id="openDate">${contract.openDate}</td>
                <td id="expDate">${contract.expirationDate}</td>
                <td id="sumNoNds">${contract.sumNoNds}</td>
                <td id="ndsRate">${contract.ndsRate}</td>
                <td id="ndsSum">${contract.ndsSum}</td>
                <td id="sumWithNds">${contract.sumWithNds}</td>
                <td id="accord">${contract.minSumAccord}</td>
                <td id="vehicle">${contract.vehicleNumber}</td>
                <td>
                    <spring:url value="/contracts/${contract.id}" var="contractUrl" />
                    <spring:url value="/contracts/${contract.id}/remove" var="removeUrl" />
                    <spring:url value="/contracts/${contract.id}/update" var="updateUrl" />
                    <button class="btn btn-info"
                            onclick="location.href='${contractUrl}'">Просмотреть</button>
                    <button class="btn btn-primary"
                            onclick="location.href='${updateUrl}'">Изменить</button>
                    <button class="btn btn-danger"
                            onclick="this.disabled=true;$.post('${removeUrl}', function() {
                                    location.reload(true);
                                    })">Удалить</button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</div>

<jsp:include page="../fragments/footer.jsp" />

<script>
    $(document).ready(function() {
        zebraRows('tbody tr:odd td', 'odd');
    });
</script>


</body>
</html>