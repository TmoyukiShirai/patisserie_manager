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
<c:set var="commNew" value="${ForwardConst.CMD_NEW.getValue()}" />
<c:set var="commCrt" value="${ForwardConst.CMD_CREATE.getValue()}" />
<c:set var="commEdit" value="${ForwardConst.CMD_EDIT.getValue()}" />
<c:set var="commSearch" value="${ForwardConst.CMD_SEARCH.getValue()}" />
<c:set var="actUnfinished" value="${ForwardConst.ACT_UNFINISHED.getValue()}" />
<c:set var="commProcess" value="${ForwardConst.CMD_PROCESS.getValue()}" />

<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">

        <form method="POST" action="<c:url value='?action=${actOrder}&command=${commCrt}' />">
            <h3>
                <span class="back-page"><a href="<c:url value='?action=${actTop}&command=${commIdx}' />">Top</a></span>
                <span class="back-page">></span>
                <span class="back-page"><a href="<c:url value='?action=${actOrder}&command=${commIdx}' />">発注書メニュー</a></span>
                <span class="back-page">></span> ${saler}発注書
                <input type="hidden" value="${saler}" name="${AttributeConst.INGR_SALER.getValue() }"/>
                <input type="hidden" name="${AttributeConst.TOKEN.getValue()}" value="${_token}" />
                <button type="submit" id="mail"><img id="mail" src="<c:url value='/img/mail.jpg'/>" /></button>
                <button type="submit" id="print"><img id="print" src="<c:url value='/img/print.jpeg'/>" /></button>
            </h3>
            <br /><br />

            <div id="wrap発注書">
                <h3>
                    <span id="発注書">発注書</span>
                </h3>
                <br />
                <h3 id="saler御中">${saler}株式会社&nbsp;御中</h3>
                <span id="発注日">${orderDate1 }発注分</span>
                <br /><br />
                <table id="order_list">
                    <tbody>
                        <tr id="order_list">
                            <th class="orderDate">発注日</th>
                            <th class="deliDate">納品日</th>
                            <th class="product">商品名</th>
                            <th class="maker">メーカー</th>
                            <th class="amount">内容量</th>
                            <th class="numberOfOrder">注文数</th>
                        </tr>

                        <c:forEach var="ingredient" items="${ingredients}" varStatus="status">
                         <tr id="order_list">
                            <td class="orderDate" ><c:out value="${orderDate2}"/><input type="hidden" value="${orderDate2}" name="${AttributeConst.ODH_ODD_DATE.getValue() }"/></td>
                            <td class="deliDate"><input id="deliDate" type="text" value="${deliDate}" name="${AttributeConst.ODH_DELI_DATE.getValue() }"/></td>
                            <td class="product"><c:out value="${ingredient.name}" /><input type="hidden" value="${ingredient.id}" name="${AttributeConst.INGR_ID.getValue() }"/></td>
                            <td class="maker"><c:out value="${ingredient.maker}" /><input type="hidden" value="${ingredient.price}" name="${AttributeConst.INGR_PRICE.getValue() }"/></td>
                            <td class="amount"><c:out value="${ingredient.amount}${ingredient.amount_u}" /></td>
                            <td class="numberOfOrder"><input id="numberOfOrder" type="number" name="${AttributeConst.NUM_OF_ORDER.getValue() }"><span>P</span></td>
                         </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <br /><br />

                <div id="orderingEmp">
                    <p>洋菓子店Little Deco<br />
                    <span class="orderingEmp">発注者:${sessionScope.login_employee.name}</span><br />
                    TEL:045-000-0000
                    </p>
                </div>
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
        </form>
        <br />
    </c:param>
</c:import>
