<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="../fragments/header.jsp" />

<div class="container">

    <c:choose>
        <c:when test="${contractForm['new']}">
            <h1>Add Contract</h1>
        </c:when>
        <c:otherwise>
            <h1>Update Contract</h1>
        </c:otherwise>
    </c:choose>
    <br />

    <spring:url value="/contracts" var="contractActionUrl" />

    <form:errors path="*" cssClass="errorblock" element="div"/>

    <%--@elvariable id="contractForm" type="sergio"--%>
    <form:form class="form-horizontal" method="post"
               modelAttribute="contractForm" action="${contractActionUrl}">

        <form:hidden path="id" />

        <spring:bind path="serie">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">Serie</label>
                <div class="col-sm-10">
                    <form:input path="serie" type="text" class="form-control"
                                id="serie" placeholder="serie" />
                    <form:errors path="serie" class="control-label" />
                </div>
            </div>
        </spring:bind>

        <spring:bind path="number">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">Number</label>
                <div class="col-sm-10">
                    <form:input path="number" type="text" class="form-control"
                                id="number" placeholder="number" />
                    <form:errors path="number" class="control-label" />
                </div>
            </div>
        </spring:bind>

        <spring:bind path="type">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">Type</label>
                <div class="col-sm-10">
                    <form:input path="type" type="text" class="form-control"
                                   id="type" placeholder="type" />
                    <form:errors path="type" class="type" />
                </div>
            </div>
        </spring:bind>

        <spring:bind path="signDate">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">Signed</label>
                <div class="col-sm-10">
                    <form:input path="signDate" class="form-control"
                                id="signDate" placeholder="signDate" />
                    <form:errors path="signDate" class="control-label" />
                </div>
            </div>
        </spring:bind>

        <spring:bind path="openDate">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">Open Date</label>
                <div class="col-sm-10">
                    <form:input path="openDate" class="form-control"
                                   id="openDate" placeholder="openDate" />
                    <form:errors path="openDate" class="control-label" />
                </div>
            </div>
        </spring:bind>

        <spring:bind path="expirationDate">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">End Date</label>
                <div class="col-sm-10">
                    <form:input path="expirationDate" class="form-control"
                                id="expirationDate" placeholder="expirationDate" />
                    <form:errors path="expirationDate" class="control-label" />
                </div>
            </div>
        </spring:bind>

        <spring:bind path="ndsSum">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">NDS Sum</label>
                <div class="col-sm-10">
                    <form:input path="ndsSum" type="number" class="form-control"
                                id="ndsSum" placeholder="ndsSum" />
                    <form:errors path="ndsSum" class="control-label" />
                </div>
            </div>
        </spring:bind>

        <spring:bind path="sumWithNds">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">Sum with NDS</label>
                <div class="col-sm-10">
                    <form:input path="sumWithNds" type="number" class="form-control"
                                id="sumWithNds" placeholder="sumWithNds" />
                    <form:errors path="sumWithNds" class="control-label" />
                </div>
            </div>
        </spring:bind>

        <spring:bind path="vehicleNumber">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">Vehicle Number</label>
                <div class="col-sm-10">
                    <form:input path="vehicleNumber" type="text" class="form-control"
                                id="vehicleNumber" placeholder="vehicleNumber" />
                    <form:errors path="vehicleNumber" class="control-label" />
                </div>
            </div>
        </spring:bind>

        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${contractForm['new']}">
                        <button type="submit" class="btn-lg btn-primary pull-right">Add
                        </button>
                    </c:when>
                    <c:otherwise>
                        <button type="submit" class="btn-lg btn-primary pull-right">Update
                        </button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </form:form>

</div>

<jsp:include page="../fragments/footer.jsp" />

</body>
</html>