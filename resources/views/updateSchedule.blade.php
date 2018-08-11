@extends('layout.main')
@section('title','Update Schedule')
@section('content')
    @include('layout.error')
    <html>
    <body>
    <div class="jumbotron">
        <form method="post" action="/updateSchedule" enctype="multipart/form-data">
            {{csrf_field()}}

            <input type="hidden" name="id" value="{{$schedules->id_support}}">


            <div class="form-group">
                <div class="col-sm-3">
                    <label for="starttime">Start Time</label>
                    <input type="time" class="form-control" id="starttime" placeholder="Time" name="StartTime"
                           value="{{old('starttime')}}"><br>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-3">
                    <label for="endtime">End Time</label>
                    <input type="time" class="form-control" id="endtime" placeholder="Time" name="EndTime"
                           value="{{old('endtime')}}">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-6">
                    <label for="notes">Description</label>
                    <textarea class="form-control" id="desc" placeholder="Product Description" name="description"
                              rows="3"> </textarea>
                </div>

            </div>
            <button type="submit" class="btn btn-default">Update</button>
            <a class="btn btn-default" href="{{url('/schedule')}}" role="button">Back</a>
        </form>

    </div>
    </body>
    </html>


@endsection