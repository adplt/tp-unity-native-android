<?php
	include('connect.php');
	
	$id = $_GET["id"];
	
	$obj_1 = array();
	$obj_2 = array();
	
	if(mysqli_connect_errno()){
		echo "connect error in: ".mysqli_connect_errno();
	}else{
		if(!$id){
			$query = "SELECT * FROM `faculty`";
		}else{
			$query = "SELECT DISTINCT `faculty`.`id`, `faculty`.`faculty_name`, `faculty`.`note` FROM `product` JOIN `faculty` ON `faculty`.`id` = `product`.`id_faculty` WHERE `product`.`id_branch` = ".$id;
		}
		
		$result = mysqli_query($connect, $query) or die(mysqli_error($connect));
					
		while($arr = mysqli_fetch_array($result)){
			$obj_1[] = $arr;
		}
		$obj_2 = array("faculty_list" => $obj_1);
	}
	
	echo json_encode($obj_2);
	$result->close();
	mysqli_close($connect);
?>