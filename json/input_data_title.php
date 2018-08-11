<?php
	include('connect.php');
	
	$data_name = $_GET["dt_name"];
	
	$err_msg = "";
	
	if(mysqli_connect_errno()){
		echo "connect error in: ".mysqli_connect_errno();
		$err_msg = "Failed to connect !";
	}else{
		$query = "INSERT INTO `data` (`data_name`) VALUES ('".$data_name."')";
		$result = mysqli_query($connect, $query) or die(mysqli_error($connect));
		
		if(!$result){
			$err_msg = "Failed to input data !";
		}else{
			$err_msg = "Input Data Successfully !";
		}
	}
	
	echo $err_msg;
?>