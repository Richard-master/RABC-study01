function getSex() {
  return {
    'MAN': {
      value: 1,
      label: '男'
    },
    'WOMAN': {
      value: 2,
      label: '女'
    }
  }
}

function getResType() {
  return {
    'MENU_GROUP': {
      value: 1,
      label: '菜单组'
    },
    'MENU': {
      value: 2,
      label: '菜单'
    },
    'BUTTON': {
      value: 3,
      label: '按钮'
    },
    'REQUEST': {
      value: 4,
      label: '请求'
    },
    'OTHER': {
      value: 9,
      label: '其他'
    }
  }
}

export default {
  getSex,
  getResType,
  findEnumLabel(value, enums) {
    for (const key in enums) {
      const item = enums[key]
      if (item.value === value) {
        return item.label
      }
    }
    return ''
  }
}
