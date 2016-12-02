<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta charset="utf-8">
<title>CSC791-show All</title>
<meta name="description" content="">
<meta name="author" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="./src/style.css">
<script src="./src/jquery-3.1.1.min.js"></script>
<!--[if lt IE 9]>
<script src="//cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.2/html5shiv.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->

</head>
<body>
<div id="container">
<div style="text-align: center;">
<h2>Using Human Feedback as the Evaluation Function in a </h2>
<h2>Genetic Algorithm to Generate an Image</h2>
<h4>By Alexandra Wellington and Shanchuan Xia</h4>
</div>
<p>Show all images</p>

<br/>
	<?php
	include 'database.php';
	$img_src="/src/genimages/";
	$link = dataConnect();
	if(getStatus($link)==11)
	{
		echo "Generating Next Generation, Please wait~";
	}
	else
	{
		$images=getImage2ShowALL($link);
		echo "<div id = 'img_container'>";
		for ($i=0; $i < count($images) ; $i++) {
			echo "<div class='showimg' id='img_",$images[$i]->id,"'>";
			echo "<img class='img_img' src='",$images[$i]->imgURL,"'>";
			
			echo "<div class='show_vote' id='img_",$images[$i]->id,"_vote","'>";
				echo "vote:".$images[$i]->vote;
				echo "<br/>";
				echo "url:",$images[$i]->imgURL;
			echo "</div>";
			echo "</div>";
		}
		echo "</div>";

	}
	$link -> close();//断开数据库连接


?>

</div>



<!-- Place your content here -->
<!-- <a href="http://sixrevisions.com/html5/html5-template/">Read the tutorial</a> -->

<!-- SCRIPTS -->
<!-- Example: <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script> -->
</body>
</html>


