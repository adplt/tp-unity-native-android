<?php
	include('connect.php');
	
	$obj_1 = array();
	$obj_2 = array();
	
	$id = $_GET["id_data"];
	
	if(mysqli_connect_errno()){
		echo "connect error in: ".mysqli_connect_errno();
	}else{
		$query = "SELECT `student_candidate`.`id`,
					`student_candidate`.`candidate_name`,
					`student_candidate`.`address`,
					`student_candidate`.`email`,
					`student_candidate`.`work_number`,
					`student_candidate`.`phone_number`,
					`student_candidate`.`company`,
					`student_candidate`.`alumni`,
					`student_candidate`.`degree`,
					`student_candidate`.`follow_up_date`,
					`student_candidate`.`result`,
					`student_candidate`.`description`,
					`data`.`data_name`
				FROM `student_candidate` JOIN `data` ON `student_candidate`.`id_data` = `data`.`id`
				WHERE `id_data` = ".$id." AND `result` = 0 AND `description` = ''";
		$result = mysqli_query($connect, $query) or die(mysqli_error($connect));
				
		while($arr = mysqli_fetch_array($result)){
			$obj_1[] = $arr;
		}
		$obj_2 = array("follow_up_history" => $obj_1);
	}
	
	echo json_encode($obj_2);
	$result->close();
	mysqli_close($connect);
?>