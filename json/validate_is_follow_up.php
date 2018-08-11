<?php
	include('connect.php');
	
	$id_prm = $_GET["no_prm_login"];
	
	$obj_1 = array();
	$obj_2 = array();
	
	if(mysqli_connect_errno()){
		echo "connect error in: ".mysqli_connect_errno();
	}else{
		$query = "SELECT * FROM `team_promotion` WHERE `no_prm` =  '".$id_prm."' AND `is_follow_up` = 1";
		$result = mysqli_query($connect, $query) or die(mysqli_error($connect));
		
		if(!$result){
			echo "PRM and Password invalid !";
		}else{
			while($arr = mysqli_fetch_array($result)){
				$obj_1[] = $arr;
			}
			$obj_2 = array("validate_follow_up" => $obj_1);
		}
	}
	
	echo json_encode($obj_2);
	
	$result->close();
	mysqli_close($connect);
?>