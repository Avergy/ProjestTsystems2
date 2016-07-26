
<%@include file="../jspf/header.jspf"%>
<%@include file="../jspf/brand_list.jspf"%>

<div class="col-md-6 account-right wow fadeInRight">
    <form class="login_form"  name="user" action="/admin/editPhoneDetail/${phone.id}"
          method="post" id="login_form">
        <div>
            <span>Name:</span>
            <input name="phone_name" type="text" id="phone_name" value="${phone.name}" pattern=".{1,20}" required>
        </div>
        <div>
            <span>Brand:</span>
            <select name="phone_brand" id="phone_brand">
                <option>${phone.brand.brand}</option>
                <c:forEach var="brand" items="${brandList}">
                    <c:if test="${!brand.brand.equals(phone.brand.brand)}">
                        <option>${brand.brand}</option>
                    </c:if>
                </c:forEach>
            </select>
        </div>
        <div>
            <span>Color:</span>
            <select name="phone_color" id="phone_color">
                <option>${phone.color.color}</option>
                <c:forEach var="color" items="${colorList}">
                    <c:if test="${!color.color.equals(phone.color.color)}">
                        <option>${color.color}</option>
                    </c:if>
                </c:forEach>
            </select>
        </div>
        <div>
            <span>New color:</span>
            <input name="new_color" id="new_color" pattern=".{1,25}">
        </div>
        <div>
            <span>Weight:</span>
            <input name="phone_weight" type="number" id="phone_weight" min="0" max="100000"
                   value="${phone.weight}" required>
        </div>
        <div>
            <span>Quantity:</span>
            <input name="phone_quantity_stock" type="number" id="phone_quantity_stock" min="0"
                   max="1000000" value="${phone.quantityStock}" required>
        </div>
        <div>
            <span>Price, rub:</span>
            <input name="phone_price" type="number" id="phone_price"
                   max="2000000" value="${phone.price}" required>
        </div>
        <input type="submit" value="Edit phone">
    </form>
</div>
<div class="clearfix"> </div>
</div>
</div>
<%@include file="../jspf/footer.jspf"%>