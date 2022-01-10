<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container">


	<div class="row">
	<c:if test="${not empty message}">	
	<div class="col-xs-12">			
				<div class="alert alert-success alert-dismissible">${message}
				
				<button type ="button" class="close" data-dismiss="alert">&times;</button>
				
				${message}
				
				</div>				
			</div>
			
			</c:if>

		<div class="col-md-offset-2 col-md-8">

			<div class="panel panel-primary">

				<div class="panel-heading">

					<h4>Product Management</h4>

				</div>
				<div class="panel-body">

					<!-- Form Elements -->

					<sf:form class="form-horizontal"  modelAttribute="product" action="/manage/products" method="POST" enctype="multipart/form-data">

						<div class="form-group">

							<label class="control-label col-md-4" for="name">Enter
								Product Name:</label>

							<div class="col-md-8">
								<sf:input type="text" path="name" id="name" class="form-control"
									placeholder="Product Name" /> 
									<sf:errors path="name" cssClass="help-block" element="em"/> 

							</div>
						</div>
						
						<div class="form-group">

							<label class="control-label col-md-4" for="brand">Enter
								Brand Name:</label>

							<div class="col-md-8">
								<sf:input type="text" path="brand" id="brand" class="form-control"
									placeholder="Brand Name" /> 
									<sf:errors path="brand" cssClass="help-block" element="em"/> 

							</div>
						</div>
						
						<div class="form-group">
							<label class="control-label col-md-4" for="description">Product Description:</label>
							<div class="col-md-8">
								<sf:textarea path="description" id="description" class="form-control"
									placeholder="Write a description for the product!"/>
									<sf:errors path="description" cssClass="help-block" element="em"/> 
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-label col-md-4" for="unitPrice">Enter Unit Price: </label>
							<div class="col-md-8">
								<sf:input type="number" path="unitPrice" id="unitPrice" class="form-control"
									placeholder="Unit Price In &#8377;" />
									<sf:errors path="unitPrice" cssClass="help-block" element="em"/> 
								
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-label col-md-4" for="quantity">Quantity Available:</label>
							<div class="col-md-8">
								<sf:input type="number" path="quantity" id="quantity" class="form-control"
									placeholder="Quantity Available" />
							
							</div>
						</div>
						<!-- File element for image upload -->
						<div class="form-group">
							<label class="control-label col-md-4" for="file">Select an Image:</label>
							<div class="col-md-8">
								<sf:input type="file" path="file" id="file" class="form-control"/>
								<sf:errors path="file" cssClass="help-block" element="em"/> 
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-label col-md-4" for="categoryId"> Select Category:</label>
							<div class="col-md-8">
							<sf:select class="form-control" id="categoryId" path="categoryId"
							items="${categories}"
							itemLabel="name"
							itemValue="id"
							
							/>
							
					
					
					<c:if test="${product.id==0}">
					<div class="text-right">
					<br/>
					<button type="button" class="btn btn-warning btn-xs" data-toggle="modal" data-target="#myCategoryModal">Add New Category</button>
					</div>
					</c:if>
									
							</div>
							
						</div>
						
						<div class="form-group">
						
							<div class="col-md-offset-4 col-md-8">
								<input type="submit" name="submit" id="submit" class="btn btn-primary"
									value="Submit" /> 
							<!-- Hidden fields for products -->
							<sf:hidden path="id"/>
									<sf:hidden path="code"/>
									<sf:hidden path="supplierId"/>
									<sf:hidden path="active"/>
									<sf:hidden path="purchases"/>
									<sf:hidden path="views"/>
									
							</div>
						</div>

					</sf:form>

				</div>
			</div>

		</div>
	</div>
<div class="row">
				
		
		<div class='col-xs-12'>
		<h3>Available Products</h3>
		<hr/>
		</div>
		<div class='col-xs-12'>
		
		<div style = "overflow:auto">
		
		<!-- Products table for Admin -->
			<table id="adminproductsTable" class="table table-striped table-bordered">
							
				<thead>					
					<tr>					
						<th>Id</th>
						<th>&#160;</th>
						<th>Name</th>
						<th>Brand</th>
						<th>Quantity</th>
						<th>Unit Price</th>
						<th>Active</th>				
						<th>Edit</th>
					</tr>					
				</thead>
				
				
				
				<tfoot>
					<tr>					
						<th>Id</th>
						<th>&#160;</th>
						<th>Name</th>
						<th>Brand</th>
						<th>Quantity</th>
						<th>Unit Price</th>
						<th>Active</th>				
						<th>Edit</th>
					</tr>									
				</tfoot>
				
							
			</table>
		
		
		
		</div>
		
		</div>
		
	</div>


<!-- Modal -->
	<div class="modal fade" id="myCategoryModal" role="dialog" tabindex="-1">
	<div class="modal-dialog" role="document">
	<div class="modal-content">
	<!-- Modal Header -->
	<div class="modal-header">
	 <button type="button" class="close" data-dismiss="modal">
	 <span>&times;</span>
	 </button>
	  <h4 class="modal-title" id="myModalLabel">Add New Category</h4>
	</div>
	<div class="modal-body">
	
	<!-- Category Form -->
	   <sf:form id="categoryForm" class="form-horizontal" modelAttribute="category" action="/manage/category" method="POST">
	   
	   <div class="form-group">
	   
	   <label class="control-label col-md-4" for="category_name">Category Name:</label>
	   
	   <div class="col-md-8 validate">
						<sf:input type="text" path="name" id ="category_name" class="form-control" /> 
					</div>
	   
	   </div>
	   
	    <div class="form-group">
	   
	   <label class="control-label col-md-4" for="category_description">Category Description:</label>
	   
	   <div class="col-md-8 validate">
						<sf:textarea cols="" rows="5" path="description" id="category_description" class="form-control" /> 
					</div>
	   
	   </div>
	   <div class="form-group">				
					<div class="col-md-offset-4 col-md-8">					
						<input type="submit" name="submit" value="Add Category" class="btn btn-primary"/>						
					</div>
				</div>	 
	   
	   </sf:form>
	</div>
	</div>
	
	</div>
	
	</div>
</div>