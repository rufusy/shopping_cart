$("#new-user-group-save").click(function(e){
	e.preventDefault();
	toastr.success('New user group created.');
	toastr.error('Error creating new user group.')

	// let form = $('#new-user-group-form');
	// $.ajax({
	// 	type: 'POST',
	// 	url: form.attrr('action'),
	// 	data: form.serialize(),
	// 	dataType: 'json',
	// 	success: function(data){
	// 		console.log('New user group created.')
	// 		toastr.success('New user group created.');
	// 	},
	// 	error: function(data){
	// 		console.log('Error creating user groups: '+ data);
	// 		toastr.error('Lorem ipsum dolor sit amet, consetetur sadipscing elitr.')
	// 	}
	// });
});

