<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="constants.ForwardConst" %>
<%@ page import="constants.AttributeConst"%>

<c:set var="actPr" value="${ForwardConst.ACT_PR.getValue()}" />
<c:set var="actTop" value="${ForwardConst.ACT_TOP.getValue()}" />
<c:set var="actReci" value="${ForwardConst.ACT_RECI.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var="commUpd" value="${ForwardConst.CMD_UPDATE.getValue()}" />
<c:set var="commUpdPr" value="${ForwardConst.CMD_UPDATE_PR.getValue()}" />
<c:set var="commShow" value="${ForwardConst.CMD_SHOW.getValue()}" />
<c:set var="commDelPr" value="${ForwardConst.CMD_DESTROY_PR.getValue()}" />

<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">

    <h3>
        <span class="back-page"><a href="<c:url value='?action=${actTop}&command=${commIdx}' />">Top</a></span>
        <span class="back-page">></span>
        <span class="back-page"><a href="<c:url value='?action=${actReci}&command=${commIdx}' />">レシピ一覧</a></span>
        <span class="back-page">></span>
        <span class="back-page"><a href="<c:url value='?action=${actReci}&command=${commShow}&id=${recipe.id}' />">${recipe.title}</a></span>
        <span class="back-page"><a href="<c:url value='?action=${actPr}&command=${commShow}&id=${process.id}' />">${process.r_title}</a></span>
        <span class="back-page">></span>
        工程編集（${recipe.title}）
    </h3>
    <br />
    <p class="create">工程編集ページ</p>
    <form method="POST" action="<c:url value='?action=${actReci}&command=${commUpdPr}&id=${recipe.id}' />">
        <c:import url="_form.jsp" />
    </form>

    <p>
        <a href="#" onclick="confirmDestroy();">この工程情報を削除する</a>
    </p>
    <form method="POST" action="<c:url value='?action=${actReci}&command=${commDelPr}' />">
        <input type="hidden" name="${AttributeConst.RECI_ID.getValue()}" value="${recipe.id}" />
        <input type="hidden" name="${AttributeConst.TOKEN.getValue()}" value="${_token}" />
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
