<%@ page session="false"%>
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
                <span aria-hidden="true">×</span>
            </button>
            <strong>${msg}</strong>
        </div>
    </c:if>

    <h1>Contract Detail</h1>
    <br />

    <div class="row">
        <label class="col-sm-2">Serie/Number</label>
        <div class="col-sm-10">${contract.serie()} ${contract.number()}</div>
    </div>

    <div class="row">
        <label class="col-sm-2">Type</label>
        <div class="col-sm-10">${contract.contractTypeId()}</div>
    </div>

    <div class="row">
        <label class="col-sm-2">Signed</label>
        <div class="col-sm-10">${contract.signDate}</div>
    </div>

    <div class="row">
        <label class="col-sm-2">From</label>
        <div class="col-sm-10">${contract.openDate}</div>
    </div>

    <div class="row">
        <label class="col-sm-2">To</label>
        <div class="col-sm-10">${contract.expirationDate}</div>
    </div>

    <div class="row">
        <label class="col-sm-2">NDS sum</label>
        <div class="col-sm-10">${contract.ndsSum}</div>
    </div>

    <div class="row">
        <label class="col-sm-2">Sum with NDS</label>
        <div class="col-sm-10">${contract.sumWithNds}</div>
    </div>

</div>

<jsp:include page="../fragments/footer.jsp" />

</body>
</html>