<?php
  $link = mysqli_connect("localhost", "parallel_user", "andrew56!", "parallel_RTR");

$amount = mysqli_real_escape_string($link, $_POST["amount"]);
$arr = array();
$arr2 = array();
$arr3 = array();

$sql = "SELECT * FROM FakeCompanies ORDER BY RAND() LIMIT $amount";
$rs = mysqli_query($link, $sql);
$row = mysqli_fetch_array($rs);
  array_push($arr2, trim($row['Company']));
  array_push($arr3, trim($row['Ticker']));
while($row = mysqli_fetch_array($rs)){
  array_push($arr2, trim($row['Company']));
  array_push($arr3, trim($row['Ticker']));
}

$sql = "SELECT * FROM RealCompanies ORDER BY RAND() LIMIT $amount";
$rs = mysqli_query($link, $sql);
$row = mysqli_fetch_array($rs);
  array_push($arr, trim($row['Company']));
while($row = mysqli_fetch_array($rs)){
  array_push($arr, trim($row['Company']));
}

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
  echo "/";
  $c++;
}

?>