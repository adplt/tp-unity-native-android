<html>
<head>
</head>
<body>
<h1>Forgot</h1>
Hello {{$staff->staff_name}},
<br>You recently requested to reset your password for your TP Unity account.
<br>
Click the button below to reset it.<br>

<a class="btn btn-danger" href="{{url('/password_reset/'.$token)}}" role="button">Click here to reset password</a><br>
The link will be active in 24 hour from the email sent.
<br>
<br>
If you did not request to password reset, please ignore this email.
<br>
<br>
Thank you,
<br>
TP Unity Admin
</body>
</html>