@extends('layout.main')
@section('title','Event')
@section('content')
    @include('layout.error')
    <html>


    <body>
    {{-- <script>
         function notif() {
             alert("Create Event Success");
         }


     </script>--}}
    <h2>Add Event</h2>
    <!-- /resources/views/post/create.blade.php -->
    {{-- @if(isset($error))
         <div class="alert alert-danger">
             <li>{{$error}}</li>
         </div>
     @endif--}}

    <div class="jumbotron">

        <form class="form-horizontal" method="post" action="" enctype="multipart/form-data">
            {{csrf_field()}}
            <div class="form-group">
                <div class="col-sm-6">
                    <label for="eventname">Event Name</label>
                    <input type="text" class="form-control" id="eventname" placeholder="Input Event Name"
                           name="EventName" value="{{old('EventName')}}">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-6">
                    <label for="datetime">Start Date</label>
                    <input type="date" class="form-control" id="date" placeholder="Date" name="Date"
                           value="{{old('Date')}}">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-6">
                    <label for="time">Start Time</label>
                    <input type="time" class="form-control" id="time" placeholder="Time" name="StartTime"
                           value="{{old('Time')}}">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-6">
                    <label for="time">End Time</label>
                    <input type="time" class="form-control" id="time" placeholder="Time" name="EndTime"
                           value="{{old('Time')}}">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-6">
                    <label for="location">Location</label>
                    <input type="text" class="form-control" id="location" placeholder="Input Location" name="Location"
                           value="{{old('Location')}}">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-6">
                    <label for="dat">Total TP</label>
                    <input type="text" class="form-control" id="totaltp" placeholder="Total TP" name="TotalTP"
                           value="{{old('TotalTP')}}">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-6">
                    <label for="pict">Upload Picture</label>
                    <input type="file" class="form-control" id="pict" name="Picture">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-6">
                    <label for="notes">Notes</label>
                    <textarea class="form-control" id="notes" placeholder="Notes" name="Notes" value="{{old('Notes')}}"
                              rows="3"> </textarea>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-6">
                    <label for="pic">PIC Event</label>
                    <select class="form-control" id="pic" name="PICName">
                        @foreach($staffs as $staff)
                            <option value="{{$staff->id}}">{{$staff->id.' - '.$staff->staff_name}}</option>
                        @endforeach
                    </select>
                </div>
            </div>

            <button type="submit" class="btn btn-success">Save</button>
            <a class="btn btn-default" href="{{url('/event')}}" role="button">Back</a>
        </form>
    </div>
    </body>
    </html>

    {{--<script>
        $(function () {
            $('.btn-success').click(function () {
                if (alert('Create Event Success!')) {

                } else {
                    return false;
                }
            });
        });
    </script>--}}

@endsection