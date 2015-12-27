
<%
	String path1 = request.getContextPath();
	String basePath1 = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path1 + "/";
	//System.out.println("BasePath : " + basePath);
%>
</div>
</div>
</div>
<!--/Body Area-->
<div class="clear"></div>
<!--Footer-->
<footer class="footer">
	<div class="fixedArea">
		<p class="copyright" align="center">Copyright &copy; 2015 Axis
			Bank, India.</p>
		<%-- <p>
			<span><a href="#">Help</a> | </span>Developed by INDUSNET TECHNOLOGY
			<a href="#" class="hideArrow"><img
				src="<%=basePath1%>resources/images/admin/footer-arrow.png"
				width="12" height="8" alt=""></a>
		</p> --%>

		<div class="clear"></div>
	</div>
</footer>
<!--/Footer-->
<div class="clear"></div>
</div>

</body>
</html>