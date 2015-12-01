<%--
 * index.jsp
 *
 * Copyright (C) 2014 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

			<!-- banner -->
<div class="banner">
		<div class="container">
			  <script src="js/responsiveslides.min.js"></script>
  <script>
    $(function () {
      $("#slider").responsiveSlides({
      	auto: true,
      	nav: true,
      	speed: 500,
        namespace: "callbacks",
        pager: true,
      });
    });
  </script>
			<div  id="top" class="callbacks_container">
			<ul class="rslides" id="slider">
			    <li>
					
						<div class="banner-text">
							<h3>Lorem Ipsum is   </h3>
						<p>Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC.</p>
						
						</div>
				
				</li>
				<li>
					
						<div class="banner-text">
							<h3>There are many  </h3>
						<p>Popular belief Contrary to , Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC.</p>
												

						</div>
					
				</li>
				<li>
						<div class="banner-text">
							<h3>Sed ut perspiciatis</h3>
						<p>Lorem Ipsum is not simply random text. Contrary to popular belief, It has roots in a piece of classical Latin literature from 45 BC.</p>
								

						</div>
					
				</li>
			</ul>
		</div>

	</div>
	</div>
<!-- banner -->
<!-- sha -->
	<div class="sha">
	</div>
<!-- sha -->
<!-- welcome -->
	<div class="welcome">
		<div class="container">
			<h2>Welcome</h2>
			<p>"Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur?</p>
		</div>
	</div>
<!-- welcome -->
<!-- welcome-btm -->
	<div class="welcome-btm">
		<div class="container">
			<div class="col-md-4 welcome-left">
				<h3>5</h3>
				<ul class="wel">
					<li><a href="#"></a></li>
					<li><a href="#"></a></li>
					<li><a href="#"></a></li>
					<li><a href="#"></a></li>
					<li><a href="#"></a></li>
						<div class="clearfix"></div>
				</ul>
				<h5>DESIGN</h5>
			</div>
			<div class="col-md-4 welcome-left">
				<h3>4</h3>
				<ul class="wel">
					<li><a href="#"></a></li>
					<li><a href="#"></a></li>
					<li><a href="#"></a></li>
					<li><a href="#"></a></li>
					<li><a href="#" class="wht"></a></li>
						<div class="clearfix"></div>
				</ul>
				<h5>MATERIAL</h5>
			</div>
			<div class="col-md-4 welcome-left">
				<h3>4</h3>
				<ul class="wel">
					<li><a href="#"></a></li>
					<li><a href="#"></a></li>
					<li><a href="#"></a></li>
					<li><a href="#"></a></li>
					<li><a href="#" class="wht"></a></li>
						<div class="clearfix"></div>
				</ul>
				<h5>USABILITY</h5>
			</div>
				<div class="clearfix"></div>
			<div class="wel-top">
				<div class="col-md-6 welcome-left1">
					<h3>4</h3>
				<ul class="wel">
					<li><a href="#"></a></li>
					<li><a href="#"></a></li>
					<li><a href="#"></a></li>
					<li><a href="#"></a></li>
					<li><a href="#" class="wht"></a></li>
						<div class="clearfix"></div>
				</ul>
				<h5>WORTHWHILE</h5>
				</div>
				<div class="col-md-6 welcome-left1">
					<h3>4.<span>25</span></h3>
				<ul class="wel">
					<li><a href="#"></a></li>
					<li><a href="#"></a></li>
					<li><a href="#"></a></li>
					<li><a href="#"></a></li>
					<li><a href="#" class="wht"></a></li>
						<div class="clearfix"></div>
				</ul>
				<h5>AVERAGE</h5>
				</div>
					<div class="clearfix"></div>
			</div>
		</div>
	</div>
<!-- welcome-btm -->
<!-- why -->
	<div class="why">
		<div class="container">
			<span class="glyphicon glyphicon-globe" aria-hidden="true"></span><h4>velit esse quam nihil molestiae consequatur</h4>
			<p>"Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur?</p>
			<ul class="ch-grid">
						<li>
							<div class="ch-item ch-img-1">
								<div class="ch-info">
									<h3>Industrier</h3>
									<p>by Daniel Nyari <a href="#">View on Dribbble</a></p>
								</div>
							</div>
						</li>
						<li>
							<div class="ch-item ch-img-2">
								<div class="ch-info">
									<h3>Industrier</h3>
									<p>by Daniel Nyari <a href="#">View on Dribbble</a></p>
								</div>
							</div>
						</li>
						<li>
							<div class="ch-item ch-img-3">
								<div class="ch-info">
									<h3>Industrier</h3>
									<p>by Daniel Nyari <a href="#">View on Dribbble</a></p>
								</div>
							</div>
						</li>
					</ul>
		</div>
	</div>
<!-- why -->
<!--  majority -->
	<div class="majority">
		<div class="container">
			<h3>when an unknown printer took a galley of type and scrambled it.</h3>
			<div class="col-md-3  majority-left">
				<img src="images/54.jpg" alt=" " class="img-responsive" />
					<h4>Readable content</h4>
					<li><a href="#">Lorem Ipsum is that</a></li>
					<li><a href="#">Lorem Ipsum is that</a></li>
					<li><a href="#">Lorem Ipsum is that</a></li>
			</div>
			<div class="col-md-3  majority-left">
				<img src="images/55.jpg" alt=" " class="img-responsive" />
				<h4>Omnis iste</h4>
					<li><a href="#">Lorem Ipsum is that</a></li>
					<li><a href="#">Lorem Ipsum is that</a></li>
					<li><a href="#">Lorem Ipsum is that</a></li>
					
			</div>
			<div class="col-md-3  majority-left">
				<img src="images/56.jpg" alt=" " class="img-responsive" />
				<h4>Natus error</h4>
					<li><a href="#">Lorem Ipsum is that</a></li>
					<li><a href="#">Lorem Ipsum is that</a></li>
					<li><a href="#">Lorem Ipsum is that</a></li>
					
			</div>
			<div class="col-md-3  majority-left">
				<img src="images/57.jpg" alt=" " class="img-responsive" />
				<h4>Iste natus</h4>
					<li><a href="#">Lorem Ipsum is that</a></li>
					<li><a href="#">Lorem Ipsum is that</a></li>
					<li><a href="#">Lorem Ipsum is that</a></li>
					
			</div>
			<div class="clearfix"></div>
		</div>
	</div>
<!--  majority -->