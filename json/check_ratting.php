<?php
	include('connect.php');
	
	$no_prm_login = $_GET["no_prm_login"];
	$no_prm = $_GET["no_prm"];
	
	$obj_1 = array();
	$obj_2 = array();
	
	if(mysqli_connect_errno()){
		echo "connect error in: ".mysqli_connect_errno();
	}else{
		$query = "SELECT * FROM `ratting` WHERE `from` = '".$no_prm_login."' AND `to` = '".$no_prm."' AND MONTH(`ratting`.`date_created`) = MONTH(CURDATE())";
		$result = mysqli_query($connect, $query) or die(mysqli_error($connect));
		
		if(!$result){
			echo "The user didn't ratting before.";
		}else{
			while($arr = mysqli_fetch_array($result)){
				$obj_1[] = $arr;
			}
			$obj_2 = array("ratting" => $obj_1);
		}
	}
	
	echo json_encode($obj_2);
	
	$result->close();
	mysqli_close($connect);
?>