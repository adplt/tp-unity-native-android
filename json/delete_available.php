<?php
	include('connect.php');
	
	$id_available = $_GET["id"];
	
	$err_msg = "";
	
	if(mysqli_connect_errno()){
		echo "connect error in: ".mysqli_connect_errno();
		$err_msg = "Failed to connect !";
	}else{
		$query = "DELETE FROM `available` WHERE `id` = '".$id_available."'";
		$result = mysqli_query($connect, $query) or die(mysqli_error($connect));
		
		if(!$result){
			$err_msg = "Failed to delete data !";
		}else{
			$err_msg = "Delete Data Successfully !";
		}
	}
	
	echo $err_msg;
?>