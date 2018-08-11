<?php
	include('connect.php');
	
	$no_prm = $_GET["no_prm"];
	$follow_up_date = $_GET["follow_up_date"];
	$result = $_GET["result"];
	$note = $_GET["note"];
	$id_cs = $_GET["id_cs"];
	
	$err_msg = "";
	
	if(mysqli_connect_errno()){
		echo "connect error in: ".mysqli_connect_errno();
		$err_msg = "Failed to connect !";
	}else{
		$query = "UPDATE `student_candidate` SET `no_prm` = '".$no_prm."', `follow_up_date` = '".$follow_up_date."', `result` = ".$result.", `description` = '".$note."' WHERE `id` = ".$id_cs;
		$result = mysqli_query($connect, $query) or die(mysqli_error($connect));
		
		if(!$result){
			$err_msg = "Failed to input data !";
		}else{
			$err_msg = "Input Data Successfully!";
		}
	}
	
	echo $err_msg;
?>