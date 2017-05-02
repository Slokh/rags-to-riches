<?php
  $link = mysqli_connect("localhost", "parallel_user", "andrew56!", "parallel_RTR");

$code = mysqli_real_escape_string($link, $_POST["code"]);

$sql = "SELECT COUNT(*) AS amt FROM `GameSessions` WHERE `Session` = '$code'";
$rs = mysqli_query($link, $sql);
$row = mysqli_fetch_array($rs);

if($row['amt'] == 0) {
  echo "FALSE";
} else {
  echo "TRUE";
}

?>