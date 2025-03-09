import { fileURLToPath, URL } from 'node:url'

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import vueDevTools from 'vite-plugin-vue-devtools'
// import AutoImport from 'unplugin-auto-import/vite'
// import Components from 'unplugin-vue-components/vite'
// import ElementPlusResolvers from "unplugin-vue-components/resolvers";
// import ElementPlus from 'unplugin-element-plus/vite'


// https://vite.dev/config/
export default defineConfig({
  plugins: [
    vue(),
    vueDevTools(),
    //   ElementPlus({
    //     useSource:true,
    //   }),
    // AutoImport({
    //   resolvers: [ElementPlusResolvers({importStyle:'sass'})]
    // }),
    //   Components({
    //     resolvers:[ElementPlusResolvers({importStyle:'sass'})]
    //   })
  ],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    },
  },
  // css:{
  //   preprocessorOptions:{
  //     scss:{
  //       additionalData:`@import "@/assets/global.css";
  //     }
  //   }
  // }
})
