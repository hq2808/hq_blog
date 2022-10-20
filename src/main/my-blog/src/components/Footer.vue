<template>
  <div class="footer">
    <div class="container">
      <div class="left section">
          <h2>About Us</h2>
          <div class="content">
              <p>HQ is a programming blog where we post blogs related to Website Development, Technology, Tips & Hacks and more creative coding content.</p>
          <div class="social">
              <a href="/"><b-icon icon="facebook" font-scale="2" variant="primary"></b-icon></a>
              <a href="/"><b-icon icon="instagram" font-scale="2"></b-icon></a>
              <a href="/"><b-icon icon="youtube" font-scale="2" variant="danger"></b-icon></a>
          </div>
          </div>
      </div>

      <div class="center section">
          <h2>Contact Us</h2>
          <div class="content">
              <div class="place">
                  <b-icon icon="geo-alt" animation="cylon" font-scale="1" variant="danger"></b-icon>
                  <span class="text">Hà Nội</span>
              </div>
              <div class="phone">
                  <b-icon icon="telephone" animation="cylon" font-scale="1"></b-icon>
                  <span class="text">0963529524</span>
              </div>
              <div class="email">
                  <b-icon icon="envelope" animation="cylon" font-scale="1"></b-icon>
                  <span class="text"> hunglk1009@gmail.com</span>
              </div>
          </div>
      </div>

      <div class="right section">
          <h2>Subscribe</h2>
          <div class="content">
              <form action="#">
                  <div class="email">
                      <div class="text">Email*</div>
                  </div>
                  <input type="email" required>
                  <div class="btn">
                      <button type="submit">SUBSCRIBE</button>
                  </div>
              </form>
          </div>
      </div>
    </div>

    <div class="footer-bottom">
        <center>
            <div><span>Powered By <a href="/">© 2022 | All Rights Reserved | Made By HQ</a></span></div>
        </center>
    </div>
  </div>
</template>

<script lang="ts">
import {Component, Vue} from 'vue-property-decorator';

@Component
export default class Footer extends Vue {

}
</script>
<style scoped>
.footer {
  width: 100%;
  background-color: pink;
}
.container{
    display: flex;
    padding: 3rem;
}

.container .section{
    flex-basis: 50%;
    padding: 0 20px;
}

.section h2{
    font-size: 1.125rem;
    font-weight: 600;
    text-transform: uppercase;
}

.section .content{
    margin:20px 0 0 0;
    position: relative;
}

.section .content::before{
    position: absolute;
    content: '';
    top: -10px;
    height: 2px;
    width: 100%;
}

.section .content::after{
    position: absolute;
    content: '';
    height: 2px;
    width: 15%;
    background-color: #dd5f24;
    top: -10px;
}

.left .content .social{
    margin: 20px 0 0 0;
}

.left .content .social a{
    padding: 0 10px;
}

.left .content .social a span{
    height: 40px;
    width: 40px;
    background: transparent;
    line-height: 40px;
    text-align: center;
    font-size: 18px;
    border-radius: 50px;
    transition: 0.4s;
    border:1px solid #656565;
}


.left .content .social a span:hover{
    background: #dd5f24;
}

.center .content .fas{
    font-size: 1.1rem;
    background: transparent;
    height: 45px;
    width: 45px;
    line-height: 45px;
    text-align: center;
    border-radius: 50px;
    transition: 0.3s;
    cursor: pointer;
    border:1px solid #656565;
}

.center .content .fas:hover{
    background: #dd5f24;
    border:1px solid #dd5f24;
}

.center .content .text{
    font-size: 1.0.6rem;
    font-weight: 500;
    padding-left: 10px;
}

.center .content .phone{
    margin: 15px 0;
}

.right form .text{
    font-size: 1.0625rem;
    margin-bottom: 2px;
    color: #656565;
}

.right form input{
    width: 100%;
    font-size: 1.0625rem;
    background: transparent;
    border: 1px solid #656565;
}

.right form input{
    height: 35px;
}

.right form .btn{
    margin-top: 10px;
}

.right form .btn button{
    height: 40px;
    width: 100%;
    border:none;
    outline: none;
    background: #dd5f24;
    font-size: 1.0625rem;
    font-weight: 500;
    cursor: pointer;
    transition: 0.3s;
}


.right form .btn button:hover{
    background: #000;
}

.footer-bottom center{
    padding: 5px;
    font-size: 0.9375rem;
    background: #151515;
}

.footer-bottom center span{
    color: #656565;
}

.footer-bottom center a{
    text-decoration: none;
}

.footer-bottom center a:hover{
    text-decoration: underline;
}

@media screen and (max-width:700px){
    footer{
        position: relative;
        bottom: 0px;
    }

    .left .content{
        padding-right:0px;
        padding-bottom: 30px;
    }

    .container{
        flex-wrap: wrap;
        flex-direction: column;
        padding:0.5rem;
    }

    .container .section{
        margin:5px 0;
    }
}
</style>