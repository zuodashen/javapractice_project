import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import '@/assets/global.css'
import zhCn from 'element-plus/es/locale/lang/zh-cn'
import * as ElementplusIconsVue from '@element-plus/icons-vue'

const app = createApp(App)

app.use(router)
app.use(ElementPlus, {
    locale: zhCn
})

for (const [key, component] of Object.entries(ElementplusIconsVue)){
    app.component(key, component)
}

app.mount('#app')
