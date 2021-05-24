import Vue from 'vue';
import VueRouter from 'vue-router';
// import Index from '@/components/index';
import Login from '@/components/user/login';
import Register from '@/components/user/register';
import Userinfo from '@/components/user/userInfo';
import List from '@/components/house/List';
import Page from '@/components/house/Pagenation';
// import Adminuser from '@/components/user/adminuser';

Vue.use(VueRouter);

const routes = [
  {
    path: '/page',
    name: 'page',
    component: Page,
  },
    {
    path: '/',
    name: 'List',
    component: List,
  },
  // {
  //   path: '/',
  //   name: 'Home',
  //   component: Home,
  // },

  {
    path: '/login',
    name: 'login',
    component: Login,
  },
  {
    path: '/register',
    name: 'register',
    component: Register,
  },

  {
    path: '/userinfo',
    name: 'userinfo',
    component: Userinfo,
  },
  // {
  //   path: '/adminuser',
  //   name: 'adminuser',
  //   component: Adminuser,
  // },
];

const router = new VueRouter({
  // mode: 'history',
  base: process.env.BASE_URL,
  routes,
});

export default router;
