<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <meta charset="utf-8">
    <title>@yield('title')</title>
    <script src="{{asset('js/jquery.js')}}"></script>
    <script src="{{asset('js/jquery-ui/jquery-ui.min.js')}}"></script>
    <link href="{{asset('js/jquery-ui/jquery-ui.min.css')}}" rel="stylesheet">
    <!--<script src="ui/jquery.ui.widget.js"></script>
    <script src="ui/jquery.ui.datepicker.js"></script>
    <script src="{{asset('bootstrap-datetimepicker.id.js')}}"></script>-->

    <title>Home</title>

    <!-- Bootstrap Core CSS -->
    <link href="{{asset("css/bootstrap.min.css")}}" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="{{asset('font-awesome/css/font-awesome.min.css')}}" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://code.highcharts.com/highcharts.js"></script>
    <script src="https://code.highcharts.com/modules/exporting.js"></script>
    <div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>

    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <script src="{{asset(url('js/bootstrap.min.js'))}}"></script>
    <link rel="stylesheet" href="{{asset('css/plugins/style.css')}}">
    <link rel="icon" href="51.png">

</head>

<body style="background-color:#e5e8e8;">
<!-- Navigation -->
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">

            <a class="navbar-brand">TP Unity</a>


        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            {{--<ul class="nav navbar-nav">

                <li><a href="{{url('/event')}}">Event</a></li>
                <li><a href="{{url('/followUp')}}">Follow Up</a></li>
                <li><a href="{{url('/schedule')}}">Schedule</a></li>
                <li><a href="{{url('/absence')}}">Absence</a></li>
                <li><a href="{{url('/profile')}}">Team Promotion</a></li>
                <li><a href="{{url('/product')}}">Product</a></li>

            </ul>--}}
                    <!-- Top Menu Items -->
            <ul class="nav navbar-nav navbar-right top-nav">
                <li class="dropdown" style="position: relative;">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i
                                class="fa fa-user"></i> {{Auth::user()->staff_name}} <b
                                class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li><a href="{{url('/changePassword2')}}"><i class="glyphicon glyphicon-cog"></i> Change Password</a>
                        </li>
                        <li role="separator" class="divider"></li>
                        <li><a href="{{url('/')}}"><i class="fa fa-fw fa-power-off"></i> Log Out</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>


<!-- batas menu -->

<div class="content">
    <div class="container">

        @yield('content')


    </div>
    <!-- /.container-fluid -->
</div> <!--content-->
<div class="footer">
    <div class="container">
        <center> &copy;TP Unity copyright 2016.</center>
    </div>
</div>


</body>
</html>

