<?php
	include('connect.php');
	
	$id = $_GET["id"];
	$start = $_GET["start"];
	$finish = $_GET["finish"];
	$desc = $_GET["desc"];
	
	$err_msg = "";
	
	if(mysqli_connect_errno()){
		echo "connect error in: ".mysqli_connect_errno();
		$err_msg = "Failed to connect !";
	}else{
		$query = "UPDATE `absence` SET `clock_in` = '".$start."', `clock_out` = '".$finish."', `description` = '".$desc."' WHERE `id` = '".$id."'";
		$result = mysqli_query($connect, $query) or die(mysqli_error($connect));
		
		if(!$result){
			$err_msg = "Failed to update data !";
		}else{
			$err_msg = "Update Data Successfully !";
		}
	}
	
	echo $err_msg;
?>