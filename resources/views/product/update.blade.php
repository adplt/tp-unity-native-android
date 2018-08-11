@extends('layout.main')
@section('title','Update Product')
@section('content')
    @include('layout.error')
    <html>
    <body>
    <div class="jumbotron">
        <form class="form-horizontal" method="post" action="/updateProduct" enctype="multipart/form-data">
            {{csrf_field()}}
            <input type="hidden" value="{{$degree->id}}" name="id">
            <div class="form-group">
                <div class="col-sm-4">
                    <label for="productname">Faculty Name</label>
                    <input type="text" class="form-control" id="productname" placeholder="Input Faculty Name"
                           name="ProductName"
                           value="{{$faculty->faculty_name}}">
                </div>

                <div class="col-sm-4">
                    <label for="productname">Degree Name</label>
                    <input type="text" class="form-control" id="productname" placeholder="Input Degree Name"
                           name="ProductName"
                           value="{{$degree->degree_name}}">
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-4">
                    <label for="desc">Description</label>
                    <textarea class="form-control" id="desc" placeholder="Product Description" name="description"
                              rows="3"    value="{{$degree->note}}"></textarea>
                </div>
            </div>

            <button type="submit" class="btn btn-default">Update</button>
        </form>
    </div>
    </body>
    </html>


@endsection