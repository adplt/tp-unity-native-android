<?php
	include('connect.php');
	
	//return json response 
	$json = array();
	
	$gcm_id = $_POST["no_prm"];
	$gcm_id  = $_POST["gcm_id"]; //GCM Registration ID got from device
	
	/**
	* Registering a user device in database
	* Store reg id in users table
	*/
	if(isset($gcm_id) && isset($gcm_id) && isset($gcm_id)){
	
		// Store user details in db
		$res = storeUser($no_prm, $gcm_id);
		
		$registatoin_id = array($gcm_id);
		$message = array("product" => "shirt");
		
		$result = send_push_notification($registatoin_id, $message);
		
		echo $result;
	}else{
		//user details not found
	}
?>