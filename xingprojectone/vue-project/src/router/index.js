import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [

    {
      path: '/manager',component: () => import('../views/Manager.vue'),
      children:[{
        path: 'home',
        name: 'home',
        meta:{title:'首页'},
        component: () => import('../views/Home.vue')
      },
        {
          path: 'test',
          name: 'test', // 修改为唯一的名称
          meta:{title:'测试'},
          component: () => import('../views/Test.vue')
        },
        {
          path: 'data',
          name: 'data', // 修改为唯一的名称
          meta:{title:'数据展示页面'},
          component: () => import('../views/Data.vue')
        },
        {
          path: 'employee',
          name: 'employee', // 修改为唯一的名称
          meta:{title:'员工信息页面'},
          component: () => import('../views/Employee.vue') },
        {
          path: 'admin',
          name: 'admin', // 修改为唯一的名称
          meta:{title:'管理员信息页面'},
          component: () => import('../views/Admin.vue') },

        {
          path: 'person',
          name: 'person', // 修改为唯一的名称
          meta:{title:'个人信息页面'},
          component: () => import('../views/Person.vue') },
      ]},

    {path:'/login',name:'Login', meta:{title: '登录系统'},component: () =>import("../views/Login.vue")},
    {path:'/register',name:'Register', meta:{title: '欢迎注册'},component: () =>import("../views/Register.vue")},
    {
      path:'/',
      redirect: '/login'
    },

    {path:'/404',name:'NotFound',meta:{title:'404找不到页面 '},
      component:()=>import('../views/404.vue')},
    {path:'/:pathMatch(.*)', redirect:'/404'}
  ]
})




//beforeEach 表示跳转之前的一些操作
router.beforeEach((to,from,next) => {
  document.title = to.meta.title
  next()
})
//to：即将进入的目标路由对象。
// from：当前导航正要离开的路由对象。
// next：一个函数，用于继续导航。必须调用 next 函数来解析这个钩子。如果不调用 next，则导航将被中断。
export default router
