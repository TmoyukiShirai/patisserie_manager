<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="constants.ForwardConst"%>
<%@ page import="constants.AttributeConst"%>

<c:set var="actTop" value="${ForwardConst.ACT_TOP.getValue()}" />
<c:set var="actReci" value="${ForwardConst.ACT_RECI.getValue()}" />
<c:set var="actIngr" value="${ForwardConst.ACT_INGR.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var="commShow" value="${ForwardConst.CMD_SHOW.getValue()}" />
<c:set var="commNew" value="${ForwardConst.CMD_NEW.getValue()}" />
<c:set var="commEdit" value="${ForwardConst.CMD_EDIT.getValue()}" />
<c:set var="commSearch" value="${ForwardConst.CMD_SEARCH.getValue()}" />

<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <br>
        <div id=ingredient-list>
            <h3>
                <span class="back-page"><a
                    href="<c:url value='?action=${actTop}&command=${commIdx}' />">Top</a></span>
                <span class="back-page">></span> 食材一覧
                <div id="search-wrap">
                    <form method="POST" action="<c:url value='/?action=${actIngr}&command=${commSearch}' />" autocomplete="on">
                    <input id="search" name="${AttributeConst.SEARCH.getValue()}" value="${id}" type="text" placeholder="食材名で検索"><input id="search-submit" value="Rechercher" type="submit">
                    </form>
                </div>
                <c:if
                    test="${sessionScope.login_employee.adminFlag == AttributeConst.ROLE_ADMIN.getIntegerValue()}">
                    <span class="new-can-admin"><a href="<c:url value='?action=${actIngr}&command=${commNew}' />">食材新規登録</a></span>
                </c:if>
            </h3>
            <br>
            <table id="ingredient_list">
                <tbody>
                    <tr>
                        <th class="ingredient_name">食材名</th>
                        <th class="ingredient_maker">メーカー</th>
                        <th class="ingredient_saler">販売店</th>
                        <th class="ingredient_price">単価</th>
                        <th class="ingredient_amount">内容量</th>
                        <th class="ingredient_energy">エネルギー(100gあたり)</th>
                        <th class="allergy_item">アレルギー</th>
                        <c:if test="${sessionScope.login_employee.adminFlag == AttributeConst.ROLE_ADMIN.getIntegerValue()}">
                            <th class="ingredient_edit"></th>
                        </c:if>

                    </tr>
                    <c:forEach var="ingredient" items="${ingredients}" varStatus="status">
                        <tr class="row${status.count % 2}">
                            <td class="ingredient_name">${ingredient.name}</td>
                            <td class="ingredient_maker">${ingredient.maker}</td>
                            <td class="ingredient_saler">${ingredient.saler}</td>
                            <td class="ingredient_price">${ingredient.price}円</td>
                            <td class="ingredient_amount">${ingredient.amount}${ingredient.amount_u}</td>
                            <td class="ingredient_energy">${ingredient.energy}<br />kcal</td>
                            <td class="allergy_item">
                                <c:forEach var="allergy" items="${ingredient.allergy}">
                                    <li>
                                        ${allergy.item.itemName} <!-- リストに紐づいたAllergyクラスの中のitemに紐づいたAllergyItemクラスのitemName -->
                                    </li>
                                </c:forEach></td>

                            <c:if test="${sessionScope.login_employee.adminFlag == AttributeConst.ROLE_ADMIN.getIntegerValue()}">
                                <td class="ingredient_edit">
                                    <a href="<c:url value='?action=${actIngr}&command=${commEdit}&id=${ingredient.id}' />">編集する</a>
                                </td>
                            </c:if>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <div id="pagination">
            （全 ${ingredients_count} 件）<br />
            <c:forEach var="i" begin="1" end="${((ingredients_count - 1) / maxRow) + 1}" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='?action=${actIngr}&command=${commIdx}&page=${i}' />"><c:out value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
    </c:param>
</c:import>
