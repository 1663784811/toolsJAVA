import Vue from 'vue';
import VueRouter from 'vue-router';

Vue.use(VueRouter);

const router = new VueRouter({
    mode: 'history',
    routes: [


        {
            name: "home",
            path: "/",
            component(resolve) {
                require(['@/pages/home/Home.vue'], resolve)
            },
            meta: {
                title: '前台',
                css: []
            }
        }



    ]

});

router.beforeEach((to, from, next) => {//beforeEach是router的钩子函数，在进入路由前执行
    if (to.meta.title) {//判断是否有标题
        document.title = to.meta.title
    }
    if (to.meta.css && to.meta.css.length > 0) {
        for (var i = 0; i < to.meta.css.length; i++) {
            let link = document.createElement('link');
            link.rel = "stylesheet";
            link.href = to.meta.css[i];
            document.head.prepend(link);
        }
    }
    next()
})

export default router
