<%@include file="jspf/header.jspf"%>

<link href="<c:url value ="/resources/css/component.css"/>" rel='stylesheet' type='text/css' />
<link href="<c:url value ="/resources/css/projectTSystems.css"/>" rel='stylesheet' type='text/css' />
<%@include file="jspf/brand_list.jspf"%>
<script src="<c:url value="/resources/js/sort.js"/>"></script>
<div class="new-product">
	<div class="mens-toolbar">
		<div class="sort">
			<div class="sort-by">
				<label>Sort By</label>
				<select onchange="sort(this)">
					<option value="0">-</option>
					<option value="1">Price</option>
				</select>

			</div>
		</div>
		<div class="clearfix"></div>
	</div>
	<div id="cbp-vm" class="cbp-vm-switcher cbp-vm-view-grid">
		<div class="cbp-vm-options">
			<a class="cbp-vm-icon cbp-vm-grid cbp-vm-selected" data-view="cbp-vm-view-grid" title="grid"></a>
			<a class="cbp-vm-icon cbp-vm-list" data-view="cbp-vm-view-list" title="list"></a>
		</div>
		<div class="clearfix"></div>
		<ul>
			<c:if test="${empty(phoneList)}">
				0 phone found
			</c:if>
			<div id="goods">
			<c:forEach var="phone" items="${phoneList}" >
				<li class="product___box">
					<a class="cbp-vm-image" href="/phoneDetail/${phone.id}">
						<div class="simpleCart_shelfItem">
							<div class="view view-first">
								<div class="inner_content clearfix">
									<div class="product_image">
										<div class="phone_image">
											<c:if test="${phone.images>0}">
												<img src="/resources/images/phoneImages/${phone.id}.jpg"
													 class="img-responsive" alt=""/>
											</c:if>
											<c:if test="${phone.images==0}">
												<img src="/resources/images/phoneImages/standart_phone_img.png"
													 class="img-responsive" alt=""/>
											</c:if>
										</div>
										<div class="mask">
											<div class="info">Quick View</div>
										</div>
										<div class="product_container">
											<div class="cart-left">
												<p class="title">${phone.name}</p>
											</div>
											<div class="pricey">
												<c:if test="${phone.quantityStock>0}">
												<span class="item_price">${phone.price}</span>
												</c:if>
											</div>
											<div class="clearfix">
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</a>
					<div class="cbp-vm-details"><p class="brand">${phone.brand.brand}</p></div>
					<c:if test="${phone.quantityStock > 0}">
					<a class="cbp-vm-icon cbp-vm-add item_add" id="${phone.id}"
					   onclick="addToCart(${phone.id})" name="modal">Add to cart</a>
					</c:if>
					<security:authorize access="hasRole('ROLE_ADMIN')">
						<a class="cbp-vm-icon cbp-vm-add" href="/admin/editPhoneDetail/${phone.id}">Edit</a>
					</security:authorize>
				</li>
			</c:forEach>
			</div>
		</ul>
	</div>
	<script src="/resources/js/cbpViewModeSwitch.js" type="text/javascript"></script>
	<script src="/resources/js/classie.js" type="text/javascript"></script>
</div>
<div id="boxes">
	<div id="dialog" class="window">
		<div class="top"><a href="#" class="link close"><img src="/resources/images/close_1.png"></a></div>
		<div class="content">Not enough on stock!</div>
	</div>
</div>
<div class="clearfix"></div>
</div>
<div class="clearfix"></div>
</div>
<%@include file="jspf/footer.jspf"%>
