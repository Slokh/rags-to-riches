<?php
  $link = mysqli_connect("localhost", "parallel_user", "andrew56!", "parallel_RTR");

$sql = "SELECT COUNT(*) AS amt FROM `UserPortfolios` WHERE `Turn` = 1";
$rs = mysqli_query($link, $sql);
$row = mysqli_fetch_array($rs);

echo $row['amt'];

?>