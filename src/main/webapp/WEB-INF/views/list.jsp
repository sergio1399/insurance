<%@ page session="false"%>
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
            <th>Serie/Number</th>
            <th>Type</th>
            <th>Signed</th>
            <th>From</th>
            <th>To</th>
            <th>NDS sum</th>
            <th>Sum with NDS</th>
            <th>Action</th>
        </tr>
        </thead>

        <c:forEach var="contract" items="${contracts}">
            <tr>
                <td>
                        ${contract.serie} ${contract.number}
                </td>
                <td>${contract.contractTypeId}</td>
                <td>${contract.signDate}</td>
                <td>${contract.openDate}</td>
                <td>${contract.expirationDate}</td>
                <td>${contract.ndsSum}</td>
                <td>${contract.sumWithNds}</td>
                <td>
                    <spring:url value="/contracts/${contract.id}" var="contractUrl" />
                    <spring:url value="/contracts/${contract.id}/remove" var="removeUrl" />
                    <spring:url value="/contracts/${contract.id}/update" var="updateUrl" />

                    <button class="btn btn-info"
                            onclick="location.href='${contractUrl}'">Query</button>
                    <button class="btn btn-primary"
                            onclick="location.href='${updateUrl}'">Update</button>
                    <button class="btn btn-danger"
                            onclick="this.disabled=true;post('${removeUrl}')">Remove</button>
                </td>
            </tr>
        </c:forEach>
    </table>

</div>

<jsp:include page="../fragments/footer.jsp" />

</body>
</html>