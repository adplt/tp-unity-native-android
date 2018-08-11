<?php
	include('connect.php');
	
	$obj_1 = array();
	$obj_2 = array();
	
	$id = $_GET["id_event"];
	
	if(mysqli_connect_errno()){
		echo "connect error in: ".mysqli_connect_errno();
	}else{
		$query = "SELECT * 
					FROM `team_promotion` JOIN `schedule` ON `team_promotion`.`no_prm` = `schedule`.`no_prm`
					JOIN `support_event` ON `schedule`.`id` = `support_event`.`id_schedule`
					WHERE `id_event` = ".$id;
		$result = mysqli_query($connect, $query) or die(mysqli_error($connect));
				
		while($arr = mysqli_fetch_array($result)){
			$obj_1[] = $arr;
		}
		$obj_2 = array("join_event" => $obj_1);
	}
	
	echo json_encode($obj_2);
	$result->close();
	mysqli_close($connect);
?>