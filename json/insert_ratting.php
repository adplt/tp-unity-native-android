<?php
	include('connect.php');
	
	$from = $_GET["no_prm_login"];
	$to = $_GET["no_prm"];
	$time_management = $_GET["time_management"];
	$initiative = $_GET["inititative"];
	$responsible = $_GET["responsible"];
	$date_created = $_GET["date_created"];
	$note = $_GET["note"];
	
	$err_msg = "";
	
	if(mysqli_connect_errno()){
		echo "connect error in: ".mysqli_connect_errno();
		$err_msg = "Failed to connect !";
	}else{
		$query = "INSERT INTO `ratting` (`from`, `to`, `time_management`, `initiative`, `responsible`, `date_created`, `comment`) VALUES ('".$from."','".$to."',".$time_management.",".$initiative.",".$responsible.",'".$date_created."','".$note."')";
		$result = mysqli_query($connect, $query) or die(mysqli_error($connect));
		
		if(!$result){
			$err_msg = "Failed to input data !";
		}else{
			$err_msg = "Input Data Successfully !";
		}
	}
	
	echo $err_msg;
?>