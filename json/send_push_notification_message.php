<?php
	include('connect.php');
	
	$gcm_id    = $_GET["gcm_id"]; // GCM Registration ID got from device
	$message = $_GET["message"];
	
	if(isset($gcm_id) && isset($message)){
		$registatoin_id = array($gcm_id);
		$message_arr = array("price" => $message);
		$result = send_push_notification($registatoin_id, $message_arr);
		echo $result;
	}
?>
