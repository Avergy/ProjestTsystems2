
<%@include file="../jspf/header.jspf"%>
<%@include file="../jspf/left_column_account.jspf"%>
<div class="col-md-6 account-right wow fadeInRight">
    <form class="login_form"   name="user" modelAttribute="new_phone" enctype="multipart/form-data"
          action="/admin/addPhone" method="post" id="login_form">
        <div>
            <span>Name:</span>
            <input name="phone_name" type="text" id="phone_name" pattern=".{1,20}" required>
        </div>

       <div class="field">
            <label for="phone_image"> </label><input type="file" name="phone_image" id="phone_image" multiple
                                                    accept="image/jpeg">
        </div>

        <div>
            <span>Brand:</span>
            <select name="phone_brand" id="phone_brand">
                <c:forEach var="brand" items="${brandList}">
                    <option>${brand.brand}</option>
                </c:forEach>
            </select>
        </div>
        <div>
            <span>Color:</span>
            <select name="phone_color" id="phone_color">
                <c:forEach var="color" items="${colorList}">
                    <option>${color.color}</option>
                </c:forEach>
            </select>
        </div>
        <div>
            <span>New color:</span>
            <input name="new_color" id="new_color" pattern=".{1,25}">
        </div>
        <div>
            <span>Weight:</span>
            <input name="phone_weight" type="number" id="phone_weight" min="0" max="100000" required>
        </div>
        <div>
            <span>Quantity:</span>
            <input name="phone_quantity_stock" type="number" id="phone_quantity_stock" min="0" max="1000000" required>
        </div>
        <div>
            <span>Price, rub:</span>
            <input name="phone_price" type="number" id="phone_price" max="2000000" required>
        </div>
        <input type="submit" value="Add phone">
    </form>
</div>
<div class="clearfix"> </div>
</div>
</div>
<%@include file="../jspf/footer.jspf"%>
