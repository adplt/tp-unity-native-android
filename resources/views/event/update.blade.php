@extends('layout.main')
@section('title','Update Event')
@section('content')
    @include('layout.error')
    <html>
    <body>
    <div class="jumbotron">
        <form class="form-horizontal" method="post" action="/updateEvent" enctype="multipart/form-data">
            {{csrf_field()}}
            <input type="hidden" value="{{$event->id}}" name="id">
            <div class="form-group">
                <div class="col-sm-6">
                    <label for="eventname">Event Name</label>
                    <input type="text" class="form-control" id="eventname" placeholder="Input Event Name"
                           name="EventName"
                           value="{{$event->event_name}}">
                </div>
            </div>


            <div class="form-group">
                <div class="col-sm-6">
                    <label for="dateandtime">Start Date</label>
                    <input type="datetime" class="form-control datepicker" id="date" placeholder="Date" name="Date"
                           value="{{old('Date')}}">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-6">
                    <label for="time">Start Time</label>
                    <input type="time" class="form-control" id="starttime" placeholder="Time" name="StartTime"
                           value="{{old('starttime')}}">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-6">
                    <label for="time">End Time</label>
                    <input type="time" class="form-control" id="endtime" placeholder="Time" name="EndTime"
                           value="{{old('endtime')}}">
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-6">
                    <label for="location">Location</label>
                    <input type="text" class="form-control" id="location" placeholder="Input Location" name="Location"
                           value="{{$event->location}}">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-6">
                    <label for="dat">Total TP</label>
                    <input type="text" class="form-control" id="totaltp" placeholder="Total TP" name="TotalTP"
                           value="{{$event->total_tp}}">
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
                    <textarea class="form-control" id="notes" placeholder="Notes" name="Notes"
                              rows="3">{{$event->note}}</textarea>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-6">
                    <label for="pic">PIC Event</label>
                    <select class="form-control" id="pic" name="PICName">
                        @foreach($staffs as $staff)
                            <option {{$event->id_staff==$staff->id?'selected':''}} value="{{$staff->id}}">{{$staff->id.' - '.$staff->staff_name}}</option>
                        @endforeach
                    </select>
                </div>
            </div>

            <button type="submit" class="btn btn-default">Update</button>
        </form>

    </div>


    </body>
    </html>


@endsection