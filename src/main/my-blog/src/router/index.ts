import Vue from 'vue'
import VueRouter, { RouteConfig } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import BlogHome from '../components/BlogHome.vue'
import BlogPost from '../components/BlogPost.vue'
import LoginPage from '../views/LoginPage.vue'
import AdminLayout from '../layouts/AdminLayout.vue'
import UserLayout from '../layouts/UserLayout.vue'
const PostListPage = () => import('@/views/post/PostListPage.vue');
import PostEditPage from '../views/post/PostEditPage.vue';

Vue.use(VueRouter)

const routes: Array<RouteConfig> = [
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
    ]
  },
  
  {
    path: '/blog/',
    name: 'blog-home',
    component: BlogHome
  },
  {
    path: '/blog/:slug',
    name: 'blog-post',
    component: BlogPost
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

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
