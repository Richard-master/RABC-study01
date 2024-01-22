import request from '@/utils/request'
const apiPath = '/department'
const departmentApi = {
  /**
   * 新增【部门】
   */
  create(data) {
    return request.post(apiPath, data)
  },
  /**
   * 修改【部门】
   */
  update(data) {
    return request.put(apiPath, data)
  },
  /**
   * 分页查询【部门】
   */
  fetchList(query) {
    return request.get(apiPath, { params: query })
  },
  /**
   * 查询【部门】选项列表
   */
  findOptions(query) {
    return request.get(`${apiPath}/options`, { params: query })
  },
  /**
   * 查看【部门】详情
   */
  fetchById(id) {
    return request.get(`${apiPath}/${id}`)
  },
  /**
   * 删除单个【部门】
   */
  deleteById(id) {
    return request.delete(`${apiPath}/${id}`)
  },
  /**
   * 批量删除【部门】
   */
  deleteBatch(ids) {
    return request.delete(apiPath, { data: ids })
  }
}
export default departmentApi
