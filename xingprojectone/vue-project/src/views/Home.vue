<template>
  <div>

  <div style="margin-bottom: 20px">
      <RouterLink to="/test">跳转到Test.vue</RouterLink>
      <a href="/test">通过a标签跳转到Test.vue</a>
  </div>

    <div>
      <el-button type="primary" @click="router.push('/manager/Test')">push跳转到新的页面</el-button>
      <el-button type="primary" @click="router.replace('/manager/Test')">replace跳转到新的页面</el-button>
    </div>

    <div style="margin-bottom: 20px">
      <el-button type="primary" @click="router.push('/manager/Test?id=1&name=xingxing')">路由传参id=2和name=xingxing</el-button>
    </div>
    <div style="margin-bottom: 20px">
      <el-button type="primary" @click="router.push({path:'/manager/test',query:{id:2,name:'xingxing'}})">路由传参id=2和name=xingxing</el-button>
    </div>

      <div>
          <el-input v-model="data.input" style="width: 240px"
                    placeholder="请输入内容" :prefix-icon="Search" />{{data.input}}
          <el-input style="width: 200px" :suffix-icon="Calendar"></el-input>
        <el-input type="textarea" v-model="data.descr" style="width:300px" placeholder="请输入一段描述"></el-input>
      </div>

<div style="margin: 20px">
  <el-select
      clearable
      multiple
      v-model="data.value"
      placeholder="Select"
      size="large"
      style="width: 240px"
  >
    <el-option
        v-for="item in data.options"
        :key="item"
        :label="item"
        :value="item"
    />
  </el-select> {{data.value}}

</div>

 <div style="margin:20px 0">
   <el-radio-group v-model="data.sex">
      <el-radio value="男">男</el-radio>
      <el-radio value="女">女</el-radio>
   </el-radio-group> <span style="margin-left: 50px">{{data.sex}}</span>

   <el-radio-group style="margin-left: 100px" v-model="data.tag" size="large">
     <el-radio-button label="New York" value="New York" />
     <el-radio-button label="Washington" value="Washington" />
     <el-radio-button label="Los Angeles" value="Los Angeles" />
     <el-radio-button label="Chicago" value="Chicago" />
   </el-radio-group>

 </div>


    <div style="margin:20px 0">
      <el-checkbox-group disabled v-model ="data.checkList">
        <el-checkbox v-for="item in data.checkList" :key="item.id" :value="item.name" :lable="item.lable"   />
      </el-checkbox-group>
    </div>

    <div class="demo-image__preview">
      <el-image
          style="width: 100px; height: 100px"
          :src="url"
          :zoom-rate="1.2"
          :max-scale="7"
          :min-scale="0.2"
          :preview-src-list="srcList"
          show-progress
          :initial-index="4"
          fit="cover"
      />
    </div>

    <div style="margin:20px 0">
      <el-carousel height="400px" style="width: 200px" >
        <el-carousel-item v-for="item in data.imgs" :key="item">
          <img style="width:100%" :src="item" alt="">
        </el-carousel-item>
      </el-carousel>
    </div>

    <div style="margin: 20px 0">
      <el-date-picker
        v-model="data.date"
        type="date"
        placeholder="请选择日期"
        format="YYYY-MM-DD"
        value-format="YYYY-MM-DD"
        />{{data.date}}
      <el-date-picker
          style="margin-left: 20px"
         v-model="data.date1"
         type="datetime"
         placeholder="请选择日期与时间"
         format="YYYY-MM-DD HH:mm:ss"
         value-format="YYYY-MM-DD HH:mm:ss"
         />{{data.date1}}

      <el-date-picker
        style="margin-left: 20px"
        v-model="data.daterange"
        type="daterange"
        range-separator="至"
        start-placeholder="开始日期"
        end-placeholder="结束日期"
        format="YYYY-MM-DD"
        value-format="YYYY-MM-DD"
        />
    </div>

    <div style="margin: 20px 0">
      <el-table :data="data.tablebox" style="width: 100%">
        <el-table-column prop="date" label="日期" width="180" header-align="center" stripe></el-table-column>
        <el-table-column prop="name" label="名称" width="180" header-align="center"></el-table-column>
        <el-table-column prop="address" label="地址" width="180" header-align="center"></el-table-column>
        <el-table-column label="操作">
          <template #default="scope">
            <el-button
                size="small"
                type="primary"
                circle
                @click="edit(scope.row)"
            >
              <el-icon><Edit /></el-icon>
            </el-button>

            <el-button
                size="small"
                type="danger"
                circle
                @click="del(scope.row.id)"
            >
              <el-icon><Delete /></el-icon>
            </el-button>
          </template>
        </el-table-column>

      </el-table>

      <div style="padding: 10px 0">
        <el-pagination
          v-model:current-page="data.currentPage"
          v-model:page-size="data.pageSize"
          :page-sizes="[5, 10, 15, 20]"
          background
          layout="total, sizes, prev, pager, next, jumper"
          :total="14"
        />
      </div>
    </div>

    <el-dialog v-model="data.dialogVisible" title="编辑行对象" width="800">
      <div style="padding: 20px">
        <div style="margin-bottom: 10px">日期：{{data.row.date}}</div>
        <div style="margin-bottom: 10px">名称：{{data.row.name}}</div>
        <div> 地址：{{data.row.address}}</div>
      </div>
    </el-dialog>

  </div>
</template>

<script setup>
import {reactive} from "vue";
import {Search, Calendar, Delete, Edit} from "@element-plus/icons-vue"
import lun1 from '@/assets/lun1.png'
import lun2 from '@/assets/lun2.png'
import router from "@/router/index.js";
import request from "@/utils/request.js";

request.get('/employee/selectAll').then(res => {
  console.log(res)
  data.emplpueeList = res.data  //就是员工的列表数据 是一个数组
})

const del = (id)=>{
  alert("删除ID=" + id +"的数据")
}
const edit=(row)=>{
  data.row=row
  data.dialogVisible=true
}

const data = reactive({
  id:router.currentRoute.value.query.id,
  name:router.currentRoute.value.query.name,
  input: null,
  descr: '学习不简单，慢慢来',
  value: '选择输入的内容',
  options: ['苹果','香蕉','橘子'],
  // options: [{id:1,name:'苹果',id:2 name:'香蕉',id:3 name:'橘子'}]
  sex:'男',
  tag:'New York',
  checkList:[{id:1,label:'Option1',name:'Option1'},{id:2,lable:'Option2', name:'Option2'}],
  imgs:[lun1,lun2],
  date:'',
  date1:'',
  daterange:'',
  tablebox:[
    {id:1,date:'2022-01-01',name:'张三',address:'北京'},
    {id:2,date:'2022-01-02',name:'李四',address:'上海'},
    {id:3,date:'2022-01-02',name:'李四',address:'上海'},
    {id:4,date:'2022-01-02',name:'李四',address:'上海'},
    {id:5,date:'2022-01-02',name:'李四',address:'上海'},
    {id:6,date:'2022-01-02',name:'李四',address:'上海'},
    {id:7,date:'2022-01-02',name:'李四',address:'上海'},
    {id:8,date:'2022-01-02',name:'李四',address:'上海'},
    {id:9,date:'2022-01-02',name:'李四',address:'上海'},
    {id:10,date:'2022-01-02',name:'李四',address:'上海'},
    {id:11,date:'2022-01-02',name:'李四',address:'上海'},
    {id:12,date:'2022-01-02',name:'李四',address:'上海'},
    {id:13,date:'2022-01-02',name:'李四',address:'上海'},
    {id:14,date:'2022-01-01',name:'张三',address:'北京'}],
    currentPage:1,
    pageSize:5,
    dialogVisible:false,
    row:null,
    emplpueeList:[]
})
console.log('获取到传递过来的id='+data.id)
console.log('获取到传递过来的name='+data.name)

const url =
    'https://fuss10.elemecdn.com/a/3f/3302e58f9a181d2509f3dc0fa68b0jpeg.jpeg'
const srcList = [
  'https://fuss10.elemecdn.com/a/3f/3302e58f9a181d2509f3dc0fa68b0jpeg.jpeg',
  'https://fuss10.elemecdn.com/1/34/19aa98b1fcb2781c4fba33d850549jpeg.jpeg',
  'https://fuss10.elemecdn.com/0/6f/e35ff375812e6b0020b6b4e8f9583jpeg.jpeg',
  'https://fuss10.elemecdn.com/9/bb/e27858e973f5d7d3904835f46abbdjpeg.jpeg',
  'https://fuss10.elemecdn.com/d/e6/c4d93a3805b3ce3f323f7974e6f78jpeg.jpeg',
  'https://fuss10.elemecdn.com/3/28/bbf893f792f03a54408b3b7a7ebf0jpeg.jpeg',
  'https://fuss10.elemecdn.com/2/11/6535bcfb26e4c79b48ddde44f4b6fjpeg.jpeg',
]
data.tablebox=data.tablebox.splice(0,5)
</script>