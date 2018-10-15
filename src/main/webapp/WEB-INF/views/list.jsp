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

    <h1>Список договоров</h1>

    <table id="listTable" class="table table-striped table-hover">
        <thead>
        <tr>
            <th class="th-common">Серия-номер</th>
            <th class="th-common">Тип договора</th>
            <th class="th-common">Дата подписания</th>
            <th class="th-common">Начало действия</th>
            <th class="th-common">Конец действия</th>
            <th class="th-common">Сумма без НДС, руб</th>
            <th class="th-common">Ставка НДС, %</th>
            <th class="th-common">Сумма НДС, руб</th>
            <th class="th-common">Сумма с НДС, руб</th>
            <th class="th-common">Соответствие мин сумме</th>
            <th class="th-common">Номер ТС</th>
            <th class="th-common" colspan="3">Действия</th>
        </tr>
        <tr>
            <td><input type="text" class="form-control" name="filterSN" value="" id="filterSN" /></td>
            <td>
                <select class="form-control" id="filterType">
                    <option>ВСЕ</option>
                    <c:forEach var="contractType" items="${typeList.keySet()}">
                        <option>${contractType}</option>
                    </c:forEach>
                </select>
            </td>
            <td><input type="text" class="form-control" name="filterSign" value="" id="filterSign" /></td>
            <td><input type="text" class="form-control" name="filterOpen" value="" id="filterOpen" /></td>
            <td><input type="text" class="form-control" name="filterExp" value="" id="filterExp" /></td>
            <td><input type="text" class="form-control" name="filterSumNoNds" value="" id="filterSumNoNds" /></td>
            <td><input type="text" class="form-control" name="filterRste" value="" id="filterRate" /></td>
            <td><input type="text" class="form-control" name="filterSumNds" value="" id="filterSumNds" /></td>
            <td><input type="text" class="form-control" name="filterSumWithNds" value="" id="filterSumWithNds" /></td>
            <td><input type="text" class="form-control" name="filterAccord" value="" id="filterAccord" /></td>
            <td><input type="text" class="form-control" name="filterTS" value="" id="filterTS" /></td>
            <td colspan="3"></td>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="contract" items="${contracts}">
            <tr>
                <td class="td-common" id="seriaNumber">${contract.serie}-${contract.number}</td>
                <td class="td-common" id="contractType">${contract.type}</td>
                <td class="td-common" id="signDate">${contract.signDate}</td>
                <td class="td-common" id="openDate">${contract.openDate}</td>
                <td class="td-common" id="expDate">${contract.expirationDate}</td>
                <td class="td-common" id="sumNoNds">${contract.sumNoNds}</td>
                <td class="td-common" id="ndsRate">${contract.ndsRate}</td>
                <td class="td-common" id="ndsSum">${contract.ndsSum}</td>
                <td class="td-common" id="sumWithNds">${contract.sumWithNds}</td>
                <td class="td-common" id="accord">${contract.accord}</td>
                <td class="td-common" id="vehicle">${contract.vehicleNumber}</td>
                <td class="td-common">
                    <spring:url value="/contracts/${contract.id}" var="contractUrl" />
                    <button class="btn btn-default"
                            onclick="location.href='${contractUrl}'">
                        <img src="/resources/pics/view.png" />
                    </button>
                </td>
                <td class="td-common">
                    <spring:url value="/contracts/${contract.id}/update" var="updateUrl" />
                    <button class="btn btn-default"
                            onclick="location.href='${updateUrl}'">
                        <img src="/resources/pics/update.png">
                    </button>
                </td>
                <td class="td-common">
                    <spring:url value="/contracts/${contract.id}/remove" var="removeUrl" />
                    <button class="btn btn-default"
                            onclick="this.disabled=true;post('${removeUrl}')">
                        <img src="/resources/pics/remove.png">
                    </button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</div>

<jsp:include page="../fragments/footer.jsp" />

<script>
    $(document).ready(function() {
        //zebraRows('tbody tr:odd td', 'odd');
    });
</script>


</body>
</html>