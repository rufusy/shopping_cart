<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta http-equiv="x-ua-compatible" content="ie=edge">

  <title> Shopping Cart | User groups</title>

  <jsp:include page="../includes/styles.jsp" />  

</head>
<!--
BODY TAG OPTIONS:
=================
Apply one or more of the following classes to to the body tag
to get the desired effect
|---------------------------------------------------------|
|LAYOUT OPTIONS | sidebar-collapse                        |
|               | sidebar-mini                            |
|---------------------------------------------------------|
-->

<body class="hold-transition sidebar-mini">
  <div class="wrapper">
    <!-- Navbar -->
    <jsp:include page="../includes/top_navs.jsp" />  
    <!-- /.navbar -->

    <!-- Main Sidebar Container -->
    <jsp:include page="../includes/aside_navs.jsp" />  
    <!-- /.Main Sidebar Container -->

   <!-- Content Wrapper. Contains page content -->
   <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <div class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <!-- <h1 class="m-0 text-dark">Dashboard</h1> -->
          </div><!-- /.col -->
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="#">Home</a></li>
              <li class="breadcrumb-item active">users groups</li>
            </ol>
          </div><!-- /.col -->
        </div><!-- /.row -->
      </div><!-- /.container-fluid -->
    </div>
    <!-- /.content-header -->

  <!-- Main content -->
  <section class="content">
      <div class="container-fluid">
        <div class="row">
          <div class="col-12">
            <div class="card">
              <div class="card-header">
                <div class="float-right"> 
                      <button type="button" class="btn btn-block btn-primary btn-flat" data-toggle="modal" data-target="#new-user-group-modal">create</button>
                </div>
              </div>
              <!-- /.card-header -->
              <div class="card-body">
                <table id="user-groups-table" class="table table-bordered table-striped">
                  <thead>
                  <tr>
                    <th>No</th>
                    <th>Id</th>
                    <th>Name</th>
                    <th>Permissions</th>
                  </tr>
                  </thead>
                  <tbody id="tBody">
                    <!--- Append table -->
                  </tbody>
                </table>
              </div>
              <!-- /.card-body -->
            </div>
            <!-- /.card -->
          </div>
          <!-- /.col -->
        </div>
        <!-- /.row -->
      </div>
      <!-- /.container-fluid -->

      <!--Modals-->
      <jsp:include page="./modal.jsp" /> 

    </section>
    <!-- /.content -->

  </div>
  <!-- /.content-wrapper -->

    <!-- Control Sidebar -->
    <aside class="control-sidebar control-sidebar-dark">
      <!-- Control sidebar content goes here -->
    </aside>
    <!-- /.control-sidebar -->

    <!-- Main Footer -->
    <jsp:include page="../includes/footer.jsp" />  
  </div>
  <!-- ./wrapper -->

  <!-- REQUIRED SCRIPTS -->
  <jsp:include page="../includes/scripts.jsp" />  

  <script>

    // $(document).ready(function(){
    //     var leaguesTable = $('#leagues-table').DataTable({
    //         processing: true,
    //         serverSide: true,
    //         ajax: '',
    //         columns: [
    //             {
    //                 data:'id', 
    //                 name:'id', 
    //                 'visible':false
    //             },
    //             {
    //                 data:'DT_RowIndex', 
    //                 name: 'DT_RowIndex', 
    //                 orderable: false,
    //                 searchable: false
    //             },
    //             {
    //                 data:'name',
    //                 name:'name'
    //             },
    //             {
    //                 data:'country',
    //                 name:'country'
    //             },
    //             {
    //                 data:'action',
    //                 name:'action',
    //                 orderable: false,
    //                 searchable: false
    //             } 
    //         ],
    //     order: [[0, 'desc']]
    // }); 

    // $(function() {

    //     $.ajax({
    //         type: 'GET',
    //         url: 'http://localhost:8080/shopping_cart/user_groups/list',
    //         dataType:'json',
    //         success: function(data){
    //             groups = data;
    //             groups.forEach(group => {
    //                 $('#user-group').append($('<option></option>').attr('value', group.id).html(group.name));
    //             });
    //         },
    //         error: function(data){
    //             toastr.error('Error. Couldn\'t fetch user groups.')
    //         }
    //     });

    //     let table = $('#user-groups-table').DataTable({
    //         ajax: 'http://localhost:8080/shopping_cart/user_groups/list',
    //         columns: [
    //             {
    //                 data:'DT_RowIndex', 
    //                 name: 'DT_RowIndex', 
    //                 orderable: false,
    //                 searchable: false
    //             },
    //             {
    //                 data:'id', 
    //                 name:'id', 
    //                 'visible':false
    //             },
    //             {
    //                 data:'name', 
    //                 name:'name', 
    //             },
    //             {
    //                 data:'permission', 
    //                 name:'permission', 
    //             }
    //         ],
    //         order: [[o, 'desc']]
    //     });
    // });

    $(document).ready(function() {  
        $.ajax({  
            type: "GET",  
            url: "http://localhost:8080/shopping_cart/users/create",  
            dataType: "json",  
            contentType: "application/json",  
            success: function(response) {  
                response.forEach(element => {
                    let tr = "<tr>";  
                    tr += "<td>" + element.id+ "</td>";  
                    tr += "<td>" + element.id + "</td>";  
                    tr += "<td>" + element.name + "</td>";  
                    tr += "<td>" + element.permission + "</td>" + "</tr>";  
                    $("#tBody").append(tr);  
                });
                $("#user-groups-table").DataTable();  
            },  
            error: function(data){
      		    console.log('Error fetching user goups: '+ data);
                toastr.error('Error fetching user goups.')
      	    }
        });  
    });


    $("#new-user-group-save").click(function(e){
      e.preventDefault();
      let form = $('#new-user-group-form');
      $.ajax({
      	type: 'POST',
      	url: 'http://localhost:8080/shopping_cart/user_group/create',
      	data: form.serialize(),
      	dataType: 'json',
      	success: function(data){
          if(data){
            form.trigger('reset'); 
      		  console.log('New user group created.')
            toastr.success('New user group created.');
          }
          else{
            console.log('Error creating user groups: '+ data);
            toastr.error('Error creating new user group.')
          }
      	},
      	error: function(data){
      		console.log('Error creating user groups: '+ data);
          toastr.error('Error creating new user group.')
      	}
      });
    });
  </script>

</body>

</html>