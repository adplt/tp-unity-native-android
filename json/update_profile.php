<?php
	include('connect.php');
	
	$id = $_GET["id_login"];
	$name = $_GET["name_login"];
	$email = $_GET["email_login"];
	$address = $_GET["address_login"];
	$phone_number = $_GET["phone_number_login"];
	$work_number = $_GET["work_number_login"];
	$degree = $_GET["degree_login"];
	
	$err_msg = "";
	
	if(mysqli_connect_errno()){
		echo "connect error in: ".mysqli_connect_errno();
		$err_msg = "Failed to connect !";
	}else{
		$query = "UPDATE `team_promotion` SET `tp_name` = '".$name."', `email` = '".$email."', `address` = '".$address."', `phone_number` = '".$phone_number."', `work_number` = '".$work_number."', `id_degree` = '".$degree."' WHERE `no_prm` = '".$id."'";
		$result = mysqli_query($connect, $query) or die(mysqli_error($connect));
		
		if(!$result){
			$err_msg = "Failed to update profile !";
		}else{
			$err_msg = "Update profile successfully !";
		}
	}
	
	echo $err_msg;
?>