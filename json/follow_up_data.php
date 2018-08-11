<?php
	include('connect.php');
	
	$obj_1 = array();
	$obj_2 = array();
	
	if(mysqli_connect_errno()){
		echo "connect error in: ".mysqli_connect_errno();
	}else{
		$query = "SELECT * FROM `data`";
		$result = mysqli_query($connect, $query) or die(mysqli_error($connect));
				
		while($arr = mysqli_fetch_array($result)){
			$obj_1[] = $arr;
		}
		$obj_2 = array("follow_up_data" => $obj_1);
	}
	
	echo json_encode($obj_2);
	$result->close();
	mysqli_close($connect);
?>