<?php
  $link = mysqli_connect("localhost", "parallel_user", "andrew56!", "parallel_RTR");

$amount = mysqli_real_escape_string($link, $_POST["amount"]);
$code = mysqli_real_escape_string($link, $_POST["code"]);
$arr = array();
$arr2 = array();
$arr3 = array();

$sql = "SELECT * FROM GameSessions WHERE `Session` = '$code' AND `Type` = 'real'";
$rs = mysqli_query($link, $sql);
$row = mysqli_fetch_array($rs);
array_push($arr, trim($row['Company0']));
array_push($arr, trim($row['Company1']));
array_push($arr, trim($row['Company2']));
array_push($arr, trim($row['Company3']));
array_push($arr, trim($row['Company4']));
array_push($arr, trim($row['Company5']));
array_push($arr, trim($row['Company6']));
array_push($arr, trim($row['Company7']));
array_push($arr, trim($row['Company8']));
array_push($arr, trim($row['Company9']));


$sql = "SELECT * FROM GameSessions WHERE `Session` = '$code' AND `Type` = 'fake'";
$rs = mysqli_query($link, $sql);
$row = mysqli_fetch_array($rs);
array_push($arr2, trim($row['Company0']));
array_push($arr2, trim($row['Company1']));
array_push($arr2, trim($row['Company2']));
array_push($arr2, trim($row['Company3']));
array_push($arr2, trim($row['Company4']));
array_push($arr2, trim($row['Company5']));
array_push($arr2, trim($row['Company6']));
array_push($arr2, trim($row['Company7']));
array_push($arr2, trim($row['Company8']));
array_push($arr2, trim($row['Company9']));


$sql = "SELECT * FROM GameSessions WHERE `Session` = '$code' AND `Type` = 'tick'";
$rs = mysqli_query($link, $sql);
$row = mysqli_fetch_array($rs);
array_push($arr3, trim($row['Company0']));
array_push($arr3, trim($row['Company1']));
array_push($arr3, trim($row['Company2']));
array_push($arr3, trim($row['Company3']));
array_push($arr3, trim($row['Company4']));
array_push($arr3, trim($row['Company5']));
array_push($arr3, trim($row['Company6']));
array_push($arr3, trim($row['Company7']));
array_push($arr3, trim($row['Company8']));
array_push($arr3, trim($row['Company9']));

$s0 = array();
$s1 = array();
$s2 = array();
$s3 = array();
$s4 = array();
$s5 = array();
$s6 = array();
$s7 = array();
$s8 = array();
$s9 = array();


$sql = "SELECT * FROM StockData ORDER BY `Date` DESC LIMIT 50";
$rs = mysqli_query($link, $sql);
while($row  = mysqli_fetch_array($rs)) {
  array_push($s0, $row[$arr[0]]);
  array_push($s1, $row[$arr[1]]);
  array_push($s2, $row[$arr[2]]);
  array_push($s3, $row[$arr[3]]);
  array_push($s4, $row[$arr[4]]);
  array_push($s5, $row[$arr[5]]);
  array_push($s6, $row[$arr[6]]);
  array_push($s7, $row[$arr[7]]);
  array_push($s8, $row[$arr[8]]);
  array_push($s9, $row[$arr[9]]);
}

$stocks = array($s0, $s1, $s2, $s3, $s4, $s5, $s6, $s7, $s8, $s9);
$c = 0;
foreach($arr as $val) {
  echo $val . "," . $arr2[$c] . "," . $arr3[$c];
  foreach($stocks[$c] as $price) {
    echo "," . $price;
  }
if($c != 9) {
  echo "/";
}
  $c++;
}

?>