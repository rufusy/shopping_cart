<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="x-ua-compatible" content="ie=edge">

    <title> Shopping Cart | Categories</title>

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
                                <li class="breadcrumb-item active">categories</li>
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
                                        <button type="button" class="btn btn-block btn-primary btn-flat"
                                            data-toggle="modal" data-target="#new-category-modal">create</button>
                                    </div>
                                </div>
                                <!-- /.card-header -->
                                <div class="card-body">
                                    <table id="categories-table" class="table table-bordered table-striped">
                                        <thead>
                                            <tr>
                                                <th>Name</th>
                                                <th>Description</th>
                                                <th>Metatitle</th>
                                                <th>Metakeyword</th>
                                                <th>Metadescription</th>
                                                <th>Parent</th>
                                                <th>Action</th>
                                            </tr>
                                        </thead>
                                        <tbody id='tBody'>
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
                <jsp:include page="./create_modal.jsp" />
                <jsp:include page="./update_modal.jsp" />

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
        $(function () {
            let categories = [];
            // Fetch all categories
            $.ajax({
                type: 'GET',
                url: 'http://localhost:8080/shopping_cart/categories_list',
                dataType: 'json',
                success: function (data) {
                    categories = data;
                    categories.forEach(category => {
                        $('#parent-category').append($('<option></option>').attr('value',
                                category.id)
                            .attr('data-name', category.name).html(category.name));

                        let tr = "<tr>";
                        tr += "<td>" + category.name + "</td>";
                        tr += "<td>" + category.description + "</td>";
                        tr += "<td>" + category.metaTitle + "</td>";
                        tr += "<td>" + category.metaKeyword + "</td>";
                        tr += "<td>" + category.metaDescription + "</td>";
                        tr += "<td>" + 'parent' + "</td>";
                        tr += "<td>" + '<div> <a href="javascript:void(0)" class="nav-link" onClick="editCategory($(this));" data-id="'+category.id+'">Edit</a> ' +'<a href="javascript:void(0)" class="nav-link"  onClick="deleteCategory($(this));" data-id="' +category.id + '">Delete</a> </div>' + "</td>" + "</tr>";
                        $("#tBody").append(tr);
                    });
                    $("#categories-table").DataTable({
                        "paging": true,
                        "lengthChange": true,
                        "searching": true,
                        "ordering": true,
                        "info": true,
                        "autoWidth": true,
                        "responsive": true,
                    });
                },
                error: function (data) {
                    toastr.error('Error. Couldn\'t fetch product categories.')
                }
            });

            // Delete category
        	let deleteCategory = function (obj) {
                let id = obj.attr('data-id');
                alert('Delete category: '+id);
            }

            // category details
            let editCategory = function (obj) {
                let id = obj.attr('data-id');
                alert('Details category: '+id);
            }

            // // Delete category
            // let deleteCategory = function (obj) {
            //     let id = obj.attr('data-id');
            //     $.ajax({
            //         type: 'POST',
            //         url: 'http://localhost:8080/shopping_cart/category_delete',
            //         data: {
            //             'id': id
            //         },
            //         dataType: 'json',
            //         success: function (data) {
            //             data == true ? toastr.success('Category deleted.') : toastr.error(
            //                 'Category failed to delete');
            //         },
            //         error: function (data) {
            //             console.log('Error: ' + data);
            //             toastr.error('Error: Category failed to delete');
            //         }
            //     });
            // }

            // // Categoriy details
            // let editCategory = function (obj) {
            //     let id = obj.attr('data-id');
            //     $('#cat-id').val(id);
            //     $('#category-form').trigger('reset');
            //     $('#update-category-modal').modal('show');
            //     $.ajax({
            //         type: 'GET',
            //         url: 'http://localhost:8080/shopping_cart/category_show?id=' + id,
            //         dataType: 'json',
            //         success: function (data) {
            //             $('#category-form').attr('action', 'category_edit');
            //             $('#name').val(data.name);
            //             $('#description').val(data.description);
            //             $('#metatitle').val(data.metaTitle);
            //             $('#metakeyword').val(data.metaKeyword);
            //             $('#metadescription').val(data.metaDescription);
            //         },
            //         error: function (data) {
            //             console.log('Error: ' + data);
            //             toastr.error('Error: Category failed to delete');
            //         }
            //     });
            // }

            // Create new category
            $("#new-category-save").click(function (e) {
                e.preventDefault();
                createUpdateCategories('category_create', 'New category created',
                    'Error creating new category.');
            });

            // Update category
            $("#category-update").click(function (e) {
                e.preventDefault();
                createUpdateCategories('category_update', 'Category updated',
                    'Error updating category.');
            });


            let createUpdateCategories = function (url, successMsg, errorMsg) {

                let form = $('#category-form');

                if ($('#name').val() == '' || $('#description').val() == '') {
                    toastr.error('Provide needed fields.');
                    return;
                }

                if ($('#name').val() != '') {
                    if ($('#name').val() == $('#parent-category').attr('data-name')) {
                        toastr.error('Category must not match parent name.')
                        return;
                    }
                }

                if ($('#parent-category').val() == '') $('#parent-category').val('1');

                $.ajax({
                    type: 'POST',
                    url: 'http://localhost:8080/shopping_cart/' + url,
                    data: form.serialize(),
                    dataType: 'json',
                    success: function (data) {
                        if (data) {
                            form.trigger('reset');
                            toastr.success(successMsg);
                        } else {
                            toastr.error(errorMsg)
                        }
                    },
                    error: function (data) {
                        console.log('Error:' + data);
                        toastr.error('Error: ' + errorMsg);
                    }
                });
            }
        });
    </script>

</body>

</html>