<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="constants.ForwardConst"%>
<%@ page import="constants.AttributeConst"%>

<c:set var="actTop" value="${ForwardConst.ACT_TOP.getValue()}" />
<c:set var="actOrder" value="${ForwardConst.ACT_ORDER.getValue()}" />
<c:set var="actReci" value="${ForwardConst.ACT_RECI.getValue()}" />
<c:set var="actIngr" value="${ForwardConst.ACT_INGR.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var="commShow" value="${ForwardConst.CMD_SHOW.getValue()}" />
<c:set var="commShowOdh" value="${ForwardConst.CMD_SHOW_ODH.getValue()}" />
<c:set var="commNew" value="${ForwardConst.CMD_NEW.getValue()}" />
<c:set var="commEdit" value="${ForwardConst.CMD_EDIT.getValue()}" />
<c:set var="commSearch" value="${ForwardConst.CMD_SEARCH.getValue()}" />
<c:set var="actUnfinished" value="${ForwardConst.ACT_UNFINISHED.getValue()}" />
<c:set var="commProcess" value="${ForwardConst.CMD_PROCESS.getValue()}" />

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
                <span class="back-page"><a href="<c:url value='?action=${actTop}&command=${commIdx}' />">Top</a></span>
                <span class="back-page">></span>
                <span class="back-page"><a href="<c:url value='?action=${actOrder}&command=${commIdx}' />">発注書メニュー</a></span>
                <span class="back-page">></span> 発注履歴一覧
            </h3>
            <br /><br />

            <h3>
                <span id="発注書">発注履歴一覧</span>
            </h3>
            <br /><br />
            <table id="orderHistory_list">
                <tbody>
                    <tr>
                        <th class="orderDate">発注日</th>
                        <th class="saler">販売店</th>
                        <th class="orderedEmp">発注者</th>
                        <th></th>
                    </tr>

                         <c:forEach var="j_orderHistory" items="${JoinOrderHistories}" varStatus="status">
                         <tr id="order_list">
                            <td class="orderDate" ><c:out value="${j_orderHistory.orderedDate}"/></td>
                            <td class="saler"><c:out value="${j_orderHistory.saler}"/></td>
                            <td class="orderedEmp"><c:out value="${j_orderHistory.orderedEmp.name}"/></td>
                            <td><a href="<c:url value='?action=${actOrder}&command=${commShowOdh}&orderedDate=${j_orderHistory.orderedDate}&orderedEmp=${j_orderHistory.orderedEmp.name}&saler=${j_orderHistory.saler}'
                            />">閲覧</a></td>
                         </tr>
                        </c:forEach>
                </tbody>
            </table>
        </div>
        <div id="pagination">
            （全 ${JoinOrderHistories_count} 件）<br />
            <c:forEach var="i" begin="1" end="${((JoinOrderHistories_count - 1) / maxRow) + 1}" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='?action=${actOrder}&command=${commIdx}&page=${i}' />"><c:out value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
    </c:param>
</c:import>
