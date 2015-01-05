<%@ include file="/WEB-INF/jsp/include.jsp" %>

<c:url var="englishLocaleUrl" value="/emp/clients.htm">
    <c:param name="lang" value="en"/>
</c:url>
<c:url var="polishLocaleUrl" value="/emp/clients.htm">
    <c:param name="lang" value="pl"/>
</c:url>
<a href="${englishLocaleUrl}">EN</a>
<a href="${polishLocaleUrl}">PL</a>