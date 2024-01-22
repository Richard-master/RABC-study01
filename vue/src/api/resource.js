import request from '@/utils/request'
const apiPath = '/resource'
const resourceApi = {
  /**
   * 新增【资源】
   */
  create(data) {
    return request.post(apiPath, data)
  },
  /**
   * 修改【资源】
   */
  update(data) {
    return request.put(apiPath, data)
  },
  /**
   * 分页查询【资源】
   */
  fetchList(query) {
    return request.get(apiPath, { params: query })
  },
  /**
   * 查询【资源】选项列表
   */
  findOptions(query) {
    return request.get(`${apiPath}/options`, { params: query })
  },
  /**
   * 查看【资源】详情
   */
  fetchById(id) {
    return request.get(`${apiPath}/${id}`)
  },
  /**
   * 删除单个【资源】
   */
  deleteById(id) {
    return request.delete(`${apiPath}/${id}`)
  },
  /**
   * 批量删除【资源】
   */
  deleteBatch(ids) {
    return request.delete(apiPath, { data: ids })
  }
}
export default resourceApi
