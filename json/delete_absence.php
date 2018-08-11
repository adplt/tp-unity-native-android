<?php
	include('connect.php');
	
	$id_absence = $_GET["id"];
	
	$err_msg = "";
	
	if(mysqli_connect_errno()){
		echo "connect error in: ".mysqli_connect_errno();
		$err_msg = "Failed to connect !";
	}else{
		$query = "DELETE FROM `absence` WHERE `id` = '".$id_absence."'";
		$result = mysqli_query($connect, $query) or die(mysqli_error($connect));
		
		if(!$result){
			$err_msg = "Failed to delete data !";
		}else{
			$err_msg = "Delete Data Successfully !";
		}
	}
	
	echo $err_msg;
?>