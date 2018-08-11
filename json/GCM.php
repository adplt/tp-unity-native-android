<?php
	include('connect.php');
	
	$id = $_GET["id"];
	$gcm_id = $_GET["gcm_id"];
	//
	
	$err_msg = "";
	
	if(mysqli_connect_errno()){
		echo "connect error in: ".mysqli_connect_errno();
		$err_msg = "Failed to connect !";
	}else{
		$query = "UPDATE `team_promotion` SET `gcm_id` = '".$gcm_id."' WHERE `id` = '".$id."'";
		$result = mysqli_query($connect, $query) or die(mysqli_error($connect));
		
		if(!$result){
			$err_msg = "Failed to update data !";
		}else{
			$err_msg = "Update Data Successfully !";
		}
	}
	
	echo $err_msg;
?>