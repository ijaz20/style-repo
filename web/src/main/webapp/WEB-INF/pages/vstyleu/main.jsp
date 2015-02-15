<%@ include file="/common/taglibs.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>

<%--<section>--%>
	<%--<div class="container">--%>
		<%--<div class="row">--%>
			<%--<div id="withFilterContent">--%>
				<%--<div class="col-sm-3">--%>
					<%--<jsp:include page="/WEB-INF/pages/vstyleu/product-filters.jsp" />--%>
				<%--</div>--%>

				<%--<div class="col-sm-6 padding-right">--%>
					<%--<decorator:body />--%>
				<%--</div>--%>
				<%--<div class="col-sm-3 padding-right">--%>
					<%--<div id="cart">--%>
                        <%--<jsp:include page="/WEB-INF/pages/vstyleu/cart-detail.jsp" />--%>
					<%--</div>--%>
				<%--</div>--%>
			<%--</div>--%>
			<%--<div id="withoutFilterContent" class="hide">--%>
				<%--<div class="col-sm-12 padding-right">--%>
					<%--<decorator:body />--%>
				<%--</div>--%>
			<%--</div>--%>
		<%--</div>--%>
	<%--</div>--%>
<%--</section>--%>
<div class="container content_sections pad-right">
    <%-- <jsp:include page="/WEB-INF/pages/vstyleu/product-filters.jsp" /> --%>
    <decorator:body />

</div>