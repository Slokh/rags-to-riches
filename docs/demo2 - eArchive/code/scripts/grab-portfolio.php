<?php
  $link = mysqli_connect("localhost", "parallel_user", "andrew56!", "parallel_RTR");

$id = mysqli_real_escape_string($link, $_POST["id"]);
$c0 = mysqli_real_escape_string($link, $_POST["c0"]);
$c1 = mysqli_real_escape_string($link, $_POST["c1"]);
$c2 = mysqli_real_escape_string($link, $_POST["c2"]);
$c3 = mysqli_real_escape_string($link, $_POST["c3"]);
$c4 = mysqli_real_escape_string($link, $_POST["c4"]);
$c5 = mysqli_real_escape_string($link, $_POST["c5"]);
$c6 = mysqli_real_escape_string($link, $_POST["c6"]);
$c7 = mysqli_real_escape_string($link, $_POST["c7"]);
$c8 = mysqli_real_escape_string($link, $_POST["c8"]);
$c9 = mysqli_real_escape_string($link, $_POST["c9"]);

$d0 = $d1 = $d2 = $d3 = $d4 = $d5 = $d6 = $d7 = $d8 = $d9 = 0;
$b = 5000;

$sql = "SELECT * FROM UserPortfolios WHERE `id` = $id";
$rs = mysqli_query($link, $sql);

if(mysqli_num_rows($rs) == 0) {
  $sql = "INSERT INTO `UserPortfolios` (`id`, `Turn`, `Balance`) VALUES ($id, 1, 5000)";
  mysqli_query($link, $sql);
} else {
  $row = mysqli_fetch_array($rs);
  $d0 = $row[$c0];
  $d1 = $row[$c1];
  $d2 = $row[$c2];
  $d3 = $row[$c3];
  $d4 = $row[$c4];
  $d5 = $row[$c5];
  $d6 = $row[$c6];
  $d7 = $row[$c7];
  $d8 = $row[$c8];
  $d9 = $row[$c9];
  $b = $row['Balance'];
}

echo $b . "/" . $c0 . "," . $d0 . "/" . $c1 . "," . $d1 . "/" . $c2 . "," . $d2 . "/" . $c3 . "," . $d3 . "/" . $c4 . "," . $d4 . "/" . $c5 . "," . $d5 . "/" . $c6 . "," . $d6 . "/" . $c7 . "," . $d7 . "/" . $c8 . "," . $d8 . "/" . $c9 . "," . $d9;

?>