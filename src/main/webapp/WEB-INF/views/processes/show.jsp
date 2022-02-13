<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="constants.ForwardConst" %>
<%@ page import="constants.AttributeConst" %>

<c:set var="actTop" value="${ForwardConst.ACT_TOP.getValue()}" />
<c:set var="actPr" value="${ForwardConst.ACT_PR.getValue()}" />
<c:set var="actReci" value="${ForwardConst.ACT_RECI.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var="commEdt" value="${ForwardConst.CMD_EDIT.getValue()}" />
<c:set var="commShow" value="${ForwardConst.CMD_SHOW.getValue()}" />
<c:set var="commEdtPr" value="${ForwardConst.CMD_EDIT_PR.getValue()}" />
<head>
<script src="/patisserie_manager/js/thshow.js"></script>
</head>

<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">

        <h3>
            <span class="back-page"><a href="<c:url value='?action=${actTop}&command=${commIdx}' />">Top</a></span>
            <span class="back-page">></span>
            <span class="back-page"><a href="<c:url value='?action=${actReci}&command=${commIdx}' />">レシピ一覧</a></span>
            <span class="back-page">></span>
            <span class="back-page"><a href="<c:url value='?action=${actReci}&command=${commShow}&id=${recipe.id}' />">${recipe.title}</a></span>
            <span class="back-page">></span>
            工程

            <c:if test="${sessionScope.login_employee.adminFlag == AttributeConst.ROLE_ADMIN.getIntegerValue()}">
             <span class="new-can-admin"><a href="<c:url value='?action=${actReci}&command=${commEdtPr}&id=${recipe.id}' />">工程編集</a></span>
            </c:if>
        </h3>
        <br /><br />

        <h3>${recipe.title}</h3>
        <br />

        <table id="recipe_detail">
            <tbody>
                <c:forEach var="processRecipe" items="${recipe.processRecipe}">
                    <tr>
                       <th class="pr"></th>
                    </tr>
                    <tr>
                        <td class="pr">
                            <c:out value="${processRecipe.description}" />
                        </td>
                    </tr>

                    <tr>
                        <th class="ut">調理器具</th>
                        <th class="temp">温度</th>
                        <th class="speed">速度</th>
                        <th class="time">時間</th>
                    </tr>
                    <tr>
                        <td class="ut">
                            <c:out value="${processRecipe.utensil}" />&nbsp;
                        </td>
                        <td class="temp">
                             <c:out value="${processRecipe.temperature}" />&nbsp;℃
                        </td>
                        <td class="speed">
                             <c:out value="${processRecipe.speed}" />&nbsp;速
                        </td>
                        <td class="time">
                             <c:out value="${processRecipe.time}" />&nbsp;分
                        </td>
                    </tr>
                    <th class=blank>&nbsp;</th>
                </c:forEach>
            </tbody>
        </table>
    </c:param>
</c:import>


