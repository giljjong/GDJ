<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
	*{
      	padding: 0;
    	margin: 0;
     }
     a {
     	text-decoration : none;
     }
     ul {
     	list-style:none;
     	overflow : hidden;
     }
	header {
		text-align : left;
	}
	.area {
		position: relative;
	}
	.gnbarea {
		background-color : #3f63bf;
	}
	.homearea {
		width : 1080px;
		margin : 0 auto;
		box-sizing: border-box;
	}
	.snb_wrap{
		position : realative;
		width : 1080px;
		height : 52px;
		line-height: 52px;
		box-sizing : border-box;
		z-index : 10;
	}
	.snb_wrap h1{
		float : left;
		font-size : 24px;
		padding-right : 1px;
	}
	ul > li {
		float : left;
	}
	.lnb_area {
		position : relative;
		height : 46px;
		line-height : 46px;
		border-bottom : 1px solid rgba(105, 105, 105, 0.233);
	}
	.lnb_inner {
		width : 1080px;
		margin : 0 auto;
	}
	.lnb_both {
		height : 100%;
		width : 100%;
		display : flex;
		-wbkit-box-align : center;
		align-items : center;
		-webkit-box-pack : justify;
		justify-content : space-between;
		box-sizing : border-box;
	}
	.snb_related_service .snb_bar {
		display : inline-block;
		vertical-align : top;
		height : 14px;
		width : 1px;
		margin : 20px 7px 0 8px;
		background-color : rgba(255, 255, 255, 0.15);
	}
	.snb_wrap li > a {
		color : rgba(247, 248, 244, 0.61);
	}
	.snb_wrap h1 > a {
		color : #fff;
	}
	.snb_related_service a {
		font-weight : 100;
	}
	.lnb_menu {
		height : 100%;
		position : relative;
		text-align : left;
		overflow : hidden;
	}
	.lnb_menu > ul {
		white-space : nowrap;
	}
	.lnb_menu li {
		display : inline-block;
        height: 100%;
		vertical-align : top;
	}
	.lnb_menu li a {
		display : block;
        height: 100%;
		color : #222;
	}
	.lnb_menu li .tx {
		display : inline-block;
		vertical-align : top;
		position : relative;
		margin : 0 7px;
		padding : 0 3px;
		font-size : 14px;
		font-weight : 600;
		white-space : nowrap;
	}
	.lnb_menu li:hover {
		height : 44px;
		border-bottom : 2px solid #3f63bf;
	}
	.lnb_menu li:hover > a {
		color : #3f63bf;
	}
</style>
</head>
<body>
<div class="area">
	<div class="gnbarea">
		<div class="homearea">
			<div class="snb_wrap">
					<h1>
						<a href="http://www.naver.com"><img></a>
						<a href="https://news.naver.com/main/main.naver?mode=LSD&mid=shm&sid1=100">뉴스</a>
					</h1>
				<ul class="snb_related_service">
					<li>
						<span class="snb_bar"></span>
						<a href="https://entertain.naver.com/home">연예</a>
					</li>
					<li><span class="snb_bar"></span>
						<a href="https://sports.news.naver.com/">스포츠</a></li>
					<li><span class="snb_bar"></span>
						<a href="https://weather.naver.com/">날씨</a></li>
					<li><span class="snb_bar"></span>
						<a href="https://contents.premium.naver.com/">프리미엄</a></li>
				</ul>
			</div>
		</div>
	</div>
	<div class="lnb_area">
		<div class="lnb_inner">
			<div class="lnb_both">
				<div class="lnb_menu">
					<ul>
						<li>
							<a href="javascript:void(0)">
								<span class="tx">
									언론사별
								</span>
							</a>
						</li>
						<li>
						<a href="javascript:void(0)">
								<span class="tx">
									정치
								</span>
							</a>
							</li>
						<li>
							<a href="javascript:void(0)">
								<span class="tx">
									경제
								</span>
							</a>
						</li>
						<li>
							<a href="javascript:void(0)">
								<span class="tx">
									사회
								</span>
							</a>
						</li>
						<li>
							<a href="javascript:void(0)">
								<span class="tx">
									생활/문화
								</span>
							</a>
						</li>
						<li>
							<a href="javascript:void(0)">
								<span class="tx">
									IT/과학
								</span>
							</a>
						</li>
						<li>
							<a href="javascript:void(0)">
								<span class="tx">
									세계
								</span>
							</a>
						</li>
						<li>
							<a href="javascript:void(0)">
								<span class="tx">
									랭킹
								</span>
							</a>
						</li>
						<li>
							<a href="javascript:void(0)">
								<span class="tx">
									신문보기
								</span>
							</a>
						</li>
						<li>
							<a href="javascript:void(0)">
								<span class="tx">
									오피니언
								</span>
							</a>
						</li>
						<li>
							<a href="javascript:void(0)">
								<span class="tx">
									TV
								</span>
							</a>
						</li>
						<li>
							<a href="javascript:void(0)">
								<span class="tx">
									팩트체크
								</span>
							</a>
						</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>