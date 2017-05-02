<?php
  $link = mysqli_connect("localhost", "parallel_user", "andrew56!", "parallel_RTR");

$id = mysqli_real_escape_string($link, $_POST["id"]);

$sql = "SELECT * FROM Achievements WHERE `id` = $id";
$rs = mysqli_query($link, $sql);

if(mysqli_num_rows($rs) == 0) {
  $sql = "INSERT INTO `Achievements` (`id`) VALUES ($id)";
  mysqli_query($link, $sql);
} else {
  $row = mysqli_fetch_array($rs);
  echo $row['Wins'] . "/" . $row['Earnings'];
}

echo "0/0";

?>