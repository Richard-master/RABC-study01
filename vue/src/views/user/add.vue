<template>
  <el-dialog title="新建用户" :visible.sync="formVisible">
    <el-form ref="dataForm" :rules="formRules" :model="form"
             label-position="left" size="small"
             label-width="100px" style="width: 400px; margin-left:50px;">
      <el-form-item label="用户名" prop="username">
        <el-input v-model="form.username"/>
      </el-form-item>
      <el-form-item label="昵称" prop="nickname">
        <el-input v-model="form.nickname"/>
      </el-form-item>
      <el-form-item label="性别" prop="sex">
        <el-radio-group v-model="form.sex">
          <el-radio v-for="item in enums.sex"
                    :key="item.value"
                    :label="item.value">{{ item.label }}</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="部门id" prop="deptId">
        <el-select v-model="form.deptId"
                   style="width:100%;" placeholder="请选择"
                   filterable clearable>
          <el-option v-for="item in options.department"
                     :key="item.key"
                     :label="item.value"
                     :value="item.key">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="角色">
        <el-select v-model="form.roleList"
                   style="width:100%;" placeholder="请选择"
                   filterable clearable multiple>
          <el-option v-for="item in options.role"
                     :key="item.key"
                     :label="item.value"
                     :value="item.key">
          </el-option>
        </el-select>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="formVisible = false">
        取消
      </el-button>
      <el-button type="primary"
                 @click="doCreate()">
        确认
      </el-button>
    </div>
  </el-dialog>
</template>

<script>
import userApi from '@/api/user'
import departmentApi from '@/api/department'
import roleApi from '@/api/role'
import enums from '@/utils/enums'

function initFormBean() {
  const formBean = {
    username: null,
    nickname: null,
    sex: null,
    deptId: null,
    roleList: []
  }
  return formBean
}

export default {
  name: 'UserAdd',
  data() {
    return {
      enums: {
        sex: enums.getSex()
      },
      options: {
        department: [],
        role: []
      },
      form: initFormBean(),
      formVisible: false,
      formRules: {
        username: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
          { max: 32, message: '长度不能超过32个字符', trigger: 'blur' }
        ],
        nickname: [
          { required: true, message: '请输入昵称', trigger: 'blur' },
          { max: 32, message: '长度不能超过32个字符', trigger: 'blur' }
        ],
        sex: [
          { required: true, message: '请输入性别', trigger: 'change' }
        ],
        deptId: [
          { required: true, message: '请输入部门id', trigger: 'change' }
        ]
      }
    }
  },
  methods: {
    /**
     * 重置表单
     */
    resetForm() {
      this.form = initFormBean()
    },
    /**
     * 打开新建表单
     */
    handleCreate() {
      this.resetForm()
      departmentApi.findOptions().then(data => { this.options.department = data })
      roleApi.findOptions().then(data => { this.options.role = data })
      this.formVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    /**
     * 执行新建操作
     */
    doCreate() {
      this.$refs['dataForm'].validate()
        .then(() => userApi.create(this.form))
        .then(data => {
          this.formVisible = false
          this.$common.showMsg('success', '创建成功')
          this.$emit('created', data)
        })
    }
  }
}
</script>
