
<%@include file="../jspf/header.jspf"%>
<%@include file="../jspf/left_column_account.jspf"%>

<div class="col-md-6 account-right wow fadeInRight">
    <br><strong>Update order status</strong>
    <form class="login_form"  name="user" action="/admin/editOrder/${order.id}" method="post" id="login_form">
        <div class="edit_phone_info">
            <div>
                <div>
                    <span>ID:</span>${order.id}
                </div>
                <div>
                    <span>Order status:</span>
                    <select name="order_status" id="order_status">
                        <option>${order.statusOrder.toString()}</option>
                        <c:forEach var="status" items="${statusOrderValues}">
                            <c:if test="${!status.toString().equals(order.statusOrder.toString())}">
                                <option>${status.toString()}</option>
                            </c:if>
                        </c:forEach>
                    </select>
                </div>
                <div>
                    <span>Payment status:</span>
                    <select name="payment_status" id="payment_status">
                        <option>${order.statusPayment.toString()}</option>
                        <c:forEach var="status" items="${statusPaymentValues}">
                            <c:if test="${!status.toString().equals(order.statusPayment.toString())}">
                                <option>${status.toString()}</option>
                            </c:if>
                        </c:forEach>
                    </select>
                </div>
                <p><input type="submit" value="update"></p>
            </div>
        </div>
    </form>
</div>
<div class="clearfix"> </div>
</div>
</div>
<%@include file="../jspf/footer.jspf"%>