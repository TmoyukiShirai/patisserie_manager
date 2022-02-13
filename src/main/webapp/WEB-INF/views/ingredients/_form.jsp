<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="constants.AttributeConst" %>
<%@ page import="actions.views.IngredientView" %>
<%@ page import="models.Allergy" %>
<%@ page import="models.AllergyItem" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<div id="wrap_create">
    <c:if test="${errors != null}">
        <div id="flush_error">
            入力内容にエラーがあります。<br />
            <c:forEach var="error" items="${errors}">
                ・<c:out value="${error}" /><br />
            </c:forEach>

        </div>
    </c:if>

    <label for="${AttributeConst.INGR_NAME.getValue()}">食材名</label><br />
    <input class="ingredient_name" type="text" name="${AttributeConst.INGR_NAME.getValue()}" value="${ingredient.name}" >
    <br /><br />                                                     <!-- ↑これから入力して送る情報                   ↑一覧から受け取った食材名 -->
    <label for="${AttributeConst.INGR_MAKER.getValue()}">メーカー</label><br />
    <select name="${AttributeConst.INGR_MAKER.getValue()}">
        <option value="" disabled selected style="display:none;" >選択してください</option>
        <option value="タカナシ乳業"<c:if test="${ingredient.maker == AttributeConst.TAKANASHI.getValue()}"> selected</c:if>>タカナシ乳業</option>
        <option value="明治乳業"<c:if test="${ingredient.maker == AttributeConst.MEIJI.getValue()}"> selected</c:if>>明治乳業</option>
        <option value="日本製粉"<c:if test="${ingredient.maker == AttributeConst.NIHONSEIHUN.getValue()}"> selected</c:if>>日本製粉</option>
        <option value="山内貿易"<c:if test="${ingredient.maker == AttributeConst.YAMAUTIBOUEKI.getValue()}"> selected</c:if>>山内貿易</option>
        <option value="山内農場"<c:if test="${ingredient.maker == AttributeConst.YAMAUTINOUJOU.getValue()}"> selected</c:if>>山内農場</option>
        <option value="その他"<c:if test="${ingredient.maker == AttributeConst.OTHER.getValue()}"> selected</c:if>>その他</option>
    </select>
    <br /><br />
    <label for="${AttributeConst.INGR_SALER.getValue()}">販売店</label><br />
    <select name="${AttributeConst.INGR_SALER.getValue()}">
        <option value="" disabled selected style="display:none;" >選択してください</option>
        <option value="タカナシ乳業"<c:if test="${ingredient.saler == AttributeConst.TAKANASHI.getValue()}"> selected</c:if>>タカナシ乳業</option>
        <option value="カリョー"<c:if test="${ingredient.saler == AttributeConst.KARYO.getValue()}"> selected</c:if>>カリョー</option>
        <option value="福山八百屋"<c:if test="${ingredient.saler == AttributeConst.HUKUYAMAYAOYA.getValue()}"> selected</c:if>>福山八百屋</option>
        <option value="永野物産"<c:if test="${ingredient.saler == AttributeConst.NAGANOBUSSAN.getValue()}"> selected</c:if>>永野物産</option>
        <option value="その他"<c:if test="${ingredient.saler == AttributeConst.OTHER.getValue()}"> selected</c:if>>その他</option>
    </select>
    <br /><br />
    <label for="${AttributeConst.INGR_PRICE.getValue()}">単価</label><br />
    <input class="ingr_price" type="number" step="0.1" placeholder="半角数字で入力" name="${AttributeConst.INGR_PRICE.getValue()}" value="${ingredient.price}">円</input>
    <br /><br />
    <label for="${AttributeConst.INGR_AMOUNT.getValue()}">内容量</label><br />
    <input class="ingr_amount" type="number" step="0.1" placeholder="半角数字で入力" name="${AttributeConst.INGR_AMOUNT.getValue()}" value="${ingredient.amount}" />
    <select name="${AttributeConst.INGR_AMOUNT_U.getValue()}">
        <option value="g"<c:if test="${ingredient.amount_u == AttributeConst.G.getValue() || ingredient.amount_u == null}"> selected</c:if>>g</option>
        <option value="ml"<c:if test="${ingredient.amount_u == AttributeConst.ML.getValue()}"> selected</c:if>>ml</option>
        <option value="本"<c:if test="${ingredient.amount_u == AttributeConst.HONN.getValue()}"> selected</c:if>>本</option>
        <option value="個"<c:if test="${ingredient.amount_u == AttributeConst.KO.getValue()}"> selected</c:if>>個</option>
        <option value="枚"<c:if test="${ingredient.amount_u == AttributeConst.MAI.getValue()}"> selected</c:if>>枚</option>
    </select>
    <br /><br />
    <label for="${AttributeConst.INGR_ENERGY.getValue()}">エネルギー(100gあたり)</label><br />
    <input class="ingr_energy" type="number" step="0.1" placeholder="半角数字で入力" name="${AttributeConst.INGR_ENERGY.getValue()}" value="${ingredient.energy}">kcal</input>
    <br /><br />

    アレルギー(複数選択可)
    <br />
    <label for="${AttributeConst.INGR_ALLERGY.getValue()}">
          <%
         IngredientView ingredientView = (IngredientView)request.getAttribute("ingredient");//IngredientViewから食材情報を受け取る（更新の時）
         List<AllergyItem> selectAllergy = new ArrayList<AllergyItem>(); //AllergyItemクラスをnew（リストとして）
         if (ingredientView !=null && ingredientView.getAllergy() != null) { //更新の時で且アレルギー品目が選択されている時
             List<Allergy> tmpAllergy = ingredientView.getAllergy();//IngredientクラスのList<Allergy>に、選択されたアレルギー品目を格納
             if (tmpAllergy != null) {//アレルギー品目が格納されている時
                 for (Allergy allergy : tmpAllergy) {//Allergyクラスをnewして、格納されたアレルギー品目の個数分処理を繰り返す
                     selectAllergy.add(allergy.getItem());//AllergyItemのリストにAllergyのItemを格納していく（Allergyクラス内で）

                 }
             }
         }
         request.setAttribute("selectAllergy", selectAllergy);//リクエストスコープに上記の格納されたアレルギー品目のデータをセット

      %>
    <c:forEach var="allergy" items="${allergy_items}" varStatus="status">
     <!--  アレルギー品目を受け取ってAllergyItemに格納を繰り返す -->
      <%
      AllergyItem tmpAllergy = (AllergyItem)pageContext.getAttribute("allergy");
                                             //↑JSPから直接サーブレットにアクセスする場合はpageContextオブジェクトを使う
      %>
                                                  <!--　↓選択される値はID -->
      <input type="checkbox" class="algy_item"
          id="${AttributeConst.ALGY_ITEM.getValue()}${allergy.id}"
          name="${AttributeConst.ALGY_ITEM.getValue()}"
          value="<c:out value='${allergy.id}' />"
          <% if(selectAllergy != null && selectAllergy.contains(tmpAllergy)) { %> checked <% } %>
          ><label for="${AttributeConst.ALGY_ITEM.getValue()}${allergy.id}"><c:out value='${allergy.itemName}'/></label>
    </c:forEach>
    </label>

    <br /><br />
    <input type="hidden" name="${AttributeConst.INGR_ID.getValue()}" value="${ingredient.id}" />
    <input type="hidden" name="${AttributeConst.TOKEN.getValue()}" value="${_token}" />
    <button type="submit">登録する</button>
    <br><br><br>

</div>