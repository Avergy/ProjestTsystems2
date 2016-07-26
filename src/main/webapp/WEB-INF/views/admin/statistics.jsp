
<%@include file="../jspf/header.jspf"%>
<%@include file="../jspf/left_column_account.jspf"%>

<div class="col-md-6 account-right wow fadeInRight">
    <div>
        <div class="top10_penal">
            <div class="admin_penal">
                <br><strong>Top-10 users</strong>

                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th>Login</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>E-mail</th>
                        <th>Proceeds</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:if test="${empty(topTenUsers)}">
                        <br><strong> 0 orders found.</strong>
                    </c:if>
                    <c:forEach var="entry" items="${topTenUsers}">
                        <tr>
                            <td>${entry.key.login}</td>
                            <td>${entry.key.firstName}</td>
                            <td>${entry.key.secondName}</td>
                            <td>${entry.key.email}</td>
                            <td>${entry.value} rub</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>

        <div class="top10_penal">

            <div class="admin_penal">

                <br><strong>Top-10 phones</strong>

                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th>Phone</th>
                        <th>Brand</th>
                        <th>Color</th>
                        <th>Price</th>
                        <th>Out Of Stock</th>
                        <th>Sales</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:if test="${empty(topTenPhones)}">
                        <br><strong> 0 orders found.</strong>
                    </c:if>
                    <c:forEach var="entry" items="${topTenPhones}">
                        <tr>
                            <td><a href="/phoneDetail/${entry.key.id}"> ${entry.key.name}</a></td>
                            <td>${entry.key.brand.brand}</td>
                            <td>${entry.key.color.color}</td>
                            <td>${entry.key.price} rub</td>
                            <td>${entry.key.quantityStock}</td>
                            <td>${entry.value}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <div class="edit_penal">

        <br><strong>Proceeds:</strong>

        <form name="proceed_form" action="/admin/showProceedsPerPeriod" method="get">

            <div class="edit_phone_info">
                <div>

                    <div class="date">
                        <span>From:</span>
                        <input name="from_date" id="from_date" type="date" lang="en" required>
                    </div>

                    <div class="date">
                        <span>To:</span>
                        <input name="to_date" id="to_date" type="date" lang="en" required>
                    </div>

                    <p><input type="submit" value="Show"></p>

                </div>
            </div>
        </form>


        <div class="admin_penal">

            <br><strong>Orders</strong>

            <div class="bs-docs-example">
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th>id</th>
                        <th>Date</th>
                        <th>User Login</th>
                        <th>Order Status</th>
                        <th>Shipping Type</th>
                        <th>Payment Type</th>
                        <th>Payment Status</th>
                        <th>Total</th>
                        <th>Edit</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:if test="${empty(ordersPerPeriod)}">
                        <br><strong> 0 orders found.</strong>
                    </c:if>
                    <c:forEach var="order" items="${ordersPerPeriod}">
                        <tr>
                            <td>${order.id}</td>
                            <td>${order.date}</td>
                            <td>${order.user.login}</td>
                            <td>${order.statusOrder.toString()}</td>
                            <td>${order.delivery.toString()}</td>
                            <td>${order.payment.toString()}</td>
                            <td>${order.statusPayment.toString()}</td>
                            <td>${order.cost} rub </td>
                            <td><a href="/admin/editOrder/${order.id}">
                                <img src="/resources/images/edit.png" alt="edit" name="edit" id="edit_img" /></a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>

        </div>

    </div>

</div>
<div class="clearfix"> </div>
</div>
</div>
<%@include file="../jspf/footer.jspf"%>