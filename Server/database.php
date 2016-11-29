<?php 
	class GAimage{
		public $id;
		public $url;
		public $vote;
		public $generation;
		public $dna_num;
		public $isshow;
		public $dnas;

	}

	function dataConnect()
	{
		$localhost = 'localhost';//主机名 
		$user = 'root';//用户名 
		$pwd = 'root';//密码 
		$db = 'csc791_gaimage';// 
		$link = new mysqli($localhost,$user,$pwd,$db);//连接数据库 
		if(mysqli_connect_errno())//如果数据库连接失败，输出错误信息并退出 
		{ 
		echo 'Error: Coulid not connect to database. Please try again later.'; 
		exit; 
		}
		return $link;
	}
	
	function updateVote($link,$imgid)
	{
		$query = "SELECT * FROM genimage WHERE id={$imgid}";//查询语句
		// echo $query;
		$result = $link -> query($query);//查询并返回结果 
		$num_results = $result -> num_rows; //结果行数
		$vote =0;
		for($i = 0;$i < $num_results;$i++)//循环输出每组元素 
		{
			$row = $result -> fetch_assoc();//提取元素，一次一行，fetch_assoc()提取出的元素，有属性以及值 
			$vote =stripslashes($row['vote']);
		}

		$vote = $vote+1;

		$update_sql = "UPDATE genimage SET vote={$vote} WHERE id={$imgid}";
		$result = $link -> query($update_sql);//查询并返回结果


		$result = $link -> query($query);//查询并返回结果 
		$num_results = $result -> num_rows; //结果行数
		$vote =0;
		for($i = 0;$i < $num_results;$i++)//循环输出每组元素 
		{
			$row = $result -> fetch_assoc();//提取元素，一次一行，fetch_assoc()提取出的元素，有属性以及值 
			$vote =stripslashes($row['vote']);
		}


		$result -> free();//释放内存
		return $vote;
	}

	function getImage2Show($link)
	{
		$query = "SELECT * FROM genimage WHERE isshow=1";//查询语句
		$result = $link -> query($query);//查询并返回结果 
		$num_results = $result -> num_rows; //结果行数

		echo "<p>Number of row found: ". $num_results ."</p>";//输出行数 
		$images=array();
		for($i = 0;$i < $num_results;$i++)//循环输出每组元素 
		{
			$row = $result -> fetch_assoc();//提取元素，一次一行，fetch_assoc()提取出的元素，有属性以及值 
			$tpimage = new GAimage();
			$id =stripslashes($row['id']);
			$vote = stripslashes($row['vote']);
			$generation = stripslashes($row['generation']);
			$imgURL = stripslashes($row['imgURL']);
			$tpimage -> id = $id;
			$tpimage -> vote = $vote;
			$tpimage -> generation = $generation;
			$tpimage -> imgURL = $imgURL;
			array_push($images, $tpimage);
		}
		$result -> free();//释放内存 
		return $images;
	}

	function getGenerationOrVote($link,$imgid,$type)
	{
		$query = "SELECT * FROM genimage WHERE isshow=1";//查询语句
		$result = $link -> query($query);//查询并返回结果 
		$num_results = $result -> num_rows; //结果行数
		$generation=0;
		$vote=0;
		for($i = 0;$i < $num_results;$i++)//循环输出每组元素 
		{
			$row = $result -> fetch_assoc();//提取元素，一次一行，fetch_assoc()提取出的元素，有属性以及值 
			$generation = stripslashes($row['generation']);
			$vote = stripslashes($row['vote']);
		}
		$result -> free();//释放内存
		if ($type==0) {
			return $generation;
		}
		else
			return $vote;
	}

	function evolve()
	{
		$cmd = "evolve.bat";
		exec($cmd);
	}

 ?>