<?php

	include 'database.php';

	header("Content-type:text/html;charset=utf-8");

	if(isset($_COOKIE["isvoted"]))
	{
		evolve();
		echo json_encode(array("isvoted" => 1,"votefor" =>$_COOKIE["votefor"],"generation"=>1));

		exit();
	}
	else
	{
		$imgid = $_POST['imgid'];
		$imgid = ltrim($imgid,"img_");
		$link = dataConnect();
		$newVote = updateVote($link,$imgid);

		$json_arr = array("vote"=>$newVote,"id"=>$imgid,"isreload"=>0,"isvoted" => 0);
		$json_obj = json_encode($json_arr);

		echo $json_obj;
		

		setcookie("isvoted", "1", time()+3600);
		setcookie("votefor", $imgid, time()+3600);
		setcookie("generation", "1", time()+3600);

		$link -> close();//断开数据库连接
	}

	


?>