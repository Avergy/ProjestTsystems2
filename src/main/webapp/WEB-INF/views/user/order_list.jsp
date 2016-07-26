
<%@include file="../jspf/header.jspf"%>
<%@include file="../jspf/left_column_account.jspf"%>

<div class="col-md-6 account-right wow fadeInRight">

    <!-- <div class="account-right">-->
    <div class="bs-docs-example">
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>id</th>
                <th>Date</th>
                <th>Order Status</th>
                <th>Shipping Type</th>
                <th>Payment Type</th>
                <th>Payment Status</th>
                <th>Total</th>
            </tr>
            </thead>
            <tbody>
            <c:if test="${empty(orderList)}">
                <br><strong> 0 orders found.</strong>
            </c:if>
            <c:forEach var="order" items="${orderList}">
                <tr>
                    <td>${order.id}</td>
                    <td>${order.date}</td>
                    <td>${order.statusOrder.toString()}</td>
                    <td>${order.delivery.toString()}</td>
                    <td>${order.payment.toString()}</td>
                    <td>${order.statusPayment.toString()}</td>
                    <td>${order.cost} rub </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</div>
<div class="clearfix"> </div>
</div>
</div>
<%@include file="../jspf/footer.jspf"%>