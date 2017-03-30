<?php
  $link = mysqli_connect("localhost", "parallel_user", "andrew56!", "parallel_RTR");

$email= mysqli_real_escape_string($link, $_POST["email"]);
$password= mysqli_real_escape_string($link, $_POST["password"]);


$sql = "SELECT * FROM Accounts WHERE `Email`='$email' AND `Password` = '$password'";
$rs = mysqli_query($link, $sql);

if (mysqli_num_rows($rs) == 0) {
    echo "FALSE";
} else {
    $row = mysqli_fetch_array($rs);
    echo $row['id'] . "/" . $row['Email'] . "/" . $row['Username'] . "/" . $row['Password'];
}

?>