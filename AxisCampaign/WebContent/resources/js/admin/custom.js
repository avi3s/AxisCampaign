//$(window).load(function(e) {
//console.log($(document).height(), $('.wholeWrap').height());
//})

$(document).ready(function(e) {
	$("#nav ul li").click(function(){
		$(this).siblings().find('a').removeClass("active");
        $(this).find('a').addClass("active");
   })
//Tabs
$('.tabContent').hide();
$('#tab1').show();
$('.tabs li a').click(function () {	
	var tabDivId = this.hash;
	//console.log(tabDivId);
	$('.tabs li').removeClass('activeState');
	$('.tabContent').hide();
	
	$(this).parents('li').addClass('activeState');
	$(tabDivId).fadeIn();
	return false;
});


//Left Menu
	var winDth = $(window).width();
	var hdHt = $('.header').height();
	var lWidth = $('.leftArea').width();	
	var mcode = $('<span class="menuOpen" style="display:none">Menu</span><span class="menuClose">Menu</span>');
	$('.leftArea').css('top','hdHt');
	
	/*$('.wholeWrap').addClass('openArea');
	$('.bodyWrap').addClass('rezArea');
	$('.bodyWrap, .footer').animate({
			width: winDth-lWidth,
		},100);
	$('.leftArea').animate({
			left: 0,
		},100);		
	$('.menuSh').append(mcode);*/
	$('.menuSh').append(mcode);
	
	if(winDth <= 767) {
		$('.wholeWrap').removeClass('openArea');
		$('.bodyWrap').removeClass('rezArea');
		$('.leftArea').css('left','-230px');
		$('.bodyWrap, .footer').animate({
				width: '100%',
			},150);
		$('.menuOpen').show();
	}
	else {	
		$('.wholeWrap').addClass('openArea');
		$('.bodyWrap').addClass('rezArea');
		$('.bodyWrap, .footer').animate({
				width: winDth-lWidth,
			},100);
		$('.leftArea').animate({
				left: 0,
			},100);		
		
	};

//Menu Click Event
$('.menuSh .menuOpen').click(function () {
	var winDth = $(window).width();
	var hdHt = $('.header').height();
	var lWidth = $('.leftArea').width();
	$('.leftArea').css('top','hdHt');

	$('.wholeWrap').addClass('openArea');
	$('.bodyWrap').addClass('rezArea');
	
	$('.bodyWrap, .footer').animate({
			width: winDth-lWidth,
		},150);
	$('.leftArea').animate({
			left: 0,
		},150);
	$(this).hide();
	$('.menuClose').show();
});

//Menu Close
$('.menuSh .menuClose').click(function () {
	$('.wholeWrap').removeClass('openArea');
	$('.bodyWrap').removeClass('rezArea');
	$('.leftArea').animate({
			left: -lWidth,
		},150);
	$('.bodyWrap, .footer').animate({
			width: '100%',
		},150);
	$(this).hide();
	$('.menuOpen').show();
});


$(function() {
    var pgurl = window.location.href.substr(window.location.href
.lastIndexOf("/")+1);
    $("#nav ul li a").each(function(){
         if($(this).attr("href") == pgurl || $(this).attr("href") == '' )
         $(this).addClass("active");
    })
});

/*accordion menu*/
$('#nav ul li ul').hide();
$('#nav ul li').click(function(){
	$(this).find('ul').slideDown();
	$(this).siblings('li').find('ul').slideUp();
	});

 var menuWd = $('.menuSh').width();
 $('.topBar .titleArea').css('padding-left',menuWd+5);
 
	// equal height
	equalHeight($("span.file-listing-icon"));
});
/*function setActive() {
	  aObj = document.getElementById('nav').getElementsByTagName('a');
	  
	  for(i=0;i<aObj.length;i++) { 
	    if(document.location.href.indexOf(aObj[i].href)>=0) {
	      aObj[i].className='active';
	     // alert(i);
	    }
	  }
	}
window.onload = setActive;*/



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