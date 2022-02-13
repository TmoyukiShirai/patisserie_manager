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
<c:set var="commIdxOdh" value="${ForwardConst.CMD_INDEX_ODH.getValue()}" />
<c:set var="commShow" value="${ForwardConst.CMD_SHOW.getValue()}" />
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
                <span class="back-page">></span>
                <span class="back-page"><a href="<c:url value='?action=${actOrder}&command=${commIdxOdh}&saler=${saler}' />">発注履歴一覧</a></span>
                <span class="back-page">></span> ${saler}&nbsp;発注履歴
            </h3>
            <br /><br />

            <p>${orderedDate}&nbsp;発注分</p>
            <p>発注者:&nbsp;${orderedEmp}</p>
            <br />
            <table id="order_list">
                <tbody>
                    <tr id="order_list">
                        <th class="orderDate">発注日</th>
                        <th class="deliDate">納品日</th>
                        <th class="product">商品名</th>
                        <th class="maker">メーカー</th>
                        <th class="amount">内容量</th>
                        <th class="numberOfOrder">注文数</th>
                        <th class="price">金額</th>
                    </tr>
                        <c:forEach var="odh" items="${orderHistories}" varStatus="status">
                         <tr id="order_list">
                            <td class="orderDate" ><c:out value="${odh.orderedDate}"/></td>
                            <td class="deliDate"><c:out value="${odh.deliDate}"/></td>
                            <td class="product"><c:out value="${odh.ingredient.name}" /></td>
                            <td class="maker"><c:out value="${odh.ingredient.maker}" /></td>
                            <td class="amount"><c:out value="${odh.ingredient.amount}${odh.ingredient.amount_u}" /></td>
                            <td class="numberOfOrder"><c:out value="${odh.numberOfOrder}" /><span>P</span></td>
                            <td class="price"><c:out value="${odh.price}" /><span>円</span></td>
                         </tr>
                        </c:forEach>
                </tbody>
            </table>
        </div>
        <div id="pagination">
            （全 ${orderHistories_count} 件）<br />
            <c:forEach var="i" begin="1" end="${((orderHistories_count - 1) / maxRow) + 1}" step="1">
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
        <h3 id="発注合計金額">
            <span>合計金額:</span>
            <span><c:out value="${totalPrice}円" /></span>
        </h3>

    </c:param>
</c:import>
