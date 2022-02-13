<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="constants.AttributeConst" %>
<%@ page import="constants.ForwardConst" %>
<c:set var="actTop" value="${ForwardConst.ACT_TOP.getValue()}" />
<c:set var="actEmp" value="${ForwardConst.ACT_EMP.getValue()}" />
<c:set var="actReci" value="${ForwardConst.ACT_RECI.getValue()}" />
<c:set var="actIngr" value="${ForwardConst.ACT_INGR.getValue()}" />
<c:set var="actEq" value="${ForwardConst.ACT_EQ.getValue()}" />
<c:set var="actOrder" value="${ForwardConst.ACT_ORDER.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var="actUnfinished" value="${ForwardConst.ACT_UNFINISHED.getValue()}" />
<c:set var="commProcess" value="${ForwardConst.CMD_PROCESS.getValue()}" />
<c:set var="commShowOrder" value="${ForwardConst.CMD_SHOW_ORDER.getValue()}" />
<c:set var="commIdxOdh" value="${ForwardConst.CMD_INDEX_ODH.getValue()}" />

<c:import url="../layout/app.jsp">
    <c:param name="content">
        <c:if test="${numError}">
            <div id="flush_error">
            注文数未入力の為、注文が処理されませんでした。
            </div>
        </c:if>
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
         <h3>
                <span class="back-page"><a href="<c:url value='?action=${actTop}&command=${commIdx}' />">Top</a></span>
                <span class="back-page">></span>発注書メニュー
            </h3>
        <br />
        <div id="option-btn">
            <h3>食材発注書</h3>
            <br />
            <div id="dent">
                <h3>タカナシ乳業</h3>
                    <a href="<c:url value='?action=${actIngr}&command=${commShowOrder}&saler=${AttributeConst.TAKANASHI.getValue()}' />" class="btn btn--blue btn--cubic btn--shadow">発注書</a>&nbsp;
                    <a href="<c:url value='?action=${actOrder}&command=${commIdxOdh}&saler=${AttributeConst.TAKANASHI.getValue()}' />" class="btn btn--blue2 btn--cubic btn--shadow">発注履歴</a>&nbsp;
            </div>
            <div id="dent">
                 <h3>カリョー</h3>
                    <a href="<c:url value='?action=${actIngr}&command=${commShowOrder}&saler=${AttributeConst.KARYO.getValue()}' />" class="btn btn--blue btn--cubic btn--shadow">発注書</a>&nbsp;
                    <a href="<c:url value='?action=${actOrder}&command=${commIdxOdh}&saler=${AttributeConst.KARYO.getValue()}' />" class="btn btn--blue2 btn--cubic btn--shadow">発注履歴</a>&nbsp;
            </div>
            <div id="dent">
                <h3>福山八百屋</h3>
                    <a href="<c:url value='?action=${actIngr}&command=${commShowOrder}&saler=${AttributeConst.HUKUYAMAYAOYA.getValue()}' />" class="btn btn--blue btn--cubic btn--shadow">発注書</a>&nbsp;
                    <a href="<c:url value='?action=${actOrder}&command=${commIdxOdh}&saler=${AttributeConst.HUKUYAMAYAOYA.getValue()}' />" class="btn btn--blue2 btn--cubic btn--shadow">発注履歴</a>&nbsp;
            </div>
            <div id="dent">
                <h3>永野物産</h3>
                    <a href="<c:url value='?action=${actIngr}&command=${commShowOrder}&saler=${AttributeConst.NAGANOBUSSAN.getValue()}' />" class="btn btn--blue btn--cubic btn--shadow">発注書</a>&nbsp;
                    <a href="<c:url value='?action=${actOrder}&command=${commIdxOdh}&saler=${AttributeConst.NAGANOBUSSAN.getValue()}' />" class="btn btn--blue2 btn--cubic btn--shadow">発注履歴</a>&nbsp;
            </div>
        </div>
        <br /><br />

        <div id="option-btn">
            <h3>備品発注書</h3>
            <br />
            <div id="dent">
                 <h3>カリョー</h3>
                    <a href="<c:url value='?action=${actEq}&command=${commShowOrder}&saler=${AttributeConst.KARYO.getValue()}' />" class="btn btn--blue btn--cubic btn--shadow">発注書</a>&nbsp;
                    <a href="<c:url value='?action=${actOrder}&command=${commIdxOdh}&saler=${AttributeConst.KARYO.getValue()}' />" class="btn btn--blue2 btn--cubic btn--shadow">発注履歴</a>&nbsp;
            </div>
            <div id="dent">
                <h3>渡辺商店</h3>
                    <a href="<c:url value='?action=${actEq}&command=${commShowOrder}&saler=${AttributeConst.WATANABESYOUTEN.getValue()}' />" class="btn btn--blue btn--cubic btn--shadow">発注書</a>&nbsp;
                    <a href="<c:url value='?action=${actOrder}&command=${commIdxOdh}&saler=${AttributeConst.WATANABESYOUTEN.getValue()}' />" class="btn btn--blue2 btn--cubic btn--shadow">発注履歴</a>&nbsp;
            </div>
            <div id="dent">
                <h3>永野物産</h3>
                    <a href="<c:url value='?action=${actEq}&command=${commShowOrder}&saler=${AttributeConst.NAGANOBUSSAN.getValue()}' />" class="btn btn--blue btn--cubic btn--shadow">発注書</a>&nbsp;
                    <a href="<c:url value='?action=${actOrder}&command=${commIdxOdh}&saler=${AttributeConst.NAGANOBUSSAN.getValue()}' />" class="btn btn--blue2 btn--cubic btn--shadow">発注履歴</a>&nbsp;
            </div>
        </div>
        <br /><br /><br />
    </c:param>
</c:import>

