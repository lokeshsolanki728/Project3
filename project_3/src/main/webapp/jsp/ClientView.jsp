<%@page import="java.util.List"%>
<%@page import="in.co.rays.project_3.controller.ClientCtl"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.rays.project_3.util.HTMLUtility"%>
<%@page import="in.co.rays.project_3.util.DataUtility"%>
<%@page import="in.co.rays.project_3.util.ServletUtility"%>
<%@page import="in.co.rays.project_3.controller.ORSView"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Issue view</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script>
	function validateMobileNo(event) {
		const input = event.target;
		input.value = input.value.replace(/[^0-9.]/g, '');
		if (input.value.length > 0 && input.value[0] <= '5') {

			input.value = '';
		}
	}
	function validateVersion(event) {
		const input = event.target;
		input.value = input.value.replace(/[^0-9.]/g, '');
	}
</script>
<script src="<%=ORSView.APP_CONTEXT%>/js/ValidateToInput.js"></script>
<style type="text/css">
i.css {
	border: 2px solid #8080803b;
	padding-left: 10px;
	padding-bottom: 11px;
	background-color: #ebebe0;
}

.input-group-addon {
	/* box-shadow: 9px 8px 7px #001a33; */
	background-image: linear-gradient(to bottom right, orange, black);
	background-repeat: no repeat;
	background-size: 100%;
	padding-bottom: 11px;
}

.hm {
	background-image: url('<%=ORSView.APP_CONTEXT%>/img/unsplash.jpg');
	background-size: cover;
	padding-top: 6%;
}
</style>

</head>
<body class="hm">
	<div class="header">
		<%@include file="Header.jsp"%>
		<%@include file="calendar.jsp"%>
	</div>
	<div>

		<main>
		<form action="<%=ORSView.CLIENT_CTL%>" method="post">
			<jsp:useBean id="dto" class="in.co.rays.project_3.dto.ClientDTO"
				scope="request"></jsp:useBean>
			<div class="row pt-3">
				<!-- Grid column -->
				<div class="col-md-4 mb-4"></div>
				<div class="col-md-4 mb-4">
					<div class="card input-group-addon">
						<div class="card-body">

							<%
								long id = DataUtility.getLong(request.getParameter("id"));

								if (dto.getId() != null && id > 0) {
							%>
							<h3 class="text-center default-text text-primary">Update
								Issue</h3>
							<%
								} else {
							%>
							<h3 class="text-center default-text text-primary">Add Issue</h3>
							<%
								}
							%>
							<!--Body-->
							<div>
								<%
									List list = (List) request.getAttribute("roleList");
									List list1 = (List) request.getAttribute("list1");
									HashMap map = (HashMap) request.getAttribute("map");
								%>

								<H4 align="center">
									<%
										if (!ServletUtility.getSuccessMessage(request).equals("")) {
									%>
									<div class="alert alert-success alert-dismissible">
										<button type="button" class="close" data-dismiss="alert">&times;</button>
										<%=ServletUtility.getSuccessMessage(request)%>
									</div>
									<%
										}
									%>
								</H4>

								<H4 align="center">
									<%
										if (!ServletUtility.getErrorMessage(request).equals("")) {
									%>
									<div class="alert alert-danger alert-dismissible">
										<button type="button" class="close" data-dismiss="alert">&times;</button>
										<%=ServletUtility.getErrorMessage(request)%>
									</div>
									<%
										}
									%>

								</H4>

								<input type="hidden" name="id" value="<%=dto.getId()%>">

							</div>

							<div class="md-form">

								<span class="pl-sm-5"><b> Date</b> <span
									style="color: red;">*</span></span></br>
								<div class="col-sm-12">
									<div class="input-group">
										<div class="input-group-prepend">
											<div class="input-group-text">
												<i class="fa fa-calendar grey-text" style="font-size: 1rem;"></i>
											</div>
										</div>
										<input type="text" name="date" id="datepicker2"
											readonly="readonly" class="form-control"
											placeholder="Enter Date"
											value="<%=DataUtility.getDateString(dto.getDate())%>">
									</div>
								</div>
								<font color="red" class="pl-sm-5"> <%=ServletUtility.getErrorMessage("date", request)%></font></br>

								<span class="pl-sm-5"><b> Name</b> <span
									style="color: red;">*</span></span> </br>
								<div class="col-sm-12">
									<div class="input-group">
										<div class="input-group-prepend">
											<div class="input-group-text">
												<i class="fa fa-user-alt grey-text" style="font-size: 1rem;"></i>
											</div>
										</div>
										<input type="text" class="form-control" name="name"
											onkeypress="return validateInput(event)"
											placeholder="Enter Name"
											value="<%=DataUtility.getStringData(dto.getName())%>">
									</div>
								</div>
								<font color="red" class="pl-sm-5"> <%=ServletUtility.getErrorMessage("name", request)%></font></br>


								<span class="pl-sm-5"><b> Address</b> <span
									style="color: red;">*</span></span></br>
								<div class="col-sm-12">
									<div class="input-group">
										<div class="input-group-prepend">
											<div class="input-group-text">
												<i class="fa fa-list grey-text" style="font-size: 1rem;"></i>
											</div>
										</div>
										<textarea class="form-control" name="address"
											placeholder="Enter Address" rows="3" cols="3">
											<%=DataUtility.getStringData(dto.getAddress())%></textarea>
									</div>
								</div>
								<font color="red" class="pl-sm-5"> <%=ServletUtility.getErrorMessage("address", request)%></font></br>


								<span class="pl-sm-5"><b> Phone Number</b> <span
									style="color: red;">*</span></span></br>
								<div class="col-sm-12">
									<div class="input-group">
										<div class="input-group-prepend">
											<div class="input-group-text">
												<i class="fa fa-list grey-text" style="font-size: 1rem;"></i>
											</div>
										</div>
										<input type="text" class="form-control" name="phone" rows="3"
											oninput="validateMobileNo(event)" cols="3"
											placeholder="Enter Phone Number" maxlength="10"
											value="<%=DataUtility.getStringData(dto.getPhone())%>">
									</div>
								</div>
								<font color="red" class="pl-sm-5"> <%=ServletUtility.getErrorMessage("phone", request)%></font></br>

								<span class="pl-sm-5"><b> Version</b> <span
									style="color: red;">*</span></span></br>
								<div class="col-sm-12">
									<div class="input-group">
										<div class="input-group-prepend">
											<div class="input-group-text">
												<i class="fa fa-list grey-text" style="font-size: 1rem;"></i>
											</div>
										</div>
										<input type="text" class="form-control" name="version"
											rows="3" oninput="validateVersion(event)" cols="3"
											placeholder="Enter version"
											value="<%=DataUtility.getDouble(dto.getVersion())%>">
									</div>
								</div>
								<font color="red" class="pl-sm-5"> <%=ServletUtility.getErrorMessage("version", request)%></font></br>

								<span class="pl-sm-5"><b>Priority</b><span
									style="color: red;">*</span></span> </br>

								<div class="col-sm-12">
									<div class="input-group">
										<div class="input-group-prepend">
											<div class="input-group-text">
												<i class="fa fa-venus-mars grey-text"
													style="font-size: 1rem;"></i>
											</div>
										</div>

										<%
											String htmlList = HTMLUtility.getList("priority", dto.getPriority(), map);
										%>
										<%=htmlList%></div>
								</div>
								<font color="red" class="pl-sm-5"> <%=ServletUtility.getErrorMessage("priority", request)%></font></br>


								<%
									if (dto.getId() != null && id > 0) {
								%>

								<div class="text-center">

									<input type="submit" name="operation"
										class="btn btn-success btn-md" style="font-size: 17px"
										value="<%=ClientCtl.OP_UPDATE%>"> <input type="submit"
										name="operation" class="btn btn-warning btn-md"
										style="font-size: 17px" value="<%=ClientCtl.OP_CANCEL%>">

								</div>
								<%
									} else {
								%>
								<div class="text-center">

									<input type="submit" name="operation"
										class="btn btn-success btn-md" style="font-size: 17px"
										value="<%=ClientCtl.OP_SAVE%>"> <input type="submit"
										name="operation" class="btn btn-warning btn-md"
										style="font-size: 17px" value="<%=ClientCtl.OP_RESET%>">
								</div>

							</div>
							<%
								}
							%>
						</div>
					</div>
		</form>
		</main>
		<div class="col-md-4 mb-4"></div>

	</div>

</body>
<%@include file="FooterView.jsp"%>

</body>
</html>