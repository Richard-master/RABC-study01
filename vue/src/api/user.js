import request from '@/utils/request'
const apiPath = '/user'
const userApi = {
  /**
   * 新增【用户】
   */
  create(data) {
    return request.post(apiPath, data)
  },
  /**
   * 修改【用户】
   */
  update(data) {
    return request.put(apiPath, data)
  },
  /**
   * 分页查询【用户】
   */
  fetchList(query) {
    return request.get(apiPath, { params: query })
  },
  /**
   * 查看【用户】详情
   */
  fetchById(id) {
    return request.get(`${apiPath}/${id}`)
  },
  /**
   * 删除单个【用户】
   */
  deleteById(id) {
    return request.delete(`${apiPath}/${id}`)
  },
  /**
   * 批量删除【用户】
   */
  deleteBatch(ids) {
    return request.delete(apiPath, { data: ids })
  }
}
export default userApi
