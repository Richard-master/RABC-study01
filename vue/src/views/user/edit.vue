<template>
  <el-dialog title="编辑用户" :visible.sync="formVisible">
    <el-form ref="dataForm" :rules="formRules" :model="form"
             label-position="left" size="small"
             label-width="100px" style="width: 400px; margin-left:50px;">
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
                 @click="doUpdate()">
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
    id: null,
    nickname: null,
    sex: null,
    deptId: null,
    roleList: []
  }
  return formBean
}

export default {
  name: 'UserEdit',
  data() {
    return {
      enums: {
        sex: enums.getSex()
      },
      options: {
        department: [],
        role: []
      },
      old: initFormBean(),
      form: initFormBean(),
      formVisible: false,
      formRules: {
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
      for (const key in initFormBean()) {
        this.form[key] = this.old[key]
      }
    },
    /**
     * 打开编辑表单
     */
    handleUpdate(id) {
      departmentApi.findOptions().then(data => { this.options.department = data })
      roleApi.findOptions().then(data => { this.options.role = data })
      userApi.fetchById(id)
        .then(data => {
          this.old = Object.assign({}, data, {
            roleList: data.roleList ? data.roleList.map(t => t.id) : []
          })
          this.resetForm()
          this.formVisible = true
          this.$nextTick(() => {
            this.$refs['dataForm'].clearValidate()
          })
        })
    },
    /**
     * 执行修改操作
     */
    doUpdate() {
      this.$refs['dataForm'].validate()
        .then(() => userApi.update(this.form))
        .then(data => {
          this.formVisible = false
          this.$common.showMsg('success', '修改成功')
          this.$emit('updated', data)
        })
    }
  }
}
</script>
