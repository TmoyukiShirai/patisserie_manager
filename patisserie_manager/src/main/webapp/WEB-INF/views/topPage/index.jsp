<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="constants.AttributeConst" %>
<%@ page import="constants.ForwardConst" %>
<c:set var="actEmp" value="${ForwardConst.ACT_EMP.getValue()}" />
<c:set var="actReci" value="${ForwardConst.ACT_RECI.getValue()}" />
<c:set var="actIngr" value="${ForwardConst.ACT_INGR.getValue()}" />
<c:set var="actEq" value="${ForwardConst.ACT_EQ.getValue()}" />
<c:set var="actOrder" value="${ForwardConst.ACT_ORDER.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var="actUnfinished" value="${ForwardConst.ACT_UNFINISHED.getValue()}" />
<c:set var="commProcess" value="${ForwardConst.CMD_PROCESS.getValue()}" />


<c:import url="../layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <h2>マネジメントツールへようこそ</h2>

        <br /><br /><br />
        <div id="option-btn">
            <c:if test="${sessionScope.login_employee.adminFlag == AttributeConst.ROLE_ADMIN.getIntegerValue()}">
                <h3>管理者用メニュー</h3>
            </c:if>
            <c:if test="${sessionScope.login_employee.adminFlag != AttributeConst.ROLE_ADMIN.getIntegerValue()}">
                <h3>従業員用メニュー</h3>
            </c:if>
            <br />
            <c:if test="${sessionScope.login_employee != null}">
                <a href="<c:url value='?action=${actReci}&command=${commIdx}' />" class="btn btn--orange btn--cubic btn--shadow">レシピ一覧</a>&nbsp;
                <a href="<c:url value='?action=${actIngr}&command=${commIdx}' />" class="btn btn--orange btn--cubic btn--shadow">食材一覧</a>&nbsp;
                <a href="<c:url value='?action=${actEq}&command=${commIdx}' />" class="btn btn--orange btn--cubic btn--shadow">備品一覧</a>&nbsp;
                <a href="<c:url value='?action=${actOrder}&command=${commIdx}' />" class="btn btn--orange btn--cubic btn--shadow">発注書</a>&nbsp;
                <a href="<c:url value='?action=${actUnfinished}&command=${commProcess}' />" class="btn btn--gray btn--cubic btn--shadow">棚卸表</a>&nbsp;
                <a href="<c:url value='?action=${actUnfinished}&command=${commProcess}' />" class="btn btn--gray btn--cubic btn--shadow">収支管理</a>&nbsp;

                <c:if test="${sessionScope.login_employee.adminFlag == AttributeConst.ROLE_ADMIN.getIntegerValue()}">
                    <a href="<c:url value='?action=${actEmp}&command=${commIdx}' />" class="btn btn--orange btn--cubic btn--shadow">従業員管理</a>&nbsp;
                </c:if>
            </c:if>
        </div>
        <br /><br /><br />
    </c:param>
</c:import>

