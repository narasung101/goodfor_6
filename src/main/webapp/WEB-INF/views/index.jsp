<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<meta name="description" content="">
	<meta name="author" content="">	
	<title>GoodFor</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
</head>

<body>
	<div id="intro">
		<link rel="stylesheet" href="http://www.kaiwa-projects.com/assets/css/qbkl-grid.css">
	  <link rel="stylesheet" href="http://www.kaiwa-projects.com/assets/css/style-ko.css">
 		
 		  <div class="top-decorations" style="width: 100%; position: absolute; "></div>  
	
		<section id="intro" class="fx-backstretch">
			<div class="info" style="position: relative; z-index: 0; background: none;">
				<div class="container" style="top: 18%;">
					<div class="row">

						<div class="col-full"><h1 style="margin-top: 100px;">나성</h1></div>

					</div>
					<div class="row">
						<div class="col-1-4 centered line"></div>
					</div>
					<div class="row">
						<div class="col-full"><h4>웹개발자</h4></div>
					</div>
				</div>
				<div class="backstretch" style="left: 0px; top: 0px; overflow: hidden; margin: 0px; padding: 0px; height: 91%; width: 100%; z-index: -999998; position: absolute;">
<!-- 					<img src="http://www.kaiwa-projects.com/assets/img/backstretch.jpg" style="position: absolute; margin: 0px; padding: 0px; border: none; width: 100%; height: 825px; max-height: none; max-width: none; z-index: -999999; left: -129.833px; top: 0px;"> -->
					<img src="<%=application.getContextPath()%>/resources/img/main.png" style="opacity: 0.5; margin: 0px; padding: 0px; border: none; width: 100%; height: 99%; max-height: none; max-width: none; z-index: -999999; left: -129.833px; top: 0px;">
				</div>
			</div>
			<div id="nav-sticky-wrapper" class="sticky-wrapper" style="height: 60px;">
				<nav id="nav" style="z-index: 100; font-size: 30px">
					<ul class="clearfix" style="width:610px; font-weight: bold;">
						<li class="current" style="margin-right: 60px;"><a href="#aboutme">인사 소개</a></li>
						<li class="" style="margin-right: 60px;"><a href="#skills"><span></span>기술</a></li>
						<li class="" style="margin-right: 60px;"><a href="#portfolio">프로젝트</a></li>
						<li class=""><a href="#contact">연락</a></li>
					</ul>
				</nav>
			</div>
		</section>
		<section id="aboutme" class="section">
			<div class="container" style="width : 70%">
				<div class="row">
					<div class="col-full">
						<h2 class="section-title">인사 소개</h2>
						<div class="centered line"></div>
					</div>
				</div>
				<div class="row section-content">
					<div class="col-1-3" style="text-align: center;">
						<img src="<%=application.getContextPath()%>/resources/img/ns2.jpg" style="height: 200px;">
					</div>

					<div class="col-1-2" style="color:black;">
						<h4>안녕하세요 굿포 프로젝트 개발에 참여한 나성입니다!</h4>
						<p>굿포 프로젝트는 모의 주식, 증권 정보를 주제로 사이트를 만들었습니다. <br> 프로젝트, 포트폴리오는  <a href="#portfolio">여기를 누르시거나</a> 페이지 아래쪽에서 구경해 보세요! 
						<br> 더 궁금하신 사항은 <a href="#contact">이메일</a>로 연락 주세요.</p>
						<ul class="social-links clearfix">
							<li><a href="" target="_blank" title="Github"><i class="fa fa-github"></i></a></li>
						</ul>
					</div>
					<!-- 
					 <div class="col-1-3" >
						<h4>개인 관심사</h4>
						<p><a href="#skills"></a></p>
						<ul>
							<li>자전거 타는 것을 좋아합니다.</li>
							<li>여행을 다니는 것을 좋아합니다</li>
							<li>영화보는 것을 좋아합니다.</li>
						</ul>
					</div>
					 -->
				</div>
			</div>
		</section>
		<section id="skills" class="section-alt">
			<div class="container" style="width:1000px">
				<div class="row" style="margin:0 auto">
					<div class="col-full">
						<h2 class="section-title">전문 기술</h2>
						<div class="centered line"></div>
					</div>
				</div>
				<div class="row section-content" style="margin:0 auto">
					<div class="skill-container">
						<div class="col-full">
							<div class="col-full text-center" style="color: #333;">Spring Framework5에 Maven 빌드 시스템을 적용, MVC2패턴으로<br>
								개발환경을 구축하였으며, 프래밍 언어와 기술은 JAVA, HTML5, CSS, JQuery, Ajax, Bootstrap, Mybatis를 적용하였고, <br>
								데이터베이스는 MySQL, Tool은 Eclipse, STS, MySQL Workbench 를 사용했습니다.
							</div>
						</div>
						<div class="col-1-5 skill">
							<h4></h4>
						</div>
						<div class="col-1-5 skill">
							<h4>Java</h4>
						</div>
						<div class="col-1-5 skill">
							<h4>Jquery</h4>
						</div>
						<div class="col-1-5 skill">
							<h4>JavaScript</h4>
						</div>
					</div>
					<div class="col-full skill-container">
						<h3>자주 사용하는 소프트웨어</h3>
					</div>
					<div class="col-2-3 col-wrap centered skill-container" style="with:100%;justify-content: center;">
						<div class="col-1-2" style="padding-left:100px">
							<h4>프레임워크하고 엔진</h4>
							<ul style="color: #333;">
								<li>Spring5</li>
								<li>전자정부</li>
								<li>MyBatis</li>
							</ul>							
						</div>
						<div class="col-1-2" style="padding-left:100px">
							<h4>소프트웨어</h4>
							<ul style="color: #333;"><li>Git</li><li>ORACLE</li><li>MySQL</li><li>MariaDB</li></ul>	
						</div>
					</div>
				</div>
			</div>
		</section>
		<section id="portfolio" class="section">
			<div class="container">
				<div class="row">
					<div class="col-full">
						<h2 class="section-title">프로젝트 & PPT & Git Hub</h2>
						<div class="centered line"></div>
					</div>
				</div>
				<div class="row section-content">
					<div class="row">
						<div class="col-full">
							<ul class="filters">
								<li class="filter active" data-filter="all">전체</li>
							</ul>
						</div>
					</div>
					<div class="row projects gallery" style="margin-left: 200px;">
						<div class="col-1-6 project port-application mix_all" style="display: inline-block;  opacity: 1; padding-top: 30px;">
							<h2 style="color:black">프로젝트</h2>
							<a id="project" href="#" >
								<img src="<%=application.getContextPath()%>/resources/img/project_logo.png" alt="project">
							</a>
						</div>										
						
							<div class="col-1-6 project port-game mix_all" style="display: inline-block;  opacity: 1; padding-top: 30px;">
							<h2 style="color:black">유튜브</h2>
							<a href="https://www.youtube.com/watch?v=yyfyVgnZFko&feature=youtu.be" title="youtube" target="_blank">
								<img src="<%=application.getContextPath()%>/resources/img/youtube_pt.png" alt="youtube">
							</a>
						</div>	
						
						<div class="col-1-6 project port-game mix_all" style="display: inline-block;  opacity: 1; padding-top: 30px;">
							<h2 style="color:black">PPT</h2>
							<a href="https://docs.google.com/presentation/d/1CFQHkpaXR5q-S6Nbi7pp-AlPZVuCBu45qdfbOBdjLaE/edit?usp=sharing" title="ppt" target="_blank">
								<img src="<%=application.getContextPath()%>/resources/img/ppt.jpeg" alt="ppt">
							</a>
						</div>	
						
						<div class="col-1-6 project port-game mix_all" style="display: inline-block;  opacity: 1; padding-top: 30px;">
							<h2 style="color:black">깃허브</h2>
							<a href="https://github.com/narasung101" title="github" target="_blank">
								<img src="<%=application.getContextPath()%>/resources/img/github-mark.png" alt="github">
							</a>
						</div>						
											
						
											
					</div>
				</div>
			</div>
		</section>
		<section id="contact" class="section">
			<div class="container">
				<div class="row">
					<div class="col-full">
						<h2 class="section-title">연락처</h2>
						<div class="centered line"></div>
					</div>
				</div>
				<div class="row section-content">
					<div class="col-2-3 col-wrap centered text-center">
						<div class="row">
							<div class="col-full" style="margin-bottom: 25px;">

								항상 초심같은 마음가짐으로 임할것이고 최신기술 트렌드에도 뒤져지지
								않고 하루하루 성장하겠습니다  <br>narasung101@naver.com으로 이메일 주세요. 곧 답장 드리겠습니다.<br>언제든지 연락주세요	
							</div>
						</div>
						<div id="form-contact-container">
							<div class="col-full">
								<label for="">이름
									<h2>나성</h2>
								</label>
							</div>
							<div class="col-full">
								<label for="">Email 주소
								<h2 style="text-transform: lowercase">narasung101@naver.com</h2>				
								</label>
							</div>
							<div class="col-full">
								<label for="">휴대폰 번호
									<h2>010-2802-7860</h2>
								</label>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>
	</div>
	<div id="root">	
	</div>
	<div id="popup-root"></div>
	<div id="popup-exchange"></div>
	<script>
	
		$('#project')
		.click(e=>{
			e.preventDefault()
			location.href = '<%=application.getContextPath()%>/home'
		})
	</script>
</body>
</html>