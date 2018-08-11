<?php
	include('connect.php');
	
	$obj_1 = array();
	$obj_2 = array();

	if(mysqli_connect_errno()){
		echo "connect error in: ".mysqli_connect_errno();
	}else{
		if(!$no_prm){
			$query = "SELECT
						`team_promotion`.`id`,
						`team_promotion`.`no_prm`,
						`team_promotion`.`tp_name`,
						`team_promotion`.`gender`,
						`team_promotion`.`join_date`,
						`team_promotion`.`email`,
						`team_promotion`.`address`,
						`team_promotion`.`phone_number`,
						`team_promotion`.`work_number`,
						`team_promotion`.`score`,
						`team_promotion`.`password`,
						`team_promotion`.`picture`,
						`team_promotion`.`accepted_tp`,
						`team_promotion`.`birth_date`,
						`team_promotion`.`id_degree`,
						`team_promotion`.`id_branch`,
						`team_promotion`.`picture`,
						`team_promotion`.`background`,
						`staff`.`username`,
						`degree`.`degree_name`
					FROM `team_promotion` JOIN `branch` ON `team_promotion`.`id_branch` = `branch`.`id`
					JOIN `degree` ON `team_promotion`.`id_degree` = `degree`.`id`
					WHERE `accepted_tp` = 1";
			$result = mysqli_query($connect, $query) or die(mysqli_error($connect));
					
			while($arr = mysqli_fetch_array($result)){
				$obj_1[] = $arr;
			}
			$obj_2 = array("tp_list" => $obj_1);
		}else{
			$query = "SELECT
						`team_promotion`.`id`,
						`team_promotion`.`no_prm`,
						`team_promotion`.`tp_name`,
						`team_promotion`.`gender`,
						`team_promotion`.`join_date`,
						`team_promotion`.`email`,
						`team_promotion`.`address`,
						`team_promotion`.`phone_number`,
						`team_promotion`.`work_number`,
						`team_promotion`.`score`,
						`team_promotion`.`password`,
						`team_promotion`.`picture`,
						`team_promotion`.`accepted_tp`,
						`team_promotion`.`id_staff`,
						`team_promotion`.`birth_date`,
						`team_promotion`.`id_degree`,
						`team_promotion`.`picture`,
						`degree`.`degree_name`
					FROM `team_promotion` JOIN `branch` ON `team_promotion`.`id_branch` = `branch`.`id`
					JOIN `degree` ON `team_promotion`.`id_degree` = `degree`.`id`
					WHERE `accepted_tp` = 1";
			$result = mysqli_query($connect, $query) or die(mysqli_error($connect));
					
			while($arr = mysqli_fetch_array($result)){
				$obj_1[] = $arr;
			}
			$obj_2 = array("tp_list" => $obj_1);
		}
	}
	
	echo json_encode($obj_2);
	$result->close();
	mysqli_close($connect);
?>