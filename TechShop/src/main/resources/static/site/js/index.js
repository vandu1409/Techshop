!function(a){"use strict";"function"==typeof define&&define.amd?define(["jquery"],a):a(jQuery)}(function(a){"use strict";function b(a){if(a instanceof Date)return a;if(String(a).match(g))return String(a).match(/^[0-9]*$/)&&(a=Number(a)),String(a).match(/\-/)&&(a=String(a).replace(/\-/g,"/")),new Date(a);throw new Error("Couldn't cast `"+a+"` to a date object.")}function c(a){return function(b){var c=b.match(/%(-|!)?[A-Z]{1}(:[^;]+;)?/gi);if(c)for(var e=0,f=c.length;f>e;++e){var g=c[e].match(/%(-|!)?([a-zA-Z]{1})(:[^;]+;)?/),i=new RegExp(g[0]),j=g[1]||"",k=g[3]||"",l=null;g=g[2],h.hasOwnProperty(g)&&(l=h[g],l=Number(a[l])),null!==l&&("!"===j&&(l=d(k,l)),""===j&&10>l&&(l="0"+l.toString()),b=b.replace(i,l.toString()))}return b=b.replace(/%%/,"%")}}function d(a,b){var c="s",d="";return a&&(a=a.replace(/(:|;|\s)/gi,"").split(/\,/),1===a.length?c=a[0]:(d=a[0],c=a[1])),1===Math.abs(b)?d:c}var e=100,f=[],g=[];g.push(/^[0-9]*$/.source),g.push(/([0-9]{1,2}\/){2}[0-9]{4}( [0-9]{1,2}(:[0-9]{2}){2})?/.source),g.push(/[0-9]{4}([\/\-][0-9]{1,2}){2}( [0-9]{1,2}(:[0-9]{2}){2})?/.source),g=new RegExp(g.join("|"));var h={Y:"years",m:"months",w:"weeks",d:"days",D:"totalDays",H:"hours",M:"minutes",S:"seconds"},i=function(b,c,d){this.el=b,this.$el=a(b),this.interval=null,this.offset={},this.instanceNumber=f.length,f.push(this),this.$el.data("countdown-instance",this.instanceNumber),d&&(this.$el.on("update.countdown",d),this.$el.on("stoped.countdown",d),this.$el.on("finish.countdown",d)),this.setFinalDate(c),this.start()};a.extend(i.prototype,{start:function(){null!==this.interval&&clearInterval(this.interval);var a=this;this.update(),this.interval=setInterval(function(){a.update.call(a)},e)},stop:function(){clearInterval(this.interval),this.interval=null,this.dispatchEvent("stoped")},pause:function(){this.stop.call(this)},resume:function(){this.start.call(this)},remove:function(){this.stop(),f[this.instanceNumber]=null,delete this.$el.data().countdownInstance},setFinalDate:function(a){this.finalDate=b(a)},update:function(){return 0===this.$el.closest("html").length?void this.remove():(this.totalSecsLeft=this.finalDate.getTime()-(new Date).getTime(),this.totalSecsLeft=Math.ceil(this.totalSecsLeft/1e3),this.totalSecsLeft=this.totalSecsLeft<0?0:this.totalSecsLeft,this.offset={seconds:this.totalSecsLeft%60,minutes:Math.floor(this.totalSecsLeft/60)%60,hours:Math.floor(this.totalSecsLeft/60/60)%24,days:Math.floor(this.totalSecsLeft/60/60/24)%7,totalDays:Math.floor(this.totalSecsLeft/60/60/24),weeks:Math.floor(this.totalSecsLeft/60/60/24/7),months:Math.floor(this.totalSecsLeft/60/60/24/30),years:Math.floor(this.totalSecsLeft/60/60/24/365)},void(0===this.totalSecsLeft?(this.stop(),this.dispatchEvent("finish")):this.dispatchEvent("update")))},dispatchEvent:function(b){var d=a.Event(b+".countdown");d.finalDate=this.finalDate,d.offset=a.extend({},this.offset),d.strftime=c(this.offset),this.$el.trigger(d)}}),a.fn.countdown=function(){var b=Array.prototype.slice.call(arguments,0);return this.each(function(){var c=a(this).data("countdown-instance");if(void 0!==c){var d=f[c],e=b[0];i.prototype.hasOwnProperty(e)?d[e].apply(d,b.slice(1)):null===String(e).match(/^[$A-Z_][0-9A-Z_$]*$/i)?(d.setFinalDate.call(d,e),d.start()):a.error("Method %s does not exist on jQuery.countdown".replace(/\%s/gi,e))}else new i(this,b[0],b[1])})}});
var is_load = 0
$(document).ready(function($) {
    !is_load && setTimeout(load_after_scroll, 10000)
    $(window).one('mousemove touchstart scroll', load_after_scroll)	
});
function load_after_scroll() {
	if (is_load) return
	is_load = 1
	var slidehome = new Swiper('.main-slider', {
		pagination: {
			el: '.section_slider .swiper-pagination',
			clickable: true,
		},
		slidesPerView: 1,
		centeredSlides: false,
		loop: false,
		grabCursor: true,
		autoPlay:true,
		spaceBetween: 0,
		roundLengths: true,
		slideToClickedSlide: false
	});
	var flash = new Swiper('.section_flash .swiper-container', {
		slidesPerView: 3,
		autoplay: true,
		grabCursor: true,
		spaceBetween: 20,
		roundLengths: true,
		slideToClickedSlide: false,
		breakpoints: {
			300: {
				slidesPerView: 1,
				spaceBetween: 0
			},
			500: {
				slidesPerView: 1,
				spaceBetween: 0
			},
			640: {
				slidesPerView: 2
			},
			768: {
				slidesPerView: 2
			},
			992: {
				slidesPerView: 2
			},
			1400: {
				slidesPerView: 3
			}
		}
	});
	$('[data-countdown]').each(function() {
		var $this = $(this), finalDate = $(this).data('countdown');
		$this.countdown(finalDate, function(event) {
			$this.html(event.strftime('<div class="date-time time-day">%D<small>Ngày</small></div><div class="date-time time-hour">%H<small>Giờ</small></div><div class="date-time time-min">%M<small>Phút</small></div><div class="date-time time-sec">%S<small>Giây</small></div>'));
		});
	});
	var service = new Swiper('.section_service', {
		slidesPerView: 5,
		autoplay: true,
		grabCursor: true,
		spaceBetween: 20,
		roundLengths: true,
		slideToClickedSlide: false,
		autoplay: true,
		breakpoints: {
			300: {
				slidesPerView: 2,
				spaceBetween: 10,
			},
			500: {
				slidesPerView: 2
			},
			640: {
				slidesPerView: 3
			},
			768: {
				slidesPerView: 3
			},
			992: {
				slidesPerView: 4
			},
			1400: {
				slidesPerView: 5
			}
		}
	});
	var swiperCarousel = new Swiper('.swiper-carousel', {
		slidesPerView: 6,
		spaceBetween: 20,
		autoplay: {
			delay: 4000
		},
		slidesPerColumn: 3,
		slidesPerColumnFill: 'row',
		breakpoints: {
			300: {
				slidesPerView: 2,
				slidesPerColumn: 2
			},
			767: {
				slidesPerView: 2,
				slidesPerColumn: 2
			},
			768: {
				slidesPerView: 3
			},
			1024: {
				slidesPerView: 4
			},
			1200: {
				slidesPerView: 6
			}
		}
	});
	/*Ajax tab 1*/
	$(".not-dqtab").each( function(e){
		/*khai báo khởi tạo ban đầu cho 2 kiểu tab*/
		var $this1 = $(this);
		var $this2 = $(this);
		var datasection = $this1.closest('.not-dqtab').attr('data-section');
		$this1.find('.tabs-title li:first-child').addClass('current');
		$this1.find('.tab-content').first().addClass('current');
		var datasection2 = $this2.closest('.not-dqtab').attr('data-section-2');
		$this2.find('.tabs-title li:first-child').addClass('current');
		$this2.find('.tab-content').first().addClass('current');

		/*khai báo cho chức năng dành cho mobile tab*/
		var _this = $(this).find('.wrap_tab_index .title_module_main');
		var droptab = $(this).find('.link_tab_check_click');

		/*type 1*/ //kiểu 1 này thì load có owl carousel
		$this1.find('.tabtitle.ajax li').click(function(){
			var $this2 = $(this),
				tab_id = $this2.attr('data-tab'),
				url = $this2.attr('data-url');
			var etabs = $this2.closest('.e-tabs');
			etabs.find('.tab-viewall').attr('href',url);
			etabs.find('.tabs-title li').removeClass('current');
			etabs.find('.tab-content').removeClass('current');
			$this2.addClass('current');
			etabs.find("."+tab_id).addClass('current');
			//Nếu đã load rồi thì không load nữa
			if(!$this2.hasClass('has-content')){
				$this2.addClass('has-content');		
				getContentTab(url,"."+ datasection+" ."+tab_id);
			}
		});
	});
	// Get content cho tab
	function getContentTab(url,selector){
		url = url+"?view=ajaxload";
		var loading = '<div class="text-center"><img src="//bizweb.dktcdn.net/100/480/314/themes/899828/assets/rolling.svg?1684863151095" alt="loading"/></div>';
		$.ajax({
			type: 'GET',
			url: url,
			beforeSend: function() {
				$(selector).html(loading);
			},
			success: function(data) {
				var content = $(data);
				setTimeout(function(){
					$(selector).html(content.html());
					ajaxSwiper(selector);
					let arrImg = document.querySelector(selector).querySelectorAll('.lazyload');
					arrImg.forEach((v) => { io.observe(v);})
					$(selector + ' .add_to_cart').bind( 'click', addToCart );
					if (window.BPR !== undefined){
						return window.BPR.initDomEls(), window.BPR.loadBadges();
					}
				},500);
			},
			dataType: "html"
		});
	}
	// Ajax carousel
	function ajaxSwiper(selector,dataLgg){
		console.log(selector);
		$(selector+' .swipertab').each( function(){
			var swiperTab = new Swiper('.swipertab', {
				slidesPerView: 6,
				spaceBetween: 20,
				autoplay: {
					delay: 4000
				},
				slidesPerColumn: 3,
				slidesPerColumnFill: 'row',
				breakpoints: {
					300: {
						slidesPerView: 2,
						slidesPerColumn: 3
					},
					767: {
						slidesPerView: 2,
						slidesPerColumn: 3
					},
					768: {
						slidesPerView: 3
					},
					1024: {
						slidesPerView: 4
					},
					1200: {
						slidesPerView: 6
					}
				}
			});
		})
	}
}
$('.coupon_copy').click(function(){
	const copyText = "Sao chép";
	const copiedText = "Đã sao chép";
	const coupon = $(this).attr('data-code');
	const _this = $(this);
	_this.html(`${copiedText}`);
	_this.addClass('disabled');
	setTimeout(function() {
		_this.html(`${copyText}`);
		_this.removeClass('disabled');
	}, 3000)
	navigator.clipboard.writeText(coupon);
})
$('#coupon-modal .modal-content .close, .coupon-product .coupon_info_toggle, .coupon-action .coupon_close, #coupon-modal .overlay').click(function(){
	$('#coupon-modal').toggleClass('active');
	if($(this).hasClass('coupon_info_toggle')) {
		var code = $(this).data('code');
		$('.modal-content .coupon-title span').html(code);
		$('.modal-content .coupon-detail').html($(this).next().html());
		$('.coupon-action button.coupon_copy').attr('data-code',code)
	}
})