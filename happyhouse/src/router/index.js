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
import Notice from '@/components/notice/Notice';
import NoticeList from '@/components/notice/List';
import NoticeCreate from '@/components/notice/Create';
import NoticeDetail from '@/components/notice/Search';
import NoticeUpdate from '@/components/notice/Update';
// import NoticeRemove from '@/components/notice/List';

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
  {
    path: '/notice',
    component: Notice,
    children: [
      {
        path: '',
        name: 'noticelist',
        component: NoticeList,
      },
      {
        path: 'create',
        name: 'noticecreate',
        component: NoticeCreate,
      },
      {
        path: 'detail',
        name: 'noticedetail',
        component: NoticeDetail,
      },
      {
        path: 'update',
        name: 'noticeupdate',
        component: NoticeUpdate,
      },
    ],
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
