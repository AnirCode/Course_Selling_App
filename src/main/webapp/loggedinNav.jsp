<!-- Nav bar starts---->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark custom-color fixed-top">
    <a class="navbar-brand" href="index.jsp">CSA</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav ml-auto">
            <li class="nav-item p-1">


                <a  class="nav-link" id="cart-icon" href="#" data-toggle="modal" data-target="#cart-modal"><i class="fas fa-cart-arrow-down" style="font-size: 20px;"></i> <i id="cart-val">(0)</i><span class="sr-only"></span></a>


            </li>

            <li class="nav-item p-1 dropdown">
                <i class="fas fa-user-alt text-white h3 nav-link dropdown-toggle" role="button" data-toggle="dropdown" aria-expanded="false">  </i>
                <div class="dropdown-menu p-0 bg-primary text-center">
                    <button class="dropdown-item bg-success" type="button"><b>Hello, <%= loggedinUser.getS_Name().split(" ")[0] %></b></button>
                    <a class="dropdown-item" href="#">My Profile</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="#" data-toggle="modal" data-target="#purchase-modal">My Purchases</a>
                    <a class="dropdown-item bg-danger" href="#">Delete Profile</a>
                </div>
            </li>
            <li class="nav-item p-1">
                <a href="logoutServlet" class="btn btn-outline-danger">Logout</a>
            </li>

        </ul>
    </div>
</nav>           
<!---Nav bar ends--->