<?php
	include('connect.php');
	
	$obj_1 = array();
	$obj_2 = array();
	
	$no_prm = $_GET["no_prm_login"];
	
	if(mysqli_connect_errno()){
		echo "connect error in: ".mysqli_connect_errno();
	}else{
		$query = "SELECT TIMESTAMPDIFF(HOUR,`clock_in`,`clock_out`) AS hour_count FROM `absence` WHERE MONTH(`clock_in`) = MONTH(NOW()) AND MONTH(`clock_out`) = MONTH(NOW()) AND `no_prm` = '".$no_prm."'";
		$result = mysqli_query($connect, $query) or die(mysqli_error($connect));
				
		while($arr = mysqli_fetch_array($result)){
			$obj_1[] = $arr;
		}

		$obj_2 = array("calculate_salary" => $obj_1);
	}
	
	echo json_encode($obj_2);
	$result->close();
	mysqli_close($connect);
?>