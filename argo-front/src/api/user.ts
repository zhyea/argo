import request from '@/utils/request';

// 用户相关API接口

// 获取用户列表
export async function getUserList(params: any) {
  const res = await request.get('/admin/user/list', { params });
  return res.data;
}

// 获取用户详情
export async function getUserDetail(id: number) {
  const res = await request.get(`/admin/user/detail/${id}`);
  return res.data;
}

// 添加用户
export async function addUser(data: any) {
  const res = await request.post('/admin/user/add', data);
  return res.data;
}

// 更新用户
export async function updateUser(id: number, data: any) {
  const res = await request.put(`/admin/user/update/${id}`, data);
  return res.data;
}

// 删除用户
export async function deleteUser(id: number) {
  const res = await request.delete(`/admin/user/delete/${id}`);
  return res.data;
}

// 修改密码
export async function changePassword(data: any) {
  const res = await request.post('/admin/user/change-password', data);
  return res.data;
}