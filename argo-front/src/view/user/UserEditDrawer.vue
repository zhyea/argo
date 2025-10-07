<template>
  <el-drawer
    v-model="visible"
    title="{{ title }}"
    size="500px"
    :before-close="handleClose"
  >
    <el-form
      ref="formRef"
      :model="form"
      :rules="rules"
      label-width="120px"
      class="user-form"
    >
      <el-form-item label="用户名" prop="username">
        <el-input v-model="form.username" placeholder="请输入用户名" :disabled="!!form.id" />
      </el-form-item>
      
      <el-form-item label="昵称" prop="nickName">
        <el-input v-model="form.nickName" placeholder="请输入昵称" />
      </el-form-item>
      
      <template v-if="!form.id">
        <el-form-item label="密码" prop="password">
          <el-input 
            v-model="form.password" 
            type="password" 
            placeholder="请输入密码" 
            :prefix-icon="Lock"
          />
        </el-form-item>
        
        <el-form-item label="确认密码" prop="rePassword">
          <el-input 
            v-model="form.rePassword" 
            type="password" 
            placeholder="请再次输入密码" 
            :prefix-icon="Lock"
          />
        </el-form-item>
      </template>
    </el-form>
    
    <div class="drawer-footer">
      <el-button @click="handleClose">取消</el-button>
      <el-button type="primary" @click="handleSubmit">确定</el-button>
    </div>
  </el-drawer>
</template>

<script lang="ts" setup>
import { ref, computed, watch } from 'vue';
import { ElMessage, ElMessageBox } from 'element-plus';
import { Lock } from '@element-plus/icons-vue';
import { addUser, updateUser } from '@/api/user';

const props = defineProps<{
  modelValue?: boolean;
}>();

const emit = defineEmits<{
  'update:modelValue': [value: boolean];
  'after-edit': [];
}>();

const visible = computed({
  get: () => props.modelValue || false,
  set: (value) => emit('update:modelValue', value)
});

const formRef = ref();
const form = ref({
  id: 0,
  username: '',
  nickName: '',
  password: '',
  rePassword: ''
});

const title = computed(() => form.value.id ? '编辑用户' : '添加用户');

// 表单验证规则
const rules = ref({
  username: [
    { required: true, message: '请输入用户名', trigger: 'blur' },
    { min: 3, max: 20, message: '用户名长度在 3 到 20 个字符', trigger: 'blur' }
  ],
  nickName: [
    { required: true, message: '请输入昵称', trigger: 'blur' },
    { min: 1, max: 20, message: '昵称长度在 1 到 20 个字符', trigger: 'blur' }
  ],
  password: [
    { required: true, message: '请输入密码', trigger: 'blur' },
    { min: 6, max: 20, message: '密码长度在 6 到 20 个字符', trigger: 'blur' }
  ],
  rePassword: [
    { required: true, message: '请再次输入密码', trigger: 'blur' },
    {
      validator: (rule: any, value: string, callback: any) => {
        if (value !== form.value.password) {
          callback(new Error('两次输入的密码不一致'));
        } else {
          callback();
        }
      },
      trigger: 'blur'
    }
  ]
});

// 打开抽屉
const open = (user?: any) => {
  // 重置表单
  form.value = {
    id: 0,
    username: '',
    nickName: '',
    password: '',
    rePassword: ''
  };
  
  if (user) {
    // 编辑模式，填充数据
    form.value = {
      id: user.id,
      username: user.username,
      nickName: user.nickName,
      password: '',
      rePassword: ''
    };
  }
  
  // 重置表单验证
  if (formRef.value) {
    formRef.value.clearValidate();
  }
  
  visible.value = true;
};

// 关闭抽屉
const handleClose = () => {
  // 如果有表单内容且未提交，询问是否放弃
  if (hasFormContent() && !form.value.id) {
    ElMessageBox.confirm(
      '表单内容尚未保存，确定要关闭吗？',
      '确认关闭',
      {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }
    ).then(() => {
      visible.value = false;
    });
    return;
  }
  visible.value = false;
};

// 判断表单是否有内容
const hasFormContent = () => {
  return form.value.username || form.value.nickName || form.value.password || form.value.rePassword;
};

// 提交表单
const handleSubmit = async () => {
  if (!formRef.value) return;
  
  try {
    await formRef.value.validate();
    
    const submitData = {
      username: form.value.username,
      nickname: form.value.nickName
    };
    
    // 添加模式下需要包含密码
    if (!form.value.id) {
      Object.assign(submitData, {
        password: form.value.password,
        rePassword: form.value.rePassword
      });
    }
    
    if (form.value.id) {
      await updateUser(form.value.id, submitData);
      ElMessage.success('更新用户成功');
    } else {
      await addUser(submitData);
      ElMessage.success('添加用户成功');
    }
    
    visible.value = false;
    emit('after-edit');
  } catch (error) {
    // 验证失败或提交失败
    if (error instanceof Error) {
      ElMessage.error(error.message);
    }
  }
};

// 暴露open方法给父组件
defineExpose({
  open
});

// 监听visible变化，同步到emit
watch(visible, (newVal) => {
  emit('update:modelValue', newVal);
});
</script>

<style lang="scss" scoped>
.user-form {
  margin-bottom: 20px;
}

.drawer-footer {
  display: flex;
  justify-content: flex-end;
  gap: 10px;
  padding-top: 20px;
  border-top: 1px solid #ebeef5;
}
</style>