<div class="modal fade" id="new-user-modal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">New user</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form role="form" id="new-user-form"> 
                    <div class="card-body">

                        <input type="text" value="" class="form-control" name="id" id="id" >

                        <div class="form-group">
                            <label for="first-name">First name</label>
                            <input type="text" class="form-control" name="first-name" id="first-name" placeholder="First name">
                        </div>
                        <div class="form-group">
                            <label for="last-name">Last name</label>
                            <input type="text" class="form-control" name="last-name" id="last-name" placeholder="Last name">
                        </div>
                        <div class="form-group">
                            <label for="email">Email</label>
                            <input type="email" class="form-control" name="email" id="email" placeholder="Email">
                        </div>
                        <div class="form-group">
                            <label for="telephone">Telephone</label>
                            <input type="text" class="form-control" name="telephone" id="telephone" placeholder="Telephone">
                        </div>
                        <div class="form-group">
                            <label>User group</label>
                            <select name="user-group" id="user-group" class="form-control select2" style="width: 100%;">
                              <option value="">Select user group</option>
                            </select>
                          </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer justify-content-between">
                <button type="button" class="btn btn-danger btn-flat" data-dismiss="modal">Close</button>
                <button type="submit" id="new-user-save" class="btn btn-success btn-flat">Save</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal -->