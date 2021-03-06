<!-- jQuery -->
<script src="http://localhost:8080/shopping_cart/admin/plugins/jquery/jquery.min.js"></script>
<!-- Bootstrap -->
<script src="http://localhost:8080/shopping_cart/admin/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- DataTables -->
<script src="http://localhost:8080/shopping_cart/admin/plugins/datatables/jquery.dataTables.min.js"></script>
<script src="http://localhost:8080/shopping_cart/admin/plugins/datatables-bs4/js/dataTables.bootstrap4.min.js"></script>
<script src="http://localhost:8080/shopping_cart/admin/plugins/datatables-responsive/js/dataTables.responsive.min.js"></script>
<script src="http://localhost:8080/shopping_cart/admin/plugins/datatables-responsive/js/responsive.bootstrap4.min.js"></script>
<!-- Toastr -->
<script src="http://localhost:8080/shopping_cart/admin/plugins/toastr/toastr.min.js"></script>
<!-- AdminLTE -->
<script src="http://localhost:8080/shopping_cart/admin/dist/js/adminlte.js"></script>

<!-- page scripts -->
<script>
    // Check if a user session exists
    let checkForUserSession = function (){
        $.ajax({
            type: 'GET',
            url: 'http://localhost:8080/shopping_cart/admin/login',
            dataType: 'json',
            contentType: 'application/json',
            success: function(data){
                if(!data.loggedIn) 
                    window.location.replace('http://localhost:8080/shopping_cart/admin/login.jsp');
            },
            error: function(data){
                toastr.error('Request not sent')
            }
        });
    }    
</script>
