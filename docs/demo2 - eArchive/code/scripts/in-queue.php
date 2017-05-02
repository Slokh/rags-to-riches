<?php
  $link = mysqli_connect("localhost", "parallel_user", "andrew56!", "parallel_RTR");
  
$id = mysqli_real_escape_string($link, $_POST["id"]);

$sql = "INSERT INTO `Queue`(`id`) VALUES ($id)";
mysqli_query($link, $sql);

$sql = "SELECT COUNT(*) AS amt FROM `Queue`";
$rs = mysqli_query($link, $sql);
$row = mysqli_fetch_array($rs);

echo $row['amt'];

?>