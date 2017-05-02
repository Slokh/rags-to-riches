<?php
  $link = mysqli_connect("localhost", "parallel_user", "andrew56!", "parallel_RTR");

$amount = mysqli_real_escape_string($link, $_POST["amount"]);
$code = mysqli_real_escape_string($link, $_POST["code"]);
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

$sql = "DELETE FROM `GameSessions` WHERE 1";
mysqli_query($link, $sql);

$sql = "DELETE FROM `UserPortfolios` WHERE 1";
mysqli_query($link, $sql);

$sql = "INSERT INTO `GameSessions` (`Session`, `Type`, `Company0`, `Company1`, `Company2`, `Company3`, `Company4`, `Company5`, `Company6`, `Company7`, `Company8`, `Company9`) VALUES ('$code', 'real', '".$arr[0]."', '".$arr[1]."', '".$arr[2]."', '".$arr[3]."', '".$arr[4]."', '".$arr[5]."', '".$arr[6]."', '".$arr[7]."', '".$arr[8]."', '".$arr[9]."')";
mysqli_query($link, $sql);

$sql = "INSERT INTO `GameSessions` (`Session`, `Type`, `Company0`, `Company1`, `Company2`, `Company3`, `Company4`, `Company5`, `Company6`, `Company7`, `Company8`, `Company9`) VALUES ('$code', 'fake', '".$arr2[0]."', '".$arr2[1]."', '".$arr2[2]."', '".$arr2[3]."', '".$arr2[4]."', '".$arr2[5]."', '".$arr2[6]."', '".$arr2[7]."', '".$arr2[8]."', '".$arr2[9]."')";
mysqli_query($link, $sql);

$sql = "INSERT INTO `GameSessions` (`Session`, `Type`, `Company0`, `Company1`, `Company2`, `Company3`, `Company4`, `Company5`, `Company6`, `Company7`, `Company8`, `Company9`) VALUES ('$code', 'tick', '".$arr3[0]."', '".$arr3[1]."', '".$arr3[2]."', '".$arr3[3]."', '".$arr3[4]."', '".$arr3[5]."', '".$arr3[6]."', '".$arr3[7]."', '".$arr3[8]."', '".$arr3[9]."')";
mysqli_query($link, $sql);


?>