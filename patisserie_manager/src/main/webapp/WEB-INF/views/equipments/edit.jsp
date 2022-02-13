<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="constants.ForwardConst" %>
<%@ page import="constants.AttributeConst"%>

<c:set var="actTop" value="${ForwardConst.ACT_TOP.getValue()}" />
<c:set var="actEq" value="${ForwardConst.ACT_EQ.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var="commShow" value="${ForwardConst.CMD_SHOW.getValue()}" />
<c:set var="commUpd" value="${ForwardConst.CMD_UPDATE.getValue()}" />
<c:set var="commDel" value="${ForwardConst.CMD_DESTROY.getValue()}" />


<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">

    <h3>
        <span class="back-page"><a href="<c:url value='?action=${actTop}&command=${commIdx}' />">Top</a></span>
        <span class="back-page">></span>
        <span class="back-page"><a href="<c:url value='?action=${actEq}&command=${commIdx}' />">備品一覧</a></span>
        <span class="back-page">></span>
        備品編集（${equipment.name}）
    </h3>
    <br />
    <p class="create">備品編集ページ</p>
    <form method="POST" action="<c:url value='?action=${actEq}&command=${commUpd}' />">
        <c:import url="_form.jsp" />
    </form>

    <p>
            <a href="#" onclick="confirmDestroy();">この備品情報を削除する</a>
        </p>
        <form method="POST"
            action="<c:url value='?action=${actEq}&command=${commDel}' />">
            <input type="hidden" name="${AttributeConst.EQ_ID.getValue()}"
                value="${equipment.id}" /> <input type="hidden"
                name="${AttributeConst.TOKEN.getValue()}" value="${_token}" />
        </form>
        <script>
            function confirmDestroy() {
                if (confirm("本当に削除してよろしいですか？")) {
                    document.forms[1].submit();
                }
            }
        </script>

    </c:param>
</c:import>
