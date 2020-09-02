<form role="form" id="category-form">
    <div class="card-body">

        <input type="hidden" class="form-control" name="cat-id" id="cat-id">

        <div class="form-group">
            <label for="name">Name *</label>
            <input type="text" class="form-control" name="name" id="name" placeholder="name">
        </div>
        <div class="form-group">
            <label for="description">Description*</label>
            <input type="text" class="form-control" name="description" id="description" placeholder="description">
        </div>
        <div class="form-group">
            <label for="metatitle">Metatitle</label>
            <input type="text" class="form-control" name="metatitle" id="metatitle" placeholder="metatitle">
        </div>
        <div class="form-group">
            <label for="metakeyword">Metakeyword</label>
            <input type="text" class="form-control" name="metakeyword" id="metakeyword" placeholder="metakeyword">
        </div>
        <div class="form-group">
            <label for="metadescription">Metadescription</label>
            <input type="text" class="form-control" name="metadescription" id="metadescription"
                placeholder="metadescription">
        </div>
        <div class="form-group">
            <label>Parent category</label>
            <select name="parent-category" id="parent-category" class="form-control select2" style="width: 100%;">
                <option value="">Select parent category</option>
            </select>
        </div>
    </div>
</form>