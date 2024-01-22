<template>
  <el-dialog title="查看用户" :visible.sync="formVisible">
    <el-form ref="dataForm" :model="form"
             label-position="left" size="small"
             label-width="100px" style="width: 400px; margin-left:50px;">
      <el-form-item label="主键">
        <span class="form-item-show">
          {{ form.id }}
        </span>
      </el-form-item>
      <el-form-item label="用户名">
        <span class="form-item-show">
          {{ form.username }}
        </span>
      </el-form-item>
      <el-form-item label="昵称">
        <span class="form-item-show">
          {{ form.nickname }}
        </span>
      </el-form-item>
      <el-form-item label="性别">
        <span class="form-item-show">
          {{ form.sex | findEnumLabel(enums.sex) }}
        </span>
      </el-form-item>
      <el-form-item label="角色">
        <el-table :data="form.roleList" style="width: 100%;"
                  size="mini" border>
          <el-table-column label="角色编码" prop="code">
            <template slot-scope="{row}">
              <span>{{ row.code }}</span>
            </template>
          </el-table-column>
          <el-table-column label="角色名称" prop="roleName">
            <template slot-scope="{row}">
              <span>{{ row.roleName }}</span>
            </template>
          </el-table-column>
        </el-table>
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
import userApi from '@/api/user'
import enums from '@/utils/enums'

export default {
  name: 'UserShow',
  filters: {
    findEnumLabel: enums.findEnumLabel
  },
  data() {
    return {
      enums: {
        sex: enums.getSex()
      },
      form: {
        id: null,
        username: null,
        nickname: null,
        sex: null,
        deptId: null
      },
      formVisible: false
    }
  },
  methods: {
    /**
     * 打开查看表单
     */
    handleShow(id) {
      userApi.fetchById(id)
        .then(data => {
          this.form = data
          this.formVisible = true
        })
    }
  }
}
</script>
