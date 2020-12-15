<?php

	require_once __DIR__ . '/db_connect.php';

    // connecting to db
    $db = new DB_CONNECT();

    // mysql inserting a new row
	
    $result = mysql_query("SELECT * FROM agancy_detail");
	
	
	if(mysql_num_rows($result) > 0)
	{
		$response["agancy_detail"] = array();
		
		while($Rows = mysql_fetch_array($result,MYSQL_ASSOC))
		{
			$agancy_detail  = array();
			
			$agancy_detail = $Rows;
			array_push($response["agancy_detail"],$agancy_detail);
		}
		$response["success"] = 1;
	}
	else
	{
		$response["Error"] = "No Any Users Found";
	}
	
	echo json_encode($response);
?>