$(document).ready(function($) {
	$("#slider2").responsiveSlides({
		auto: true,
		pager: true,
		nav: false,
		speed: 300,
	});
	$("#owl-example").owlCarousel();
	
	openMobileNav();
	
		
	$('.notification-block > ul').toggleClass('ul js');
    $('.notification-block .js ul').hide();
    $('.notification-block li').click(function (e) {
        $('.notification-block li').not(this).find('ul').slideUp(200);
        $(this).find('ul').slideToggle(200);
		$('.clicker').toggleClass('active');
        e.stopPropagation();
    });
	$(document).click(function() {
		if ($('.notification-block .js ul').is(':visible')) {
		  $('.notification-block .js ul', this).slideUp();
		  $('.notification-block').removeClass('active');
		}
	});
	

	$('.nav-list-item').hover(
	  function () {
		$('.sub-catagories', this).show();
	  },
	  function () {
		$('.sub-catagories', this).hide();
	  }
	);
	
	var table = $('.example').DataTable( {
		responsive: true,
		ordering: false,
	});
	
	var $tabs = $('#horizontalTab');
	$tabs.responsiveTabs({
		rotate: false,
		collapsible:false,
		/*startCollapsed: false,
		startCollapsed: 'accordion',
		
		setHash: true,*/
		active: 0,
		activate: function(event, tab){
			//table.columns.adjust().draw();
			$($.fn.dataTable.tables(true)).DataTable().responsive.recalc();
		},
	});
	
	$($.fn.dataTable.tables(true)).DataTable().responsive.recalc();
	
	// equal height
	equalHeight($(".dashboard li a"));
});

$(window).resize(function(){
	if($(window).width() >= 901 ){
		 $.sidr('close', 'sidr-main');
	} else {
		openMobileNav();
	}
});


function openMobileNav(){
	$('#responsive-menu-button').sidr({
		name: 'sidr-main',
		source: '#navigation'
	});
	
	/*$(window).touchwipe({
		wipeLeft: function() {
			// Open
			$.sidr('open', 'sidr-main');
		},
		wipeRight: function() {
			// Close
			$.sidr('close', 'sidr-main');
		},
		preventDefaultEvents: false
	});*/
	
	$('.sidr-class-container > ul').toggleClass('sidr-class-sub-catagories-item js');
    $('.sidr-class-container .js ul').hide();
    $('.sidr-class-container li').click(function (e) {
        $('.sidr-class-container li').not(this).find('ul').slideUp(200);
        $(this).find('ul').slideToggle(200);
        e.stopPropagation();
    });
}


function equalHeight(group) {
	   tallest = 0;
	   group.each(function(i) {
	      thisHeight = $(this).height();
	      if(thisHeight > tallest) {
	         tallest = thisHeight;	
	      }
		  
	   });
	   group.height(tallest);
	}