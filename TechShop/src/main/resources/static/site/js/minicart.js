window.addEventListener('DOMContentLoaded', (event) => {
	setTimeout(function(){
		$.getJSON('/cart.js', function(cart){ 
			var resultcart = cart.item_count;
			$('.count_item_pr').html(resultcart);
		});
	},300);
})

$('.add_to_cart').bind( 'click', addToCart );

function addToCart(e){
	if (typeof e !== 'undefined') e.preventDefault();
	var $this = $(this);
	var form = $this.parents('form');		
	$.ajax({
		type: 'POST',
		url: '/cart/add.js',
		async: false,
		data: form.serialize(),
		dataType: 'json',
		error: addToCartFail,
		beforeSend: function() {  
		},
		success: addToCartOk,
		cache: false
	});
}

function addToCartOk(product) { 
	cartCount++;
	$('.count_item_pr').html(cartCount);
	new Notify ({
		status: 'success',title: 'Thêm vào giỏ hàng thành công',text: 'bấm <a style="color:#2196f3" href="/cart">vào đây</a> để tới trang giỏ hàng',
		effect: 'slide',speed: 300,showIcon: true,showCloseButton: true,autoclose: true,autotimeout: 2000,gap: 20,distance: 20,type: 1,position: 'right top',
	})
} 

function addToCartFail(obj, status) { 
	new Notify ({
		status: 'error',title: 'Thêm vào giỏ hàng thất bại',text: 'Vui lòng thử lại sau',
		effect: 'slide',speed: 300,showIcon: true,showCloseButton: true,autoclose: true,autotimeout: 2000,gap: 20,distance: 20,type: 1,position: 'right top',
	})
}