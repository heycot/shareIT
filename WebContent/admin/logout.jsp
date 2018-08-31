<?php
 ob_start();
 session_start();
?>
<?php require_once $_SERVER['DOCUMENT_ROOT'].'/abc/functions/dbconnect.php'; ?>
<?php require_once $_SERVER['DOCUMENT_ROOT'].'/abc/functions/checkuser.php'; ?>
<?php
session_destroy();
header("location:/abc/admin/login.php")
?>
<?php ob_end_flush(); ?>
