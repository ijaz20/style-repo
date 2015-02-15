<%@ include file="/common/taglibs.jsp"%>

<div class="row">
	<div id="online-store-images" class="tabcontent online-coupons-tab">
		<s:iterator var="partner" value="%{partners}" status="rowstatus">
			<div class="col-lg-2 col-md-2 col-sm-2 col-xs-12 partner-bucket"
				id="partner_<s:property value='%{id}'/>">
				<a href="/vstyleu/branches?brand=<s:property
							value='partnerName'/>"><img class="brand-image"
					src="<s:property value="imagePath"/>"> <strong><span class="text-capitalize"><s:property
							value="partnerName" /></span></strong> </a>
				<div class="clearfix"></div>
			</div>
		</s:iterator>
	</div>
</div>