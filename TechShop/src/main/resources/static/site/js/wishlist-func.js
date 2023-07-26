var array_wish = JSON.parse(localStorage.getItem('EgoWishStorage')) || [],
	activeIconWish = '<svg class="icon"> <use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#wishlistactive"></use> </svg>',
	iconWish = '<svg class="icon"> <use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#wishlist"></use> </svg>',
	addW = 'Thêm vào yêu thích',
	removeW = 'Bỏ yêu thích',
	list_iwish_pro_old = localStorage.getItem("EgoWishStorage");
document.addEventListener("DOMContentLoaded", function(event) { 
	renderWish();
	activityWish();
	Onwishlist();
})
function Onwishlist() {
	document.querySelectorAll('.setWishlist').forEach(function(wishclick) {
		wishclick.addEventListener('click', function() {
			var itemwish = this.dataset.wish,
				elm = this;
			if (this.classList.contains('active')) {
				removeItemWish(elm, itemwish);
			}else {
				addNewItemWish (elm, itemwish);
			}
		});
	})
}

function renderWish() {
	if(localStorage.EgoWishStorage != undefined) {
		var arrList = JSON.parse(localStorage.EgoWishStorage);
		var wishlist_promises = [];
		var size_Wish = arrList.length;
		document.querySelector('.headerWishlistCount').innerHTML = size_Wish;
		if (size_Wish > 0 ) {
			for (i = 0; i < size_Wish; i++){
				var productWish = arrList[i];
				var promiseWish = new Promise(function(resolve, reject) {
					var urlwish = '/products/' + productWish.url + '?view=wish';
					fetch(urlwish)
						.then(function(response) {
						if(!response.ok) {
							throw new Error("HTTP error, status = " + response.status);
						}
						var product_promise = response.text(); // =====> Error
						resolve(product_promise);
					})
						.then(function(product) {
						resolve(product);
					})
						.catch(function(error)  {
						console.log(error);
					});
				});
				wishlist_promises.push(promiseWish);	
			}

			Promise.all(wishlist_promises).then(function(values) {
				//debugger
				if (document.querySelector('.page-wishlist') != null) {
					document.querySelector('.page-wishlist').innerHTML = '';
					for (var i = 0; i < values.length; i++) {
						document.querySelector('.page-wishlist').innerHTML += values[i];
					}
					setTimeout(function () {
						let arrImg = document.querySelector('.page-wishlist').querySelectorAll('.lazyload');
						arrImg.forEach((v) => { io.observe(v);})
					}, 300);
					setTimeout(function() {
						Onwishlist();
					},800);
					$('.add_to_cart').bind( 'click', addToCart );
				}
			});
		}else {
			if (document.querySelector('.page-wishlist') != null)
				document.querySelector('.page-wishlist').innerHTML = '<div class="col-12"><p>Không có sản phẩm nào được yêu thích</p></div>';
		}
	}
}
function addNewItemWish(elm, itemwish) {
	var productwish = {url: itemwish};
	array_wish.push(productwish);
	var resultWish = array_wish.reduce((unique, o) => {if(!unique.some(obj => obj.url === o.url)) {unique.push(o);}return unique;},[]);
	localStorage.setItem('EgoWishStorage',JSON.stringify(resultWish));
	activityWish(elm);
	new Notify ({
		status: 'success',
		title: 'Thêm yêu thích thành công',
		text: '<a style="color:#2196f3" href="'+theme.settings.urlpage_yeuthich+'">vào đây</a> để tới trang yêu thích',
		effect: 'slide',
		speed: 300,
		showIcon: true,
		showCloseButton: true,
		autoclose: true,
		autotimeout: 2000,
		gap: 20,
		distance: 20,
		type: 1,
		position: 'right top',
	})
	renderWish();
}
function removeItemWish(elm, itemwish) {
	const greetings = JSON.parse(localStorage.getItem("EgoWishStorage"));
	const filtered = greetings.filter(product => product.url !== itemwish);
	localStorage.setItem("EgoWishStorage", JSON.stringify(filtered));
	renderWish();
	if(document.querySelectorAll('.setWishlist[data-wish="'+itemwish+'"]' != null)) {
		document.querySelectorAll('.setWishlist[data-wish="'+itemwish+'"]').forEach(function(el){
			el.classList.remove('active');el.innerHTML = iconWish;el.title = addW;
		})
	}
	new Notify ({
		status: 'warning',
		title: 'Cảnh báo',
		text: 'Bạn vừa bỏ sản phẩm ra khỏi mục yêu thích',
		effect: 'slide',
		speed: 300,
		showIcon: true,
		showCloseButton: true,
		autoclose: true,
		autotimeout: 2000,
		gap: 20,
		distance: 20,
		type: 1,
		position: 'right top',
	})
}

function activityWish(add_W) {
	if(localStorage.EgoWishStorage != undefined) {
		var arrGet = JSON.parse(localStorage.EgoWishStorage);
		document.querySelector('.headerWishlistCount').innerHTML = arrGet.length;
		arrGet.forEach(function (e) {
			if(document.querySelectorAll('.setWishlist[data-wish="' + e.url + '"]') != null) {
				document.querySelectorAll('.setWishlist[data-wish="' + e.url + '"]').forEach(function(el){
					el.innerHTML = activeIconWish;
					el.classList.add('active');
					el.title = removeW;
				})
			}
		});
	}
}