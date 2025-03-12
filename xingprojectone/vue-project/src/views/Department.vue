<template>
  <div>
    <div class="card" style="margin-bottom: 5px">
      <el-input style="width: 240px; margin-right: 10px" v-model="data.name"placeholder="请输入名称查询"prefix-icon="Search"></el-input>
      <el-button type="primary" @click="load">查询</el-button>
      <el-button type="warning" @click="reset">重置</el-button>
    </div>

    <div class="card" style="margin-bottom: 5px">
      <el-button type="primary" @click="handleAdd">新 增</el-button>
      <el-button type="danger" @click="delBatch(data.ids)">批量删除</el-button>
<!--      <el-button type="info">导入</el-button>-->
<!--      <el-button type="success">导出</el-button>-->
    </div>

    <div class="card" style="margin-bottom: 5px">
      <el-table :data="data.tablebox" stripe @selection-change = "handleSelectChange">
        <el-table-column type="selection" width="55"/>
        <el-table-column label="名称" prop="name"/>

        <el-table-column label="操作" width="120px">
          <template #default="scope">
            <el-button @click="handleUpdate(scope.row)" type="primary" :icon="Edit" circle></el-button>
            <el-button @click="del(scope.row.id)" type="danger" :icon="Delete" circle></el-button>
          </template>
          </el-table-column>
      </el-table>
      <div style="margin-top: 15px">
        <el-pagination
            @size-change="load"
            @current-change="load"
            v-model:current-page="data.pageNum"
            v-model:page-size="data.pageSize"
            :page-sizes="[5, 10, 15, 20]"
            background
            layout="total, sizes, prev, pager, next, jumper"
            :total="data.total"
        />
      </div>
    </div>

<el-dialog title="部门信息" v-model="data.formVisible" width="500" destroy-on-close>
  <el-form ref="formRef" :rules="data.rules" :model="data.form" style="padding-right: 40px;padding-top: 20px "label-width="80px">
    <el-form-item label="名称" prop="name"><el-input v-model="data.form.name" autocomplete="off" placeholder="请输入名称" /></el-form-item>
  </el-form>
  <template #footer>
    <div class="dialog-footer">
      <el-button @click="data.formVisible = false">取 消</el-button>
      <el-button type="primary" @click="save">保 存</el-button>
    </div>
  </template>
</el-dialog>
<

  </div>
</template>

<script setup>
import {reactive,ref} from "vue";
import {Delete, Search,Edit} from "@element-plus/icons-vue";
import request from "@/utils/request.js";
import {ElMessage,ElMessageBox} from "element-plus";


const data=reactive({
  name:null,
  tablebox:[],
  pageNum:1,
  pageSize:5,
  total:0,
  formVisible:false,
  form:{},
  ids:[],
  rules:{
    name:[
      {required:true,message:'请输入名称',trigger:'blur'},
    ],

  }
})

const formRef = ref()

const load =() => {
  request.get('department/selectPage',{
    params: {
      pageNum: data.pageNum,
      pageSize: data.pageSize,
      name:data.name
    }
  }).then(res => {
    data.tablebox = res.data.list,
        data.total=res.data.total
  })
}
load()

const reset =() => {
  data.name=null
  load()
}

const handleAdd =() =>{
  data.formVisible=true
  data.form={}
}
const save = () => {  //在一个报错方法里面做2个操作，一个是新增，一个是编辑
  formRef.value.validate((valid) => {
    if (valid) {
      data.form.id ? update() : add()
    }
  })
}

const add =() =>{
  request.post('department/add',data.form).then(res =>{      //新增的里面没有id
    if(res.code === '200'){
      data.formVisible=false
      ElMessage.success('操作成功')
      load()  //新增后一定要重新加载最新的数据
    }else {
      ElMessage.error(res.msg)
    }
  })
}

const update =() =>{
  request.put('department/update',data.form).then(res =>{   //编辑的对象里面包含id
    if(res.code === '200'){
      data.formVisible=false
      ElMessage.success('操作成功')
      load()  //更新后一定要重新加载最新的数据
    }else {
      ElMessage.error(res.msg)
    }
  })
}


const handleUpdate = (row) =>{
    data.form = JSON.parse(JSON.stringify(row)) ////深拷贝一个新的对象，用于编辑，这样就不会影响行对象
    data.formVisible=true
}

const del = (id) => {
  ElMessageBox.confirm('删除数据后无法恢复，您确认删除吗？', '删除确认', {type: 'warning'}).then(() => {
    request.delete('department/deleteById/' + id).then(res => {
      if (res.code === '200') {
        ElMessage.success('操作成功')
        load() //删除数据后一定要重新加载最新数据
      } else {
        ElMessage.error(res.msg)
      }
    })
  }).catch()
}

const handleSelectChange = (rows) => {  //返回所有选中的行对象数组
  //从选中的行数组里面取出所有行的id组成一个新的数组
  data.ids = rows.map(row => row.id)
  console.log(data.ids)
}

const delBatch = () => {
  if (data.ids.length === 0){
    ElMessage.warning('请选择要删除的数据')
    return
  }
  ElMessageBox.confirm('删除数据后无法恢复，您确认删除吗？', '删除确认', {type: 'warning'}).then(() => {
    request.delete('department/deleteBatch', {data: data.ids}).then(res => {
      if (res.code === '200') {
        ElMessage.success('操作成功')
        load() //删除数据后一定要重新加载最新数据
      } else {
        ElMessage.error(res.msg)
      }
    })
  }).catch()
}


</script>









