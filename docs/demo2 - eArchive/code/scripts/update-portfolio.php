<?php
  $link = mysqli_connect("localhost", "parallel_user", "andrew56!", "parallel_RTR");

$id = mysqli_real_escape_string($link, $_POST["id"]);
$d = mysqli_real_escape_string($link, $_POST["d"]);
$c = mysqli_real_escape_string($link, $_POST["c"]);
$t = mysqli_real_escape_string($link, $_POST["t"]);
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
$d0 = mysqli_real_escape_string($link, $_POST["d0"]);
$d1 = mysqli_real_escape_string($link, $_POST["d1"]);
$d2 = mysqli_real_escape_string($link, $_POST["d2"]);
$d3 = mysqli_real_escape_string($link, $_POST["d3"]);
$d4 = mysqli_real_escape_string($link, $_POST["d4"]);
$d5 = mysqli_real_escape_string($link, $_POST["d5"]);
$d6 = mysqli_real_escape_string($link, $_POST["d6"]);
$d7 = mysqli_real_escape_string($link, $_POST["d7"]);
$d8 = mysqli_real_escape_string($link, $_POST["d8"]);
$d9 = mysqli_real_escape_string($link, $_POST["d9"]);

$real_b = $d . "." . $c;

$sql = "UPDATE `UserPortfolios` SET `Turn` = '$t', `Balance` = '$real_b', `$c0` = $d0, `$c1` = $d1, `$c2` = $d2, `$c3` = $d3, `$c4` = $d4, `$c5` = $d5, `$c6` = $d6, `$c7` = $d7, `$c8` = $d8, `$c9` = $d9 WHERE `id` = $id";
mysqli_query($link, $sql);

echo mysqli_error($link);

?>