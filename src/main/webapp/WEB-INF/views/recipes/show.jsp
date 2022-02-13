<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="constants.ForwardConst" %>
<%@ page import="constants.AttributeConst" %>
<%@ page import="models.Allergy" %>
<%@ page import="models.AllergyItem" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.stream.Collectors" %>

<c:set var="actTop" value="${ForwardConst.ACT_TOP.getValue()}" />
<c:set var="actReci" value="${ForwardConst.ACT_RECI.getValue()}" />
<c:set var="actPr" value="${ForwardConst.ACT_PR.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var="commNew" value="${ForwardConst.CMD_NEW.getValue()}" />
<c:set var="commNewPr" value="${ForwardConst.CMD_NEW_PR.getValue()}" />
<c:set var="commEdt" value="${ForwardConst.CMD_EDIT.getValue()}" />
<c:set var="commShow" value="${ForwardConst.CMD_SHOW.getValue()}" />
<c:set var="commShowPr" value="${ForwardConst.CMD_SHOW_PR.getValue()}" />
<c:set var="commDel" value="${ForwardConst.CMD_DESTROY.getValue()}" />
<c:set var="commCalc" value="${ForwardConst.CMD_CALCULATE.getValue()}" />
<head>
<script src="/patisserie_manager/js/thshow.js"></script>
</head>

<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">

        <h3>
            <span class="back-page"><a href="<c:url value='?action=${actTop}&command=${commIdx}' />">Top</a></span>
            <span class="back-page">></span>
            <span class="back-page"><a href="<c:url value='?action=${actReci}&command=${commIdx}' />">レシピ一覧</a></span>
            <span class="back-page">></span>
            ${recipe.title}

            <c:if test="${sessionScope.login_employee.adminFlag == AttributeConst.ROLE_ADMIN.getIntegerValue()}">
               <span class="new-can-admin"><a href="<c:url value='?action=${actReci}&command=${commEdt}&id=${recipe.id}' />">レシピ編集</a></span>
            </c:if>
            <c:if test="${sessionScope.login_employee.adminFlag == AttributeConst.ROLE_ADMIN.getIntegerValue() && recipe.processRecipe == '[]'}">
               <span class="new-can-admin"><a href="<c:url value='?action=${actReci}&command=${commNewPr}&id=${recipe.id}' />">工程作成</a></span>
            </c:if>
        </h3>
        <br ><br >

        <h3>${recipe.title}
            <br /><br />

            <span class="計算フォーム">
                <form method="POST" name ="inputForm" action="<c:url value='?action=${actReci}&command=${commCalc}' />">

                    <label>
                      <input type="number" class="number" value="${recipe.number}" name="${AttributeConst.RECI_NUMBER.getValue()}" >
                      人分
                    </label>

                    <input type="button" value="計算" onclick="executeCalc();"/>
                    <input type="hidden" name="${AttributeConst.RECI_ID.getValue()}" value="${recipe.id}" />
                    <input type="hidden" name="${AttributeConst.TOKEN.getValue()}" value="${_token}" />
                </form>
                <script>
                function executeCalc() {
                   /*  document.inputForm.action = "<c:url value='?action=${actReci}&command=${commCalc}' />"; */
                    document.inputForm.submit();
                }
                </script>
            </span>

            <c:if test="${recipe.processRecipe != '[]'}">
                <span class="check-process"><a href="<c:url value='?action=${actReci}&command=${commShowPr}&id=${recipe.id}' />">工程をみる&nbsp;&nbsp;&nbsp;</a></span>
            </c:if>
        </h3>

        <table id="recipe_detail">
            <tbody>
                <tr>
                    <th class="ingredient">原材料</th>
                    <th class="amount">分量</th>
                    <th class="maker">メーカー</th>
                    <th class="energy">エネルギー</th>
                    <th class="price">原価</th>
                </tr>
                     <c:forEach var="joinIngredient" items="${recipe.joinIngredient}" >
                          <tr>
                             <td class="ingredient"><c:out value="${joinIngredient.ingredient.name}" /></td>
                             <td class="amount"><c:out value="${joinIngredient.amount}${joinIngredient.amount_u}" /></td>
                             <td class="maker"><c:out value="${joinIngredient.ingredient.maker}" /></td>
                             <td class="energy"><c:out value="${joinIngredient.ingredient.energy}kcal" /></td>
                             <td class="price"><c:out value="${joinIngredient.ingredient.price}円" /></td>
                          </tr>
                     </c:forEach>
                     <th class="sum">合計</th>
                        <td class="sumAmount"><c:out value="${totalAmountReci}g" /></td>
                        <td class="empty"></td>
                        <td class="sumEnergy"><c:out value="${totalEnergy}kcal" /></td>
                        <td class="sumPrice"><c:out value="${totalPrice}円" /></td>


            </tbody>
         </table>
         <br />

        <table>
            <tbody>
                <tr>
                    <th>アレルギー品目</th>
                    <td class="allergy">
                        <%
                            List<String> allergyList = new ArrayList<String>();
                        %>
                        <c:forEach var="joinIngredient" items="${recipe.joinIngredient}">
                            <c:forEach var="allergy" items="${joinIngredient.ingredient.allergy}">
                                <%
                                    Allergy tempAllergy = (Allergy)pageContext.getAttribute("allergy");
                                    // ☆アレルギー品目の名称だけでいいので、Listには名称だけ詰める
                                    allergyList.add(tempAllergy.getItem().getItemName());
                                %>
                            </c:forEach>
                        </c:forEach>
                        <%
                            List<String> allergyListAfter = allergyList.stream()
                                                                       .distinct()
                                                                       .collect(Collectors.toList());

                            // EL式で利用するにはpageContext#setAttribute()でオブジェクトをセットする必要あり
                            pageContext.setAttribute("allergyListAfter", allergyListAfter);
                        %>
                        <c:forEach var="itemName" items="${allergyListAfter}" varStatus="status">
                            <span class="char-yellow">
                                <c:out value="${itemName}" />
                            </span>
                        </c:forEach>
                    </td>
                </tr>
            </tbody>
        </table>
    </c:param>
</c:import>
