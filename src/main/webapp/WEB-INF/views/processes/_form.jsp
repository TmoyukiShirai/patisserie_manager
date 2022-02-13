<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="constants.AttributeConst"%>

<div id="wrap_create">
    <c:if test="${errors != null}">
        <div id="flush_error">
            入力内容にエラーがあります。<br />
            <c:forEach var="error" items="${errors}">
                ・<c:out value="${error}" />
                <br />
            </c:forEach>

        </div>
    </c:if>

    <label for="${AttributeConst.RECI_TITLE.getValue()}">${recipe.title}</label>
    <br />

    <c:forEach begin='0' end='9' varStatus="status">
        <label for="${AttributeConst.PR_DESCRIPTION.getValue()}">工程説明文</label>
        <br />

        <textarea class="process" placeholder="工程説明文を入力してください" id="${AttributeConst.PR_DESCRIPTION.getValue()}" name="${AttributeConst.PR_DESCRIPTION.getValue()}"
           ><c:out value="${recipe.processRecipe[status.count-1].description}"></c:out></textarea>
        <br />

        <label id="utensil" for="${AttributeConst.PR_UTENSIL.getValue()}">調理器具</label>
        <label id="temp" for="${AttributeConst.PR_TEMPERATURE.getValue()}">温度</label>
        <label id="speed" for="${AttributeConst.PR_SPEED.getValue()}">速度</label>
        <label id="time" for="${AttributeConst.PR_TIME.getValue()}">時間</label>
        <br />

            <select class="utensil" name="${AttributeConst.PR_UTENSIL.getValue()}" id="${AttributeConst.PR_UTENSIL.getValue()}">
                <option value="noSelected" selected style="display: none;">選択してください</option>
                <option value="手鍋"
                    <c:if test="${recipe.processRecipe[status.count-1].utensil == AttributeConst.TENABE.getValue()}"> selected</c:if>>手鍋</option>
                <option value="銅鍋"
                    <c:if test="${recipe.processRecipe[status.count-1].utensil == AttributeConst.DOUNABE.getValue()}"> selected</c:if>>銅鍋</option>
                <option value="卓上ミキサー"
                    <c:if test="${recipe.processRecipe[status.count-1].utensil == AttributeConst.MIXER.getValue()}"> selected</c:if>>卓上ミキサー</option>
                <option value="30コートミキサー"
                    <c:if test="${recipe.processRecipe[status.count-1].utensil == AttributeConst.MIXER30.getValue()}"> selected</c:if>>30コートミキサー</option>
                <option value="オーブン"
                    <c:if test="${recipe.processRecipe[status.count-1].utensil == AttributeConst.OVEN.getValue()}"> selected</c:if>>オーブン</option>
            </select>

            <input class="temp" type="number" placeholder="半角数字で入力" id="${AttributeConst.PR_TEMPERATURE.getValue()}"
               name="${AttributeConst.PR_TEMPERATURE.getValue()}"value="${recipe.processRecipe[status.count-1].temperature}" />℃

            <select class="speed" name="${AttributeConst.PR_SPEED.getValue()}" id="${AttributeConst.PR_SPEED.getValue()}">
                <option value="noSelected" selected style="display: none;">選択</option>
                <option value="1"
                    <c:if test="${recipe.processRecipe[status.count-1].speed == '1'}"> selected</c:if>>1速</option>
                <option value="2"
                    <c:if test="${recipe.processRecipe[status.count-1].speed == '2'}"> selected</c:if>>2速</option>
                <option value="3"
                    <c:if test="${recipe.processRecipe[status.count-1].speed == '3'}"> selected</c:if>>3速</option>
                <option value="4"
                    <c:if test="${recipe.processRecipe[status.count-1].speed == '4'}"> selected</c:if>>4速</option>
                <option value="5"
                    <c:if test="${recipe.processRecipe[status.count-1].speed == '5'}"> selected</c:if>>5速</option>
                <option value="6"
                    <c:if test="${recipe.processRecipe[status.count-1].speed == '6'}"> selected</c:if>>6速</option>
            </select>

            <input class="time" type="number" placeholder="半角数字" id="${AttributeConst.PR_TIME.getValue()}"
               name="${AttributeConst.PR_TIME.getValue()}" value="${recipe.processRecipe[status.count-1].time}" />分
        <br /><br />
    </c:forEach>

    <br />
    <br />
    <br />
    <input type="hidden" name="${AttributeConst.RECI_ID.getValue()}" value="${recipe.id}" />
    <input type="hidden" name="${AttributeConst.TOKEN.getValue()}" value="${_token}" />
    <button type="submit">登録</button>
    <br> <br> <br>


</div>