
        var vue = new Vue({
            el: '#myapp',
            data: {
                listProductDetails: [],
                listVersion: [],
                product: {},
                version: {},
                productDetails: {},
                cart: {
                    quantity: 1,
                    productDetailsId: null
                },
                wishlist: {},
                colorName: '',
                statuStock :''



            },
            filters: {
                customCurrency: function (input, symbol) {
                    if (!input) return '';

                    var sign = (symbol !== undefined) ? symbol : 'VNĐ';

                    input = input.toFixed(0).replace(/(\d)(?=(\d{3})+(?:\.\d+)?$)/g, "$1,");

                    return input + ' ' + sign;
                }
            },
            methods: {
                getProduct() {
                    var productCode = '[[${productCode}]]'
                    var self = this
                    axios.get('/dtdd/getProduct/' + productCode)
                        .then(res => {
                            self.product = res.data
							console.log(self.product)
                            self.getVersionByProduct(self.product.productId)
                        })
                        .catch(error => {

                        })
                },
                getColor(producVersionId) {

                    const params = new URLSearchParams(window.location.search);
                    const versionId = params.get('v')


                    var self = this
                    axios.get('/dtdd/getProductDetailsByVersion/' + producVersionId)
                        .then(res => {


                            let version = null
                            if (self.listVersion && self.listVersion.length > 0) {
                                const versionId = parseInt(producVersionId)
                                version = self.listVersion.find(item => item.productVersionId === versionId)

                            }

                            let versionName = ''
                            if (version) {
                                versionName = version.versionName
                                console.log(versionName)
                            }

                            self.listProductDetails = res.data
                            self.version.versionName = versionName
                            self.version.versionId = producVersionId;


                            params.set('v', self.version.versionId);
                            window.history.pushState({}, '', '?' + params.toString());

                            if (self.listProductDetails.length === 0) {
                                self.colorName = ''
                                self.productDetails.productDetailsId = null
                            }


                            self.product.price = version.price
                            self.product.discountedPrice = version.discountedPrice


                            if (versionId != self.version.versionId) {

                                params.delete('p'); // Xóa tham số color khỏi url
                                window.history.pushState({}, '', '?' + params.toString()); // Cập nhật URL
                                self.productDetails.productDetailsId = null
                                self.colorName = null
                                self.statuStock = null
                                // this.product.image=this.product.image
                            }

                            if (self.listProductDetails.length > 0) {
                                self.selectProductDetails(self.productDetails.productDetailsId)
                            }


                        })
                        .catch(error => {

                        })
                }
                , getVersionByProduct(productId) {

                    var self = this
                    axios.get('/dtdd/getVersionByProduct/' + productId)
                        .then(res => {
                            self.listVersion = res.data

                            if (self.version.versionId !== undefined && self.version.versionId !== null) {
                                console.log('đã chạy get color')
                                self.getColor(self.version.versionId)
                            }

                        })
                        .catch(error => {

                        })
                }
                ,
                selectProductDetails(productDetailsId) {


                    this.productDetails.productDetailsId = productDetailsId

                    console.log(productDetailsId)

                    var product = this.listProductDetails.find(item => item.productDetailsId === parseInt(productDetailsId))

                    this.colorName = product.color.name
                    this.product.image = product.image
                    this.statuStock = product.checkOutOfStockProducts?'Hết hàng':'Còn hàng'

                    this.product.price = product.price
                    this.product.discountedPrice = product.discountedPrice


                    var params = new URLSearchParams(window.location.search);
                    params.set('v', this.version.versionId);
                    params.set('p', this.productDetails.productDetailsId);
                    window.history.pushState({}, '', '?' + params.toString());



                },
                init() {
                    this.getVersionByProduct()
                    this.getColor()
                    this.selectProductDetails()
                },
                addCart() {

                    this.cart.productDetailsId = this.productDetails.productDetailsId

                    // console.log(this.cart.productDetailsId +'demoooo')

                    if (this.cart.productDetailsId == null || this.cart.productDetailsId == undefined) {

                        $.toast({
                            heading: 'Thông báo',
                            text: "Vui lòng chọn màu sắc!",
                            showHideTransition: 'slide',
                            position: 'top-right',
                            icon: 'error',
                        })

                        return
                    }


                    axios.post('/cart/insert', this.cart)
                        .then(res => {

                            $.toast({
                                heading: 'Thành công',
                                text: 'Thêm sản phẩm vào giỏ hàng thành công!',
                                showHideTransition: 'slide',
                                position: 'top-right',
                                icon: 'success'
                            })
                        })
                        .catch(error => {

                            // alert(error.response.data.message);
                            if (error.response.status === 401) {

                                $.toast({
                                    heading: 'Thông báo',
                                    text: 'Vui lòng <a href="/login">đăng nhập</a> để thực hiện chức năng!',
                                    showHideTransition: 'slide',
                                    position: 'top-right',
                                    icon: 'error',
                                });

                            } else if (error.response.status === 400) {
                                $.toast({
                                    heading: 'Thông báo',
                                    text: error.response.data.message,
                                    showHideTransition: 'slide',
                                    position: 'top-right',
                                    icon: 'error',
                                })
                            }



                        })

                },

                addToWishlist() {
                    var self = this;
                    self.wishlist.productId = self.product.productId;
                    axios.post('/addToWishlist', self.wishlist)
                        .then(res => {

                            $.toast({
                                heading: 'Thành công',
                                text: res.data,
                                showHideTransition: 'slide',
                                position: 'top-right',
                                icon: 'success'
                            })
                        }).catch(err => {

                            if (err.response.status === 401) {
                                $.toast({
                                    heading: 'Thông báo',
                                    text: 'Vui lòng <a href="/login">đăng nhập</a> để thực hiện chức năng!',
                                    showHideTransition: 'slide',
                                    position: 'top-right',
                                    icon: 'error'
                                })
                                // window.location.href = '/login';
                            }


                        });
                }
            }
            ,
            created() {

                var params = new URLSearchParams(window.location.search);
                this.version.versionId = params.get('v')
                this.productDetails.productDetailsId = params.get('p')
                this.getProduct()



            }
        });
  