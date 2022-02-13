<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="constants.AttributeConst"%>
<%@ page import="actions.views.RecipeView"%>
<%@ page import="models.JoinIngredient"%>
<%@ page import="models.Ingredient"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>

<div id="wrap_create">
    <c:if test="${errors != null}">
        <div id="flush_error">
            入力内容にエラーがあります。<br />
            <c:forEach var="error" items="${errors}">
                <c:out value="${error}" />
                <br />
            </c:forEach>
        </div>
    </c:if>

    <label for="${AttributeConst.RECI_TITLE.getValue()}">レシピ名</label>
    <br />
        <input class="recipe_title" type="text" id="${AttributeConst.RECI_TITLE.getValue()}" name="${AttributeConst.RECI_TITLE.getValue()}" value="${recipe.title}" />
    <br /><br />

    <label for="${AttributeConst.RECI_TYPE.getValue()}">分類</label>
    <br />
        <select name="${AttributeConst.RECI_TYPE.getValue()}" id="${AttributeConst.RECI_TYPE.getValue()}">
            <option value="" disabled selected style="display: none;">選択してください</option>
            <option value="クリーム"
                <c:if test="${recipe.type == AttributeConst.CREAM.getValue() || recipe.type == null}"> selected</c:if>>クリーム</option>
            <option value="生地"
                <c:if test="${recipe.type == AttributeConst.KIJI.getValue()}"> selected</c:if>>生地</option>
            <option value="ムース"
                <c:if test="${recipe.type == AttributeConst.MOUSE.getValue()}"> selected</c:if>>ムース</option>
            <option value="焼き菓子"
                <c:if test="${recipe.type == AttributeConst.YAKIGASHI.getValue()}"> selected</c:if>>焼き菓子</option>
            <option value="コンフィチュール"
                <c:if test="${recipe.type == AttributeConst.CONFITURE.getValue()}"> selected</c:if>>コンフィチュール</option>
            <option value="その他"
                <c:if test="${recipe.type == AttributeConst.OTHER.getValue()}"> selected</c:if>>その他</option>
        </select>
    <br /><br />

    <label id="人数タグ" for="${AttributeConst.RECI_NUMBER.getValue()}">人数</label>
         <input type="number" value="${recipe.number}" class="number" id="${AttributeConst.RECI_NUMBER.getValue()}" name="${AttributeConst.RECI_NUMBER.getValue()}">人分
    <br /><br/>

    <label id="材料タグ" for="${AttributeConst.J_INGR_INGREDIENT.getValue()}">食材名</label>
    <label id="分量タグ" for="${AttributeConst.J_INGR_AMOUNT.getValue()}">内容量</label>
    <br />
    <span class="注意書き">※登録済みの食材の単位とレシピ中の使用食材単位を合わせてください。</span>
    <br />

    <c:forEach begin='0' end='9' var="JI" items="${recipe.joinIngredient}" varStatus="status">
        <div>
            <select class="ingr" name="${AttributeConst.J_INGR_INGREDIENT.getValue()}" id="${AttributeConst.J_INGR_INGREDIENT.getValue()}">
                <option value="noSelected"  selected style="display:none;" >選択してください</option>
                    <c:forEach var="IG" items="${ingredients}" varStatus="status">
                        <option value="${IG.id}"<c:if test="${IG.id == JI.ingredient.id}"> selected</c:if>>${IG.name}</option>
                    </c:forEach>
            </select>

            <input type="number" step="0.1" placeholder="半角数字で入力" class="amount" id="${AttributeConst.J_INGR_AMOUNT.getValue()}"
                name="${AttributeConst.J_INGR_AMOUNT.getValue()}" value="<c:out value='${JI.amount}' />">

            <select class="amount_u" name="${AttributeConst.J_INGR_AMOUNT_U.getValue()}">
                <option value="g"
                    <c:if test="${JI.amount_u == AttributeConst.G.getValue() || JI.amount_u == null}">
                    selected</c:if>>g</option>
                <option value="ml"
                    <c:if test="${JI.amount_u == AttributeConst.ML.getValue()}">
                    selected</c:if>>ml</option>
                <option value="本"
                    <c:if test="${JI.amount_u == AttributeConst.HONN.getValue()}">
                    selected</c:if>>本</option>
                <option value="個"
                    <c:if test="${JI.amount_u == AttributeConst.KO.getValue()}">
                    selected</c:if>>個</option>
            </select>
        </div>
    </c:forEach>

    <br /><br />
    <input type="hidden" name="${AttributeConst.RECI_ID.getValue()}" value="${recipe.id}" />
    <input type="hidden" name="${AttributeConst.TOKEN.getValue()}" value="${_token}" />
    <button type="submit">登録</button>
    <br /><br />

</div>
