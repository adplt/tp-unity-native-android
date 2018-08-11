@extends('layout.main')
@section('title','Product')
@section('content')
    @include('layout.error')
    <html>
    <body>
    <h2>Add Product</h2>
    <div class="jumbotron">

        <form class="form-horizontal" method="post" action="" enctype="multipart/form-data">
            {{csrf_field()}}
            <div class="form-group">
                <div class="col-sm-6">
                    <label for="facultyname">Faculty Name</label>
                    <input type="text" class="form-control" id="productname" placeholder="Input Faculty Name"
                           name="FacultyName">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-6">
                    <label for="degreename">Degree Name</label>
                    <input type="text" class="form-control" id="productname" placeholder="Input Degree Name"
                           name="DegreeName">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-6">
                    <label for="notes">Description</label>
                    <textarea class="form-control" id="desc" placeholder="Notes" name="description"
                              rows="3"> </textarea>
                </div>
            </div>

            <button type="submit" class="btn btn-success">Save</button>
            <a class="btn btn-default" href="{{url('/product')}}" role="button">Back</a>
        </form>
    </div>
    </body>
    </html>

@endsection