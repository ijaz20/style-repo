<%@ include file="/common/taglibs.jsp" %>

<head>
    <title><fmt:message key="schedules.title"/></title>
    <meta name="menu" content="Booking"/>
</head>

<div class="col-sm-10">
    <h2><fmt:message key="schedules.heading"/></h2>

    <form method="get" action="${ctx}/branch/schedules" id="searchForm" class="form-inline">
    <div id="search" class="text-right">
        <span class="col-sm-9">
            <input type="text" size="20" name="q" id="query" value="${param.q}"
                   placeholder="<fmt:message key="search.enterTerms"/>" class="form-control input-sm"/>
        </span>
        <button id="button.search" class="btn btn-default btn-sm" type="submit">
            <i class="icon-search"></i>
            <fmt:message key="button.search"/>
        </button>
    </div>
    </form>

    <div id="actions" class="btn-group">
        <a class="btn btn-primary" href="<c:url value='/branch/showBooking?method=Add&from=list'/>" >
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/>
        </a>
        <a class="btn btn-default" href="<c:url value="/branch/home"/>">
            <i class="icon-ok"></i> <fmt:message key="button.done"/>
        </a>
    </div>

    <display:table name="bookings" cellspacing="0" cellpadding="0" requestURI=""
                   defaultsort="1" id="bookings" pagesize="25" class="table table-condensed table-striped table-hover" export="true">
        <display:column property="user.fullName" escapeXml="true" sortable="true" titleKey="schedules.userName" style="width: 25%"
                        url="/branch/showBooking?from=list" paramId="id" paramProperty="id"/>
        <display:column property="user.phoneNumber" escapeXml="true" sortable="true" titleKey="schedules.phoneNumber" style="width: 25%"/>
        <display:column property="startTime" escapeXml="true" sortable="true" titleKey="schedules.startTime" style="width: 25%"/>
        <display:column property="endTime" escapeXml="true" sortable="true" titleKey="schedules.endTime" style="width: 25%"/>
        <display:setProperty name="paging.banner.item_name"><fmt:message key="schedules.schedules"/></display:setProperty>
        <display:setProperty name="paging.banner.items_name"><fmt:message key="schedules.schedules"/></display:setProperty>

        <display:setProperty name="export.excel.filename" value="schedules.xls"/>
        <display:setProperty name="export.csv.filename" value="schedules.csv"/>
        <display:setProperty name="export.pdf.filename" value="schedules.pdf"/>
    </display:table>
</div>
