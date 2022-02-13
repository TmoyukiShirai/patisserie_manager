<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="constants.ForwardConst"%>
<%@ page import="constants.AttributeConst"%>

<c:set var="actTop" value="${ForwardConst.ACT_TOP.getValue()}" />
<c:set var="actReci" value="${ForwardConst.ACT_RECI.getValue()}" />
<c:set var="actPr" value="${ForwardConst.ACT_PR.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var="commShow" value="${ForwardConst.CMD_SHOW.getValue()}" />
<c:set var="commShowPr" value="${ForwardConst.CMD_SHOW_PR.getValue()}" />
<c:set var="commNew" value="${ForwardConst.CMD_NEW.getValue()}" />
<c:set var="commSearch" value="${ForwardConst.CMD_SEARCH.getValue()}" />

<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <br />

        <div id=recipe-list>
            <h3>
                <span class="back-page"><a
                    href="<c:url value='?action=${actTop}&command=${commIdx}' />">Top</a></span>
                <span class="back-page">></span> レシピ一覧

                <div id="search-wrap">
                    <form method="POST" action="<c:url value='/?action=${actReci}&command=${commSearch}' />" autocomplete="on">
                    <input id="search" name="${AttributeConst.SEARCH.getValue()}" value="${id}" type="text" placeholder="レシピ名で検索"><input id="search-submit" value="Rechercher" type="submit">
                    </form>
                </div>

                <c:if test="${sessionScope.login_employee.adminFlag == AttributeConst.ROLE_ADMIN.getIntegerValue()}">
                    <span class="new-can-admin"><a href="<c:url value='?action=${actReci}&command=${commNew}' />">レシピ新規作成</a></span>
                </c:if>
            </h3>
            <br />

            <table id="recipe_list">
                <tbody>
                    <tr>
                        <th class="recipe_title">レシピ名</th>
                        <th class="recipe_type">分類</th>
                        <th class="recipe_process">工程</th>
                    </tr>
                    <c:forEach var="recipe" items="${recipes}" varStatus="status">
                        <tr class="row${status.count % 2}">
                            <!-- ↓これはDBから受け取った値 -->
                            <td class="recipe_title"><a
                                href="<c:url value='?action=${actReci}&command=${commShow}&id=${recipe.id}' />">${recipe.title}</a></td>
                            <td class="recipe_type">
                            ${recipe.type}
                            </td>
                            <td class="recipe_action">
                             <c:if test="${recipe.processRecipe != '[]'}">
                            <a href="<c:url value='?action=${actReci}&command=${commShowPr}&id=${recipe.id}' />">工程を見る</a>
                            </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <div id="pagination">
            （全 ${recipes_count} 件）<br />
            <c:forEach var="i" begin="1" end="${((recipes_count - 1) / maxRow) + 1}" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='?action=${actReci}&command=${commIdx}&page=${i}' />"><c:out value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
    </c:param>
</c:import>
