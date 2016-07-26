
<%@include file="../jspf/header.jspf"%>
<%@include file="../jspf/left_column_account.jspf"%>

<div class="col-md-6 account-right wow fadeInRight">
    <form action="/admin/editBrandList" method="post">
        <div class="modify_info">
            <div>
                <div class="field">
                    <label for="select_brand">Select or add a new brand.</label>
                    <select name="select_brand" id="select_brand">
                        <option>add new brand</option>
                        <c:forEach var="brand" items="${brandList}">
                            <option>${brand.brand}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="field">
                    <span>Enter a new brand name:</span>
                    <input name="new_brand_name" type="text" id="new_brand_name" pattern=".{1,20}" required>
                </div>
                <p><input type="submit" name ="edit" value="Add/Edit"></p>
            </div>
        </div>
    </form>
</div>
<div class="clearfix"> </div>
</div>
</div>
<%@include file="../jspf/footer.jspf"%>