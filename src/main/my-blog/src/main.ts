import Vue from 'vue'
import App from './App.vue'
import router from './router'
import Vuesax from 'vuesax'
import store from './store';
// import jwt_decode from 'jwt-decode';

import 'vuesax/dist/vuesax.css'
Vue.use(Vuesax)

import { BootstrapVue, IconsPlugin } from 'bootstrap-vue'

// Import Bootstrap and BootstrapVue CSS files (order is important)
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'jquery/src/jquery.js'
import 'bootstrap/dist/js/bootstrap.min.js'
import 'bootstrap';

// Make BootstrapVue available throughout your project
Vue.use(BootstrapVue)
// Optionally install the BootstrapVue icon components plugin
Vue.use(IconsPlugin)

import Vuetify from 'vuetify'
import 'vuetify/dist/vuetify.min.css'

Vue.use(Vuetify)

Vue.config.productionTip = false

// axios.interceptors.request.use((config) => {
//   // Do something before request is sent
//   //check fist time login
//   if(localStorage['x-auth-token']){
//     const decoded: any = jwt_decode(localStorage['x-auth-token']);
//     if (!decoded.isLogin) {
//       router.push({ name: 'router.reset_first_password' });
//     }
//   }
//   NProgress.start();
//   return loginService.addToken(config);
// }, (error) => {
//   // Do something with request error
//   NProgress.done();
//   return Promise.reject(error);
// });

import axios from 'axios';
import VueAxios from 'vue-axios';

Vue.use(VueAxios, axios);
new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
