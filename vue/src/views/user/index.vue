<template>
  <div class="app-container">
    <div class="filter-container">
      <el-input v-model="query.username" placeholder="用户名"
                style="width: 200px;" class="filter-item"
                @keyup.enter.native="handleQuery"/>
      <el-input v-model="query.nickname" placeholder="昵称"
                style="width: 200px;" class="filter-item"
                @keyup.enter.native="handleQuery"/>
      <el-select v-model="query.sex" class="filter-item"
                 style="width:200px;" placeholder="性别"
                 filterable clearable multiple>
        <el-option v-for="item in enums.sex"
                   :key="item.value"
                   :label="item.label"
                   :value="item.value">
        </el-option>
      </el-select>
      <el-select v-model="query.deptId" class="filter-item"
                 style="width:200px;" placeholder="部门id"
                 filterable clearable multiple>
        <el-option v-for="item in options.department"
                   :key="item.key"
                   :label="item.value"
                   :value="item.key">
        </el-option>
      </el-select>
      <el-input v-model="query.roleCode" placeholder="角色编码"
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
      <el-table-column label="用户名"
                       prop="username"
                       align="center" width="150">
        <template slot-scope="{row}">
          <span>{{ row.username }}</span>
        </template>
      </el-table-column>
      <el-table-column label="昵称"
                       prop="nickname"
                       align="center" width="200">
        <template slot-scope="{row}">
          <span>{{ row.nickname }}</span>
        </template>
      </el-table-column>
      <el-table-column label="性别"
                       prop="sex"
                       align="center" width="150">
        <template slot-scope="{row}">
          <span>{{ row.sex | findEnumLabel(enums.sex) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="部门id"
                       prop="deptId"
                       align="center" width="200">
        <template slot-scope="{row}">
          <span>{{ row.deptId }}</span>
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
      <el-table-column label="角色" align="center">
        <template slot-scope="{row}">
          <span v-for="item in row.roleList"
                :key="item.id"
                class="table-inner-mtm">
            {{ item.roleName }}
          </span>
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
    <user-add ref="userAdd" @created="doQueryList({ pageNo: 1 })"/>
    <!-- 编辑表单 -->
    <user-edit ref="userEdit" @updated="doQueryList({})"/>
    <!-- 查看表单 -->
    <user-show ref="userShow"/>
  </div>
</template>

<script>
import userAdd from './add'
import userEdit from './edit'
import userShow from './show'
import userApi from '@/api/user'
import departmentApi from '@/api/department'
import enums from '@/utils/enums'
import Pagination from '@/components/Pagination'

export default {
  name: 'UserTable',
  components: {
    Pagination,
    userAdd,
    userEdit,
    userShow
  },
  filters: {
    findEnumLabel: enums.findEnumLabel
  },
  data() {
    return {
      enums: {
        sex: enums.getSex()
      },
      options: {
        department: []
      },
      list: [],
      total: 0,
      listLoading: true,
      query: {
        pageNo: 1,
        pageSize: 10,
        username: null,
        nickname: null,
        sex: [],
        deptId: [],
        createdTimeSortSign: 0,
        operatedTimeSortSign: 0
      },
      selectItems: []
    }
  },
  created() {
    this.doQueryList({ pageNo: 1 })
    departmentApi.findOptions().then(data => { this.options.department = data })
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
      return userApi.fetchList(this.query)
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
        .then(() => userApi.deleteById(row.id))
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
        this.$common.showMsg('warning', '请选择用户')
        return
      }
      return this.$common.confirm('是否确认删除')
        .then(() => userApi.deleteBatch(this.selectItems.map(row => row.id)))
        .then(() => {
          this.$common.showMsg('success', '删除成功')
          return this.doQueryList({ pageNo: 1 })
        })
    },
    /**
     * 打开新建表单
     */
    handleCreate() {
      this.$refs.userAdd.handleCreate()
    },
    /**
     * 打开查看表单
     */
    handleShow(row) {
      this.$refs.userShow.handleShow(row.id)
    },
    /**
     * 打开编辑表单
     */
    handleUpdate(row) {
      this.$refs.userEdit.handleUpdate(row.id)
    }
  }
}
</script>
