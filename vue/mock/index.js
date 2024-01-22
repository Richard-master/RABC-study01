import Mock from 'mockjs'
import { param2Obj } from '../src/utils'

import _user from './_user'
import user from './user'
import role from './role'
import resource from './resource'
import department from './department'

function initMockData() {
  user.initMockDataStage1()
  role.initMockDataStage1()
  resource.initMockDataStage1()
  department.initMockDataStage1()
  user.initMockDataStage2()
  role.initMockDataStage2()
  resource.initMockDataStage2()
  department.initMockDataStage2()
}

const reqMocks = [
  ..._user,
  ...user.reqMocks,
  ...role.reqMocks,
  ...resource.reqMocks,
  ...department.reqMocks
]

// for front mock
// please use it cautiously, it will redefine XMLHttpRequest,
// which will cause many of your third-party libraries to be invalidated(like progress event).
export function mockXHR() {
  // mock patch
  // https://github.com/nuysoft/Mock/issues/300
  Mock.setup({
    timeout: '100-500'
  })
  Mock.XHR.prototype.proxy_send = Mock.XHR.prototype.send
  Mock.XHR.prototype.send = function() {
    if (this.custom.xhr) {
      this.custom.xhr.withCredentials = this.withCredentials || false

      if (this.responseType) {
        this.custom.xhr.responseType = this.responseType
      }
    }
    this.proxy_send(...arguments)
  }

  function XHR2ExpressReqWrap(respond) {
    return function(options) {
      const { body, type, url } = options
      console.info(`${type} ${url} ${body ? 'body=' + JSON.stringify(body) : ''}`)
      let result = null
      if (respond instanceof Function) {
        // https://expressjs.com/en/4x/api.html#req
        result = respond({
          method: type,
          body: JSON.parse(body),
          query: param2Obj(url),
          url: url
        })
      } else {
        result = respond
      }
      const mockResult = Mock.mock(result)
      console.info(mockResult)
      return mockResult
    }
  }
  // 初始化所有mock数据
  initMockData()
  // 设置异步请求的mock规则
  for (const i of reqMocks) {
    Mock.mock(new RegExp(i.url), i.type || 'get', XHR2ExpressReqWrap(i.response))
  }
}

// for mock server
const responseFake = (url, type, respond) => {
  return {
    url: new RegExp(`/mock${url}`),
    type: type || 'get',
    response(req, res) {
      res.json(Mock.mock(respond instanceof Function ? respond(req, res) : respond))
    }
  }
}

export default reqMocks.map(route => {
  return responseFake(route.url, route.type, route.response)
})
