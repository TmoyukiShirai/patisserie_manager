<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="constants.ForwardConst"%>
<%@ page import="constants.AttributeConst"%>

<c:set var="actTop" value="${ForwardConst.ACT_TOP.getValue()}" />
<c:set var="actEq" value="${ForwardConst.ACT_EQ.getValue()}" />
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
        <div id=equipment-list>
            <h3>
                <span class="back-page"><a
                    href="<c:url value='?action=${actTop}&command=${commIdx}' />">Top</a></span>
                <span class="back-page">></span> 備品一覧
                <div id="search-wrap">
                    <form method="POST" action="<c:url value='/?action=${actEq}&command=${commSearch}' />" autocomplete="on">
                    <input id="search" name="${AttributeConst.SEARCH.getValue()}" value="${id}" type="text" placeholder="備品名で検索"><input id="search-submit" value="Rechercher" type="submit">
                    </form>
                    </div>
                <c:if test="${sessionScope.login_employee.adminFlag == AttributeConst.ROLE_ADMIN.getIntegerValue()}">
                    <span class="new-can-admin"><a href="<c:url value='?action=${actEq}&command=${commNew}' />">備品新規登録</a></span>
                </c:if>
            </h3>
            <br>
            <table id="equipment_list">
                <tbody>
                    <tr>
                        <th class="equipment_name">備品名</th>
                        <th class="equipment_maker">メーカー</th>
                        <th class="equipment_saler">販売店</th>
                        <th class="equipment_price">単価</th>
                        <th class="equipment_amount">内容量</th>
                        <c:if
                            test="${sessionScope.login_employee.adminFlag == AttributeConst.ROLE_ADMIN.getIntegerValue()}">
                            <th class="equipment_edit"></th>
                        </c:if>
                    </tr>

                    <c:forEach var="equipment" items="${equipments}" varStatus="status">
                        <tr class="row${status.count % 2}">
                            <td class="equipment_name">${equipment.name}</td>
                            <td class="equipment_maker">${equipment.maker}</td>
                            <td class="equipment_saler">${equipment.saler}</td>
                            <td class="equipment_price">${equipment.price}円</td>
                            <td class="equipment_amount">${equipment.amount}${equipment.amount_u}</td>
                            <c:if
                                test="${sessionScope.login_employee.adminFlag == AttributeConst.ROLE_ADMIN.getIntegerValue()}">
                                <td class="equipment_edit"><a
                                    href="<c:url value='?action=${actEq}&command=${commEdit}&id=${equipment.id}' />">編集する</a>
                                </td>
                            </c:if>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <div id="pagination">
            （全 ${equipments_count} 件）<br />
            <c:forEach var="i" begin="1" end="${((equipments_count - 1) / maxRow) + 1}" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='?action=${actEq}&command=${commIdx}&page=${i}' />">
                            <c:out value="${i}" />
                        </a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
    </c:param>
</c:import>
