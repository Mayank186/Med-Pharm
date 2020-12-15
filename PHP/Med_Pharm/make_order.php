<?php


// array for JSON response
$response = array();


// include db connect class
require_once __DIR__ . '/db_connect.php';

// connecting to db
$db = new DB_CONNECT();

// check for post data
if (isset($_GET["rid"])) {
    $rid = $_GET['rid'];

    // get a product from products table
   $result = mysql_query("SELECT * FROM order_detail WHERE r_id= '$rid'");

    if (!empty($result)) {
        // check for empty result
        if (mysql_num_rows($result) > 0) 
		{

            $response["order_detail"] = array();
		
		while($Rows = mysql_fetch_array($result,MYSQL_ASSOC))
		{
			$order_detail  = array();
			$order_detail = $Rows;
			array_push($response["order_detail"],$order_detail);
		}

           
            // success
            $response["success"] = 1;

            echo json_encode($response);
        } else {
            // no product found
            $response["success"] = 0;
            $response["message"] = "No record found";

            // echo no users JSON
            echo json_encode($response);
        }
    } else {
        // no product found
        $response["success"] = 0;
        $response["message"] = "No record found";

        // echo no users JSON
        echo json_encode($response);
    }
}

 else {
    // required field is missing
    $response["success"] = 0;
    $response["message"] = "Required field(s) is missing";

    // echoing JSON response
    echo json_encode($response);
}
?>