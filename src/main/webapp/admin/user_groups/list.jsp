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
                <table id="example1" class="table table-bordered table-striped">
                  <thead>
                  <tr>
                    <th>Rendering engine</th>
                    <th>Browser</th>
                    <th>Platform(s)</th>
                    <th>Engine version</th>
                    <th>CSS grade</th>
                  </tr>
                  </thead>
                  <tbody>
                  <tr>
                    <td>Trident</td>
                    <td>Internet
                      Explorer 4.0
                    </td>
                    <td>Win 95+</td>
                    <td> 4</td>
                    <td>X</td>
                  </tr>
                  <tr>
                    <td>Trident</td>
                    <td>Internet
                      Explorer 5.0
                    </td>
                    <td>Win 95+</td>
                    <td>5</td>
                    <td>C</td>
                  </tr>
                  </tbody>
                  <tfoot>
                  <tr>
                    <th>Rendering engine</th>
                    <th>Browser</th>
                    <th>Platform(s)</th>
                    <th>Engine version</th>
                    <th>CSS grade</th>
                  </tr>
                  </tfoot>
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
  </script>

</body>

</html>