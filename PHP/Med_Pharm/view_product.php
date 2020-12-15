<?php

	require_once __DIR__ . '/db_connect.php';

    // connecting to db
    $db = new DB_CONNECT();

    // mysql inserting a new row
	
    $result = mysql_query("SELECT * FROM product_detail");
	
	
	if(mysql_num_rows($result) > 0)
	{
		$response["product_detail"] = array();
		
		while($Rows = mysql_fetch_array($result,MYSQL_ASSOC))
		{
			$product_detail  = array();
			
			$product_detail = $Rows;
			array_push($response["product_detail"],$product_detail);
		}
		$response["success"] = 1;
	}
	else
	{
		$response["Error"] = "No Any Users Found";
	}
	
	echo json_encode($response);
?>