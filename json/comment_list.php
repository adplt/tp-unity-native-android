<?php
	include('connect.php');
	
	$obj_1 = array();
	$obj_2 = array();
	
	$no_prm = $_GET["no_prm_login"];
	
	if(mysqli_connect_errno()){
		echo "connect error in: ".mysqli_connect_errno();
	}else{
		$query = "SELECT 
			`ratting`.`id`,
			`ratting`.`date_created`,
			`ratting`.`comment`,
			`team_promotion`.`no_prm`,
			`team_promotion`.`tp_name` AS `from`,
			`team_promotion`.`score`,
			`team_promotion`.`picture` 
			FROM `ratting` JOIN `team_promotion` ON `ratting`.`from` = `team_promotion`.`no_prm`
			WHERE `ratting`.`to` = '".$no_prm."' AND MONTH(`ratting`.`date_created`) = MONTH(CURDATE())";
		$result = mysqli_query($connect, $query) or die(mysqli_error($connect));
				
		while($arr = mysqli_fetch_array($result)){
			$obj_1[] = $arr;
		}
		$obj_2 = array("ratting" => $obj_1);
	}
	
	echo json_encode($obj_2);
	$result->close();
	mysqli_close($connect);
?>