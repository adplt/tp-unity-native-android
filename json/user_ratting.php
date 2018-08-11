<?php
	include('connect.php');
	
	$no_prm_login = $_GET["no_prm_login"];
	
	$obj_1 = array();
	$obj_2 = array();
	
	if(mysqli_connect_errno()){
		echo "connect error in: ".mysqli_connect_errno();
	}else{
		$query = "SELECT COALESCE(ROUND(SUM(`time_management`),2),0) AS `tm`, COALESCE(ROUND(SUM(`initiative`),2),0) AS `i`, COALESCE(ROUND(SUM(`responsible`),2),0) AS `r`, COUNT(`id`) AS `number_id` FROM `ratting`WHERE `to` = '".$no_prm_login."' ORDER BY `id`";
		$result = mysqli_query($connect, $query) or die(mysqli_error($connect));
		
		if(!$result){
			echo "The user didn't ratting before.";
		}else{
			while($arr = mysqli_fetch_assoc($result)){
				$obj_1[] = $arr;
			}
			$obj_2 = array("ratting" => $obj_1);
		}
	}
	
	echo json_encode($obj_2);
	
	$result->close();
	mysqli_close($connect);
?>