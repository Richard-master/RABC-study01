<template>
  <div class="app-container">
    <div class="filter-container">
      <el-input v-model="query.code" placeholder="角色编码"
                style="width: 200px;" class="filter-item"
                @keyup.enter.native="handleQuery"/>
      <el-input v-model="query.roleName" placeholder="角色名称"
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
      <el-table-column label="角色编码"
                       prop="code"
                       align="center" width="150">
        <template slot-scope="{row}">
          <span>{{ row.code }}</span>
        </template>
      </el-table-column>
      <el-table-column label="角色名称"
                       prop="roleName"
                       align="center" width="200">
        <template slot-scope="{row}">
          <span>{{ row.roleName }}</span>
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
    <role-add ref="roleAdd" @created="doQueryList({ pageNo: 1 })"/>
    <!-- 编辑表单 -->
    <role-edit ref="roleEdit" @updated="doQueryList({})"/>
    <!-- 查看表单 -->
    <role-show ref="roleShow"/>
  </div>
</template>

<script>
import roleAdd from './add'
import roleEdit from './edit'
import roleShow from './show'
import roleApi from '@/api/role'
import Pagination from '@/components/Pagination'

export default {
  name: 'RoleTable',
  components: {
    Pagination,
    roleAdd,
    roleEdit,
    roleShow
  },
  data() {
    return {
      list: [],
      total: 0,
      listLoading: true,
      query: {
        pageNo: 1,
        pageSize: 10,
        code: null,
        roleName: null,
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
      return roleApi.fetchList(this.query)
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
        .then(() => roleApi.deleteById(row.id))
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
        this.$common.showMsg('warning', '请选择角色')
        return
      }
      return this.$common.confirm('是否确认删除')
        .then(() => roleApi.deleteBatch(this.selectItems.map(row => row.id)))
        .then(() => {
          this.$common.showMsg('success', '删除成功')
          return this.doQueryList({ pageNo: 1 })
        })
    },
    /**
     * 打开新建表单
     */
    handleCreate() {
      this.$refs.roleAdd.handleCreate()
    },
    /**
     * 打开查看表单
     */
    handleShow(row) {
      this.$refs.roleShow.handleShow(row.id)
    },
    /**
     * 打开编辑表单
     */
    handleUpdate(row) {
      this.$refs.roleEdit.handleUpdate(row.id)
    }
  }
}
</script>
