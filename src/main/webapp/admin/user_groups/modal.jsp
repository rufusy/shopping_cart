<div class="modal fade" id="new-user-group-modal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">New user group</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form role="form" id="new-user-group-form"> 
                    <div class="card-body">
                        <div class="form-group">
                            <label for="exampleInputEmail1">Group name</label>
                            <input type="text" class="form-control" name="group-name" id="group-name" placeholder="Enter group name">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer justify-content-between">
                <button type="button" class="btn btn-danger btn-flat" data-dismiss="modal">Close</button>
                <button type="submit" id="new-user-group-save" class="btn btn-success btn-flat">Save</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal -->