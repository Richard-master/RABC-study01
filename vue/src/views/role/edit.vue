<template>
  <el-dialog title="编辑角色" :visible.sync="formVisible">
    <el-form ref="dataForm" :rules="formRules" :model="form"
             label-position="left" size="small"
             label-width="100px" style="width: 400px; margin-left:50px;">
      <el-form-item label="角色编码" prop="code">
        <el-input v-model="form.code"/>
      </el-form-item>
      <el-form-item label="角色名称" prop="roleName">
        <el-input v-model="form.roleName"/>
      </el-form-item>
      <el-form-item label="资源">
        <el-select v-model="form.resourceList"
                   style="width:100%;" placeholder="请选择"
                   filterable clearable multiple>
          <el-option v-for="item in options.resource"
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
import roleApi from '@/api/role'
import resourceApi from '@/api/resource'

function initFormBean() {
  const formBean = {
    id: null,
    code: null,
    roleName: null,
    resourceList: []
  }
  return formBean
}

export default {
  name: 'RoleEdit',
  data() {
    return {
      options: {
        resource: []
      },
      old: initFormBean(),
      form: initFormBean(),
      formVisible: false,
      formRules: {
        code: [
          { required: true, message: '请输入角色编码', trigger: 'blur' },
          { max: 32, message: '长度不能超过32个字符', trigger: 'blur' }
        ],
        roleName: [
          { required: true, message: '请输入角色名称', trigger: 'blur' },
          { max: 32, message: '长度不能超过32个字符', trigger: 'blur' }
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
      resourceApi.findOptions().then(data => { this.options.resource = data })
      roleApi.fetchById(id)
        .then(data => {
          this.old = data
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
        .then(() => roleApi.update(this.form))
        .then(data => {
          this.formVisible = false
          this.$common.showMsg('success', '修改成功')
          this.$emit('updated', data)
        })
    }
  }
}
</script>
