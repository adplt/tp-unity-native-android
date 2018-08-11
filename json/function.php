<?php
	include('connect.php');
	
	//Storing new user and returns user details
	function storeUser($no_prm,$gcm_id){
	
		//insert user into database
		$result = mysqli_query($GLOBALS['connect'],"UPDATE `team_promotion` SET `gcm_id` = '".$gcm_id."' WHERE `no_prm` = '".$no_prm."'");
		
		//check for successful store
		if($result){		
			//get user details
			$id = mysqli_insert_id(); // last inserted id
			$result = mysqli_query($GLOBALS['connect'],"SELECT * FROM `team_promotion` WHERE `no_prm` = '".$no_prm."'") or die(mysql_error());
	
			//return user details
			if(mysqli_num_rows($result) > 0){
				return mysqli_fetch_array($result);
			}else{
				return false;
			}
		
		}else{
			return false;
		}
	}
	
	//Get user by email
	function getUserByEmail($no_prm){
		$result = mysqli_query($GLOBALS['connect'],"SELECT * FROM `team_promotion` WHERE `no_prm` = '".$no_prm."' LIMIT 1");
		return $result;
	}
	
	//Getting all registered users
	function getAllUsers(){
		$result = mysqli_query($GLOBALS['connect'],"SELECT * FROM `team_promotion` WHERE COALESCE(`gcm_id`) IS NOT NULL AND `accepted_tp` = 1");
		return $result;
	}
	
	//Validate user
	function isUserExisted($no_prm){
		$result    = mysqli_query($GLOBALS['connect'],"SELECT `email` FROM `team_promotion` WHERE `no_prm` = '".$no_prm."'");
		$NumOfRows = mysqli_num_rows($result);
		
		if($NumOfRows > 0){
			//user existed
			return true;
		}else{
			//user not existed
			return false;
		}
	}
	
	//Sending Push Notification
	function send_push_notification($registatoin_ids, $message) {
	
		//Set POST variables
		$url = 'https://android.googleapis.com/gcm/send';
		
		$fields = array(
			'registration_ids' => $registatoin_ids,
			'data' => $message,
		);
		
		$headers = array(
			'Authorization: key=' . GOOGLE_API_KEY,
			'Content-Type: application/json'
		);
		
		//print_r($headers);
		//Open connection
		$ch = curl_init();
		
		//Set the url, number of POST vars, POST data
		curl_setopt($ch, CURLOPT_URL, $url);
		curl_setopt($ch, CURLOPT_POST, true);
		curl_setopt($ch, CURLOPT_HTTPHEADER, $headers);
		curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
		
		//Disabling SSL Certificate support temporarly
		curl_setopt($ch, CURLOPT_SSL_VERIFYPEER, false);
		curl_setopt($ch, CURLOPT_POSTFIELDS, json_encode($fields));
		
		//Execute post
		$result = curl_exec($ch);
			if($result === FALSE){
			die('Curl failed: ' . curl_error($ch));
		}
		
		//Close connection
		curl_close($ch);
		echo $result;
	}
?>