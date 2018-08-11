@extends('layout.main')
@section('title','Home')
@section('content')
    <h2>Leader Board</h2>
    <div class="row">
        <div class="col-md-4">
            <div class="panel panel-default">
                <div class="panel-heading">Top Ten Scores</div>
                <div class="panel-body">
                    <ul class="media-list">

                        <!--App\TeamPromotionModel::orderBy('score','desc')-->
                        @foreach($tps as $team_promotion )

                            <li class="media">
                                <div class="media-left">
                                    <a href="{{url('/profile/'.$team_promotion->id)}}">
                                        <img class="media-object" height="60" width="60" src="{{$team_promotion->picture}}" alt="Picture">
                                    </a>
                                </div>
                                <div class="media-body">
                                    <h4 class="media-heading">{{$team_promotion->tp_name}}</h4>
                                    {{$team_promotion->score}}
                                </div>
                            </li>
                        @endforeach

                    </ul>
                </div>
            </div>
        </div>
        <div class="col-md-8">
            <h2>Graphic Report</h2>
            <html>
            <head>
                <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
                <script type="text/javascript">
                    google.charts.load('current', {'packages': ['corechart']});
                    google.charts.setOnLoadCallback(drawChart);

                    function drawChart() {
                        var data = google.visualization.arrayToDataTable([
                            ['Month', 'Student'],
                                @foreach($chart as $data)
                            ['{{$data->month}}', {{$data->total}}],
                            @endforeach
                        ]);

                        var options = {
                            title: 'Team Promotion Reports',
                            curveType: 'function',
                            legend: {position: 'bottom'}
                        };

                        var chart = new google.visualization.LineChart(document.getElementById('curve_chart'));

                        chart.draw(data, options);
                    }
                </script>
            </head>
            <body>
            <div id="curve_chart" style="width: 900px; height: 500px"></div>
            </body>
            </html>

        </div>

    </div>

@endsection