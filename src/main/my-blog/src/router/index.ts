import Vue from 'vue'
import VueRouter, { RouteConfig } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import BlogHome from '../components/BlogHome.vue'
import BlogPost from '../components/BlogPost.vue'
import LoginPage from '../views/LoginPage.vue'
import AdminLayout from '../layouts/AdminLayout.vue'
import UserLayout from '../layouts/UserLayout.vue'
import PostListPage from '@/views/post/PostListPage.vue'

Vue.use(VueRouter)

const routes: Array<RouteConfig> = [
  {
    path: '/',
    name: 'home',
    component: UserLayout,
    children: [
      {
        path: '/',
        name: 'home',
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
    component: AdminLayout,
    meta: { },
    // children: [
    //   {
    //     path: 'post',
    //     name: 'routes.post',
    //     component: Dashboard,
    //     meta: {},
    //     children: [
    //       {
    //         path: 'list',
    //         component: PicManagementListPage,
    //         meta: { requiresAuth: true, adminAuth: true, userAuth: true, isMenu: true },
    //       },
    //       {
    //         path: 'add',
    //         name: 'routes.pic_management_add',
    //         component: PicManagementEditPage,
    //         meta: { requiresAuth: true, adminAuth: true, userAuth: true },
    //       },
    //       {
    //         path: 'edit/:id',
    //         name: 'routes.pic_management_edit',
    //         component: PicManagementEditPage,
    //         meta: { requiresAuth: true, adminAuth: true, userAuth: true },
    //       },
    //     ]
    //   },
    // ]
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
