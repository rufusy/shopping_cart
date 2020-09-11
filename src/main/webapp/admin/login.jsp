<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Shopping Cart | Log in</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <jsp:include page="./includes/styles.jsp" />

</head>

<body class="hold-transition login-page">
    <div class="login-box">
        <div class="login-logo">
            <a href="#"><b>Shopping cart</b></a>
        </div>
        <!-- /.login-logo -->
        <div class="card">
            <div class="card-body login-card-body">
                <p class="login-box-msg">Sign in to start your session</p>

                <form role="form" id="login-form">
                    <div class="input-group mb-3">
                        <input type="email" name="email" class="form-control" placeholder="Email">
                        <div class="input-group-append">
                            <div class="input-group-text">
                                <span class="fas fa-envelope"></span>
                            </div>
                        </div>
                    </div>
                    <div class="input-group mb-3">
                        <input type="password" name="password" class="form-control" placeholder="Password">
                        <div class="input-group-append">
                            <div class="input-group-text">
                                <span class="fas fa-lock"></span>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-8">
                            <!-- <div class="icheck-primary">
                                <input type="checkbox" id="remember">
                                <label for="remember">
                                    Remember Me
                                </label>
                            </div> -->
                        </div>
                        <!-- /.col -->
                        <div class="col-4">
                            <button type="submit" id="login-btn" class="btn btn-primary btn-block">Sign In</button>
                        </div>
                        <!-- /.col -->
                    </div>
                </form>
                <p class="mb-1">
                    <a href="forgot_password.jsp">I forgot my password</a>
                </p>
            </div>
            <!-- /.login-card-body -->
        </div>
    </div>
    <!-- /.login-box -->

    <!-- REQUIRED SCRIPTS -->
    <jsp:include page="./includes/scripts.jsp" />

    <script>
        $(function(){
            $('#login-btn').click(function(e){
                e.preventDefault();
                let form = $('#login-form');
                $.ajax({
                    type: 'POST',
                    url: 'http://localhost:8080/shopping_cart/admin/login',
                    data: form.serialize(),
                    dataType: 'json',
                    success: function(data){
                        if(data.authenticated){
                            form.trigger('reset');
                            toastr.success('Logged in successfuly');
                            window.location.replace('http://localhost:8080/shopping_cart/admin');
                        }
                        else{
                            toastr.error(data.authMsg);
                        }
                    },
                    error: function(data){
                        toastr.error('Request not sent')
                    }
                });
            });
        });
    </script>

</body>

</html>