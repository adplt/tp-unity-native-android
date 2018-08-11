<html>
<title>Reset Password</title>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <meta charset="utf-8">
    <title>@yield('title')</title>
    <!--<link rel="stylesheet" href="themes/base/jquery.ui.all.css">-->
    <script src="{{asset('js/jquery.js')}}"></script>
    <script src="{{asset('js/jquery-ui/jquery-ui.min.js')}}"></script>
    <link href="{{asset('js/jquery-ui/jquery-ui.min.css')}}" rel="stylesheet">
    <!--<script src="ui/jquery.ui.core.js"></script>
    <script src="ui/jquery.ui.widget.js"></script>
    <script src="ui/jquery.ui.datepicker.js"></script>-->
    <title>Login</title>

    <!-- Bootstrap Core CSS -->
    <link href="{{asset(url("css/bootstrap.min.css"))}}" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="{{asset(url('font-awesome/css/font-awesome.min.css'))}}" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <script src="{{asset(url('js/bootstrap.min.js'))}}"></script>
    <link rel="stylesheet" href="{{url('css/plugins/style.css')}}">
    <link rel="icon" href="51.png">
</head>
<body style="background-color:#e5e8e8;">
<!-- Navigation -->
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <!-- Top Menu Items -->

        </div>
    </div>
</nav>


<div class="container">
    <!-- Page Heading -->

    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse"
                        data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand">TP Unity</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->

        </div>
    </nav>
</div>


<div class="content">
    <div class="container">

        @yield('content')

    </div>

</div>


<div class="footer">
    <div class="container">
        <center>&copy;TP Unity coppyright 2016.</center>
    </div>
</div>

</body>
</html>
