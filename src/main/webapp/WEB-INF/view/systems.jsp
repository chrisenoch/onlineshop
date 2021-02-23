<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>


	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB"
    crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/style.css">
	<title>SYSTEMS Home Page</title>
</head>

<body>
 <nav class="navbar navbar-expand-sm bg-dark navbar-dark fixed-top" id="main-nav">
    <div class="container">
      <a href="index.html" class="navbar-brand">Company name</a>
      <button class="navbar-toggler" data-toggle="collapse" data-target="#navbarCollapse">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarCollapse">
        <ul class="navbar-nav ml-auto">
          <li class="nav-item">
            <a href="#home" class="nav-link">Home</a>
          </li>
          <li class="nav-item">
            <a href="#explore-head-section" class="nav-link">Explore</a>
          </li>
          <li class="nav-item">
            <a href="#create-head-section" class="nav-link">Create</a>
          </li>
          <li class="nav-item">
            <a href="#share-head-section" class="nav-link">Share</a>
          </li>
        </ul>
      </div>
    </div>
  </nav>



<h2>SYSTEMS Home Page</h2>

<hr>

<p>
	We have our annual holiday Caribbean cruise coming up. Register now!
	<br>
	Keep this trip a secret, don't tell the regular employees LOL :-)
</p>

<hr>

<a href="${pageContext.request.contextPath}/">Back to Profile Page</a>

</body>

</html>