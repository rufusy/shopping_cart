<div class="modal fade" id="update-category-modal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">Update category</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">

                <jsp:include page="./form.jsp" />
                
            </div>
            <div class="modal-footer justify-content-between">
                <button type="button" class="btn btn-danger btn-flat" data-dismiss="modal">Close</button>
                <button type="submit" id="category-update" class="btn btn-success btn-flat">Update</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal -->