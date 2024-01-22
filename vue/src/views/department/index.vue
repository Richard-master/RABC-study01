<template>
  <div class="app-container">
    <div class="filter-container">
      <el-input v-model="query.deptName" placeholder="部门名称"
                style="width: 200px;" class="filter-item"
                @keyup.enter.native="handleQuery"/>
      <el-button class="filter-item" icon="el-icon-search" type="primary"
                 @click="handleQuery">
        搜索
      </el-button>
      <el-button class="filter-item" style="margin-left: 10px;" type="success"
                 icon="el-icon-edit" @click="handleCreate">
        新建
      </el-button>
      <el-button class="filter-item" style="margin-left: 10px;" type="danger"
                 icon="el-icon-delete" @click="handleDeleteBatch">
        删除
      </el-button>
    </div>

    <el-table v-loading="listLoading" :data="list"
              @selection-change="selectionChange"
              @sort-change="sortChange"
              border stripe style="width: 100%;">
      <el-table-column type="selection" width="50" />
      <el-table-column label="主键"
                       prop="id"
                       align="center" width="100">
        <template slot-scope="{row}">
          <span>{{ row.id }}</span>
        </template>
      </el-table-column>
      <el-table-column label="部门名称"
                       prop="deptName"
                       align="center">
        <template slot-scope="{row}">
          <span>{{ row.deptName }}</span>
        </template>
      </el-table-column>
      <el-table-column label="创建时间"
                       prop="createdTime"
                       sortable="custom"
                       align="center" width="200">
        <template slot-scope="{row}">
          <span>{{ row.createdTime }}</span>
        </template>
      </el-table-column>
      <el-table-column label="修改时间"
                       prop="operatedTime"
                       sortable="custom"
                       align="center" width="200">
        <template slot-scope="{row}">
          <span>{{ row.operatedTime }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" width="230">
        <template slot-scope="{row}">
          <el-button size="mini"
                     @click="handleShow(row)" class="table-inner-button">查看</el-button>
          <el-button type="primary" size="mini"
                     @click="handleUpdate(row)" class="table-inner-button">编辑</el-button>
          <el-button type="danger" size="mini"
                     @click="handleDeleteSingle(row)" class="table-inner-button">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <pagination v-show="total>0" :total="total" :pageNo.sync="query.pageNo"
                :pageSize.sync="query.pageSize" @pagination="doQueryList"/>
    <!-- 新建表单 -->
    <department-add ref="departmentAdd" @created="doQueryList({ pageNo: 1 })"/>
    <!-- 编辑表单 -->
    <department-edit ref="departmentEdit" @updated="doQueryList({})"/>
    <!-- 查看表单 -->
    <department-show ref="departmentShow"/>
  </div>
</template>

<script>
import departmentAdd from './add'
import departmentEdit from './edit'
import departmentShow from './show'
import departmentApi from '@/api/department'
import Pagination from '@/components/Pagination'

export default {
  name: 'DepartmentTable',
  components: {
    Pagination,
    departmentAdd,
    departmentEdit,
    departmentShow
  },
  data() {
    return {
      list: [],
      total: 0,
      listLoading: true,
      query: {
        pageNo: 1,
        pageSize: 10,
        deptName: null,
        createdTimeSortSign: 0,
        operatedTimeSortSign: 0
      },
      selectItems: []
    }
  },
  created() {
    this.doQueryList({ pageNo: 1 })
  },
  methods: {
    /**
     * 选择框变化
     */
    selectionChange(val) {
      this.selectItems = val
    },
    /**
     * 触发后端排序
     */
    sortChange({ prop, order }) {
      const sortKeyMap = {
        'createdTime': 'createdTimeSortSign',
        'operatedTime': 'operatedTimeSortSign'
      }
      for (var k in sortKeyMap) {
        const sortKey = sortKeyMap[k]
        if (k !== prop) {
          this.query[sortKey] = null
        } else {
          if (order === 'ascending') {
            this.query[sortKey] = 1
          } else {
            this.query[sortKey] = -1
          }
        }
      }
      this.doQueryList({})
    },
    /**
     * 触发搜索操作
     */
    handleQuery() {
      this.doQueryList({ pageNo: 1 })
    },
    /**
     * 执行列表查询
     */
    doQueryList({ pageNo, pageSize }) {
      if (pageNo) {
        this.query.pageNo = pageNo
      }
      if (pageSize) {
        this.query.pageSize = pageSize
      }
      this.listLoading = true
      return departmentApi.fetchList(this.query)
        .then(data => {
          this.list = data.list
          this.total = data.total
        })
        .finally(() => {
          this.listLoading = false
        })
    },
    /**
     * 删除单条记录
     */
    handleDeleteSingle(row) {
      return this.$common.confirm('是否确认删除')
        .then(() => departmentApi.deleteById(row.id))
        .then(() => {
          this.$common.showMsg('success', '删除成功')
          return this.doQueryList({ pageNo: 1 })
        })
    },
    /**
     * 批量删除记录
     */
    handleDeleteBatch() {
      if (this.selectItems.length <= 0) {
        this.$common.showMsg('warning', '请选择部门')
        return
      }
      return this.$common.confirm('是否确认删除')
        .then(() => departmentApi.deleteBatch(this.selectItems.map(row => row.id)))
        .then(() => {
          this.$common.showMsg('success', '删除成功')
          return this.doQueryList({ pageNo: 1 })
        })
    },
    /**
     * 打开新建表单
     */
    handleCreate() {
      this.$refs.departmentAdd.handleCreate()
    },
    /**
     * 打开查看表单
     */
    handleShow(row) {
      this.$refs.departmentShow.handleShow(row.id)
    },
    /**
     * 打开编辑表单
     */
    handleUpdate(row) {
      this.$refs.departmentEdit.handleUpdate(row.id)
    }
  }
}
</script>
