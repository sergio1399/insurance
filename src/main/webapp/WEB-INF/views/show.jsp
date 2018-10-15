<%@ page session="false" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="../fragments/header.jsp" />

<div class="container">

    <c:if test="${not empty msg}">
        <div class="alert alert-${css} alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert"
                    aria-label="Close">
                <span aria-hidden="true">x</span>
            </button>
            <strong>${msg}</strong>
        </div>
    </c:if>

    <h1>Детали договора</h1>
    <br />

    <div class="row">
        <label class="col-sm-2">Серия/Номер</label>
        <div class="col-sm-10">${contract.serie}-${contract.number}</div>
    </div>

    <div class="row">
        <label class="col-sm-2">Тип договора</label>
        <div class="col-sm-10">${contract.type}</div>
    </div>

    <div class="row">
        <label class="col-sm-2">Дата подписания</label>
        <div class="col-sm-10">${contract.signDate}</div>
    </div>

    <div class="row">
        <label class="col-sm-2">Действует от</label>
        <div class="col-sm-10">${contract.openDate}</div>
    </div>

    <div class="row">
        <label class="col-sm-2">Действует до</label>
        <div class="col-sm-10">${contract.expirationDate}</div>
    </div>

    <div class="row">
        <label class="col-sm-2">Сумма НДС</label>
        <div class="col-sm-10">${contract.ndsSum}</div>
    </div>

    <div class="row">
        <label class="col-sm-2">Сумма с НДС</label>
        <div class="col-sm-10">${contract.sumWithNds}</div>
    </div>

    <div class="row">
        <label class="col-sm-2">Номер ТС</label>
        <div class="col-sm-10">${contract.vehicleNumber}</div>
    </div>

    <div class="row">
        <label class="col-sm-2">Примечание</label>
        <div class="col-sm-10">${contract.note}</div>
    </div>

</div>

<jsp:include page="../fragments/footer.jsp" />

</body>
</html>