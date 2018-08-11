@extends('layout.main')
@section('title','Team Promotion Request')
@section('content')
    <h2>New Team Promotion Request</h2>


    <div class="row">

        <div class="col-md-9">
            <div class="panel panel-default">
                <div class="panel-heading">Team Promotion List</div>
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>No</th>
                        <th>Name</th>
                        <th>Degree</th>
                        <th>Request</th>

                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <?php $i = 1; ?>
                        @foreach($team_promotions as $team_promotion)
                            <th scope="row">{{$i++}}</th>
                            <td>{{$team_promotion->tp_name}}</td>
                            <td>{{$team_promotion->degree_name}}</td>
                            <td>
                                <div class="btn-group btn-default" role="group">
                                    <a href="{{url('/tprequest/'.$team_promotion->id.'/accept')}}"
                                        data-placement="top" class="btn btn-success">
                                         <span class="glyphicon glyphicon-ok" aria-hidden="true">Accept</span>
                                     </a>
                                    {{--<button type="button" href="{{url('/tprequest/'.$team_promotion->id.'/accept')}}"
                                            class="btn btn-primary" data-toggle="modal"
                                            data-target="#exampleModal" data-whatever="PRM">Accept
                                    </button>--}}
                                   {{-- <button type="button" href="{{url('/tprequest/'.$team_promotion->id.'/accept')}}"
                                            class="btn btn-primary" data-toggle="modal"
                                            data-target=".bs-example-modal-sm">Accept
                                    </button>

                                    <button type="button" href="{{url('/tprequest/'.$team_promotion->id.'/decline')}}"
                                            class="btn btn-default">Decline
                                    </button>--}}
                                    <a href="{{url('/tprequest/'.$team_promotion->id.'/decline')}}"
                                       class="btn btn-delete">
                                        <span class="glyphicon glyphicon-remove" aria-hidden="true"> Decline</span>
                                    </a>
                                </div>
                            </td>
                    </tr>
                    @endforeach

                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <div class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
        <div class="modal-dialog modal-sm">
            <div class="modal-content">
                <div class="form-group">
                    <label for="noprm" class="control-label">Input No PRM:</label>
                    <input type="text" class="form-control" id="noprm">
                </div>
            </div>
        </div>
    </div>
    {{--  <script>
          $(function(){
              $('.btn-delete','.btn-success').click(function(){
                  if(confirm('Are You Sure?')){

                  }else {
                      return false;
                  }
              });
          });
      </script>--}}
@endsection