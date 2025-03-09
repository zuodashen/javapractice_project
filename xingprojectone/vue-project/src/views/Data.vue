<template>
  <div>
     <div class="card" style="margin-bottom: 5px">
       <el-input style="width: 240px; margin-right: 10px" v-model="data.name"placeholder="请输入名称查询"prefix-icon="Search"></el-input>
       <el-button type="primary">查询</el-button>
       <el-button type="warning">重置</el-button>
     </div>

     <div class="card" style="margin-bottom: 5px">
        <el-button type="primary">新 增</el-button>
        <el-button type="warning">批量删除</el-button>
        <el-button type="info">导入</el-button>
        <el-button type="success">导出</el-button>
     </div>

      <div class="card" style="margin-bottom: 5px">
        <el-table :data="data.tablebox" stripe>
          <el-table-column label="日期" prop="date"/>
          <el-table-column label="名称" prop="name"/>
          <el-table-column label="地址" prop="address"/>
        </el-table>
        <div style="margin-top: 15px">
          <el-pagination
                  v-model:current-page="data.pageNum"
                  v-model:page-size="data.pageSize"
                  :page-sizes="[5, 10, 15, 20]"
                  background
                  layout="total, sizes, prev, pager, next, jumper"
                  :total="data.total"
          />


        </div>
      </div>


  </div>
</template>


<script setup>
import {reactive} from "vue";
import {Search} from "@element-plus/icons-vue";

const data=reactive({
  name:null,
  tablebox:[
    {id:1,date:'2022-01-01',name:'张三',address:'北京'},
    {id:2,date:'2022-01-02',name:'李四',address:'上海'},
    {id:3,date:'2022-01-02',name:'李四',address:'上海'},
    ],
  pageNum:1,
  pageSize:5,
  total:14
})

</script>


