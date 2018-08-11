<?php
	include('connect.php');
	
	$no_prm = $_GET["no_prm"];
	$start = $_GET["start"];
	$until = $_GET["until"];
	
	$err_msg = "";
	
	if(mysqli_connect_errno()){
		echo "connect error in: ".mysqli_connect_errno();
		$err_msg = "Failed to connect !";
	}else{
		$query = "INSERT INTO `available`(`no_prm`, `available_from`, `available_until`) VALUES ('".$no_prm."', '".$start."', '".$until."')";
		$result = mysqli_query($connect, $query) or die(mysqli_error($connect));
		
		if(!$result){
			$err_msg = "Failed to input data !";
		}else{
			$err_msg = "Input Data Successfully !";
		}
	}
	
	echo $err_msg;
?>