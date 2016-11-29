<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta charset="utf-8">
<title></title>
<meta name="description" content="">
<meta name="author" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="./src/style.css">
<script src="./src/jquery-3.1.1.min.js"></script>
<!--[if lt IE 9]>
<script src="//cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.2/html5shiv.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->

<script>
$(function(){
    $(".showimg").click(function(){
		var imgid = $(this).attr("id");
		 // var cont = $("input").serialize();
		 // alert(cont);
		$.ajax({
		    url:'vote.php',
		    type:'post',
		    dataType:'json',
		    data:{"imgid":imgid},
		    success:function(data){
		    	if (data.isvoted==1) {
		    		alert("You have voted!\nVote for image id: "+data.votefor);
		    		return;
		    	}
		    	var id  = "img_"+data.id+"_vote";
		    	// alert(id);
		    	var str = data.vote;
				$("#"+id).html(str);
		    }
		});
    });
});

</script>
</head>
<body>
<div id="container">
	<?php
	include 'database.php';
	$link = dataConnect();
	$images=getImage2Show($link);
	echo "Generation:",$images[0]->generation,"</br>";
	for ($i=0; $i < count($images) ; $i++) {
		echo "<div class='showimg' id='img_",$images[$i]->id,"'>";
		echo $images[$i]->id,", url:",$images[$i]->imgURL;
		echo "<div id='img_",$images[$i]->id,"_vote","'>";
			echo $images[$i]->vote;
		echo "</div>";
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


