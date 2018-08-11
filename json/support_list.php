<?php
	include('connect.php');
	
	$obj_1 = array();
	$obj_2 = array();
	
	$noprm = $_GET["no_prm_login"];
	$date = $_GET["date_filter"];
	
	if(mysqli_connect_errno()){
		echo "connect error in: ".mysqli_connect_errno();
	}else{
		if(!$date && $noprm){
			$date = date("Y-m-d");
			$query = "SELECT * FROM `support` JOIN `schedule` ON `support`.`id` = `schedule`.`id_support` WHERE `schedule`.`no_prm` = '".$noprm."' AND MONTH(`support`.`support_from`) = MONTH('".$date."')";
		}else{
			$query = "SELECT * FROM `support` JOIN `schedule` ON `support`.`id` = `schedule`.`id_support` WHERE `schedule`.`no_prm` = '".$noprm."' AND DATE(`support`.`support_from`) = '".$date."'";
		}
			
		$result = mysqli_query($connect, $query) or die(mysqli_error($connect));
				
		while($arr = mysqli_fetch_array($result)){
			$obj_1[] = $arr;
		}
		
		$obj_2 = array("support_list" => $obj_1);
	}
	
	echo json_encode($obj_2);
	$result->close();
	mysqli_close($connect);
?>