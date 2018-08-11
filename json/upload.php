<?php
	include('connect.php');

	$id = $_GET["id"];
	$folder = $_GET["folder"];
	
	if(isset($_POST['image'])){
		$path = $folder."/".$id.".jpeg";
		$image = $_POST['image'];
		
		if(file_put_contents($path, base64_decode($image)) != false){
			if(mysqli_connect_errno()){
				echo "connect error in: ".mysqli_connect_errno();
				$err_msg = "Failed to connect !";
			}else{
				if($folder == "picture"){
					$query = "UPDATE `team_promotion` SET `picture` = '".$id.".jpeg' WHERE `no_prm` = '".$id."'";
					$result = mysqli_query($connect, $query) or die(mysqli_error($connect));
				}else{
					$query = "UPDATE `team_promotion` SET `background` = '".$id.".jpeg' WHERE `no_prm` = '".$id."'";
					$result = mysqli_query($connect, $query) or die(mysqli_error($connect));
				}
				
				if(!$result){
					echo "Upload success !";
					exit;
				}
			}
		}else{
			echo "Upload failed !";
			exit;
		}
	}else{
		echo "File not found !";
		exit;
	}
	
?>