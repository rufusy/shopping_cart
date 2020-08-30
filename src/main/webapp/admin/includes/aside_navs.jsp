 <!-- Main Sidebar Container -->
 <aside class="main-sidebar sidebar-dark-primary elevation-4">
   <!-- Sidebar -->
   <div class="sidebar">
     <!-- Sidebar user panel (optional) -->
     <div class="user-panel mt-3 pb-3 mb-3 d-flex">
       <div class="image">
         <img src="http://localhost:8080/shopping_cart/admin/dist/img/user.png" class="img-circle elevation-2"
           alt="User Image">
       </div>
       <div class="info">
         <a href="#" class="d-block">Rufusy Idachi</a>
       </div>
     </div>

     <!-- Sidebar Menu -->
     <nav class="mt-2">
       <ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">
         <!-- Add icons to the links using the .nav-icon class
             with font-awesome or any other icon font library -->
         <li class="nav-item">
           <a href="http://localhost:8080/shopping_cart/admin" class="nav-link">
             <i class="nav-icon fas fa-tachometer-alt"></i>
             <p>Dashboard</p>
           </a>
         </li>
         <li class="nav-item has-treeview">
           <a href="#" class="nav-link">
             <i class="nav-icon fas fa-tags"></i>
             <p>
               Catalog
               <i class="right fas fa-angle-left"></i>
             </p>
           </a>
           <ul class="nav nav-treeview">
             <li class="nav-item">
               <a href="http://localhost:8080/shopping_cart/admin/categories/list.jsp" class="nav-link">
                 <i class="far fa-circle nav-icon"></i>
                 <p>Categories</p>
               </a>
             </li>
             <li class="nav-item">
               <a href="http://localhost:8080/shopping_cart/admin/products/list.jsp" class="nav-link">
                 <i class="far fa-circle nav-icon"></i>
                 <p>Products</p>
               </a>
             </li>
             <li class="nav-item">
               <a href="http://localhost:8080/shopping_cart/admin/stock_status/list.jsp" class="nav-link">
                 <i class="far fa-circle nav-icon"></i>
                 <p>Stock status</p>
               </a>
             </li>
             <li class="nav-item">
               <a href="http://localhost:8080/shopping_cart/admin/reviews/list.jsp" class="nav-link">
                 <i class="far fa-circle nav-icon"></i>
                 <p>Reviews</p>
               </a>
             </li>
           </ul>
         </li>
         <li class="nav-item has-treeview">
           <a href="#" class="nav-link">
             <i class="nav-icon fas fa-shopping-cart"></i>
             <p>
               Sales
               <i class="right fas fa-angle-left"></i>
             </p>
           </a>
           <ul class="nav nav-treeview">
             <li class="nav-item">
               <a href="http://localhost:8080/shopping_cart/admin/orders/list.jsp" class="nav-link">
                 <i class="far fa-circle nav-icon"></i>
                 <p>Orders</p>
               </a>
             </li>
             <li class="nav-item">
               <a href="http://localhost:8080/shopping_cart/admin/returns/list.jsp" class="nav-link">
                 <i class="far fa-circle nav-icon"></i>
                 <p>Returns</p>
               </a>
             </li>
           </ul>
         </li>
         <li class="nav-item has-treeview">
           <a href="#" class="nav-link">
             <i class="nav-icon fas fa-users"></i>
             <p>
               Customers
               <i class="right fas fa-angle-left"></i>
             </p>
           </a>
           <ul class="nav nav-treeview">
             <li class="nav-item">
               <a href="http://localhost:8080/shopping_cart/admin/customers/list.jsp" class="nav-link">
                 <i class="far fa-circle nav-icon"></i>
                 <p>Customers</p>
               </a>
             </li>
           </ul>
         </li>
         <li class="nav-item has-treeview">
           <a href="#" class="nav-link">
             <i class="nav-icon fas fa-cog"></i>
             <p>
               System
               <i class="right fas fa-angle-left"></i>
             </p>
           </a>
           <ul class="nav nav-treeview">
             <li class="nav-item">
               <a href="http://localhost:8080/shopping_cart/admin/users/list.jsp" class="nav-link">
                 <i class="far fa-circle nav-icon"></i>
                 <p>Users</p>
               </a>
             </li>
             <li class="nav-item">
               <a href="http://localhost:8080/shopping_cart/admin/user_groups/list.jsp" class="nav-link">
                 <i class="far fa-circle nav-icon"></i>
                 <p>User groups</p>
               </a>
             </li>
           </ul>
         </li>
     </nav>
     <!-- /.sidebar-menu -->
   </div>
   <!-- /.sidebar -->
 </aside>