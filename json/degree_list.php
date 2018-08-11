<?php
	include('connect.php');
	
	$name = $_GET["name"];
	
	$obj_1 = array();
	$obj_2 = array();
	
	if(mysqli_connect_errno()){
		echo "connect error in: ".mysqli_connect_errno();
	}else{
		if(!$id){
			$query = "SELECT * FROM `degree` ORDER BY `id` ASC";
		}else{
			$query = "SELECT DISTINCT * FROM `product` JOIN `degree` ON `degree`.`id` = `product`.`id_degree` JOIN `faculty` ON `faculty`.`id` = `product`.`id_faculty` WHERE `faculty`.`faculty_name` LIKE '%".$name."%'";
		}
		
		$result = mysqli_query($connect, $query) or die(mysqli_error($connect));

		while($arr = mysqli_fetch_array($result)){
			$obj_1[] = $arr;
		}
		$obj_2 = array("degree_list" => $obj_1);
	}
	
	echo json_encode($obj_2);
	$result->close();
	mysqli_close($connect);
?>