// solving the active menu problem
switch (menu) {

	case 'About Us':
		$('#about').addClass('active');
		break;
	case 'Contact Us':
		$('#contact').addClass('active');
		break;
	case 'All Products':
		$('#listProducts').addClass('active');
		break;
	case 'Manage Products':
		$('#manageProducts').addClass('active');
		break;
	case 'Shopping Cart':
		$('#userModel').addClass('active');
		break;
	default:
		if (menu == "Home")
			break;
		$('#listProducts').addClass('active');
		$('#a_' + menu).addClass('active');
		break;
}



// code for jquery dataTable
var $table = $('#productListTable');

// execute the below code only where we have this table
if ($table.length) {
	// console.log('Inside the table!');

	var jsonUrl = '';
	if (window.categoryId == '') {
		jsonUrl = 'http://localhost:8080/all/products/1';
	} else {
		jsonUrl = 'http://localhost:8080/category/1/' + window.categoryId;
	}

	$table
		.DataTable({

			lengthMenu: [[3, 5, 10, -1],
			['3 Records', '5 Records', '10 Records', 'ALL']],
			pageLength: 5,
			ajax: {
				url: jsonUrl,
				dataSrc: ''
			},
			columns: [

				{
					data: 'code',
					mRender: function(data, type, row) {

						return '<img src="/assets/images/' + data + '.jpg" class ="dataTableImg"/>';
					}

				},

				{
					data: 'name'
				},
				{
					data: 'brand'
				},
				{
					data: 'unitPrice',
					mRender: function(data, type, row) {
						return '&#8377; ' + data
					}

				},
				{
					data: 'quantity',
					mRender: function(data, type, row) {

						if (data < 1) {
							return '<span style="color:red">Out of Stock!</span>';
						}

						return data;

					}

				},
				{

					data: 'id',
					bSortable: false,
					mRender: function(data, type, row) {

						var str = "";
						str += '<a href="/show/' + data + '/product" class="btn btn-primary"><span class="glyphicon glyphicon-eye-open"></span></a> &#160;';

						if (row.quantity < 1) {
							str += '<a href="javascript:void(0)" class="btn btn-success disabled"><span class="glyphicon glyphicon-shopping-cart"></span></a>';
						}


						else {
							str += '<a href="/cart/add/' + data + '/product" class="btn btn-success"><span class="glyphicon glyphicon-shopping-cart"></span></a>';
						}
						return str;
					}

				}

			]
		});
}



/* dismissing the alert message after 3 seconds */
$alert = $('.alert');
if ($alert.length) {
	setTimeout(function() {
		$alert.fadeOut('slow');
	}, 3000)
}


//--------------------------------------------------------------------------



// data table for admin


var $adminProductsTable = $('#adminproductsTable');

// execute the below code only where we have this table
if ($adminProductsTable.length) {
	// console.log('Inside the table!');

	var jsonUrl = 'http://localhost:8080/admin/all/products';
	

	$adminProductsTable
		.DataTable({

			lengthMenu: [[10, 30, 50, -1],
			['10 Records', '30 Records', '50 Records', 'ALL']],
			pageLength: 30,
			ajax: {
				url: jsonUrl,
				dataSrc: ''
			},
			columns: [
			
			{
			data:'id',
			
			
			},

				{
					data: 'code',
					mRender: function(data, type, row) {

						return '<img src="/assets/images/' + data + '.jpg" class ="adminDataTableImg"/>';
					}

				},

				{
					data: 'name'
				},
				{
					data: 'brand'
				},
				{
					data: 'quantity',
					mRender: function(data, type, row) {

						if (data < 1) {
							return '<span style="color:red">Out of Stock!</span>';
						}

						return data;

					}

				},
				{
					data: 'unitPrice',
					mRender: function(data, type, row) {
						return '&#8377; ' + data
					}

				},
				{
					                data : 'active',
									bSortable : true,
									mRender : function(data, type, row) {
										var str = '';
										if(1) {											
											str += '<label class="switch"> <input type="checkbox" value="'+row.id+'" checked="checked">  <div class="slider round"> </div></label>';
											
										}else {
											str += '<label class="switch"> <input type="checkbox" value="'+row.id+'">  <div class="slider round"> </div></label>';
										}
										
										return str;
									}
					
				},
				
				{
				
				                    data : 'id',
									bSortable : false,
									mRender : function(data, type, row) {

										var str = '';
										str += '<a href="/manage/'+data+'/product"class="btn btn-warning">';
										str +='<span class="glyphicon glyphicon-pencil"></span></a>';

										return str;
									}
				}

			],
			initComplete: function () {
			
			var api = this.api();
			api.$('.switch input[type="checkbox"]').on('change', function() {


	var checkbox = $(this);
	var checked = checkbox.prop('checked');
	var dMsg = (checked) ? 'You want to activate the product?' : 'You want to deactivate the product?';
	var value = checkbox.prop('value');
	bootbox.confirm({

		size: 'medium',
		title: 'Product Activation & Deactivation',
		message: dMsg,
		callback: function(confirmed) {

			if (confirmed) {
				console.log(value);
				
				var activationUrl= '/manage/product/'+value+'/activation';
				$.post(activationUrl,function(data){
				bootbox.alert({

					size: 'medium',
					title: 'Information',
					message: data

				});
				
				});
				
			}
			else {
				checkbox.prop('checked', !checked);
			}

		}

	});

});
			
			}
		});
}


//-------------------------------------------------------------------------
// validation code for category

$categoryForm = $('#categoryForm');
if($categoryForm.length) {
		
		$categoryForm.validate({			
				rules: {
					name: {
						required: true,
						minlength: 2
					},
					description: {
						required: true,
						//minlength: 3					
					}				
				},
				messages: {					
					name: {
						required: 'Please add the category name!',
						minlength: 'The category name should not be less than 2 characters'
					},
					description: {
						required: 'Please add a description for this category!'
					// 	minlength: 'Please enter atleast five characters'
					}					
				},
				errorElement : 'em',
				errorPlacement : function(error, element) {
				//add the class of help-block
				error.addClass('help-block')
				//add the error element after the input element
				error.insertAfter(element);
				}				
			}
		
		);
		
	}
	



