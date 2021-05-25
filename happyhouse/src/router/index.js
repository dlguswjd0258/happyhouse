import Vue from 'vue';
import VueRouter from 'vue-router';
// import Index from '@/components/index';
import Login from '@/components/user/login';
import Register from '@/components/user/register';
import Userinfo from '@/components/user/userInfo';
import List from '@/components/house/List';
import Adminuser from '@/components/user/adminuser';
// import QnaList from '@/components/qna/List';
// import QnaCreate from '@/components/qna/List';
// import QnaDetail from '@/components/qna/List';
// import QnaUpdate from '@/components/qna/List';
// import QnaRemove from '@/components/qna/List';
// import NotationList from '@/components/notation/List';
// import NotationCreate from '@/components/qna/List';
// import NotationDetail from '@/components/qna/List';
// import NotationUpdate from '@/components/qna/List';
// import NotationRemove from '@/components/qna/List';

Vue.use(VueRouter);

const routes = [
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

  {
    path: '/adminuser',
    name: 'adminuser',
    component: Adminuser,
  },

  // {
  //   path: '/qna',
  //   component: QnaList,
  //   children: [{}],
  // },
];

const router = new VueRouter({
  // mode: 'history',
  base: process.env.BASE_URL,
  routes,
});

export default router;
