import Vue from 'vue'
import VueRouter from 'vue-router'
import store from '../store';
import {RoleEnum} from '@/models';

import {loginService, userService} from '@/services';
import BlogHome from '../components/BlogHome.vue'
import BlogPost from '../components/BlogPost.vue'
import LoginPage from '../views/LoginPage.vue'
// import AdminLayout from '../layouts/AdminLayout.vue'
import UserLayout from '../layouts/UserLayout.vue'
const PostListPage = () => import('@/views/post/PostListPage.vue');
import PostEditPage from '../views/post/PostEditPage.vue';
import ReadPost from '@/views/post/ReadPost.vue'

Vue.use(VueRouter)

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'home',
      redirect: '/post',
      component: UserLayout,
      children: [
        {
          path: '/post',
          name: 'router.post',
          component: PostListPage
        },
        {
          path: '/about',
          name: 'about',
          component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
        },
        {
          path: '/blog/:slug',
          name: 'blog-post',
          component: ReadPost
        },
      ]
    },
    
    {
      path: '/blog/',
      name: 'blog-home',
      component: BlogHome
    },
    {
      path: '/login',
      name: 'routes.login',
      component: LoginPage,
      meta: {},
    },
    {
      path: '/admin',
      name: 'routes.admin',
      redirect: '/admin/post',
      component: UserLayout,
      meta: { },
      children: [
        {
          path: 'post',
          name: 'routes.post',
          component: PostListPage,
          // children: [
          //   {
          //     path: 'edit/:id',
          //     name: 'routes.post_edit',
          //     component: PostEditPage,
          //   }
          // ]
        },
        {
          path: 'edit/:id',
          name: 'routes.post_edit',
          component: PostEditPage,
        },
        {
          path: 'add',
          name: 'routes.post_add',
          component: PostEditPage,
        }
      ]
    }
  ]
})

router.beforeEach((to, from, next) => {
  if (!loginService.isAuthenticated()) {
    if (to.meta.requiresAuth) {
      next('login');
      return;
    } else {
      next();
      return;
    }
  }

  if (!to.meta.requiresAuth) {
    next();
    return;
  }

  userService.getUserDetail().then((res) => {
    const user = res.data.data;
    if (user && user.role) {
      // Save to store
      store.dispatch('setUser', user);
      checkRole(to, from, next, user.role);
    }
  });
});

function checkRole(to: any, from: any, next: any, role: string) {
   // Check user roles
   let isAdmin = false;
   let isUser = false;

   if (role === RoleEnum.ROLE_ADMIN) {
    isAdmin = true;
  } else if (role === RoleEnum.ROLE_USER) {
    isUser = true;
  }
  if (isAdmin) {
    if (to.meta.adminAuth !== undefined && to.meta.adminAuth) {
      next();
    } else {
      next('/');
    }
  } else if (isUser) {
    if (to.meta.userAuth !== undefined && to.meta.userAuth) {
      next();
    } else {
      next('/');
    }
  }
}

export default router
