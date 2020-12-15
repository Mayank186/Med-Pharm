<?php


// array for JSON response
$response = array();


// include db connect class
require_once __DIR__ . '/db_connect.php';

// connecting to db
$db = new DB_CONNECT();

// check for post data
if (isset($_GET["pid"])) {
    $pid = $_GET['pid'];

    // get a product from products table
   $result = mysql_query("SELECT * FROM sub_pro WHERE sp_id= '$pid'");

    if (!empty($result)) {
        // check for empty result
        if (mysql_num_rows($result) > 0) 
		{

            $response["sub_pro"] = array();
		
		while($Rows = mysql_fetch_array($result,MYSQL_ASSOC))
		{
			$sub_pro  = array();
			$sub_pro = $Rows;
			array_push($response["sub_pro"],$sub_pro);
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