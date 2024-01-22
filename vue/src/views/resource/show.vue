<template>
  <el-dialog title="查看资源" :visible.sync="formVisible">
    <el-form ref="dataForm" :model="form"
             label-position="left" size="small"
             label-width="100px" style="width: 400px; margin-left:50px;">
      <el-form-item label="主键">
        <span class="form-item-show">
          {{ form.id }}
        </span>
      </el-form-item>
      <el-form-item label="资源编码">
        <span class="form-item-show">
          {{ form.resCode }}
        </span>
      </el-form-item>
      <el-form-item label="资源名称">
        <span class="form-item-show">
          {{ form.resName }}
        </span>
      </el-form-item>
      <el-form-item label="资源类型">
        <span class="form-item-show">
          {{ form.resType | findEnumLabel(enums.resType) }}
        </span>
      </el-form-item>
      <el-form-item label="排序号">
        <span class="form-item-show">
          {{ form.orderNo }}
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
import resourceApi from '@/api/resource'
import enums from '@/utils/enums'

export default {
  name: 'ResourceShow',
  filters: {
    findEnumLabel: enums.findEnumLabel
  },
  data() {
    return {
      enums: {
        resType: enums.getResType()
      },
      form: {
        id: null,
        resCode: null,
        resName: null,
        resType: null,
        orderNo: null
      },
      formVisible: false
    }
  },
  methods: {
    /**
     * 打开查看表单
     */
    handleShow(id) {
      resourceApi.fetchById(id)
        .then(data => {
          this.form = data
          this.formVisible = true
        })
    }
  }
}
</script>
