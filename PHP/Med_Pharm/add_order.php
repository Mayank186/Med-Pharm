<?php

/*
 * Following code will create a new product row
 * All product details are read from HTTP Post Request
 */

// array for JSON response
$response = array();

// check for required fields
if (isset($_POST['pname']) && isset($_POST['rate']) && isset($_POST['qty']) && isset($_POST['spid']) && isset($_POST['name']) && isset($_POST['phone']) && isset($_POST['address']) && isset($_POST['r_id']) && isset($_POST['o_date'])) 
{
    
  
    $pname = $_POST['pname'];
    $rate = $_POST['rate'];
    $qty = $_POST['qty'];
    $spid = $_POST['spid'];
    $name = $_POST['name'];
    $phone = $_POST['phone'];
    $address = $_POST['address'];
    $r_id = $_POST['r_id'];
	$o_date = $_POST['o_date'];
    
     $arr = explode(",",$pname);
     $rate_ = explode(",",$rate);
     $qty_ = explode(",",$qty);
     $spid_ = explode(",",$spid);
	
    // include db connect class
    require_once __DIR__ . '/db_connect.php';

    // connecting to db
    $db = new DB_CONNECT();
$i=0;
foreach($arr as $rec)
{
    // mysql inserting a new row
    $result = mysql_query("INSERT INTO order_detail(name,address,phone,pname,rate,qty,spid,rid,o_date) VALUES('$name','$address','$phone','".$rec."','".$rate_[$i]."','".$qty_[$i]."','".$spid_[$i]."','$r_id','$o_date')");
	$result1 = mysql_query("update sub_pro set stock = stock - '".$qty_[$i]."' where sp_id='".$spid_[$i]."'");
    // check if row inserted or not
    
	$i++;
}
if ($result) 
{
        // successfully inserted into database
        $response["success"] = 1;
        $response["message"] = "Insert successfully created.";

        // echoing JSON response
        echo json_encode($response);
    } else {
        // failed to insert row
        $response["success"] = 0;
        $response["message"] = "Oops! An error occurred.";
        
        // echoing JSON response
        echo json_encode($response);
    }
} else
{
    // required field is missing
    $response["success"] = 0;
    $response["message"] = "Required field(s) is missing";

    // echoing JSON response
    echo json_encode($response);
}
?>