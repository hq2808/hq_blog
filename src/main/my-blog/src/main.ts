import Vue from 'vue'
import App from './App.vue'
import router from './router'
import Vuesax from 'vuesax'
import store from './store';
import jwt_decode from 'jwt-decode';

import { loginService, toastService } from '@/services';

import NProgress from 'nprogress';
NProgress.configure({
  showSpinner: false,
  template: '<div class="bar" role="bar"><div class="peg"></div></div>',
});

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

import Toasted from 'vue-toasted';
Vue.use(Toasted)

axios.interceptors.request.use((config) => {
  // Do something before request is sent
  if(localStorage['x-auth-token']){
    const decoded: any = jwt_decode(localStorage['x-auth-token']);
    // if (!decoded.isLogin) {
    //   router.push({ name: 'routes.login' });
    // }
  }
  NProgress.start();
  return loginService.addToken(config);
}, (error) => {
  // Do something with request error
  NProgress.done();
  return Promise.reject(error);
});
axios.interceptors.response.use((res) => {
  NProgress.done();
  if(res.data.code) {
    toastService.error(res.data.code);
  }
  // if (res.data.code
  //   && res.data.code !== ResponseCodeEnum.CODE_SUCCESS
  //   && res.data.code !== ResponseCodeEnum.CODE_BUSINESS) {
  //   // Show error
  //   toastService.error(res.data.code);
  // } else if (res.data.code) {
  //   // Show business error
  //   toastService.error("CODE_BUSINESS");
  // } else if (res.data.code && res.data.code === ResponseCodeEnum.CODE_EXPIRED) {
  //   // Logout
  //   toastService.error("CODE_EXPIRED");
  //   loginService.logout();
  //   router.push({ name: 'routes.login' });
  // } else if (res.data.code && res.data.code === ResponseCodeEnum.CODE_INTERNAL_ERROR) {
  //   // Show internal error
  //   toastService.error("CODE_INTERNAL_ERROR");
  // }
  return res;
}, (error) => {
  NProgress.done();
  // Do something with response error
  if (error.response && error.response.status) {
    if (error.response.status !== 400 && error.response.status !== 401) {
      toastService.errorMessage(error.response.data.error);
    } else if (error.response.status === 401) {
      // Logout
      toastService.error("CODE_EXPIRED");
      loginService.logout();
      router.push({ name: 'routes.login' });
    }
  } else {
    toastService.errorMessage(error.message);
  }
  return Promise.reject(error);
});

import axios from 'axios';
import VueAxios from 'vue-axios';

Vue.use(VueAxios, axios);
// new Vue({
//   router,
//   store,
//   render: h => h(App)
// }).$mount('#app')


const mainApp = new Vue({
  router,
  store,
  render: (h) => h(App),
}).$mount('#app');
export default mainApp;
