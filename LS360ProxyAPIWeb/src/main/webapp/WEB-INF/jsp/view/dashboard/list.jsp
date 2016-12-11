<spring:message code="title.roleList" var="listRoles" />
<template:basic htmlTitle="${listRoles}" bodyTitle="${listRoles}">
    <c:choose>
        <c:when test="${fn:length(ticketDatabase) == 0}">
            <i><spring:message code="message.ticketList.none" /></i>
        </c:when>
        <c:otherwise>
            <c:forEach items="${tickets}" var="ticket">
                <spring:message code="message.ticketList.ticket" />&nbsp;${ticket.id}:
                <a href="<c:url value="/ticket/view/${ticket.id}" />">
                <c:out value="acv"/>
                </a><br />
                <c:out value="${ticket.customerName}" />&nbsp;
                <spring:message code="message.ticketList.created" />&nbsp;
                <br />
            </c:forEach>
        </c:otherwise>
    </c:choose>
</template:basic>
