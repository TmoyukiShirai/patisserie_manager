<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="constants.ForwardConst" %>

<c:set var="actPr" value="${ForwardConst.ACT_PR.getValue()}" />
<c:set var="actTop" value="${ForwardConst.ACT_TOP.getValue()}" />
<c:set var="actReci" value="${ForwardConst.ACT_RECI.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var="commUpd" value="${ForwardConst.CMD_UPDATE.getValue()}" />
<c:set var="commShow" value="${ForwardConst.CMD_SHOW.getValue()}" />
<c:set var="commCrtPr" value="${ForwardConst.CMD_CREATE_PR.getValue()}" />


<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">

    <h3>
        <span class="back-page"><a href="<c:url value='?action=${actTop}&command=${commIdx}' />">Top</a></span>
        <span class="back-page">></span>
        <span class="back-page"><a href="<c:url value='?action=${actReci}&command=${commIdx}' />">レシピ一覧</a></span>
        <span class="back-page">></span>
        <span class="back-page"><a href="<c:url value='?action=${actReci}&command=${commShow}&id=${recipe.id}' />">${recipe.title}</a></span>
        <span class="back-page">></span>
        工程作成
    </h3>
    <br />
    <p class="create">工程作成ページ</p>
    <form method="POST" action="<c:url value='?action=${actReci}&command=${commCrtPr}&id=${recipe.id}' />">
        <c:import url="_form.jsp" />
    </form>
    </c:param>
</c:import>
