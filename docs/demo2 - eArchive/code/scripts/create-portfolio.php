<?php
  $link = mysqli_connect("localhost", "parallel_user", "andrew56!", "parallel_RTR");

$id = mysqli_real_escape_string($link, $_POST["id"]);


  $sql = "DELETE FROM `UserPortfolios` WHERE `id` = '$id'";
  mysqli_query($link, $sql);


  $sql = "INSERT INTO `UserPortfolios` (`id`, `Turn`, `Balance`) VALUES ($id, 1, 5000)";
  mysqli_query($link, $sql);

?>