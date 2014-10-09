<%@ include file="/WEB-INF/jsp/include.jsp" %>
		<h2>
   			<spring:message code="employee.employeeList.list"/>:
   		</h2>
		
		
	<div class="table">
               <table id = "tabb"> 
                 <tr>
                  <td>
					<spring:message code="employee.employeeList.firstName"/>
				  </td>
                  <td>
					<spring:message code="employee.employeeList.lastName"/>
				  </td>
                  <td>
					<spring:message code="employee.employeeList.phoneNumber"/>
				  </td>
                  <td>
					<spring:message code="employee.employeeList.email"/>
				  </td>
				  <td></td>
				  <td></td>
                 </tr>
					<c:forEach items="${employeeList}" var="employee">
						<tr>
							<td>  <c:out value="${employee.firstName}"/>  </td>
							<td>  <c:out value="${employee.lastName}"/>  </td>
							<td>  <c:out value="${employee.phoneNumber}"/>  </td>
							<td>  <c:out value="${employee.email}"/>  </td>
							<td> 
							  <form name="editemployee" id="editemp" action="<c:url value="employees/edit"/>" method="post">
                                  <input type="hidden" name="id" value="${employee.id}" />
                                  <input type="submit" value="<spring:message code="common.button.edit"/>" class = "myButton"/>
                              </form>
							</td>
							<td> 
							  <!--a href="<c:url value="employees/delete/${employee.id}"/>.json" id = "delemp" class = "myButton">Delete</a-->
							  <form name="deleteemployee" class="delemp" action="<c:url value="employees/delete"/>" method="POST">
                                  <input type="hidden" name="id" value="${employee.id}" />
                                  <input type="submit" value="<spring:message code="common.button.delete"/> ${employee.id}" class = "delempbtn"/>
                              </form>
							</td>
						</tr>
					</c:forEach>   
               </table>
	</div>
	</br>
	<div id="page_numbers">
			  <table>  <tr>
			   <td>
			<%--Displaying First link except for the 1st page--%>   
			   <form name="firstlink" id="firstlink" action="<c:url value="employees/page"/>" method="get">
				 <input type="hidden" name="currentPage" value = "1" />
                 <input type="submit" value="|<<" class = "myButtonTwo"/>
               </form>
			   </td>

			   <td>
			<%--Displaying Previous link except for the 1st page--%>
			   <form name="previouslink" id="previouslink" action="<c:url value="employees/page"/>" method="get">
				 <input type="hidden" name="currentPage" value = "${currentPage - 1}" />
                 <input type="submit" value="<" class = "myButtonTwo"/>
               </form>
			   </td>
 
			<%--Displaying Page numbers--%>
				<td>
					<div id = "page">
					<c:out value="${currentPage}"/>/<c:out value="${noOfPages}"/> 
					</div>
				</td>

				<td>
			<%--Displaying Next link--%>
			   <form name="nextlink" id="nextlink" action="<c:url value="employees/page"/>" method="get">
				 <input type="hidden" name="currentPage" value = "${currentPage + 1}" />
                 <input type="submit" value=">" class = "myButtonTwo"/>
               </form>
			   </td>

			   <td>
			<%--Displaying Last link--%>
			   <form name="lastlink" id="lastlink" action="<c:url value="employees/page"/>" method="get">
				 <input type="hidden" name="currentPage" value = "${noOfPages}" />
                 <input type="submit" value=">>|" class = "myButtonTwo"/>
               </form>
			   </td>
			   </tr>
			   </table>
			   </div>
	</br>
	   <form name="newemployee" id="newemp" action="<c:url value="employees/new"/>" method="post">
		 <input type="hidden" name="id" value = "new" />
		 <input type="submit" value="<spring:message code="employee.button.newemployee"/>" class = "myButton"/>
	   </form>
	</br>
	
	<script>
		$(document).ready(function(){
		var currentPage = 1;
		var noOfPages = ${noOfPages};
		
		var loading = $('<div id="loading"/>');
		loading.prepend('<img src="${loadgif}" />');
		
		if (currentPage == 1){
			$("#firstlink").hide();
			$("#previouslink").hide();
		}
		if (currentPage == noOfPages){
			$("#nextlink").hide();
			$("#lastlink").hide();
		}
			
			$("#nextlink").click(function() {
				currentPage += 1;
				$(".table").empty().append(loading);

				$.ajax
				({ type: $("#nextlink").attr("method"),
				   url: $("#nextlink").attr("action"),
				   data: $("#nextlink").serialize(),
				   success: function(html) {
					$(".table").empty().append(html);
						if (currentPage == noOfPages){
							$("#nextlink").hide();
							$("#lastlink").hide();
							$("#firstlink").show();
							$("#previouslink").show();
						}
					}
				});
				
				$("#page").text(currentPage + " / " + noOfPages);
				return false;
			});
			
			$("#firstlink").click(function() {
				currentPage = 1;
				$(".table").empty().append(loading);

				$.ajax
				({ type: $("#firstlink").attr("method"),
				   url: $("#firstlink").attr("action"),
				   data: $("#firstlink").serialize(),
				   success: function(html) {
					$(".table").empty().append(html);
						if (currentPage == 1){
							$("#nextlink").show();
							$("#lastlink").show();
							$("#firstlink").hide();
							$("#previouslink").hide();
						}
					}
				});
				
				$("#page").text(currentPage + " / " + noOfPages);
				return false;
			});
			
			$("#previouslink").click(function() {
				currentPage -= 1;
				$(".table").empty().append(loading);

				$.ajax
				({ type: $("#previouslink").attr("method"),
				   url: $("#previouslink").attr("action"),
				   data: $("#previouslink").serialize(),
				   success: function(html) {
					$(".table").empty().append(html);
						if (currentPage == 1){
							$("#nextlink").show();
							$("#lastlink").show();
							$("#firstlink").hide();
							$("#previouslink").hide();
						}
					}
				});
				
				$("#page").text(currentPage + " / " + noOfPages);
				return false;
			});
			
			$("#lastlink").click(function() {
				currentPage = ${noOfPages};
				$(".table").empty().append(loading);

				$.ajax
				({ type: $("#lastlink").attr("method"),
				   url: $("#lastlink").attr("action"),
				   data: $("#lastlink").serialize(),
				   success: function(html) {
					$(".table").empty().append(html);
						if (currentPage == noOfPages){
							$("#nextlink").hide();
							$("#lastlink").hide();
							$("#firstlink").show();
							$("#previouslink").show();
						}
					}
				});
				
				$("#page").text(currentPage + " / " + noOfPages);
				return false;
			});
			
			$(".delempbtn").on("click",function(e) {
				e.preventDefault();
				var conBox = confirm("Are you sure ?");
				if(conBox){
					$.ajax
					({ 
					   type: $(".delemp").attr("method"),
					   url: $(".delemp").attr("action"),
					   data: $(this).parent().serialize(),
					   success: function(response) {
						alert("done deleted." + response);
						$(".table").load("<c:url value="employees/list"/> #tabb");
				//		$(e.target).closest("tr").remove;
						
						}
					});
					return false;
				} else {
				//	$(this).dialog("close");
					return false;
				}
			});
			
			
			
			$("#newemp").click(function() {
				$.ajax
				({ type: $("#newemp").attr("method"),
				   url: $("#newemp").attr("action"),
				   data: $("#newemp").serialize(),
				   success: function(html) {

						
					}
				});
				return false;
			});
			
			$("#editemp").click(function() {
				$.ajax
				({ type: $("#editemp").attr("method"),
				   url: $("#editemp").attr("action"),
				   data: $("#editemp").serialize(),
				   success: function(html) {

						
					}
				});
				return false;
			});
		
		});
	</script>