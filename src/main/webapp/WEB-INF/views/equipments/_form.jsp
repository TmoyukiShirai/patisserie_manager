<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="constants.AttributeConst" %>

<div id="wrap_create">
    <c:if test="${errors != null}">
        <div id="flush_error">
            入力内容にエラーがあります。<br />
            <c:forEach var="error" items="${errors}">
                ・<c:out value="${error}" /><br />
            </c:forEach>

        </div>
    </c:if>

    <label for="${AttributeConst.EQ_NAME.getValue()}">備品名</label><br />
    <input class="equipment_name" type="text" name="${AttributeConst.EQ_NAME.getValue()}" value="${equipment.name}" />
    <br /><br />
    <label for="${AttributeConst.EQ_MAKER.getValue()}">メーカー</label><br />
    <select name="${AttributeConst.EQ_MAKER.getValue()}">
        <option value="" disabled selected style="display:none;" >選択してください</option>
        <option value="サンリツ"<c:if test="${equipment.maker == AttributeConst.SANRITSU.getValue()}"> selected</c:if>>サンリツ</option>
        <option value="KOKUYO"<c:if test="${equipment.maker == AttributeConst.KOKUYO.getValue()}"> selected</c:if>>KOKUYO</option>
        <option value="大王製紙"<c:if test="${equipment.maker == AttributeConst.DAIOUSEISHI.getValue()}"> selected</c:if>>大王製紙</option>
        <option value="ライオン"<c:if test="${equipment.maker == AttributeConst.LION.getValue()}"> selected</c:if>>ライオン</option>
        <option value="その他"<c:if test="${equipment.maker == AttributeConst.OTHER.getValue()}"> selected</c:if>>その他</option>
    </select>
    <br /><br />
    <label for="${AttributeConst.EQ_SALER.getValue()}">販売店</label><br />
    <select name="${AttributeConst.EQ_SALER.getValue()}">
        <option value="" disabled selected style="display:none;" >選択してください</option>
        <option value="カリョー"<c:if test="${equipment.saler == AttributeConst.KARYO.getValue()}"> selected</c:if>>カリョー</option>
        <option value="渡辺商店"<c:if test="${equipment.saler == AttributeConst.WATANABESYOUTEN.getValue()}"> selected</c:if>>渡辺商店</option>
        <option value="永野物産"<c:if test="${equipment.saler == AttributeConst.NAGANOBUSSAN.getValue()}"> selected</c:if>>永野物産</option>
        <option value="その他"<c:if test="${equipment.saler == AttributeConst.OTHER.getValue()}"> selected</c:if>>その他</option>

    </select>
    <br /><br />s
    <label for="${AttributeConst.EQ_PRICE.getValue()}">単価</label><br />
    <input class="eq_price" type="number" step="0.1" placeholder="半角数字で入力" name="${AttributeConst.EQ_PRICE.getValue()}" value="${equipment.price}">円</input>
    <br /><br />
    <label for="${AttributeConst.EQ_AMOUNT.getValue()}">内容量</label><br />
    <input class="eq_amount" type="number" step="0.1" placeholder="半角数字で入力" name="${AttributeConst.EQ_AMOUNT.getValue()}" value="${equipment.amount}" />
    <select name="${AttributeConst.EQ_AMOUNT_U.getValue()}">
        <option value="g"<c:if test="${equipment.amount_u == AttributeConst.G.getValue() || equipment.amount_u == null}"> selected</c:if>>g</option>
        <option value="ml"<c:if test="${equipment.amount_u == AttributeConst.ML.getValue()}"> selected</c:if>>ml</option>
        <option value="本"<c:if test="${equipment.amount_u == AttributeConst.HONN.getValue()}"> selected</c:if>>本</option>
        <option value="個"<c:if test="${equipment.amount_u == AttributeConst.KO.getValue()}"> selected</c:if>>個</option>
        <option value="枚"<c:if test="${equipment.amount_u == AttributeConst.MAI.getValue()}"> selected</c:if>>枚</option>
    </select>
    <br /><br />
    <input type="hidden" name="${AttributeConst.EQ_ID.getValue()}" value="${equipment.id}" />
    <input type="hidden" name="${AttributeConst.TOKEN.getValue()}" value="${_token}" />
    <button type="submit">登録する</button>
    <br><br><br>

</div>