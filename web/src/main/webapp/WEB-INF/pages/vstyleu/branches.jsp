<%@ include file="/common/taglibs.jsp"%>

<div class="col-md-3 col-sm-3 col-lg-3 left-nav">
	<div class="leftnav-products">
		<strong>Brands</strong>
		<ul class="leftnav-product-list text-capitalize">
			<c:forEach items="${availablePartners}" var="brand">
				<li><label class="brand checkbox" for="${brand.label}">
						<input type="checkbox" value="${brand.value}"
						id="brand_${brand.label}" data-toggle="checkbox" />
						${brand.label}
				</label></li>
			</c:forEach>
		</ul>
	</div>
</div>

<div id="branch-list" class="col-lg-9 col-md-9 col-sm-9 col-xs-12">
	<div class="row">
		<s:iterator var="branch" value="%{branches}" status="rowstatus">
			<div class="panel panel-default">
				<div class="panel-heading branch-title"><strong><s:property value="branchName"/></strong></div>
				<div class="panel-body">
					<div id="data" class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<div class="col-lg-10 col-md-10 col-sm-10 col-xs-12">
							<span class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-capitalize"> Address : <s:property value="address1"/>,
							<s:property value="address2"/></span>
							<span class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-capitalize">Landmark : <s:property value="landmark"/></span>
							<span class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-capitalize">Opening Time : <s:property value="openTime"/></span>
							<span class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-capitalize">Closing Time : <s:property value="closeTime"/></span>
						</div>
						
						<div class="col-lg-2 col-md-2 col-sm-2 col-xs-12 text-capitalize">
							<br/>
							<span class="pull-right"><a href="/vstyleu/products"><button type="button" class="btn btn-danger active">View Products</button></a></span><br/>
						</div>
					</div>
					<div id="review" class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<div class="col-lg-2 col-md-2 col-sm-2 col-xs-12 text-capitalize pull-right">
						<span class="pull-right"><img class="review-img" src="/images/review.jpg" alt="<s:property value="branchName"/> review" title="<s:property value="branchName"/> review"></img></span>
						</div>
					</div>
				</div>
			</div>
		</s:iterator>
	</div>
</div>

