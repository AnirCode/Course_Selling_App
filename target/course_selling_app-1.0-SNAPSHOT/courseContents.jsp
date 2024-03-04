<%
            List<Course> courses = CourseDAO.getAllCourses();
            
%>
<div class="row">
    <div class="card-columns">
        <% for(Course course : courses) { %>
        <div class="card">
            <img src="course_pics/<%= course.getC_img() %>" class="card-img-top" alt="...">
            <div class="card-body">
                <h5 class="card-title"><%= course.getC_name()%></h5>
                <p class="card-text"><%= course.getC_details()%></p>
            </div>
            <div class="card-footer ">
                <span class="heading">User Rating</span>
                <span class="fa fa-star checked"></span>
                <span class="fa fa-star checked"></span>
                <span class="fa fa-star checked"></span>
                <span class="fa fa-star checked"></span>
                <span class="fa fa-star"></span>
                <span>(34)</span>
                <br>
                <button class="btn mt-2"><b><span>&#8377;</span> <%= course.getC_fees() %></b></button>
                <button class="btn btn-success mt-2" onclick="addToCart(<%= course.getC_id() %>, '<%= course.getC_name()%>', <%= course.getC_fees() %>)"><b>Enroll</b></button>
            </div>
        </div>
        <% } %>
    </div>
</div>


<!--Cart Modal-->

<div class="modal fade" id="cart-modal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Your cart</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <table class="table" id="cart-table">
                    <thead class="text-dark">
                        <tr>
                            <th scope="col">no.</th>
                            <th scope="col">Item name</th>
                            <th scope="col">Price</th>
                            <th scope="col">Action</th>
                        </tr>
                    </thead>
                    <tbody id="tBody">
                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-outline-danger" id="clear-cart" onclick="clearCart()">Clear cart</button>
                <button type="button" class="btn btn-success" id="check-out" onclick="checkoutToServlet()" >Check out</button>
            </div>
        </div>
    </div>
</div>
<!--Cart Modal End-->


<!--JQuery CDN for AJAX-->
<script src="https://code.jquery.com/jquery-3.7.0.min.js" integrity="sha256-2Pmvv0kuTBOenSvLm6bvfBSSHrUJ+3A7x6P5Ebd07/g=" crossorigin="anonymous"></script>
<!--Razor Pay Script-->
<script src="https://checkout.razorpay.com/v1/checkout.js"></script>

<!--Sweet Alert-->
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>



<!--Cart Script-->
<script>

//    All the required action will be preformed here, those which we want to get performed once the page gets loaded.
                    window.onload = function ()
                    {
                        let cart = document.getElementById('cart-val');
                        let localStr = localStorage.getItem('itemsJSON');
//        alert(localStr)
                        if (localStr === null || JSON.parse(localStr).length === 0)
                        {
                            cart.innerHTML = '(' + 0 + ')';

                            let checkOut = document.getElementById('check-out');
                            checkOut.classList.add("disabled");

                            let clearCart = document.getElementById('clear-cart');
                            clearCart.classList.add("disabled");
                        } else
                        {
                            itemsArrayTemp = JSON.parse(localStr);
                            cart.innerHTML = '(' + itemsArrayTemp.length + ')';

                            let checkOut = document.getElementById('check-out');
                            checkOut.classList.remove("disabled");

                            let clearCart = document.getElementById('clear-cart');
                            clearCart.classList.remove("disabled");
                        }

                        // populate th table....
                        if (localStr !== null) {
                            itemsArray = JSON.parse(localStr);
                            let tBody = document.getElementById('tBody');
                            let str = "";
                            itemsArray.forEach((e, index) => {
                                str +=
                                        `<tr>
                                        <th scope="row">\${index+1}</th>
                                        <td>\${e[1]}</td>
                                        <td>\${e[2]}</td>
                                        <td><button  id="remove" class="btn btn-danger" onclick="removeItemById(\${e[0]})">Remove</button></td>
                              </tr>`;
                            });
                            tBody.innerHTML = str;
                        }





                    };

                    function removeItemById(pId)
                    {
                        let itemsArray = [];
                        let localStr = localStorage.getItem('itemsJSON');
                        itemsArray = JSON.parse(localStr);

                        let indx;
                        /*
                         itemsArray.forEach(
                         function (e, index)
                         {
                         if (e[0] == pId)
                         {
                         indx = index;
                         }
                         }
                         )
                         */

                        for (let i = 0; i < itemsArray.length; i++) {
                            if (itemsArray[i][0] === pId) {
                                indx = i;
                                break;
                            }
                        }

                        itemsArray.splice(indx, 1);
                        localStorage.setItem('itemsJSON', JSON.stringify(itemsArray));


                        let str = "";
                        itemsArray.forEach((e, index) => {
                            str +=
                                    `<tr>
                                    <th scope="row">\${index+1}</th>
                                    <td>\${e[1]}</td>
                                    <td>\${e[2]}</td>
                                    <td><button id="remove" class="btn btn-danger" onclick="removeItemById(\${e[0]})">Remove</button></td>
                          </tr>`;
                        });
                        let tBody = document.getElementById('tBody');
                        tBody.innerHTML = str;

                        let cart = document.getElementById('cart-val');
                        cart.innerHTML = '(' + itemsArray.length + ')';

                        //after removing every item from cart, check if it was the last item
                        if (itemsArray.length === 0)
                        {
                            clearCart();
                        }


                    }

                    function addToCart(cId, cName, cPrice) {
//                        alert(cid);
//                        alert(cName);
//                        alert(cPrice);

                        let itemsArray = [];
                        if (localStorage.getItem('itemsJSON') === null)
                        {
                            //Means cart is empty....
                            itemsArray.push([cId, cName, cPrice]);
                            localStorage.setItem('itemsJSON', JSON.stringify(itemsArray));

                            let cart = document.getElementById('cart-val');
                            cart.innerHTML = '(' + itemsArray.length + ')';
                        } else {
                            //Controll will come here when localStorage has a key with a name 'itemsJSON'
                            let flag = true;
                            //Means cart is not empty............
                            let localStr = localStorage.getItem('itemsJSON');
                            itemsArray = JSON.parse(localStr);

                            //This loop will make the flag 'false' if the requested course is already exsisting in the cart...
                            for (let i = 0; i < itemsArray.length; i++) {
                                if (itemsArray[i][0] === cId) {
                                    flag = false;
                                    break;
                                }
                            }

                            if (flag) {
                                itemsArray.push([cId, cName, cPrice]);

                                let cart = document.getElementById('cart-val');
                                cart.innerHTML = '(' + itemsArray.length + ')';
                            }
                            localStorage.setItem('itemsJSON', JSON.stringify(itemsArray));
                        }

                        //console.log(itemsArray);


                        // populate th table....
                        let localStr = localStorage.getItem('itemsJSON');
                        itemsArray = JSON.parse(localStr);
                        let tBody = document.getElementById('tBody');
                        let str = "";
                        itemsArray.forEach((e, index) => {
                            str +=
                                    `<tr>
                                    <th scope="row">\${index+1}</th>
                                    <td>\${e[1]}</td>
                                    <td>\${e[2]}</td>
                                    <td><button  id="remove" class="btn btn-danger" onclick="removeItemById(\${e[0]})">Remove</button></td>
                          </tr>`;
                        });
                        tBody.innerHTML = str;

                        //enable the checkout, clearcart button..................
                        let checkOut = document.getElementById('check-out');
                        checkOut.classList.remove("disabled");

                        let clearCart = document.getElementById('clear-cart');
                        clearCart.classList.remove("disabled");

                    }


                    function clearCart()
                    {
                        let itemsArray = [];
                        if (localStorage.getItem('itemsJSON') !== null) {
                            let localStr = localStorage.getItem('itemsJSON');
                            itemsArray = JSON.parse(localStr);
                        }
                        itemsArray = [];
                        localStorage.setItem('itemsJSON', JSON.stringify(itemsArray));

                        let cartTable = document.getElementById('cart-table');
                        let str = "";
                        str += ` <thead class="text-dark">
                                <tr>
                                    <th scope="col">no.</th>
                                    <th scope="col">Item name</th>
                                    <th scope="col">Price</th>
                                    <th scope="col">Action</th>
                                </tr>
                            </thead>
                            <tbody id="tBody">
                            </tbody>`;
                        cartTable.innerHTML = str;

                        let cart = document.getElementById('cart-val');
                        cart.innerHTML = '(' + 0 + ')';

                        //disable the check out, clear cart button.....
                        let checkOut = document.getElementById('check-out');
                        checkOut.classList.add("disabled");

                        let clearCart = document.getElementById('clear-cart');
                        clearCart.classList.add("disabled");
                    }


</script>
<!--Cart Script End-->

<!--Checkout Script-->
<script>

    function checkoutToServlet() {

        let localStr = localStorage.getItem('itemsJSON');
        let itemsArray = [];
        if (localStr !== null) {
            itemsArray = JSON.parse(localStr);

            if (itemsArray.length > 0)
            {
                //Actual Play Ground.......
                const d = {"courses": localStr};


                //Ajax Starts here....
                $.ajax({
                    type: "post",
                    url: "checkoutServlet",
                    dataType: 'json',
                    data: d,
                    success: function (response, textStatus, jqXHR) {
//                        console.log("response success pe aya hain");
//                        console.log(response);
                        if (response.status === "created")
                        {
                            //open the payment form.....................
                            let options = {
                                "key": "rzp_test_ZnrDw2paOVbmsr",
                                "amount": response.amount,
                                "currency": response.currency,
                                "name": response.receipt,
                                "description": "Test",
                                "order_id": response.id,
                                "handler": function (payment_response) {

                                    const dt = {
                                        "paymentId": payment_response.razorpay_payment_id,
                                        "orderId": payment_response.razorpay_order_id
                                    };


                                    $.ajax({
                                        type: "post",
                                        url: "purchaseServlet",
                                        data: dt,
                                        success: function (response, textStatus, jqXHR)
                                        {
//                                            console.log(response);
//                                            console.log(typeof response);
                                            clearCart();
                                            if (response.trim() === "success")
                                            {
//                                                console.log("Hum andar aa chuke hain bhaiya ji");
                                                Swal.fire({
                                                    icon: 'success',
                                                    title: 'Your Course has been successfully Enrolled',
                                                    showConfirmButton: false,
                                                    timer: 4000
                                                });
                                            }
                                        },
                                        error: function (errorData, textStatus, errorThrown) {
                                            console.log("inner AJAX response error section pe aya hain")
                                            console.log(errorData);
                                        }
                                    });
                                },
                                "prefill": {
                                    "name": "Tech Ghosh",
                                    "email": "test.techghosh@gmail.com",
                                    "contact": "7001290785"
                                },
                                "notes": {
                                    "address": "Sodepur, North 24 Pgs, WB, 7001110"
                                },
                                "theme": {
                                    "color": "#3399cc"
                                }

                            };
                            let rzp = new Razorpay(options);
                            rzp.on('payment.failed', function (response) {
                                alert(response.error.code);
                                alert(response.error.description);
                                alert(response.error.source);
                                alert(response.error.step);
                                alert(response.error.reason);
                                alert(response.error.metadata.order_id);
                                alert(response.error.metadata.payment_id);
                            });
                            rzp.open();

                        }
                    },
                    error: function (errorData, textStatus, errorThrown) {
                        console.log("response error pe aya hain");
                        console.log(errorData);
                    }
                });


            } else {
                alert("itemsArray is empty");
            }

        } else {
            alert("itemsJSON is null");
        }


    }



</script>
<!--Checkout Script End-->


