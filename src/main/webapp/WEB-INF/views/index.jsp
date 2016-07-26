
<%@include file="jspf/header.jspf"%>
<div class="banner">
	<div class="container">
		<div class="banner-bottom">
			<div class="banner-bottom-left">
				<h2>B<br>U<br>Y</h2>
			</div>
			<div class="banner-bottom-right">
				<div  class="callbacks_container">
					<ul class="rslides" id="slider4">
							<div class="banner-info">
								<p>Start your shopping here...</p>
							</div>
					</ul>
				</div>

			</div>
			<div class="clearfix"> </div>
		</div>
		<div class="shop">

		</div></div>
</div>
<div class="other-products">
	<div class="container">
		<h3 class="like text-center">Top Phones</h3>
		<ul id="flexiselDemo3">
			<c:forEach var="entry" items="${top10}">
				<c:if test="${entry.key.quantityStock > 0}">
				<li><a href="/phoneDetail/${entry.key.id}">
					<c:if test="${entry.key.images>0}">
					<img src="/resources/images/phoneImages/${entry.key.id}.jpg" class="img-responsive" alt="" />
					</c:if>
					<c:if test="${entry.key.images==0}">
						<img src="/resources/images/phoneImages/standart_phone_img.png" class="img-responsive" alt="" />
					</c:if>
				</a>
					<div class="product liked-product simpleCart_shelfItem">
						<a class="like_name" href="/phoneDetail/${entry.key.id}" name="modal">${entry.key.name}</a>
						<p><a class="item_add" id="${entry.key.id}" onclick="addToCart(${entry.key.id})"><i></i>
							<span class="item_price">${entry.key.price} rub</span></a></p>
					</div>

				</li>
				</c:if>
			</c:forEach>
		</ul>
		<script type="text/javascript">
			$(window).load(function() {
				$("#flexiselDemo3").flexisel({
					visibleItems: 3,
					animationSpeed: 1000,
					autoPlay: true,
					autoPlaySpeed: 3000,
					pauseOnHover: true,
					enableResponsiveBreakpoints: true,
					responsiveBreakpoints: {
						portrait: {
							changePoint:480,
							visibleItems: 1
						},
						landscape: {
							changePoint:640,
							visibleItems: 2
						},
						tablet: {
							changePoint:768,
							visibleItems: 3
						}
					}
				});
			});
		</script>
		<script type="text/javascript" src="<c:url value="/resources/js/jquery.flexisel.js"/>"></script>

	</div>
</div>
<div id="boxes">
	<div id="dialog" class="window">
		<div class="top"><a href="#" class="link close"><img src="/resources/images/close_1.png"></a></div>
		<div class="content">Not enough on stock!</div>
	</div>
</div>
<div id="mask"></div>
<%@include file="jspf/footer.jspf"%>