<?php

	require_once __DIR__ . '/db_connect.php';

    // connecting to db
    $db = new DB_CONNECT();

    // mysql inserting a new row
	
    $result = mysql_query("SELECT * FROM retailer");
	
	
	if(mysql_num_rows($result) > 0)
	{
		$response["retailer"] = array();
		
		while($Rows = mysql_fetch_array($result,MYSQL_ASSOC))
		{
			$retailer  = array();
			
			$retailer = $Rows;
			array_push($response["retailer"],$retailer);
		}
		$response["success"] = 1;
	}
	else
	{
		$response["Error"] = "No Any Users Found";
	}
	
	echo json_encode($response);
?>