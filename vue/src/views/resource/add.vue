<template>
  <el-dialog title="新建资源" :visible.sync="formVisible">
    <el-form ref="dataForm" :rules="formRules" :model="form"
             label-position="left" size="small"
             label-width="100px" style="width: 400px; margin-left:50px;">
      <el-form-item label="资源编码" prop="resCode">
        <el-input v-model="form.resCode"/>
      </el-form-item>
      <el-form-item label="资源名称" prop="resName">
        <el-input v-model="form.resName"/>
      </el-form-item>
      <el-form-item label="资源类型" prop="resType">
        <el-select v-model="form.resType"
                   style="width:100%;" placeholder="请选择"
                   filterable clearable>
          <el-option v-for="item in enums.resType"
                     :key="item.value"
                     :label="item.label"
                     :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="排序号" prop="orderNo">
        <el-input-number v-model="form.orderNo" style="width:100%;" controls-position="right"></el-input-number>
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
import resourceApi from '@/api/resource'
import enums from '@/utils/enums'

function initFormBean() {
  const formBean = {
    resCode: null,
    resName: null,
    resType: null,
    orderNo: null
  }
  return formBean
}

export default {
  name: 'ResourceAdd',
  data() {
    return {
      enums: {
        resType: enums.getResType()
      },
      form: initFormBean(),
      formVisible: false,
      formRules: {
        resCode: [
          { required: true, message: '请输入资源编码', trigger: 'blur' },
          { max: 32, message: '长度不能超过32个字符', trigger: 'blur' }
        ],
        resName: [
          { required: true, message: '请输入资源名称', trigger: 'blur' },
          { max: 32, message: '长度不能超过32个字符', trigger: 'blur' }
        ],
        resType: [
          { required: true, message: '请输入资源类型', trigger: 'change' }
        ],
        orderNo: [
          { required: true, message: '请输入排序号', trigger: 'blur' }
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
        .then(() => resourceApi.create(this.form))
        .then(data => {
          this.formVisible = false
          this.$common.showMsg('success', '创建成功')
          this.$emit('created', data)
        })
    }
  }
}
</script>
