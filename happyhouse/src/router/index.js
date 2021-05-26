import Vue from 'vue';
import VueRouter from 'vue-router';
import Index from '@/components/indexm';
import Login from '@/components/user/login';
import Register from '@/components/user/register';
import Userinfo from '@/components/user/userInfo';
import HouseList from '@/components/house/List';
import Wishhouse from '@/components/house/Wishhouse';
import Adminuser from '@/components/user/adminuser';
import Qna from '@/components/qna/Qna';
import QnaList from '@/components/qna/List';
import QnaCreate from '@/components/qna/Create';
import QnaDetail from '@/components/qna/Search';
import QnaUpdate from '@/components/qna/Update';
import Notice from '@/components/notice/Notice';
import NoticeList from '@/components/notice/List';
import NoticeCreate from '@/components/notice/Create';
import NoticeDetail from '@/components/notice/Search';
import NoticeUpdate from '@/components/notice/Update';
import NotFound from '@/views/NotFoundPage.vue';

Vue.use(VueRouter);

const routes = [
  {
    path: '/',
    name: 'Index',
    component: Index,
  },
  {
    path: '/house',
    name: 'HouseList',
    component: HouseList,
  },
  {
    path: '/wish',
    name: 'Wishhouse',
    component: Wishhouse,
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

  {
    path: '/qna',
    component: Qna,
    children: [
      {
        path: '',
        name: 'qnalist',
        component: QnaList,
      },
      {
        path: 'create',
        name: 'qnacreate',
        component: QnaCreate,
      },
      {
        path: 'detail',
        name: 'qnadetail',
        component: QnaDetail,
      },
      {
        path: 'update',
        name: 'qnaupdate',
        component: QnaUpdate,
      },
    ],
  },
  { path: '*', component: NotFound },
];

const router = new VueRouter({
  // mode: 'history',
  base: process.env.BASE_URL,
  routes,
});

export default router;
