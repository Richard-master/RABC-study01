import request from '@/utils/request'
const apiPath = '/role'
const roleApi = {
  /**
   * 新增【角色】
   */
  create(data) {
    return request.post(apiPath, data)
  },
  /**
   * 修改【角色】
   */
  update(data) {
    return request.put(apiPath, data)
  },
  /**
   * 分页查询【角色】
   */
  fetchList(query) {
    return request.get(apiPath, { params: query })
  },
  /**
   * 查询【角色】选项列表
   */
  findOptions(query) {
    return request.get(`${apiPath}/options`, { params: query })
  },
  /**
   * 查看【角色】详情
   */
  fetchById(id) {
    return request.get(`${apiPath}/${id}`)
  },
  /**
   * 删除单个【角色】
   */
  deleteById(id) {
    return request.delete(`${apiPath}/${id}`)
  },
  /**
   * 批量删除【角色】
   */
  deleteBatch(ids) {
    return request.delete(apiPath, { data: ids })
  }
}
export default roleApi
