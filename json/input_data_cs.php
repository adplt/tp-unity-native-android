<?php
	include('connect.php');
	
	$data_title_id = $_GET["id"];
	$name_cs = $_GET["name"];
	$email_cs = $_GET["email"];
	$address_cs = $_GET["address"];
	$phone_number_cs = $_GET["phone_number"];
	$work_number_cs = $_GET["work_number"];
	$company_cs = $_GET["company"];
	$alumni_cs = $_GET["alumni"];
	$degree_cs = $_GET["degree"];
	
	$err_msg = "";
	
	if(mysqli_connect_errno()){
		echo "connect error in: ".mysqli_connect_errno();
		$err_msg = "Failed to connect !";
	}else{
		$query = "INSERT INTO `student_candidate` (`candidate_name`, `address`, `email`, `work_number`, `phone_number`, `company`, `alumni`, `degree`, `id_data`) VALUES ('".$name_cs."','".$email_cs."','".$address_cs."','".$phone_number_cs."','".$work_number_cs."','".$company_cs."','".$alumni_cs."','".$degree_cs."','".$data_title_id."')";
		$result = mysqli_query($connect, $query) or die(mysqli_error($connect));
		
		if(!$result){
			$err_msg = "Failed to input data !";
		}else{
			$err_msg = "Input Data Successfully!";
		}
	}
	
	echo $err_msg;
?>