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
        <el-table-column label="账号" prop="username"/>
        <el-table-column label="头像">
          <template #default="scope">
            <img v-if="scope.row.avatar" :src="scope.row.avatar" style="display: block; width: 40px; height: 40px; border-radius: 50%">
          </template>
        </el-table-column>
        <el-table-column label="名称" prop="name"/>
        <el-table-column label="性别" prop="sex"/>
        <el-table-column label="工号" prop="no"/>
        <el-table-column label="年龄" prop="age"/>
        <el-table-column label="个人介绍" prop="descr" show-overflow-tooltip />
        <el-table-column label="部门" prop="departmentname"/>
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

<el-dialog title="员工信息" v-model="data.formVisible" width="500" destroy-on-close>
  <el-form ref="formRef" :rules="data.rules" :model="data.form" style="padding-right: 40px;padding-top: 20px "label-width="80px">
    <el-form-item label="账号" prop="username"><el-input v-model="data.form.username" autocomplete="off" placeholder="请输入账号" :disabled="data.form.id"/></el-form-item>
    <el-form-item label="名称" prop="name"><el-input v-model="data.form.name" autocomplete="off" placeholder="请输入名称"/></el-form-item>
    <el-form-item label="头像">
      <el-upload
          action="http://localhost:9090/files/upload"
          list-type="picture"
          :on-success="handleAvatarSuccess"
      >
        <el-button type="primary">上传头像</el-button>
      </el-upload>
    </el-form-item>
    <el-form-item label="性别">
      <el-radio-group v-model="data.form.sex">
        <el-radio value="男" label="男"></el-radio>
        <el-radio value="女" label="女"></el-radio>
      </el-radio-group>
    </el-form-item>
    <el-form-item label="工号" prop="no" label-width="80px"><el-input v-model="data.form.no" autocomplete="off" placeholder="请输入工号"/></el-form-item>
    <el-form-item label="年龄" label-width="80px"><el-input-number style="width: 160px" min="18" v-model="data.form.age" autocomplete="off" placeholder="请输入年龄"/></el-form-item>
    <el-form-item label="个人介绍" label-width="80px"><el-input rows=3 type="textarea" v-model="data.form.descr" autocomplete="off" placeholder="请输入个人介绍"/></el-form-item>
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
    username:[
      {required:true,message:'请输入账号',trigger:'blur'},
    ],
    name:[
      {required:true,message:'请输入名称',trigger:'blur'},
    ],
    no:[
      {required:true,message:'请输入工号',trigger:'blur'},
    ]
  }
})
const handleAvatarSuccess = (res) => {
  console.log(res.data)
  data.form.avatar = res.data
}

const formRef = ref()

const load =() => {
  request.get('employee/selectPage',{
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
  request.post('employee/add',data.form).then(res =>{      //新增的里面没有id
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
  request.put('employee/update',data.form).then(res =>{   //编辑的对象里面包含id
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
    request.delete('employee/deleteById/' + id).then(res => {
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
    request.delete('employee/deleteBatch', {data: data.ids}).then(res => {
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









