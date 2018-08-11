<html>
<title>Login</title>
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
    <script>
        $(function () {
            $(".datepicker").datepicker({
                singleDatePicker: true,
                showDropDowns: true,

                changeMonth: true,
                changeYear: true,
                minDate: '12/31/1990',
                maxDate: '12/31/2005'


            });
        });
    </script>
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
            <a class="navbar-brand">TP Unity</a>
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
</div>

<div class="content">
    <div class="container">

        @yield('content')


    </div>

    <!-- Button trigger modal -->
    <div class="right-bottom">
        <button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">
            Join Team Promotion!

        </button>
    </div>

    <!-- Modal -->
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">Team Promotion Registration Form</h4>
                </div>
                <div class="modal-body">
                    <div class="alert alert-danger" id="errorLogin">
                        <ul id="listError">

                        </ul>
                    </div>

                    <form id="register_form" class="form-horizontal" method="post" action="{{url('/login')}}">
                        {{csrf_field()}}
                        <div class="form-group">
                            <div class="col-sm-6">
                                <label for="fullname">Full Name</label>
                                <input type="text" class="form-control" id="fullname" placeholder="Input Full Name"
                                       name="fullName">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-6">
                                <label for="degree">Degree</label>
                                <select class="form-control" id="pic" name="degree">
                                    @foreach($degrees as $degree)
                                        <option value="{{$degree->id}}">{{$degree->degree_name}}</option>
                                    @endforeach
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-6">
                                <label for="birth">Date of Birth</label>
                                <input type="datetime" class="form-control datepicker" id="birth" placeholder="Date"
                                       name="birth"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-6">
                                <label for="location">Phone Number</label>
                                <input type="text" class="form-control" id="phonenumber"
                                       placeholder="Input Phone Number"
                                       name="phoneNumber">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-6">
                                <label for="dat">Email</label>
                                <input type="email" class="form-control" id="email" placeholder="Input Email"
                                       name="email">
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-6">
                                <label for="dat">Create Password</label>
                                <input type="password" class="form-control" id="pass1" placeholder="Input Password"
                                       name="pass1">
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-6">
                                <label for="dat">Confirm Password</label>
                                <input type="password" class="form-control" id="pass2"
                                       placeholder="Input Confirm Password"
                                       name="pass2">
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-6">
                                <label for="brnch">Branch</label>
                                <select class="form-control" id="brc" name="branch">
                                    @foreach($branchs as $branch)
                                        <option value="{{$branch->id}}">{{$branch->branch_name}}</option>
                                    @endforeach
                                </select>
                            </div>
                        </div>

                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                    <button type="button" class="btn btn-primary">Register</button>
                </div>
            </div>

        </div>
    </div>
</div>
</div>


<div id="showModal" class="modal fade">
    <div class="modal modal-dialog">
        <div class="modal-content">
            <!--dialog body-->
            <div class="modal-body">
                <button type="button" class="close" data-dismiss="modal">&time;</button>
                Registration Success!
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary"> OK</button>


            </div>

        </div>

    </div>
</div>

<div class="footer">
    <div class="container">
        <center>&copy;TP Unity copyright 2016.</center>
    </div>
</div>
<script>
    $(document).ready(function () {
        $('#errorLogin').hide();

    });

    $(function () {
        //$(".datepicker").datepicker();
        $('#myModal .btn-primary').click(function () {

            $('#listError').html('');
            $fullname = $('[name="fullName"]').val();
            $major = $('[name="degree"]').val();
            $birth = $('[name="birth"]').val()
            var checkDate = Date.parse($birth);
            $phoneNumber = $('[name="phoneNumber"]').val()
            //alert($phoneNumber);
            $email = $('[name="email"]').val()
            var regex = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
            $errors = false;
            if ($fullname.length < 2) {

                $errors = $('<li>Full Name Incorrect</li>');
                $('#listError').append($errors);
                $('#errorLogin').fadeIn('slow');
                $errors = true;
                //return false;

            }
            if ($major.length == 0) {

                $errors = $('<li>Degree Incorrect</li>');
                $('#listError').append($errors);
                $('#errorLogin').fadeIn('slow');
                $errors = true;
                //return false;
            }
            if (isNaN(checkDate)) {

                $errors = $('<li> Birth Date Incorect</li>');
                $('#listError').append($errors);
                $('#errorLogin').fadeIn('slow');
                $errors = true;
                //return false;
            }
            if (isNaN($phoneNumber) || $phoneNumber.length == 0) {

                $errors = $('<li>Phone Number Incorrect</li>');
                $('#listError').append($errors);
                $('#errorLogin').fadeIn('slow');
                $errors = true;
                //return false;
            }
            if (!$email.match(regex)) {

                $errors = $('<li>Email Incorrect</li>');
                $('#listError').append($errors);
                $('#errorLogin').fadeIn('slow');
                $errors = true;
                //return false;
            }

            if ($errors) return false;
            $('#errorLogin').hide();
            jQuery.ajax({
                url: '{{url('/')}}',
                type: 'POST',
                data: $('#register_form').serialize(),
                success: function (data, status) {
                    // $("#showModal").modal('show');
                    alert('Registration Success');
                    window.location.href = '/';
                },
                error: function (e, s) {
                    alert(e);
                    alert(s);
                    alert("Registration Invalid");
                }
            })
        });

    });
</script>
</body>
</html>
