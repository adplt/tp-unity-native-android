<?php
	include('connect.php');
	
	$no_prm = $_GET["no_prm"];
	$id_event = $_GET["id_event"];
	$start_date = $_GET["start_date"];
	$end_date = $_GET["end_date"];
	$note = $_GET["note"];
	
	$err_msg = "";
	
	if(mysqli_connect_errno()){
		echo "connect error in: ".mysqli_connect_errno();
		$err_msg = "Failed to connect !";
	}else{
		$query = "SELECT COUNT(*) AS Jumlah FROM `support`";
		$result = mysqli_query($connect, $query) or die(mysqli_error($connect));
		
		$row = $result->fetch_assoc();
		$id_support = $row['Jumlah'];
		echo "ID Support: ".$id_support."<br>";
		
		$query = "INSERT INTO `support` (`id`,`support_from`, `support_until`, `description`) VALUES (".$id_support.",'".$start_date."','".$end_date."','".$note."')" ;
		$result = mysqli_query($connect, $query) or die(mysqli_error($connect));
		
		if(!$result){
			$err_msg = "Failed to input data !";
		}else{
			$query = "SELECT COUNT(*) AS Jumlah FROM `support`";
			$result = mysqli_query($connect, $query) or die(mysqli_error($connect));
			
			$row = $result->fetch_assoc();
			$id_schedule = $row['Jumlah'];
			echo "ID Schedule: ".$id_schedule.'<br>';
		
			$query = "INSERT INTO `schedule` (`id`,`id_support`, `no_prm`) VALUES (".$id_schedule.",".$id_support.",'".$no_prm."')";
			$result = mysqli_query($connect, $query) or die(mysqli_error($connect));
			
			if(!$result){
				$err_msg = "Failed to input data !";
			}else{
				$query = "INSERT INTO `support_event` (`id_event`, `id_schedule`) VALUES (".$id_event.",'".$id_schedule."')";
				$result = mysqli_query($connect, $query) or die(mysqli_error($connect));
				
				if(!$result){
					$err_msg = "Failed to input data !";
				}else{
					$err_msg = "Input Data Successfully !";
				}
			}
		}
	}
	
	echo $err_msg;
?>