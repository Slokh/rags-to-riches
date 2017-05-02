<?php
  $link = mysqli_connect("localhost", "parallel_user", "andrew56!", "parallel_RTR");

$sql = "SELECT a.Username AS user, a.id AS id, u.Balance AS bal FROM `UserPortfolios` u, `Accounts` a WHERE u.id = a.id ORDER BY u.Balance DESC LIMIT 2";
$rs = mysqli_query($link, $sql);
$row = mysqli_fetch_array($rs);

echo $row['user'] . "," . $row['bal'] . "/";

$id = $row['id'];

$sql = "SELECT * FROM UserPortfolios WHERE `id` = $id";
$rs2 = mysqli_query($link, $sql);
$row2 = mysqli_fetch_array($rs2);

$sql = "UPDATE Achievements SET `Wins` = `Wins` + 1, `Earnings` = `Earnings` + ".$row2['Balance']." - 5000 WHERE `id` = $id";
mysqli_query($link, $sql);

$row = mysqli_fetch_array($rs);

echo $row['user'] . "," . $row['bal'];

$id = $row['id'];

$sql = "SELECT * FROM UserPortfolios WHERE `id` = $id";
$rs2 = mysqli_query($link, $sql);
$row2 = mysqli_fetch_array($rs2);

$sql = "UPDATE Achievements SET `Earnings` = `Earnings` + ".$row2['Balance']." - 5000 WHERE `id` = $id";
mysqli_query($link, $sql);

?>