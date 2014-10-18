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
                                  <input type="submit" value="<spring:message code="common.button.delete"/>" class = "delempbtn"/>
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
	
	<c:url value="employees/list" var="adres"/>
	
	<script>
		$(document).ready(function(){
		
		initPaginationButtons("${noOfPages}");
		initActionButtons("${adres}");	
		
		});
	</script>