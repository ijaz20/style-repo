<%@ page language="java" isErrorPage="true" %>


<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page"%>

<page:applyDecorator name="branch">
    <head>
        <title><fmt:message key='404.title'/></title>
        <meta name="heading" content="<fmt:message key='404.title'/>"/>
    </head>
    <body>
    <div class="error-page-wrap">
        <%--<img src="/images/404/404-error.png" alt="Back To Home." title="Back To Home."/>--%>
        <a class="btn btn--red home-button mtop" href="https://www.vstyleu.com">Back to home</a>
    </div>
    <script type="text/javascript">
        window.onload = function() {
            setTimeout('window.location="/";', 15 * 1000);
        };
    </script>
    </body>
</page:applyDecorator>