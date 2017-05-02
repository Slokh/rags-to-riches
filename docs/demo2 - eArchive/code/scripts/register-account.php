<?php
  $link = mysqli_connect("localhost", "parallel_user", "andrew56!", "parallel_RTR");

$email= mysqli_real_escape_string($link, $_POST["email"]);
$username= mysqli_real_escape_string($link, $_POST["username"]);
$password= mysqli_real_escape_string($link, $_POST["password"]);


$sql = "INSERT INTO `Accounts`(`Email`, `Username`, `Password`) VALUES ('$email', '$username', '$password')";

if (mysqli_query($link, $sql) === TRUE) {
    echo "TRUE";
} else {
    echo "FALSE";
}

?>