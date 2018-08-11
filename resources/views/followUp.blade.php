@extends('layout.main')
@section('title','Follow Up')
@section('content')
    <h2>Follow Up Data</h2>
    <div class="row">
        <div class="col-md-3">

            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">Search</h3>
                </div>
                <div class="panel-body">
                    <form method="get" action="{{url('/followUp')}}">
                        <div class="form-group">
                            <label for="inputName">TP Name</label>
                            <input type="text" id="inputName" class="form-control" name="tpname" value="{{ $tpname }}"/>
                        </div>
                        <div class="form-group">

                        </div>
                        <div class="form-group">
                            <label for="filter">Filter By:</label>
                        </div>

                        <div class="checkbox">
                            <label>
                                <input type="checkbox" value="1" name="followed" {{ ($followed==1) ? "checked" : "" }} />
                                has been followed up
                            </label>
                        </div>
                        <div class="checkbox">
                            <label>
                                <input type="checkbox" value="1" name="notfollowed" {{ ($notfollowed==1) ? "checked" : "" }}>
                                have not followed up
                            </label>
                        </div>
                        <div class="checkbox">
                            <label>
                                <input type="checkbox" value="1" name="interested" {{ ($interested==1) ? "checked" : "" }}>
                                Interest up to the master's level
                            </label>
                        </div>
                        <div class="form-group">
                            <label for="graduation">Data Name</label>
                            <select class="form-control" id="data" name="datatype">
                                <option value="0">Select Data</option>
                                @foreach($datas as $data)
                                    <option value="{{$data->id}}" {{  ($datatype==$data->id) ? "selected" : "" }}>{{$data->data_name}}</option>
                                @endforeach
                            </select>
                        </div>
                        <button type="submit" class="btn btn-default">Search</button>
                    </form>
                </div>
            </div>
        </div>
        <div class="col-md-9">
            <div class="panel panel-default">
                <div class="panel-heading">Follow Up List</div>
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>Name</th>
                        <th>Phone Number</th>
                        <th>Work Number</th>
                        <th>Email</th>
                        <th>Interested</th>
                        <th>Followed By</th>
                    </tr>
                    </thead>
                    <tbody>
                    <?php $i = 1; ?>
                    @foreach($student_candidates as $student_candidate)
                        <tr>
                            <td>{{$student_candidate->candidate_name}}</td>
                            <td>{{$student_candidate->phone_number}}</td>
                            <td>{{$student_candidate->work_number}}</td>
                            <td>{{$student_candidate->email}}</td>
                            <td>{{$student_candidate->result == 0 ? 'No' : 'Yes'}}</td>
                            <td>{!! $student_candidate->team_promotion == null ? '0': $student_candidate->team_promotion->tp_name  !!}</td>
                        </tr>

                    @endforeach
                    </tbody>

                </table>
            </div>

            <div align="center">
                {!! $student_candidates->appends(['interested' => $interested, 'tpname' => $tpname, 'followed' => $followed, 'notfollowed' => $notfollowed, 'datatype' => $datatype])->render() !!}
            </div>
        </div>
       {{-- <nav>
            <ul class="pagination small" style="align-content: center">
                <li>
                    <a href="#" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
                <li><a href="#">1</a></li>
                <li><a href="#">2</a></li>
                <li><a href="#">3</a></li>
                <li><a href="#">4</a></li>
                <li><a href="#">5</a></li>
                <li>
                    <a href="#" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
            </ul>
        </nav>--}}
    </div>

@endsection