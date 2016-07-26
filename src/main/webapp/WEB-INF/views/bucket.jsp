<%@include file="jspf/header.jspf"%>
<div class="cart-items">
    <div class="container">
        <div class="dreamcrub">
            <ul class="breadcrumbs">
                <li class="home">
                    <a href="/" title="Go to Home Page">Home</a>&nbsp;
                    <span>&gt;</span>
                </li>
                <li class="women">
                    Cart
                </li>
            </ul>
            <div class="clearfix"></div>
        </div>
        <h2>MY SHOPPING BAG</h2>
        <div class="cart-gd">
            <c:if test="${empty(cart)}">
                Shopping Bag is empty!
            </c:if>
            <c:forEach var="orderItem" items="${cart}">
            <div class="cart-header" id="cart_item_${orderItem.phone.id}">
                <div class="close1"
                     onclick="removeOrderLine(${orderItem.phone.id})"> </div>
                <div class="cart-sec simpleCart_shelfItem">
                    <div class="cart-item cyc">
                        <img src="/resources/images/phoneImages/${orderItem.phone.id}.jpg" class="img-cart" alt="">
                    </div>
                    <div class="cart-item-info">
                        <h3><a href="/phoneDetail/${orderItem.phone.id}"> ${orderItem.phone.name} </a>
                            <span style="font-size:13px">${orderItem.phone.brand.brand}</span></h3>
                        <ul class="qty">
                            <li><p>Quantity:</p></li>
                            <li><p><input type="number" id="quantity_${orderItem.phone.id}"
                                          min="1" max="${orderItem.phone.quantityStock}" value="${orderItem.quantity}" style="width:45px"
                                          onchange="changeQuantity(${orderItem.phone.id},
                                          ${cart.indexOf(orderItem)})"/></p></li>
                            <li><p style="text-align: right" id="price_${orderItem.phone.id}">
                            ${orderItem.phone.price * orderItem.quantity} rub </p></li>
                        </ul>
                        <!--<div class="delivery">

                            <div class="clearfix"></div>
                        </div>-->
                    </div>
                    <div class="clearfix"></div>

                </div>
            </div>
            </c:forEach>
            <c:if test="${not empty(cart)}">
            <div style="text-align: center">
                <a class="cbp-vm-icon cbp-vm-add item_add " id="create_order"
                   onclick="createOrder()">Create order </a>
            </div>
            </c:if>

            <div id="box" style="display: none">

                <security:authorize access="isAnonymous()">
                    <br><a href="/login">Sign in</a></br></p>
                </security:authorize>
                <form class="login_form"  name="user" action="/cart/createOrder" method="post" id="login_form"
                style="    text-align: center; margin: 15px;">
                    <security:authorize access="isAuthenticated()">
                        <br>Shipping type:
                        <select name="shipping_type" onchange="addAddress(this)" style="margin-bottom: 15px;">
                            <c:forEach var="shipping" items="${shippingType}">
                                <option  value="${shipping.toString()}">${shipping.toString()}</option>
                            </c:forEach>
                        </select>

                        <br>Payment type:
                        <select name="payment_type" style="margin-bottom: 15px;">
                            <c:forEach var="payment" items="${paymentType}" >
                                <option>${payment.toString()}</option>
                            </c:forEach>
                        </select>

                        <div id="address" style="display: none" style="margin-bottom: 15px;">
                            <label for="address_name">Enter a shipping address:</label>
                            <input name="address_name" type="text" id="address_name">
                        </div><div>
                        <input type="submit" value="Proceed to checkout" style="margin-bottom: 15px;"></div>
                </form>
                </security:authorize>

            </div>

        </div>
    </div>
    <c:if test="${not empty(success)}">

    <a name="modal">
        <div id="boxes">
            <div id="dialog" class="window" style="display: block">
                <div class="top"><a href="#" class="link close"><img src="/resources/images/close_1.png"></a></div>
                <div class="content">Success!</div>
            </div>
        </div>
    </a>
    </c:if>
</div>

<script>$(document).ready(totalSum());</script>

<!--<div id="order_ok" class="overlay">
    <div class="popup">
        <h2>Order</h2>
        <a class="close" href="/cart">×</a>

        <div>
            Success!
        </div>
    </div>
</div>-->

<%@include file="jspf/footer.jspf"%>