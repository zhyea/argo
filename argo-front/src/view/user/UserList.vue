<template>
  <div class="user-list-container">
    <div class="header">
      <h2>用户管理</h2>
      <div class="header-actions">
        <el-input 
          v-model="searchForm.keyword"
          placeholder="请输入用户名或昵称"
          class="search-input"
          :prefix-icon="Search"
          @keyup.enter="handleSearch"
        />
        <el-button 
          type="primary"
          :icon="Plus"
          @click="handleAdd"
        >
          添加用户
        </el-button>
      </div>
    </div>
    
    <div class="table-container">
      <el-table 
        v-loading="loading"
        :data="userList"
        style="width: 100%"
        border
      >
        <el-table-column prop="id" label="ID" width="80" />
        <el-table-column prop="username" label="用户名" />
        <el-table-column prop="nickName" label="昵称" />
        <el-table-column prop="lastLoginTime" label="上次登录时间">
          <template #default="scope">
            {{ formatDate(scope.row.lastLoginTime) }}
          </template>
        </el-table-column>
        <el-table-column label="操作" width="180" fixed="right">
          <template #default="scope">
            <el-button type="primary" size="small" @click="handleEdit(scope.row)">编辑</el-button>
            <el-button type="danger" size="small" @click="handleDelete(scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    
    <div class="table-footer">
      <el-pagination 
        v-model:current-page="pagination.pageNo"
        v-model:page-size="pagination.pageSize"
        :page-sizes="[10, 20, 50, 100]"
        layout="total, sizes, prev, pager, next, jumper"
        :total="pagination.total"
        @size-change="handleSizeChange"
        @current-change="handlePageChange"
      />
    </div>
    
    <user-edit-drawer 
      ref="userEditDrawerRef"
      @after-edit="loadUserList"
    />
  </div>
</template>

<script lang="ts" setup>
import { ref, onMounted } from 'vue';
import { ElMessage } from 'element-plus';
import { Search, Plus } from '@element-plus/icons-vue';
import UserEditDrawer from './UserEditDrawer.vue';
import { getUserList, deleteUser } from '@/api/user';
import { formatDate } from '@/utils/helper';

const userList = ref<any[]>([]);
const loading = ref(false);
const searchForm = ref({ keyword: '' });
const userEditDrawerRef = ref();

const pagination = ref({
  pageNo: 1,
  pageSize: 10,
  total: 0
});

// 加载用户列表
const loadUserList = async () => {
  loading.value = true;
  try {
    const params = {
      ...pagination.value,
      keyword: searchForm.value.keyword
    };
    const result = await getUserList(params);
    userList.value = result.records || [];
    pagination.value.total = result.total;
  } catch (error) {
    ElMessage.error('获取用户列表失败');
  } finally {
    loading.value = false;
  }
};

// 搜索
const handleSearch = () => {
  pagination.value.pageNo = 1;
  loadUserList();
};

// 添加用户
const handleAdd = () => {
  userEditDrawerRef.value?.open();
};

// 编辑用户
const handleEdit = (row: any) => {
  userEditDrawerRef.value?.open(row);
};

// 删除用户
const handleDelete = async (row: any) => {
  try {
    await ElMessageBox.confirm(
      `确定要删除用户「${row.username}」吗？`,
      '确认删除',
      {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }
    );
    await deleteUser(row.id);
    ElMessage.success('删除成功');
    loadUserList();
  } catch (error) {
    // 用户取消删除
  }
};

// 分页处理
const handleSizeChange = (size: number) => {
  pagination.value.pageSize = size;
  loadUserList();
};

const handlePageChange = (current: number) => {
  pagination.value.pageNo = current;
  loadUserList();
};

// 初始加载
onMounted(() => {
  loadUserList();
});
</script>

<style lang="scss" scoped>
.user-list-container {
  padding: 20px;
  height: 100%;
  display: flex;
  flex-direction: column;
  
  .header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 20px;
    
    h2 {
      margin: 0;
    }
    
    .header-actions {
      display: flex;
      align-items: center;
      gap: 10px;
      
      .search-input {
        width: 280px;
      }
    }
  }
  
  .table-container {
    flex: 1;
    overflow: auto;
    margin-bottom: 20px;
  }
  
  .table-footer {
    display: flex;
    justify-content: flex-end;
  }
}
</style>