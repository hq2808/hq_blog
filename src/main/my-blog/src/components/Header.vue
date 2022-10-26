<template>
  <nav class="navbar">
     <!-- LOGO -->
     <div class="logo">HQ</div>
     <!-- NAVIGATION MENU -->
     <ul class="nav-links">
       <!-- USING CHECKBOX HACK -->
       <input type="checkbox" id="checkbox_toggle" />
       <label for="checkbox_toggle" class="hamburger">&#9776;</label>
       <!-- NAVIGATION MENUS -->
      
       <div class="menu">
         <li><b-button to="/" variant="outline-light">Home</b-button></li>
         <li><b-button to="/about" variant="outline-light">About</b-button></li>
         <!-- <li class="services">
           <b-button to="/" variant="outline-light">Services</b-button>
           DROPDOWN MENU
           <ul class="dropdown">
              <li><b-button to="/"  variant="outline-light">Dropdown 1 </b-button></li>
              <li><b-button to="/"  variant="outline-light">Dropdown 2 </b-button></li>
              <li><b-button to="/"  variant="outline-light">Dropdown 3 </b-button></li>
              <li><b-button to="/"  variant="outline-light">Dropdown 4 </b-button></li>
              <li><b-button to="/"  variant="outline-light">Dropdown 5 </b-button></li>
           </ul>
         </li>
         <li><b-button to="/"  variant="outline-light">Pricing</b-button></li>
         <li><b-button to="/"  variant="outline-light">Contact</b-button></li> -->
         <li><b-icon icon="search"></b-icon></li>
       </div>
     </ul>
     <b-button v-if="!isLogin" @click="login">Login</b-button>
     <b-button v-if="isLogin" @click="logout">Logout</b-button>
     <b-button v-if="!isLogin" @click="register">Register</b-button>
   </nav>
</template>

<script lang="ts">
import {Component, Vue} from 'vue-property-decorator';
import { loginService } from '@/services';
@Component
export default class Header extends Vue {
  private isLogin?: boolean = false
  private created() {
    if(this.$store.state.user) {
      this.isLogin = true;
    }
  }
  private logout() {
    loginService.logout();
    this.$router.go(0)
  }
  private login() {
    this.$router.push({
      name: 'routes.login'
    })
  }
}
</script>
<style scoped>
  a {
    text-decoration: none;
  }
  li {
    list-style: none;
  }
  ol, ul, dl {
    margin-top: 0;
    margin-bottom: 0 !important;
  }
  /* NAVBAR STYLING STARTS */
  .navbar {
    display: flex;
    align-items: center;
    justify-content: space-between;
    padding: 20px;
    background-color: pink;
    color: #fff;
    height: auto;
  }
  .nav-links a {
    color: #fff;
    margin: 0 !important;
  }
  /* LOGO */
  .logo {
    font-size: 32px;
  }
  /* NAVBAR MENU */
  .menu {
    display: flex;
    gap: 1em;
    font-size: 18px;
    z-index: 1;
  }
  .menu li {
    padding: 5px 14px;
  }
  /* DROPDOWN MENU */
  .services {
  position: relative; 
  }
  .dropdown {
    background-color: pink;
    padding: 1em 0;
    position: absolute; /*WITH RESPECT TO PARENT*/
    display: none;
    border-radius: 8px;
    top: 35px;
    z-index: 1;
  }
  .dropdown li + li {
    margin-top: 10px;
  }
  .dropdown li {
    padding: 0.5em 1em;
    width: 8em;
    text-align: center;
  }
  
  .services:hover .dropdown {
    display: block;
  }
  /* CHECKBOX HACK */
  input[type=checkbox]{
    display: none;
  } 
  /*HAMBURGER MENU*/
  .hamburger {
    display: none;
    font-size: 24px;
    user-select: none;
  }
  .btn-outline-light:hover {
    background-color: rgb(243, 159, 173);
  }
  /* APPLYING MEDIA QUERIES */
  @media (max-width: 768px) {
    .menu { 
      display:none;
      position: absolute;
      background-color: pink;
      right: 0;
      left: 0;
      text-align: center;
      padding: 16px 0;
      height: 100%;
      z-index: 1;
    }
    input[type=checkbox]:checked ~ .menu{
      display: block;
      height: auto;
    }
    .hamburger {
      display: block;
    }
    .dropdown {
      left: 50%;
      top: 30px;
      transform: translateX(35%);
    }
  }
</style>