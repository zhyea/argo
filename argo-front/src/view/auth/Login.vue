<!-- -->
<!-- 登录页面 -->
<template>

	<div class="login-register-container">

		<div class="container right-panel-active" ref="containerRef">

			<div class="container-form container-register">


				<el-form ref="registerFormRef"
				         :model="registerForm"
				         :rules="registerRules"
				         status-icon label-width="0" class="form" action="#" id="registerForm">

					<h2 class="form-title">注册</h2>

					<el-form-item prop="username">
						<el-input v-model="registerForm.username"
						          placeholder="用户名"
						          autocomplete="off"
						          :prefix-icon="User" class="input"/>
					</el-form-item>


					<el-form-item prop="password">
						<el-input v-model="registerForm.password"
						          type="password"
						          autocomplete="off"
						          placeholder="密码"
						          :prefix-icon="Lock" class="input"/>
					</el-form-item>


					<el-form-item prop="confirmPassword">
						<el-input v-model="registerForm.confirmPassword"
						          type="password"
						          autocomplete="off"
						          placeholder="确认密码"
						          :prefix-icon="Lock" class="input"/>
					</el-form-item>

					<el-form-item prop="invitationCode">
						<el-input v-model="registerForm.invitationCode"
						          placeholder="邀请码"
						          autocomplete="off"
						          :prefix-icon="Promotion" class="input"/>
					</el-form-item>

					<el-button size="large" class="btn" @click="submitRegister">提交</el-button>
				</el-form>
			</div>

			<div class="container-form container-login">

				<el-form ref="loginFormRef"
				         :model="loginForm"
				         :rules="loginRules"
				         status-icon label-width="0" class="form" action="#" id="loginForm">

					<h2 class="form-title">登录</h2>

					<el-form-item prop="username">
						<el-input v-model="loginForm.username"
						          placeholder="用户名"
						          autocomplete="off"
						          :prefix-icon="User" class="input"/>
					</el-form-item>


					<el-form-item prop="password">
						<el-input v-model="loginForm.password"
						          autocomplete="off"
						          placeholder="密码"
						          :type="showPwd ? 'text' : 'password'"
						          :prefix-icon="Lock" class="input">
							<template #suffix>
									<span @click="showPwd = !showPwd">
										<el-icon v-if="showPwd"><View/></el-icon>
										<el-icon v-else><Hide/></el-icon>
									</span>
							</template>
						</el-input>
					</el-form-item>

					<el-button size="large" class="btn" @click="submitLogin">提交</el-button>
				</el-form>
			</div>

			<div class="container-overlay">
				<div class="overlay">
					<div class="overlay-panel overlay-left">
						<el-button id="signUp" size="large" round class="btn"
						           @click="switchToRegister">
							注册
						</el-button>
					</div>
					<div class="overlay-panel overlay-right">
						<el-button id="signIn" size="large" round class="btn"
						           @click="switchToLogin">
							登录
						</el-button>
					</div>
				</div>
			</div>
			<!--   end of form container   -->
		</div>
	</div>

</template>


<script lang="ts" setup>
import {ref} from 'vue';
import {useRouter} from 'vue-router';
import {User, Lock, Promotion, View, Hide} from '@element-plus/icons-vue';
import {useAuthStore} from '@/store/auth';
import {ROUTE_NAMES} from "@/config";
import {useAppStore} from "@/store/app.js";
import {getLastVisitedApp} from "@/api/preference";


const loginFormRef = ref();
const registerFormRef = ref();
//密码图标显示标识
const showPwd = ref(false)


const loginForm = ref({
	username: '',
	password: '',
});


const registerForm = ref({
	username: '',
	password: '',
	confirmPassword: '',
	invitationCode: '',
});


const loginRules = {
	username: [
		{required: true, message: '请输入用户名', trigger: 'blur'},
	],
	password: [
		{required: true, message: '请输入密码', trigger: 'blur'},
		{min: 5, max: 16, message: '密码长度在6到16个字符', trigger: 'blur'},
	],
};


const validatePassword = (rule: any, value: string, callback: any) => {
	return (value === registerForm.value.password);
}

const registerRules = {
	username: [
		{required: true, message: '请输入用户名', trigger: 'blur'},
	],
	password: [
		{required: true, message: '请输入密码', trigger: 'blur'},
		{min: 6, max: 16, message: '密码长度在6到16个字符', trigger: 'blur'},
	],
	confirmPassword: [
		{required: true, message: '请再次输入密码', trigger: 'blur'},
		{min: 6, max: 16, message: '密码长度在6到16个字符', trigger: 'blur'},
		{validator: validatePassword, message: '两次输入的密码不一致', trigger: 'blur'},
	],
	invitationCode: [
		{required: true, message: '请输入邀请码', trigger: 'blur'},
		{min: 7, max: 9, message: '邀请码长度在7到9个字符', trigger: 'blur'},
	],
};


const containerRef = ref()


const switchToRegister = () => {
	containerRef.value.classList.remove("right-panel-active");
	loginFormRef.value.resetFields();
}


const switchToLogin = () => {
	containerRef.value.classList.add("right-panel-active");
	registerFormRef.value.resetFields();
}


const router = useRouter();
const authStore = useAuthStore();
const appStore = useAppStore();


const submitRegister = () => {

}

const submitLogin = () => {
	loginFormRef.value.validate((valid: any) => {
		if (!valid) {
			return false;
		}
		const formData = {...loginForm.value,}
		authStore.useLogin(formData).then(async () => {
			console.log('login success')
			await appStore.fetchAllApps();
			const appId = await getLastVisitedApp();
			if (appId) {
				appStore.changeCurrent(appId);
				await router.push({path: '/app/' + appId})
			} else {
				await router.push({name: ROUTE_NAMES.homeRouteName})
			}
		}).catch(err => {
			console.log('login fail，error: ' + err)
		})
	})
}


</script>


<style lang="less" scoped>

.login-register-container {
	--white: #e9e9e9;
	--gray: #333;
	--blue: #0367a6;
	--lightblue: #008997;
	--deepgreen: #2b4b6b;
	--button-radius: 3px;

	height: 360px;
	width: 720px;
	position: relative;
	left: 50%;
	top: 50%;
	transform: translate(-50%, -50%);
}

.container {
	background-color: var(--deepgreen);
	box-shadow: 0 0.9rem 1.7rem rgba(0, 0, 0, 0.25), 0 0.7rem 0.7rem rgba(0, 0, 0, 0.22);
	height: 100%;
	width: 100%;
	overflow: hidden;
	border-radius: 6px;
}


.form-title {
	font-weight: 300;
	margin: 0 0 24px;
}

.link {
	color: var(--gray);
	font-size: 0.9rem;
	margin: 1.5rem 0;
	text-decoration: none;
}


.container-form {
	height: 100%;
	position: absolute;
	top: 0;
	transition: all 0.6s ease-in-out;
}

.container-register {
	left: 0;
	opacity: 1;
	width: 50%;
	z-index: 2;
}

.container-login {
	left: 0;
	opacity: 0;
	width: 50%;
	z-index: 1;
}

.container.right-panel-active .container-register {
	animation: show 1s;
	opacity: 0;
	transform: translateX(100%);
}

.container.right-panel-active .container-login {
	transform: translateX(100%);
	opacity: 1;
	z-index: 5;
}

.container-overlay {
	height: 100%;
	left: 50%;
	overflow: hidden;
	position: absolute;
	top: 0;
	transition: transform 0.6s ease-in-out;
	width: 50%;
	z-index: 100;
}

.container.right-panel-active .container-overlay {
	transform: translateX(-100%);
}

.overlay {
	background: var(--lightblue) url("/argo-navis-3.jpg") no-repeat fixed center;
	background-size: cover;
	height: 100%;
	left: -100%;
	position: relative;
	transform: translateX(0);
	transition: transform 0.6s ease-in-out;
	width: 200%;
}

.container.right-panel-active .overlay {
	transform: translateX(50%);
}

.overlay-panel {
	align-items: center;
	display: flex;
	flex-direction: column;
	height: 100%;
	justify-content: center;
	position: absolute;
	text-align: center;
	top: 0;
	transform: translateX(0);
	transition: transform 0.6s ease-in-out;
	width: 50%;
}

.overlay-left {
	transform: translateX(-20%);
}

.container.right-panel-active .overlay-left {
	transform: translateX(0);
}

.overlay-right {
	right: 0;
	transform: translateX(0);
}

.container.right-panel-active .overlay-right {
	transform: translateX(20%);
}

.btn {
	background-color: var(--blue);
	background-image: linear-gradient(90deg, var(--blue) 0%, var(--lightblue) 74%);
	border-radius: 20px;
	border: 1px solid var(--blue);
	color: var(--white);
	cursor: pointer;
	font-size: 0.8rem;
	font-weight: bold;
	letter-spacing: 0.1rem;
	padding: 0.9rem 4rem;
	text-transform: uppercase;
	transition: transform 80ms ease-in;
}

.form > .btn {
	margin-top: 9px;
}

.btn:active {
	transform: scale(0.95);
}

.btn:focus {
	outline: none;
}

.form {
	background-color: var(--white);
	display: flex;
	align-items: center;
	justify-content: center;
	flex-direction: column;
	padding: 0 27px;
	height: 100%;
	text-align: center;
}

.el-input {
	border-radius: 0;
}

.input {
	background-color: #fff;
	border: none;
	margin: 3px 0;
	width: 100%;
	border-radius: 0;
}

.el-form-item {
	width: 100%;
}

@keyframes show {

	0%,
	49.99% {
		opacity: 0;
		z-index: 1;
	}

	50%,
	100% {
		opacity: 1;
		z-index: 5;
	}
}

</style>