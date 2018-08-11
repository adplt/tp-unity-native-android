@extends('layout.main')
@section('title','Team Promotion')
@section('content')
    <h2>Team Promotion Profile</h2>


    <div class="row">
        <div class="col-md-3">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">Search Data By:</h3>
                </div>
                <div class="panel-body">
                    <form method="get" action="{{url('/profile')}}">
                        <div class="form-group">
                            <label for="inputName">Team Promotion Name</label>
                            <input type="text" id="inputName" class="form-control" name="tpname" value="{{ $tpname }}"/>
                        </div>


                        <button type="submit" class="btn btn-default">Search</button>
                        <a href="{{url('/profile')}}" class="btn btn-default">Show All</a>

                    </form>
                </div>
            </div>
        </div>
        <div class="col-md-9">
            <div class="panel panel-default">
                <div class="panel-heading">Team Promotion List</div>
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>No</th>
                        <th>Name</th>
                        <th>Degree</th>
                        <th>Score</th>

                    </tr>
                    </thead>
                    <tbody>

                    <tr>
                        <?php $i = 1; ?>
                        @foreach($team_promotions as $team_promotion)
                            <th scope="row">{{$i++}}</th>
                            <td>{{$team_promotion->tp_name}}</td>
                            <td>{{$team_promotion->degree_name}}</td>
                            <td>{{$team_promotion->score}}</td>

                            <td>
                                <div class="btn-group btn-group-xs " role="group">
                                    <a href="{{url('/profile/'.$team_promotion->id)}}" data-toggle="tooltip"
                                       data-placement="top" class="btn btn-default">
                                        <span class="glyphicon glyphicon-eye-open" aria-hidden="true"> View</span>
                                    </a>
                                    <a href="{{url('/profile/delete/'.$team_promotion->id)}}" class="btn btn-default btn-delete">
                                        <span class="glyphicon glyphicon-trash" aria-hidden="true"> Delete</span>
                                    </a>

                                </div>
                            </td>


                    </tr>
                    @endforeach

                    </tbody>
                </table>
            </div>
        </div>
        <div align="center">
            {!! $team_promotions->appends(['tpname' => $tpname])->render() !!}
        </div>
    </div>
    <script>
        $(function(){
            $('.btn-delete').click(function(){
                if(confirm('Are you sure?')){

                }else {
                    return false;
                }
            });
        });
    </script>


@endsection