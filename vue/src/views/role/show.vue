<template>
  <el-dialog title="查看角色" :visible.sync="formVisible">
    <el-form ref="dataForm" :model="form"
             label-position="left" size="small"
             label-width="100px" style="width: 400px; margin-left:50px;">
      <el-form-item label="主键">
        <span class="form-item-show">
          {{ form.id }}
        </span>
      </el-form-item>
      <el-form-item label="角色编码">
        <span class="form-item-show">
          {{ form.code }}
        </span>
      </el-form-item>
      <el-form-item label="角色名称">
        <span class="form-item-show">
          {{ form.roleName }}
        </span>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="formVisible = false">
        取消
      </el-button>
    </div>
  </el-dialog>
</template>

<script>
import roleApi from '@/api/role'

export default {
  name: 'RoleShow',
  data() {
    return {
      form: {
        id: null,
        code: null,
        roleName: null
      },
      formVisible: false
    }
  },
  methods: {
    /**
     * 打开查看表单
     */
    handleShow(id) {
      roleApi.fetchById(id)
        .then(data => {
          this.form = data
          this.formVisible = true
        })
    }
  }
}
</script>
