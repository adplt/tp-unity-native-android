<?php
	include('connect.php');
	
	$obj_1 = array();
	$obj_2 = array();
	
	$id = $_GET["id"];
	$date = $_GET["date_filter"];
	
	if(mysqli_connect_errno()){
		echo "connect error in: ".mysqli_connect_errno();
	}else{
		if(!$date){
			$date = date("Y-m-d");
			$query = "SELECT
						`event`.`id`,
						`event`.`event_name`,
						`event`.`location`,
						`event`.`start`,
						`event`.`end`,
						`event`.`total_tp`,
						`event`.`note`,
						`event`.`image_path`
						FROM `event` JOIN `staff` ON `event`.`id_staff` = `staff`.`id`
						JOIN `branch` ON `branch`.`id` = `staff`.`id_branch`
						WHERE `branch`.`id` = ".$id." AND MONTH(`event`.`start`) = MONTH('".$date."')";
		}else{
			$query = "SELECT
						`event`.`id`,
						`event`.`event_name`,
						`event`.`location`,
						`event`.`start`,
						`event`.`end`,
						`event`.`total_tp`,
						`event`.`note`,
						`event`.`image_path`
						FROM `event` JOIN  `staff` ON `event`.`id_staff` = `staff`.`id`
						JOIN `branch` ON `branch`.`id` = `staff`.`id_branch`
						WHERE `branch`.`id` = ".$id." AND DATE(`event`.`start`) = '".$date."'";
		}
		$result = mysqli_query($connect, $query) or die(mysqli_error($connect));
		
		// See the password_hash() example to see where this came from.
		
		while($arr = mysqli_fetch_array($result)){
			/*$hash = $arr['image_path'];
		
			if(password_verify('rasmuslerdorf', $hash)){
				$arr['image_path'] = password_verify('rasmuslerdorf', $hash);
				echo 'Password is valid!';
			} else {
				echo 'Invalid password.';
			}*/

			$obj_1[] = $arr;
		}
		
		$obj_2 = array("event_list" => $obj_1);
	}
	
	echo json_encode($obj_2);
	$result->close();
	mysqli_close($connect);
?>