<%@ include file="/WEB-INF/jsp/include.jsp" %>
		<h2>
   			<spring:message code="employee.employeeList.list"/>:
   		</h2>
		
		
	<div class="table">
               <table> 
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
							  <form name="editemployee" id="editemp" method="post">
                                  <input type="hidden" name="id" value="${employee.id}" />
                                  <input type="submit" value="<spring:message code="common.button.edit"/>" class = "myButton"/>
                              </form>
							</td>
							<td> 
							  <form name="deleteemployee" id="delemp" method="post" onsubmit="return ConfirmDelete();">
                                  <input type="hidden" name="id" value="${employee.id}" />
                                  <input type="submit" value="<spring:message code="common.button.delete"/>" class = "myButton"/>
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
			   <c:if test="${currentPage != 1}">
			   <form name="firstlink" id="firstlink" action="<c:url value="employees"/>" method="get">
				 <input type="hidden" name="currentPage" value = "1" />
                 <input type="submit" value="|<<" class = "myButtonTwo"/>
               </form>
				</c:if>
			   </td>

			   <td>
			<%--Displaying Previous link except for the 1st page--%>
			   <c:if test="${currentPage != 1}">
			   <form name="previouslink" id="previouslink" action="<c:url value="employees"/>" method="get">
				 <input type="hidden" name="currentPage" value = "${currentPage - 1}" />
                 <input type="submit" value="<" class = "myButtonTwo"/>
               </form>
				</c:if>
			   </td>
 
			<%--Displaying Page numbers--%>
            <td> <c:out value="${currentPage}"/>/<c:out value="${noOfPages}"/> </td>

				<td>
			<%--Displaying Next link--%>
			   <c:if test="${currentPage < noOfPages}">
			   <form name="nextlink" id="nextlink" action="<c:url value="employees"/>" method="get">
				 <input type="hidden" name="currentPage" value = "${currentPage + 1}" />
                 <input type="submit" value=">" class = "myButtonTwo"/>
               </form>
				</c:if>
			   </td>

			   <td>
			<%--Displaying Last link--%>
			   <c:if test="${currentPage < noOfPages}">
			   <form name="lastlink" id="lastlink" action="<c:url value="employees"/>" method="get">
				 <input type="hidden" name="currentPage" value = "${noOfPages}" />
                 <input type="submit" value=">>|" class = "myButtonTwo"/>
               </form>
				</c:if>
			   </td>
			   </tr>
			   </table>
			   </div>
	</br>
	   <form name="newemployee" id="newemp" action="#" method="post">
		 <input type="hidden" name="id" value = "new" />
		 <input type="submit" value="<spring:message code="employee.button.newemployee"/>" class = "myButton"/>
	   </form>
	</br>
	
	<script>
		$(document).ready(function(){
			var currentPage = 1;
			var noOfPages = ${noOfPages};
			
			
			$("#nextlink").click(function() {
				currentPage += 1;
				
				var loading = $('<div id="loading"/>');
				loading.prepend('<img src="${loadgif}" />');
				
				$(".table").empty().append(loading);

				$.ajax
				({ type: $("#nextlink").attr("method"),
				   url: $("#nextlink").attr("action"),
				   data: $("#nextlink").serialize(),
				   success: function(html) {
					$(".table").empty().append(html);
					}
				});
				
			//	alert( "next link, page = " + currentPage + " / " + noOfPages);
				return false;
			});
			
			$("#firstlink").click(function() {
				currentPage = 1;
				alert( "first link, page = " + currentPage + " / " + noOfPages);
				return false;
			});
			
			$("#previouslink").click(function() {
				currentPage -= 1;
				alert( "previous link, page = " + currentPage + " / " + noOfPages);
				return false;
			});
			
			$("#lastlink").click(function() {
				currentPage = ${noOfPages};
				alert( "last link, page = " + currentPage + " / " + noOfPages);
				return false;
			});
		
		});
	</script>