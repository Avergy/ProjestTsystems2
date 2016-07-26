
<%@include file="jspf/header.jspf"%>
<link href="<c:url value ="/resources/css/projectTSystems.css"/>" rel='stylesheet' type='text/css' />
<%@include file="jspf/brand_list.jspf"%>

<div class="new-product">
	<div class="col-md-5 zoom-grid">
		<div class="phone_image_item">
			<c:if test="${phone.images>0}">
				<img src="/resources/images/phoneImages/${phone.id}.jpg" data-imagezoom="true" class="img-responsive" alt="" />
			</c:if>
			<c:if test="${phone.images==0}">
				<img src="/resources/images/phoneImages/standart_phone_img.png" data-imagezoom="true" class="img-responsive" alt="" />
			</c:if>
		</div>
		<c:if test="${phone.images>1}">
			<img src="/resources/images/phoneImages/${phone.id}.jpg" data-imagezoom="true" class="img-responsive" alt="" />
		</c:if>
	</div>
	<div class="col-md-7 dress-info">
		<div class="dress-name">
			<h3>${phone.name}</h3>
			<span>${phone.price} rub.</span>
			<div class="clearfix"></div>
		</div>
		<div class="span span1">
			<p class="left">BRAND</p>
			<p class="right">${phone.brand.brand}</p>
			<div class="clearfix"></div>
		</div>
		<div class="span span2">
			<p class="left">COLOR</p>
			<p class="right">${phone.color.color}</p>
			<div class="clearfix"></div>
		</div>
		<div class="span span3">
			<p class="left">WEIGHT</p>
			<p class="right">${phone.weight}</p>
			<div class="clearfix"></div>
		</div>
		<c:if test="${phone.quantityStock > 0}">
			<a class="cbp-vm-icon cbp-vm-add item_add item_phone_detail" id="${phone.id}"
			   	onclick="addToCart(${phone.id})" name="modal">Add to cart</a>
		</c:if>
		<security:authorize access="hasRole('ROLE_ADMIN')">
			<a class="cbp-vm-icon cbp-vm-add item_phone_detail" href="/admin/editPhoneDetail/${phone.id}">Edit</a>
		</security:authorize>


		<script src="/resources/js/imagezoom.js"></script>

	</div>
	<div class="clearfix"></div>
	<div class="reviews-tabs"></div>
</div>
<div class="clearfix"></div>
</div>
</div>
<div id="boxes">
	<div id="dialog" class="window">
		<div class="top"><a href="#" class="link close"><img src="/resources/images/close_1.png"></a></div>
		<div class="content">Not enough on stock!</div>
	</div>
</div>


<%@include file="jspf/footer.jspf"%>